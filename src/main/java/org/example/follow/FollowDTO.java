package org.example.follow;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Getter
@Setter

public class FollowDTO {
    private int id;
    private int userId;
    private int followId;
    private String createDate;
    private String modifiedDate;
    private String name;
    private int idFromUser;

    public FollowDTO(Map<String, Object> row) {
        this.id = (int) row.get("id");
        this.userId = (int) row.get("userId");
        this.followId = (int) row.get("followId");
        this.createDate = row.get("createDate").toString();
        this.modifiedDate = row.get("modifiedDate").toString();
        this.name = (String)row.get("name");
        this.idFromUser = (int)row.get("idFromUser");
    }
}
