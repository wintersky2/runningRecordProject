package org.example;

import org.example.db.DBConnection;
import org.example.follow.FollowController;
import org.example.record.RecordController;
import org.example.user.UserController;

public class App {
    UserController userController;
    FollowController followController;
    RecordController recordController;


    public App() {
        DBConnection.DB_NAME = "runningRecordProject";
        DBConnection.DB_PORT = 3306;
        DBConnection.DB_USER = "root";
        DBConnection.DB_PASSWORD = "";

        Global.getDBConnection().connect();

        userController = new UserController();
        followController = new FollowController();
        recordController = new RecordController();
    }

    public void run() {
        System.out.println("==== 런닝 기록, 조회 게시판 실행 ====");
        System.out.println();
        while (true) {
            if (Global.getLoginedUser() == null) {
                System.out.println("*** 메뉴에 해당하는 숫자를 입력하세요. ***");
                System.out.println("1.회원가입 2.로그인 0.앱종료");
                String command = Global.getScanner().nextLine().trim();
                switch (command) {
                    case "1": //회원가입
                        this.userController.createUser();
                        continue;
                    case "2": //로그인
                        this.userController.login();
                        continue;
                    case "0": //앱종료
                        System.out.println("== 런닝 기록, 조회 게시판을 종료합니다. ==");
                        System.out.println("-----------------------------------------");
                        return;
                }
            } else {
                System.out.println("*** 메뉴에 해당하는 숫자를 입력하세요. ***");
                System.out.println("1.런닝기록관련메뉴 2.회원검색 3.팔로우관련메뉴 \n" +
                        "4.개인정보설정 및 공개여부설정 5.회원탈퇴 0.로그아웃");
                String command = Global.getScanner().nextLine().trim();
                switch (command) {
                    case "1": //런닝기록관련메뉴
                        this.recordController.recordMenu();
                        continue;
                    case "2": //회원검색
                        this.userController.searchUser();
                        continue;
                    case "3": //팔로우관련메뉴
                        this.followController.followMenu();
                        continue;
                    case "4": //개인정보설정 및 공개여부설정
                        this.userController.settingUser();
                        continue;
                    case "5": //회원탈퇴
                        this.userController.deleteUser();
                        continue;
                    case "0":
                        this.userController.logout();
                        break;
                }
            }
        }
    }
}
