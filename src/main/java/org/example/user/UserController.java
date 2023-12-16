package org.example.user;

import org.example.Global;

public class UserController {
    public void createUser() {
        System.out.println("== 회원가입 == ");
        System.out.print("이름 입력 : ");
        String name = Global.getScanner().nextLine().trim();
        System.out.print("비밀번호 입력 : ");
        String password = Global.getScanner().nextLine().trim();
        System.out.print("나이 입력 : ");
        int age = Integer.parseInt(Global.getScanner().nextLine().trim());
        System.out.print("현재 체중 입력(Kg생략) : ");
        double weight = Double.parseDouble(Global.getScanner().nextLine().trim());
        String createDate = Global.getNowDateTime().toString().trim();
        System.out.println("타인에게 검색노출여부와 기록노출여부는 기본 비공개 상태입니다. '개인설정' 명령어로 수정가능합니다. ");
        setShowWhenSearch(2);
        setShowMyRecord(2);
        //공개여부 기본값을 setter로 한다.
    }

    public void login() {
        System.out.println("== 로그인 ==");
        System.out.print("이름 입력 : ");
        String name = Global.getScanner().nextLine().trim();
        //회원 이름 검증
        System.out.println("비밀번호 입력 : ");
        String password = Global.getScanner().nextLine().trim();
        //회원 이름 검증후에 해당 row에 password와 비교.
        //만약 맞으면 loginedUser에 해당 유저 row객체넣기

    }

    public void settingUser() {
        System.out.println("== 개인 설정 ==");
        System.out.println("1.개인정보조회 2.개인정보변경 3.공개상태변경");
        int category = Integer.parseInt(Global.getScanner().nextLine().trim());
        if (category == 1) {
            _searchPersonInfo();
        } else if (category == 2) {
            _changePersonInfo();
        } else if (category == 3) {
            _changePrivateStatus();
        }
    }

    public void _searchPersonInfo() {
        System.out.println("== 개인정보조회 ==");
        System.out.println("회원 고유ID : %d(번)\n회원명 : %s(님)\n회원 나이 : %s(세)\n" +
                "회원 체중 : %f(Kg)\n타인의 검색노출여부 : %s\n나의 기록조회권한 : %s", );
    }

    public void _changePersonInfo() {
        System.out.println("== 개인정보 변경 ==");
        System.out.println("1.회원명 변경 2.회원나이변경 3.회원체중변경");
        int category = Integer.parseInt(Global.getScanner().nextLine().trim());
        if (category == 1) {
            System.out.println("기존 회원명 : %s", );
            System.out.print("새로운 회원명 입력 : ");
            String newUserName = Global.getScanner().nextLine().trim();
            setUserName(newUserName);
        } else if (category == 2) {
            System.out.println("기존 회원나이 : %d", );
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

    public void _changePrivateStatus() {
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
