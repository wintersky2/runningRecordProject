package org.example.user;

import org.example.Global;
import org.example.db.DBConnection;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class UserController {
    UserService userService;
    DBConnection dbConnection;

    public UserController() {
        userService = new UserService();
    }

    public void createUser() {
        String userId;
        String password;
        String passwordConfirm;
        System.out.println("== 회원가입 == ");
        while (true) { // 아이디 입력 및 중복검사
            System.out.print("아이디 입력 : ");
            userId = Global.getScanner().nextLine().trim();
            if (this.userService.uerFindByUserId(userId) != null) {
                System.out.println("중복된 아이디 존재");
            } else {
                break;
            }

        }
        while (true) { // 비밀번호 입력 및 비밀번호 재확인
            System.out.print("비밀번호 입력 : ");
            password = Global.getScanner().nextLine().trim();
            System.out.print("비밀번호 재확인 : ");
            passwordConfirm = Global.getScanner().nextLine().trim();
            if (password != (passwordConfirm)) {
                System.out.println("비밀번호 확인,재확인 불일치");
            } else {
                break;
            }
        }
        System.out.print("이름 입력 : ");
        String name = Global.getScanner().nextLine().trim();
        System.out.print("현재 체중 입력(Kg생략) : ");
        double weight = Double.parseDouble(Global.getScanner().nextLine().trim());

        int id = userService.createUser(userId, password, name, weight);
        if (id == -1) {
            System.out.println("회원가입에 실패하셨습니다. 다시 시도하세요.");
            return;
        }
        System.out.println(userId + "님 가입이 완료되었습니다.");
        System.out.println("타인에게 검색노출여부와 기록노출여부는 기본 비공개 상태입니다. '개인설정메뉴에서 수정가능합니다. ");
    }

    public void login() {
        String userId;
        User user;

        System.out.println("== 로그인 ==");
        while (true) {
            System.out.print("ID 입력 : ");
            userId = Global.getScanner().nextLine().trim();
            if (userService.uerFindByUserId(userId) == null) {
                System.out.println("존재하지 않는 ID 입니다.");
            } else {
                user = userService.uerFindByUserId(userId);
                break;
            }
        }
        while (true) {
            System.out.print("비밀번호 입력 : ");
            String password = Global.getScanner().nextLine().trim();
            if (user.getPassword() != password) {
                System.out.println("잘못된 비밀번호입니다.");
            } else {
                Global.setLoginedUser(user);
            }
        }
    }

    public void logout() {
        System.out.println("로그아웃 되었습니다.");
        Global.setLoginedUser(null);
    }

    public void searchUser() {
        System.out.print("회원 이름 검색 : ");
        String searchName = Global.getScanner().nextLine().trim();
        List<User> userList = new ArrayList<>();
        userList = userService.FindByShowWhenSearch("공개", searchName);
        if (userList == null) {
            System.out.println("이름이 유사한 또는 일치하는 회원이 없습니다.");
            return;
        }
        for (User user : userList) {
            System.out.println("회원 이름 : " + user.getName() +
                    "회원 고유ID번호 : " + user.getId() + "(번)");
        }
    }

    public void settingUser() {
        System.out.println("== 개인 설정 ==");
        System.out.println("1.개인정보조회 2.개인정보변경 3.공개상태변경");
        int category = Integer.parseInt(Global.getScanner().nextLine().trim());
        if (category == 1) {
            searchPersonInfo();
        } else if (category == 2) {
            changePersonInfo();
        } else if (category == 3) {
            changePrivateStatus();
        }
    }

    public void searchPersonInfo() {
        System.out.println("== 개인정보조회 ==");
        System.out.println("회원 고유ID번호 : " + Global.getLoginedUser().getId() + "(번)\n" +
                "회원 ID : " + Global.getLoginedUser().getUserId() +
                "회원명 : " + Global.getLoginedUser().getName() + "(님)\n" +
                "회원 체중 : " + Global.getLoginedUser().getWeight() + "(Kg)\n" +
                "타인의 검색노출여부 : " + Global.getLoginedUser().getShowWhenSearch() + "\n" +
                "나의 기록조회권한 : " + Global.getLoginedUser().getShowMyRecord());
    }

    public void changePersonInfo() {
        System.out.println("== 개인정보변경 ==");
        System.out.println("1.회원이름 변경 2.회원체중변경 0.뒤로가기");
        int category = Integer.parseInt(Global.getScanner().nextLine().trim());
        if (category == 1) {
            System.out.println("기존 회원이름 : " + Global.getLoginedUser().getUserId());
            System.out.print("새로운 회원이름 입력 : ");
            String newUserName = Global.getScanner().nextLine().trim();
            Global.getLoginedUser().setUserId(newUserName);
        } else if (category == 2) {
            System.out.println("기존 회원나이 : " + G);
            System.out.print("새로운 나이 입력 : %d", );
            int newUserAge = Integer.parseInt(Global.getScanner().nextLine().trim());
            setUserAge(newUserAge);
        } else if (category == 3) {
            System.out.println("기존 회원체중 : %f", );
            System.out.print("새로운 체중 입력 : %f", );
            int newUserWeight = Integer.parseInt(Global.getScanner().nextLine().trim());
            setUserWeight(newUserWeight);
        } else {
            System.out.println("잘못된 메뉴 선택");

        }
    }

    public void changePrivateStatus() {
        System.out.println("== 공개상태변경 ==");
        System.out.println("1.타인에게 검색노출여부 2.타인에게 기록노출여부");
        int category = Integer.parseInt(Global.getScanner().nextLine().trim());
        if (category == 1) {
            System.out.println("1.공개 2.비공개");
            int showWhenSearch = Integer.parseInt(Global.getScanner().nextLine().trim());
            setShowWhenSearch(showWhenSearch);
        } else if (category == 2) {
            System.out.println("1.공개 2.비공개 3.팔로워만");
            int showMyRecord = Integer.parseInt(Global.getScanner().nextLine().trim());
            setShowMyRecord(showMyRecord);
        }
    }
}
