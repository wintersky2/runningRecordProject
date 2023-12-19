package org.example;

import org.example.db.DBConnection;
import org.example.user.UserController;

public class App {
    UserController userController;

    public App() {
        DBConnection.DB_NAME = "runningRecordProj";
        DBConnection.DB_PORT = 3306;
        DBConnection.DB_USER = "root";
        DBConnection.DB_PASSWORD = "";

        Global.getDBConnection().connect();

        userController = new UserController();
    }

    public void run() {
        System.out.println("==== 런닝 기록 앱 실행 ====");
        System.out.println();
        if (Global.getLoginedUser() == null) {
            System.out.println("*** 메뉴에 해당하는 숫자를 입력하세요. ***");
            System.out.println("1.회원가입 2.로그인 3.회원검색");
            String command = Global.getScanner().nextLine().trim();
            switch (command) {
                case "1": //회원가입
                    userController.createUser();
                case "2": //로그인
                    userController.login();
                case "3": //회원검색
                    userController.searchUser();
            }
        }else {
            System.out.println("*** 메뉴에 해당하는 숫자를 입력하세요. ***");
            System.out.println("1.런닝내용기록메뉴 2.목표관련메뉴 2.팔로우관련메뉴");
            String command = Global.getScanner().nextLine().trim();
            switch (command){
                case
            }
        }

    }
}
