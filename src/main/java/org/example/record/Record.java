package org.example.record;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@AllArgsConstructor
@Getter
@Setter
public class Record {
    private int id;
    private int userId;
    private double runDistance;
    private String runTime;
    private double calorie;
    private String createDate;
    private String modifiedDate;

    public Record(Map<String, Object> row) {
        this.id = (int) row.get("id");
        this.userId = (int) row.get("userId");
        this.runDistance = (double) row.get("runDistance");
        this.runTime = row.get("runTime").toString();
        this.calorie = (double) row.get("calorie");
        this.createDate = row.get("createDate").toString();
        this.modifiedDate = row.get("modifiedDate").toString();
    }
}
