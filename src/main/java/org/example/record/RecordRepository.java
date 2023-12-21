package org.example.record;

import org.example.Global;

public class RecordRepository {
    public void createRecord(double runDistance, String runTime, double calorie) {
        String sql = String.format("INSERT INTO `record` SET `userId`=%d, runDistance=%f," +
                " runTime='%s', calorie=%f, createDate=NOW(), modifiedDate=NOW()", Global.getLoginedUser().getId(), runDistance, runTime, calorie);
        Global.getDBConnection().insert(sql);
    }
}
