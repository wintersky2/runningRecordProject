package org.example.follow;

import org.example.Global;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FollowRepository {
    public List<Follow> findByAll() {
        List<Follow> followList = new ArrayList<>();

        String sql = String.format("SELECT * FROM `follow`");
        List<Map<String, Object>> rows = Global.getDBConnection().selectRows(sql);

        for (Map<String, Object> row : rows) {
            Follow follow = new Follow(row);
            followList.add(follow);
        }
        return followList;
    }
    public List<Follow>
}
