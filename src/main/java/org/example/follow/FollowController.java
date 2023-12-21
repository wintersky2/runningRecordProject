package org.example.follow;

import org.example.Global;
import org.example.user.User;
import org.example.user.UserService;

import java.util.ArrayList;
import java.util.List;

public class FollowController {
    FollowService followService;
    UserService userService;

    public FollowController() {
        userService = new UserService();
        followService = new FollowService();
    }

    public void followMenu() {
        while (true) {
            System.out.println("== 팔로우 관련 메뉴 == ");
            System.out.println("1.팔로우 하기 2.언팔로우 \n" +
                    "3.내가 팔로우한 사람 조회 4.나를 팔로우한 사람 조회 0.뒤로가기");
            String category = Global.getScanner().nextLine().trim();
            switch (category) {
                case "1":
                    createFollow();
                    continue;
                case "2":
                    unFollow();
                    continue;
                case "3":
                    showFollowing();
                    continue;
                case "4":
                    showFollower();
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

    public void createFollow() {
        System.out.println("== 팔로우 하기 ==");
        System.out.print("원하는 회원 이름 또는 ID 입력 : ");
        String searchName = Global.getScanner().nextLine().trim();
        System.out.println("-----------------------------------------");

        List<User> userList = searchUserByName(searchName);
        List<Integer> canFollowUserId = new ArrayList<>();
        for (User user : userList) {
            System.out.println("회원이름 : " + user.getName() + "(님)\n" +
                    "회원고유ID : " + user.getId() + "(번)");
            System.out.println("-----------------------------------------");
            canFollowUserId.add(user.getId());
        }
        while (true) {
            System.out.println("팔로우하고 싶은 회원의 고유 ID를 입력하세요. \n ***0을 입력하면 이전메뉴로 갑니다.***");
            try {
                System.out.print("고유 ID 입력 : ");
                int id = Integer.parseInt(Global.getScanner().nextLine().trim());
                if (id == 0) {
                    System.out.println("뒤로가기 선택");
                    System.out.println("-----------------------------------------");
                    return;
                }
                for (int canFollowId : canFollowUserId) {
                    if (canFollowId == id) {
                        this.followService.createFollow(id);
                        System.out.println("팔로우 완료");
                        System.out.println("-----------------------------------------");
                        break;
                    }
                }
                return;
            } catch (NumberFormatException e) {
                System.out.println("잘못된 숫자 입력");
                System.out.println("-----------------------------------------");
            }
        }
    }

    public void unFollow() {
        System.out.println("== 언팔로우 ==");
        List<FollowDTO> followDTOList = this.followService.showFollowing();
        List<Integer> canUnFollowList = new ArrayList<>();
        showFollowing();
        for (FollowDTO followDTO : followDTOList) {
            canUnFollowList.add(followDTO.getFollowId());
        }
        while (true) {
            System.out.println("팔로우하고 싶은 회원의 고유 ID를 입력하세요. \n ***0을 입력하면 이전메뉴로 갑니다.***");
            try {
                System.out.print("고유 ID 입력 : ");
                int deleteId = Integer.parseInt(Global.getScanner().nextLine().trim());
                if (deleteId == 0) {
                    System.out.println("뒤로가기 선택");
                    System.out.println("-----------------------------------------");
                    return;
                }
                for (int canFollowId : canUnFollowList) {
                    if (canFollowId == deleteId) {
                        this.followService.deleteFollow(deleteId);
                        System.out.println("언팔로우 완료");
                        System.out.println("-----------------------------------------");
                        break;
                    }
                }
                return;
            } catch (NumberFormatException e) {
                System.out.println("숫자만 입력 가능합니다.");
                System.out.println("-----------------------------------------");
            }
        }
    }

    public void showFollowing() {
        System.out.println("== 내가 팔로우 한 회원 목록 ==");
        System.out.println("-----------------------------------------");
        List<FollowDTO> followDTOList = this.followService.showFollowing();
        if (followDTOList.size() == 0) {
            System.out.println("팔로우한 회원이 없습니다.");
            System.out.println("-----------------------------------------");
        }
        for (FollowDTO followDTO : followDTOList) {
            System.out.println("회원이름 : " + followDTO.getFollowUserName() + "(님)" + "  회원고유ID : " + followDTO.getFollowId() + "(번)");
            System.out.println("팔로우 한 날짜 : " + followDTO.getCreateDate());
            System.out.println("-----------------------------------------");
        }
    }

    public void showFollower() {
        System.out.println("== 나를 팔로우한 회원 목록 ==");
        System.out.println("-----------------------------------------");
        List<FollowDTO> followDTOList = this.followService.showFollower();
        if (followDTOList.size() == 0) {
            System.out.println("나를 팔로우한 회원이 없습니다.");
            System.out.println("-----------------------------------------");
        }
        for (FollowDTO followDTO : followDTOList) {
            System.out.println("회원이름 : " + followDTO.getFollowUserName() + "(님)" + "  회원고유ID : " + followDTO.getUserId() + "(번)");
            System.out.println("팔로우 한 날짜 : " + followDTO.getCreateDate());
            System.out.println("-----------------------------------------");
        }
    }


    public List<User> searchUserByName(String searchName) {
        return this.followService.searchUserByName(searchName);
    }
}
