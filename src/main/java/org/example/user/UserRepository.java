package org.example.user;

import org.example.Global;
import org.example.db.DBConnection;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserRepository {
    public int createUser(String userId, String password, String name, double weight) {
        String sql = String.format("INSERT INTO `user` SET userId='%s', `password`='%s', name='%s', weight=%f," +
                "showWhenSearch='비공개', showMyRecord='비공개', createDate=NOW(), modifiedDate=NOW()", userId, password, name, weight);
        int id = Global.getDBConnection().insert(sql);

        return id;
    }

    public void updateUserName(String newUserName) {
        String sql = String.format("UPDATE `user` SET name='%s', modifiedDate=NOW() WHERE id=%d", newUserName, Global.getLoginedUser().getId());
        Global.getDBConnection().update(sql);
    }

    public void updateUserWeight(double newUserWeight) {
        String sql = String.format("UPDATE `user` SET weight=%f, modifiedDate=NOW() WHERE id=%d", newUserWeight, Global.getLoginedUser().getId());
        Global.getDBConnection().update(sql);
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

    public List<User> FindByShowWhenSearch(String showWhenSearch, String searchName) {
        List<User> userList = new ArrayList<>();

        String sql = String.format("SELECT * FROM `user` WHERE showWhenSearch='%s' AND `name` LIKE \"%%%s%%\"", showWhenSearch, searchName);
        List<Map<String, Object>> rows = Global.getDBConnection().selectRows(sql);

        for (Map<String, Object> row : rows) {
            User user = new User(row);
            userList.add(user);
        }
        return userList;
    }

    public String updateShowWhenSearch(String showWhenSearchStatus) {
        String status = numToPermStatus(showWhenSearchStatus);
        if(status==null||showWhenSearchStatus.equals("3")){
            return null;
        }else {
            String sql = String.format("UPDATE `user` SET showWhenSearch='%s', modifiedDate=NOW() WHERE id=%d",status,Global.getLoginedUser().getId());
            Global.getDBConnection().update(sql);
            return status;
        }
    }

    public String updateShowMyRecord(String showMyRecordStatus) {
        String status = numToPermStatus(showMyRecordStatus);
        if(status==null){
            return null;
        }else {
            String sql = String.format("UPDATE `user` SET showMyRecord='%s', modifiedDate=NOW() WHERE id=%d",status,Global.getLoginedUser().getId());
            Global.getDBConnection().update(sql);
            return status;
        }
    }
    public String numToPermStatus (String num){
        switch (num){
            case "1":
                return "공개";
            case "2":
                return "비공개";
            case "3":
                return "팔로우만";
            default:
                return null;
        }
    }
}
