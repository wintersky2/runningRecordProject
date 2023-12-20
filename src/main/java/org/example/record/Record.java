package org.example.record;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Record {
    private int id;
    private int userId;
    private String startTime;
    private String endTime;
    private double runDistance;
    private String runTime;
    private double calorie;
    private String createDate;
    private String modifiedTime;
}
