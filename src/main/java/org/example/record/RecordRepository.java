package org.example.record;

import org.example.Global;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RecordRepository {
    public int createRecord(double runDistance, String runTime, double calorie) {
        String sql = String.format("INSERT INTO `record` SET `userId` = %d, runDistance = %f," +
                " runTime = '%s', calorie = ROUND(%f,1), createDate = NOW(), modifiedDate = NOW()", Global.getLoginedUser().getId(), runDistance, runTime, calorie);
        return Global.getDBConnection().insert(sql);
    }

    public List<RecordDTOForShowMyRecord> showMyRecord() {
        List<RecordDTOForShowMyRecord> recordList = new ArrayList<>();
        String sql = String.format("SELECT R.*,U.name FROM record AS R JOIN `user` AS U ON R.userId = U.id WHERE R.userId = %d", Global.getLoginedUser().getId());
        List<Map<String, Object>> rows = Global.getDBConnection().selectRows(sql);
        for (Map<String, Object> row : rows) {
            RecordDTOForShowMyRecord recordDTOForShowMyRecord = new RecordDTOForShowMyRecord(row);
            recordList.add(recordDTOForShowMyRecord);
        }
        return recordList;
    }

    public List<RecordDTOForShowMyRecord> showMyRecordById(int id) {
        List<RecordDTOForShowMyRecord> recordList = new ArrayList<>();
        String sql = String.format("SELECT R.*,U.name FROM record AS R JOIN `user` AS U ON R.userId = U.id WHERE R.userId = %d", id);
        List<Map<String, Object>> rows = Global.getDBConnection().selectRows(sql);
        for (Map<String, Object> row : rows) {
            RecordDTOForShowMyRecord recordDTOForShowMyRecord = new RecordDTOForShowMyRecord(row);
            recordList.add(recordDTOForShowMyRecord);
        }
        return recordList;
    }


    public void deleteMyRecord(int DeleteId) {
        String sql = String.format("DELETE FROM record WHERE id = %d AND userId = %d", DeleteId, Global.getLoginedUser().getId());
        Global.getDBConnection().delete(sql);
    }
}
