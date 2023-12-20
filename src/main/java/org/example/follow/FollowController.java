package org.example.follow;

import org.example.Global;

import java.util.List;

public class FollowController {
    FollowService followService;

    public FollowController() {
        followService = new FollowService();
    }

    public void followMenu() {
        while (Glo) {
            System.out.println("== 팔로우 관련 메뉴 == ");
            System.out.println("1.팔로우 하기 2.팔로우 취소 \n" +
                    "3.나를 팔로우한 사람 조회 4.내가 팔로우한 사람 조회 0.뒤로가기");
            String category = Global.getScanner().nextLine().trim();
            switch (category) {
                case "1":
                    follow();
                    continue;
                case "2":
                    continue;
                case "3":
                    continue;
                case "4":
                    continue;
                case "0":
                    System.out.println("뒤로가기 선택");
                    System.out.println();
                    return;
                default:
                    System.out.println("잘못된 숫자 입력");
                    System.out.println();
            }
        }
    }

    public void follow() {
        System.out.println("== 팔로우 하기 ==");
        System.out.print("회원 이름 입력 : ");
        String searchName = Global.getScanner().nextLine().trim();
        List<FollowDTO> userList = searchUserByName(searchName);
        for (FollowDTO followDTO : userList) {
            System.out.println("회원이름 : " + followDTO.getName() + "(님)\n" +
                    "회원고유ID : " + followDTO.getIdFromUser() + "(번)\n");
        }
        while (true) {
            System.out.println("팔로우하고 싶은 회원의 고유 ID를 입력하세요. \n ***0을 입력하면 이전메뉴로 갑니다.***");
            System.out.print("고유 ID 입력 : ");
            int id = Integer.parseInt(Global.getScanner().nextLine().trim());
            if (id == 0) {
                System.out.println("뒤로가기 선택");
                System.out.println();
                return;
            }
            this.followService.createFollow
        }
    }

    public void unFollow() {

    }

    public List<FollowDTO> searchUserByName(String searchName) {
        return this.followService.searchUserByName(searchName);
    }
}
