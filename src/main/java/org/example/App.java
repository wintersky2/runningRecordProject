package org.example;

import org.example.db.DBConnection;
import org.example.user.UserController;

public class App {
    UserController userController;

    public App() {
        DBConnection.DB_NAME = "runningRecordProject";
        DBConnection.DB_PORT = 3306;
        DBConnection.DB_USER = "root";
        DBConnection.DB_PASSWORD = "";

        Global.getDBConnection().connect();

        userController = new UserController();
    }

    public void run() {
        System.out.println("==== 런닝 기록 앱 실행 ====");
        System.out.println();
        while (true) {
            if (Global.getLoginedUser() == null) {
                System.out.println("*** 메뉴에 해당하는 숫자를 입력하세요. ***");
                System.out.println("1.회원가입 2.로그인 3.회원검색 0.앱종료");
                String command = Global.getScanner().nextLine().trim();
                switch (command) {
                    case "1": //회원가입
                        this.userController.createUser();
                        continue;
                    case "2": //로그인
                        this.userController.login();
                        continue;
                    case "3": //회원검색
                        this.userController.searchUser();
                        continue;
                    case "0": //앱종료
                        break;
                }
            } else {
                System.out.println("*** 메뉴에 해당하는 숫자를 입력하세요. ***");
                System.out.println("1.런닝내용기록메뉴 2.목표관련메뉴 3.팔로우관련메뉴 \n" +
                        "   4.개인정보설정 및 공개여부설정 0.로그아웃");
                String command = Global.getScanner().nextLine().trim();
                switch (command) {
                    case "1":

                    case "2":

                    case "3":

                    case "4": //개인정보설정 및 공개여부설정
                        this.userController.settingUser();
                        continue;
                    case "0":
                        this.userController.logout();
                        break;
                }
            }
        }
    }
}
