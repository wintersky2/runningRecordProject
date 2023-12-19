package org.example.user;

import org.example.Global;
import org.example.db.DBConnection;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserRepository {
    public int createUser(String userId, String password, String name, double weight) {
        String sql = String.format("INSERT INTO `user` SET userId=`%s`, password=`%s`, name=`%s`, weight=`%f`," +
                "showWhenSearch=`비공개`, showMyRecord=`비공개`", userId, password, name, weight);
        int id = Global.getDBConnection().insert(sql);

        return id;
    }

    public List<User> findByAll() {
        List<User> userList = new ArrayList<>();

        String sql = String.format("SELECT * FROM `user`");
        List<Map<String, Object>> rows = Global.getDBConnection().selectRows(sql);

        for (Map<String, Object> row : rows) {
            User user = new User(row);
            userList.add(user);
        }
        return userList;
    }

    public User userFindById(int id) {
        List<User> userList = new ArrayList<>();

        userList = this.findByAll();
        for (User user : userList) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public User userFindByUserId(String userId) {
        List<User> userList = new ArrayList<>();

        userList = this.findByAll();
        for (User user : userList) {
            if (user.getUserId().equals(userId)) {
                return user;
            }
        }
        return null;
    }
    public List<User> FindByShowWhenSearch(String showWhenSearch,String searchName){
        List<User> userList = new ArrayList<>();

        String sql = String.format("SELECT * FROM `user` WHERE showWhenSearch=`%s` AND `name` LIKE \"%%%s%%\"",showWhenSearch, searchName);
        List<Map<String, Object>> rows = Global.getDBConnection().selectRows(sql);

        for (Map<String, Object> row : rows) {
            User user = new User(row);
            userList.add(user);
        }
        return userList;
    }
}
