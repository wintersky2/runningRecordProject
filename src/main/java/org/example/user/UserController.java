package org.example.user;

import org.example.Global;

import java.util.ArrayList;
import java.util.List;

public class UserController {
    UserService userService;

    public UserController() {
        userService = new UserService();
    }

    public void createUser() {
        double weight;
        String userId;
        String password;

        System.out.println("== 회원가입 == ");
        while (true) { // 아이디 입력 및 중복검사
            System.out.print("아이디 입력 : ");
            userId = Global.getScanner().nextLine().trim();
            if (this.userService.userFindByUserId(userId) != null) {
                System.out.println("중복된 아이디 존재");
                System.out.println("-----------------------------------------");
                return;
            } else {
                break;
            }

        }
        password = passwordSameTest();

        System.out.print("이름 입력 : ");
        String name = Global.getScanner().nextLine().trim();

        while (true) { //체중 입력받고 실수값인지 검증
            try {
                System.out.print("현재 체중 입력(Kg생략) : ");
                weight = Double.parseDouble(Global.getScanner().nextLine().trim());
                break;
            } catch (NumberFormatException e) {
                System.out.println("올바른 숫자를 입력해주세요.");
                System.out.println("-----------------------------------------");
            }
        }
        int id = userService.createUser(userId, password, name, weight);
        if (id == -1) {
            System.out.println("회원가입에 실패하셨습니다. 다시 시도하세요.");
            System.out.println("-----------------------------------------");
            return;
        }
        System.out.println(userId + "님 가입이 완료되었습니다.");
        System.out.println("-----------------------------------------");
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
            System.out.println("-----------------------------------------");
            return;
        }
        System.out.print("비밀번호 입력 : ");
        String password = Global.getScanner().nextLine().trim();
        if (user.getPassword().equals(password) == false) {
            System.out.println("비밀번호가 일치하지 않습니다.");
            System.out.println("-----------------------------------------");
            return;
        }
        Global.setLoginedUser(user);
        System.out.println(Global.getLoginedUser().getUserId() + "으로 로그인하셨습니다.");
        System.out.println("-----------------------------------------");
    }

    public void logout() {
        System.out.println("로그아웃 되었습니다.");
        System.out.println("-----------------------------------------");
        Global.setLoginedUser(null);
    }

    public void searchUser() {
        System.out.print("회원 이름 또는 ID 검색 : ");
        String searchName = Global.getScanner().nextLine().trim();
        List<User> userList = userService.findByShowWhenSearch(searchName);
        if (userList.size() == 0) {
            System.out.println("이름이 유사한 또는 일치하는 회원이 없습니다.");
            System.out.println("-----------------------------------------");
            return;
        }
        for (User user : userList) {
            System.out.println("회원 이름 : " + user.getName() + "(님)\n" +
                    "회원 고유ID번호 : " + user.getId() + "(번)\n");
            System.out.println("-----------------------------------------");
        }
    }

    public void settingUser() {
        while (Global.getLoginedUser() != null) {
            System.out.println("== 개인정보설정 및 공개여부설정 ==");
            System.out.println("1.개인정보조회 2.개인정보변경 3.공개상태변경 0.뒤로가기");
            String category = Global.getScanner().nextLine().trim();
            switch (category) {
                case "1":
                    showLoginedUserInfo();
                    continue;
                case "2":
                    changeLoginedUserInfo();
                    continue;
                case "3":
                    changePrivateStatus();
                    continue;
                case "0":
                    System.out.println("뒤로가기 선택");
                    System.out.println("-----------------------------------------");
                    return;
                default:
                    System.out.println("잘못된 숫자 입력");
                    System.out.println("-----------------------------------------");
            }
        }
    }

    public void showLoginedUserInfo() {
        System.out.println("== 개인정보조회 ==");
        System.out.println("-----------------------------------------");
        System.out.println("회원 고유ID번호 : " + Global.getLoginedUser().getId() + "(번)\n" +
                "회원 ID : " + Global.getLoginedUser().getUserId() + "\n" +
                "회원명 : " + Global.getLoginedUser().getName() + "(님)\n" +
                "회원 체중 : " + Global.getLoginedUser().getWeight() + "(Kg)\n" +
                "타인의 검색노출여부 : " + statusConverter(Global.getLoginedUser().getShowWhenSearch()) + "\n" +
                "나의 기록조회권한 : " + statusConverter(Global.getLoginedUser().getShowMyRecord()) + "\n" +
                "가입일 : " + Global.getLoginedUser().getCreateDate().substring(0, 10) + "\n" +
                "마지막 정보 수정일 : " + Global.getLoginedUser().getModifiedDate() + "\n");
        System.out.println("-----------------------------------------");
    }

    public void changeLoginedUserInfo() {
        while (Global.getLoginedUser() != null) {
            System.out.println("== 개인정보변경 ==");
            System.out.println("1.회원이름변경 2.회원체중변경 3.비밀번호변경 0.뒤로가기");
            String category = Global.getScanner().nextLine().trim();
            switch (category) {
                case "1":
                    System.out.println("-----------------------------------------");
                    System.out.println("== 회원 이름 변경 ==");
                    System.out.println("기존 회원이름 : " + Global.getLoginedUser().getName());
                    System.out.print("새로운 회원이름 입력 : ");
                    String newUserName = Global.getScanner().nextLine().trim();
                    this.userService.updateUserName(newUserName);
                    System.out.println("회원이름 '" + newUserName + "' 으로 변경되었습니다.");
                    System.out.println("-----------------------------------------");

                    reloadUser();
                    continue;
                case "2":
                    System.out.println("-----------------------------------------");
                    System.out.println("== 회원 체중 변경 ==");
                    System.out.println("기존 회원체중 : " + Global.getLoginedUser().getWeight());
                    System.out.print("새로운 체중 입력 : ");
                    double newUserWeight = Double.parseDouble(Global.getScanner().nextLine().trim());
                    this.userService.updateUserWeight(newUserWeight);
                    System.out.println("현재 체중이 '" + newUserWeight + "'(Kg)으로 변경되었습니다.");
                    System.out.println("-----------------------------------------");

                    reloadUser();
                    continue;
                case "3":
                    System.out.println("-----------------------------------------");
                    System.out.println("== 비밀번호 변경 ==");
                    System.out.print("현재 비밀번호 입력 : ");
                    String password = Global.getScanner().nextLine().trim();
                    String confirmPassword = Global.getLoginedUser().getPassword();
                    if (confirmPassword.equals(password) == false) {
                        System.out.println("비밀번호가 일치하지 않습니다.");
                        System.out.println("-----------------------------------------");
                        continue;
                    }
                    String newUserPassword = passwordSameTest();
                    this.userService.updateUserPassword(newUserPassword);
                    System.out.println("비밀번호 변경이 완료되었습니다. 다시 로그인 해주세요.");
                    System.out.println("-----------------------------------------");

                    Global.setLoginedUser(null);
                    continue;
                case "0":
                    System.out.println("뒤로가기 선택");
                    System.out.println("-----------------------------------------");
                    return;
                default:
                    System.out.println("잘못된 숫자 입력");
                    System.out.println("-----------------------------------------");
            }
        }
    }

    public void changePrivateStatus() {
        while (Global.getLoginedUser() != null) {
            System.out.println("== 공개상태변경 ==");
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
                    System.out.println("-----------------------------------------");
                    return;
                default:
                    System.out.println("잘못된 숫자 입력");
                    System.out.println("-----------------------------------------");
            }
        }
    }

    public void updateShowWhenSearch() {
        while (Global.getLoginedUser() != null) {
            System.out.println("== 검색노출여부 선택 ==");
            System.out.println("1.공개 2.비공개");
            String showWhenSearchStatus = Global.getScanner().nextLine().trim();

            switch (showWhenSearchStatus) {
                case "1":
                case "2":
                    System.out.println("-----------------------------------------");
                    String status = this.userService.updateShowWhenSearch(showWhenSearchStatus);
                    System.out.println("검색노출여부가 " + statusConverter(status) + "로 변경되었습니다.");
                    System.out.println("-----------------------------------------");

                    reloadUser();
                    return;
                default:
                    System.out.println("잘못된 숫자 입력");
                    System.out.println("-----------------------------------------");
            }
        }
    }

    public void updateShowMyRecord() {
        while (Global.getLoginedUser() != null) {
            System.out.println("== 기록노출여부 선택 ==");
            System.out.println("1.공개 2.비공개 3.팔로워만");
            String showMyRecordStatus = Global.getScanner().nextLine().trim();

            switch (showMyRecordStatus) {
                case "1":
                case "2":
                case "3":
                    System.out.println("-----------------------------------------");
                    String status = this.userService.updateShowMyRecord(showMyRecordStatus);
                    System.out.println("기록노출여부가 " + statusConverter(status) + "로 변경되었습니다.");
                    System.out.println("-----------------------------------------");

                    reloadUser();
                    return;
                default:
                    System.out.println("잘못된 숫자 입력");
                    System.out.println("-----------------------------------------");
            }
        }
    }

    public void deleteUser() {
        System.out.println("== 회원 탈퇴 ==");
        while (Global.getLoginedUser() != null) {
            System.out.print("현재 비밀번호 입력 (0 입력시 뒤로가기) : ");
            String passwordConfirm = Global.getScanner().nextLine().trim();
            if (passwordConfirm.equals("0")) {
                return;
            }
            if (Global.getLoginedUser().getPassword().equals(passwordConfirm)) {
                System.out.println("정말로 탈퇴하시겠습니까?\n 동의 = '예', 비동의는 아무거나 입력");
                String confirm = Global.getScanner().nextLine().trim();
                if (confirm.equals("예")) {
                    System.out.println("회원 탈퇴가 완료되었습니다.");
                    this.userService.deleteUser();
                    Global.setLoginedUser(null);
                } else {
                    System.out.println("회원탈퇴 취소");
                    System.out.println("-----------------------------------------");
                    return;
                }
            }
        }
    }

    public void reloadUser() {
        User user = userService.userFindById(Global.getLoginedUser().getId());
        Global.setLoginedUser(user);
    }

    public String statusConverter(String status) {
        switch (status) {
            case "Y":
                return "공개";
            case "N":
                return "비공개";
            case "F":
                return "팔로워만";
            default:
                return null;
        }
    }

    public String passwordSameTest() {
        while (true) {
            System.out.print("비밀번호 입력 : ");
            String password = Global.getScanner().nextLine().trim();
            System.out.print("비밀번호 재확인 : ");
            String passwordConfirm = Global.getScanner().nextLine().trim();
            if (password.equals(passwordConfirm)) {
                return password;
            }
            System.out.println("비밀번호 확인,재확인 불일치");
            System.out.println("-----------------------------------------");
        }
    }
}