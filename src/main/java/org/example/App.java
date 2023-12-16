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
        if (Global.getLoginedUser() == null) {
            _notLogined();
        } else if (Global.getLoginedUser() != null) {

        }

    }

    public void _notLogined() {
        while (true) {
            System.out.println("1.로그인 2.회원가입");
            int category = Integer.parseInt(Global.getScanner().nextLine().trim());
            if (category == 1) {
                userController.login();
            } else if (category == 2) {
                userController.createUser();
            }
        }
    }
    public void //로그인 되어있을떄 할 명령들 함수
}
