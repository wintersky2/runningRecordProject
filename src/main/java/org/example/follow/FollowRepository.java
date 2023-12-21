package org.example.follow;

import org.example.Global;
import org.example.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FollowRepository {
    public void createFollow(int id) {
        String sql = String.format("INSERT INTO `follow` SET `userId`= %d, followId = %d, createDate = NOW(), modifiedDate = NOW()", Global.getLoginedUser().getId(), id);
        Global.getDBConnection().insert(sql);
    }

    public void deleteFollow(int deleteId) {
        String sql = String.format("DELETE FROM `follow` WHERE userId = %s AND followId = %s", Global.getLoginedUser().getId(), deleteId);
        Global.getDBConnection().delete(sql);
    }

    public List<FollowDTO> showFollowing() {
        List<FollowDTO> followList = new ArrayList<>();
        String sql = String.format("SELECT F.*,U.name AS userName,U2.name AS followUserName " +
                "FROM follow AS F JOIN `user` AS U ON F.userId = U.id " +
                "JOIN `user`AS U2 ON F.followId = U2.id WHERE F.userId = %d ORDER BY F.followId;", Global.getLoginedUser().getId());
        List<Map<String, Object>> rows = Global.getDBConnection().selectRows(sql);

        for (Map<String, Object> row : rows) {
            FollowDTO followDTO = new FollowDTO(row);
            followList.add(followDTO);
        }
        return followList;
    }

    public List<FollowDTO> showFollower() {
        List<FollowDTO> followList = new ArrayList<>();
        String sql = String.format("SELECT F.*,U.name AS followUserName,U2.name AS Username " +
                "FROM follow AS F JOIN `user` AS U ON F.userId = U.id " +
                "JOIN `user`AS U2 ON F.followId = U2.id WHERE F.followId = %d", Global.getLoginedUser().getId());
        List<Map<String, Object>> rows = Global.getDBConnection().selectRows(sql);

        for (Map<String, Object> row : rows) {
            FollowDTO followDTO = new FollowDTO(row);
            followList.add(followDTO);
        }
        return followList;
    }

    public List<User> searchUserByName(String searchName) {
        List<User> searchUserList = new ArrayList<>();
        String sql = String.format("SELECT * FROM `user` WHERE showWhenSearch = 'Y' AND (`name` LIKE '%%%s%%' OR `userId` LIKE '%%%s%%')", searchName, searchName);
        List<Map<String, Object>> rows = Global.getDBConnection().selectRows(sql);
        for (Map<String, Object> row : rows) {
            User user = new User(row);
            searchUserList.add(user);
        }
        return searchUserList;
    }
}
