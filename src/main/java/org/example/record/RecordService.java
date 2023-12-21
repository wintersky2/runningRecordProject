package org.example.record;

import java.util.List;

public class RecordService {
    RecordRepository recordRepository;

    public RecordService() {
        recordRepository = new RecordRepository();
    }

    public int createRecord(double runDistance, String runTime, double calorie) {
        return this.recordRepository.createRecord(runDistance, runTime, calorie);
    }
    public List<RecordDTOForShowMyRecord> showMyRecord(){
        return this.recordRepository.showMyRecord();
    }

    public void deleteMyRecord(int DeleteId) {
        this.recordRepository.deleteMyRecord(DeleteId);
    }
}
