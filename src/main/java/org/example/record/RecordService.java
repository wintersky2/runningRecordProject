package org.example.record;

public class RecordService {
    RecordRepository recordRepository;

    public RecordService() {
        recordRepository = new RecordRepository();
    }

    public void createRecord(double runDistance, String runTime, double calorie) {
        this.recordRepository.createRecord(runDistance, runTime, calorie);
    }
}
