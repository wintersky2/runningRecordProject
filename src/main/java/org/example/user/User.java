package org.example.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@AllArgsConstructor
@Getter
@Setter
public class User {
    private int id;
    private String userId;
    private String password;
    private String name;
    private double weight;
    private String showWhenSearch;
    private String showMyRecord;
    private String createDate;
    private String modifiedDate;

    User(Map<String, Object> row) {
        this.id = (int) row.get("id");
        this.userId = (String) row.get("userId;");
        this.password = (String) row.get("password;");
        this.name = (String) row.get("name;");
        this.weight = (double) row.get("weight;");
        this.showWhenSearch = (String) row.get("showWhenSearch;");
        this.showMyRecord = (String) row.get("showMyRecord;");
        this.createDate = row.get("createDate;").toString();
        this.modifiedDate = row.get("modifiedDate;").toString();
    }
}
