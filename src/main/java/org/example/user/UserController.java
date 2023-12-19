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
            if (this.userService.userFindByUserId(userId) != null) {
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
            if (password.equals(passwordConfirm)) {
                break;
            }
            System.out.println("비밀번호 확인,재확인 불일치");
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
        System.out.print("ID 입력 : ");
        userId = Global.getScanner().nextLine().trim();
        user = this.userService.userFindByUserId(userId);
        if (user == null) {
            System.out.println("존재하지 않는 ID 입니다.");
            return;
        }
        System.out.print("비밀번호 입력 : ");
        String password = Global.getScanner().nextLine().trim();
        if (user.getPassword().equals(password)== false) {
            System.out.println("비밀번호가 일치하지 않습니다.");
            return;
        }
        Global.setLoginedUser(user);
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
        System.out.println("== 개인정보설정 및 공개여부설정 ==");
        while (true) {
            System.out.println("1.개인정보조회 2.개인정보변경 3.공개상태변경 0.뒤로가기");
            String category = Global.getScanner().nextLine().trim();
            switch (category) {
                case "1":
                    showLoginedUserInfo();
                    continue;
                case "2":
                    changePersonInfo();
                    continue;
                case "3":
                    changePrivateStatus();
                    continue;
                case "0":
                    System.out.println("뒤로가기 선택");
                    return;
                default:
                    System.out.println("잘못된 숫자 입력");
            }
        }
    }

    public void showLoginedUserInfo() {
        System.out.println("== 개인정보조회 ==");
        System.out.println("회원 고유ID번호 : " + Global.getLoginedUser().getId() + "(번)\n" +
                "회원 ID : " + Global.getLoginedUser().getUserId() + "\n" +
                "회원명 : " + Global.getLoginedUser().getName() + "(님)\n" +
                "회원 체중 : " + Global.getLoginedUser().getWeight() + "(Kg)\n" +
                "타인의 검색노출여부 : " + Global.getLoginedUser().getShowWhenSearch() + "\n" +
                "나의 기록조회권한 : " + Global.getLoginedUser().getShowMyRecord() + "\n");
    }

    public void changePersonInfo() {
        System.out.println("== 개인정보변경 ==");
        while (true) {
            System.out.println("1.회원이름변경 2.회원체중변경 0.뒤로가기");
            String category = Global.getScanner().nextLine().trim();
            switch (category) {
                case "1":
                    System.out.println("기존 회원이름 : " + Global.getLoginedUser().getName());
                    System.out.print("새로운 회원이름 입력 : ");
                    String newUserName = Global.getScanner().nextLine().trim();
                    this.userService.updateUserName(newUserName);
                    continue;
                case "2":
                    System.out.println("기존 회원체중 : " + Global.getLoginedUser().getWeight());
                    System.out.print("새로운 체중 입력 : ");
                    double newUserWeight = Double.parseDouble(Global.getScanner().nextLine().trim());
                    this.userService.updateUserWeight(newUserWeight);
                    continue;
                case "0":
                    System.out.println("뒤로가기 선택");
                    return;
                default:
                    System.out.println("잘못된 숫자 입력");
            }
        }
    }

    public void changePrivateStatus() {
        System.out.println("== 공개상태변경 ==");
        while (true) {
            System.out.println("1.타인에게 검색노출여부 2.타인에게 기록노출여부 0.뒤로가기");
            String category = Global.getScanner().nextLine().trim();
            switch (category) {
                case "1":
                    updateShowWhenSearch();
                    continue;
                case "2":
                    updateShowMyRecord();
                    continue;
                case "0":
                    System.out.println("뒤로가기 선택");
                    return;
                default:
                    System.out.println("잘못된 숫자 입력");
            }
        }
    }

    public void updateShowWhenSearch() {
        while (true) {
            System.out.println("1.공개 2.비공개");
            String showWhenSearchStatus =Global.getScanner().nextLine().trim();

            switch (showWhenSearchStatus){
                case "1","2":
                    String status = this.userService.updateShowWhenSearch(showWhenSearchStatus);
                    System.out.println("검색노출여부가 " + status + "로 변경되었습니다.");
                    break;
                default:
                    System.out.println("잘못된 숫자 입력");
            }
        }
    }

    public void updateShowMyRecord() {
        while (true) {
            System.out.println("1.공개 2.비공개 3.팔로워만");
            String showMyRecordStatus = Global.getScanner().nextLine().trim();

            switch (showMyRecordStatus){
                case "1","2","3":
                String status = this.userService.updateShowMyRecord(showMyRecordStatus);
                    System.out.println("기록노출여부가 " + status + "로 변경되었습니다.");
                    break;
                default:
                    System.out.println("잘못된 숫자 입력");
            }
        }
    }
}