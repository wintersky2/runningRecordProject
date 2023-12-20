package org.example.follow;

import org.example.Global;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FollowRepository {
    public List<FollowDTO> findByAll() {
        List<FollowDTO> followList = new ArrayList<>();

        String sql = String.format("SELECT * FROM `follow`");
        List<Map<String, Object>> rows = Global.getDBConnection().selectRows(sql);

        for (Map<String, Object> row : rows) {
            FollowDTO followDTO = new FollowDTO(row);
            followList.add(followDTO);
        }
        return followList;
    }
    public List<FollowDTO> searchUserByName(String searchName){
        List<FollowDTO> searchUserList = new ArrayList<>();
        String sql = String.format("SELECT `name`,`id` FROM `user` WHERE showWhenSearch='Y' AND `name` LIKE '%%%s%%'",searchName);
        List<Map<String,Object>> rows = Global.getDBConnection().selectRows(sql);
        for (Map<String,Object> row : rows){
            FollowDTO followDTO =new FollowDTO(row);
            searchUserList.add(followDTO);
        }
        return (searchUserList);
    }
    public List<FollowDTO> findByWhenShowSearch (){
        List<FollowDTO> followDTOList = new ArrayList<>();
        String sql = String.format("SELECT")
    }
}
