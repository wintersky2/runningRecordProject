package org.example.record;

import org.example.Global;
import org.example.follow.FollowDTO;
import org.example.follow.FollowService;

import java.util.ArrayList;
import java.util.List;

public class RecordController {
    RecordService recordService;
    FollowService followService;

    public RecordController() {
        recordService = new RecordService();
        followService = new FollowService();
    }

    public void recordMenu() {
        while (true) {
            System.out.println("== 런닝기록관련메뉴 ==");
            System.out.println("1.기록하기 2.기록 삭제 3.나의 기록 조회 \n" +
                    "4.팔로우한 사람의 기록 조회 0.뒤로가기");
            String category = Global.getScanner().nextLine().trim();
            switch (category) {
                case "1":
                    createRecord();
                    continue;
                case "2":
                    deleteMyRecord();
                    continue;
                case "3":
                    showMyRecord();
                    continue;
                case "4":
                    showFollowingUserRecord();
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

    public void createRecord() {
        String hour = "";
        String min = "";
        String sec = "";
        String runTime = "";
        double runDistance = 0;
        double met = 0;
        double calorie;
        System.out.println("-----------------------------------------");
        System.out.println("== 기록하기 ==");
        System.out.print("달린 거리 입력 (KM) : ");
        try {
            runDistance = Double.parseDouble(Global.getScanner().nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("잘못된 거리 입력");
            System.out.println("-----------------------------------------");
        }
        System.out.println("달린 시간 입력(시간,분,초) x입력시 기록취소");
        try {
            while (true) {
                System.out.print("(시간) : ");
                hour = Global.getScanner().nextLine().trim();
                if (hour.equals("0")) {
                    hour = "00";
                } else if (hour.equals("x")) {
                    System.out.println("기록 취소");
                    System.out.println("-----------------------------------------");
                    return;
                }
                System.out.print("(분) : ");
                min = Global.getScanner().nextLine().trim();
                if (min.equals("0")) {
                    min = "00";
                } else if (min.equals("x")) {
                    System.out.println("기록 취소");
                    System.out.println("-----------------------------------------");
                    return;
                }
                System.out.print("(초) : ");
                sec = Global.getScanner().nextLine().trim();
                if (sec.equals("0")) {
                    sec = "00";
                } else if (sec.equals("x")) {
                    System.out.println("기록 취소");
                    System.out.println("-----------------------------------------");
                    return;
                }
                runTime = String.format(hour + ":" + min + ":" + sec);
                break;
            }
        } catch (NumberFormatException e) {
            System.out.println("잘못된 시간 입력");
            System.out.println("-----------------------------------------");
        }
        System.out.print("운동강도 입력 [ 2(가벼운걷기) ~ 7(등산 및 축구) ]  0입력시 기록취소 : ");
        try {
            while (true) {
                String input = Global.getScanner().nextLine().trim();
                if (input.equals("0")) {
                    System.out.println("기록취소 선택");
                    System.out.println("-----------------------------------------");
                    return;
                }
                met = Double.parseDouble(input);
                if (met >= 2 && met <= 7) {
                    System.out.println("2~7 사이만 입력하세요.");
                    System.out.println("-----------------------------------------");
                    break;
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("잘못된 운동강도 입력");
            System.out.println("-----------------------------------------");
        }
        calorie = ((Integer.parseInt(hour) * 60 + Integer.parseInt(min) + Integer.parseInt(sec) / (double) 60) *
                3.5 * met * 0.001 * Global.getLoginedUser().getWeight() * 5);
        System.out.printf("예상된 소모한 칼로리 : %.1f(kcal)\n", calorie);
        int id = this.recordService.createRecord(runDistance, runTime, calorie);
        if (id == -1) {
            System.out.println("달리기 기록 실패. 다시 시도하세요.");
            System.out.println("-----------------------------------------");
            return;
        }
        System.out.println(id + "(번) 달리기 기록이 저장되었습니다.");
        System.out.println("-----------------------------------------");
    }

    private void deleteMyRecord() {
        System.out.println("== 기록 삭제 ==");
        this.showMyRecord();
        while (true) {
            List<RecordDTOForShowMyRecord> recordList = this.recordService.showMyRecord();
            List<Integer> canDeleteIdList = new ArrayList<>();
            for (RecordDTOForShowMyRecord recordDTOForShowMyRecord : recordList) {
                canDeleteIdList.add(recordDTOForShowMyRecord.getId());
            }

            System.out.print("삭제하고 싶은 기록고유ID 입력 (0 입력시 삭제 취소) : ");
            try {
                int DeleteId = Integer.parseInt(Global.getScanner().nextLine().trim());
                for (int canDeleteId : canDeleteIdList) {
                    if (DeleteId == 0) {
                        System.out.println("삭제 취소");
                        System.out.println("-----------------------------------------");
                        return;
                    }
                    if (DeleteId == canDeleteId) {
                        this.recordService.deleteMyRecord(DeleteId);
                        System.out.println(DeleteId + "(번) 기록 삭제완료.");
                        System.out.println("-----------------------------------------");
                        return;
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("잘못된 숫자 입력");
                System.out.println("-----------------------------------------");
            }
            System.out.println("잘못된 숫자 입력");
            System.out.println("-----------------------------------------");
        }
    }

    private void showMyRecord() {
        System.out.println("== 나의 기록 조회 == ");
        List<RecordDTOForShowMyRecord> recordList = this.recordService.showMyRecord();
        for (RecordDTOForShowMyRecord recordDTOForShowMyRecord : recordList) {
            System.out.println("기록 고유ID : " + recordDTOForShowMyRecord.getId() + "(번)\n" +
                    "작성자 : " + recordDTOForShowMyRecord.getName() + "(님)\n" +
                    "달린 거리 : " + recordDTOForShowMyRecord.getRunDistance() + "(KM)\n" +
                    "소모한 칼로리 : " + recordDTOForShowMyRecord.getCalorie() + "(kcal)\n" +
                    "작성일 : " + recordDTOForShowMyRecord.getCreateDate());
            System.out.println("-----------------------------------------");
        }
    }

    private void showFollowingUserRecord() {
        System.out.println("== 팔로우한 사람의 기록 조회 ==");
        System.out.println();
        System.out.println("--------------팔로우 목록--------------");
        List<FollowDTO> followDTOList = this.followService.showFollower();
        List<Integer> canShowUserList = new ArrayList<>();
        for (FollowDTO followDTO : followDTOList) {
            System.out.println("회원이름 : " + followDTO.getFollowUserName() + "(님)" + "  회원고유ID : " + followDTO.getUserId() + "(번)");
            System.out.println("--------------------------------------");
            canShowUserList.add(followDTO.getUserId());
        }
        System.out.print("조회 하고 싶은 회원의 ID번호입력 (0입력시 뒤로가기) : ");
        while (true) {
            try {
                int id = Integer.parseInt(Global.getScanner().nextLine().trim());
                if (id == 0) {
                    System.out.println("뒤로가기 선택");
                    System.out.println("--------------------------------------");
                    return;
                }
                for (int conFirmId : canShowUserList) {
                    if (conFirmId == id) {
                        break;
                    }
                    System.out.println("잘못된 숫자 입력");
                    System.out.println("--------------------------------------");
                }
                List<RecordDTOForShowMyRecord> recordList = this.recordService.showFollowingUserRecord(id);
                for (RecordDTOForShowMyRecord recordDTOForShowMyRecord : recordList) {
                    System.out.println("작성자 : " + findByRecordUserId(recordDTOForShowMyRecord.getUserId()) + "(님)\n" +
                            "달린거리 : " + recordDTOForShowMyRecord.getRunDistance() + "(Km)\n" +
                            "달린시간 : " + recordDTOForShowMyRecord.getRunTime() + "\n" +
                            "소모한 칼로리 : " + recordDTOForShowMyRecord.getCalorie() + "(kcal)");
                    System.out.println("---------------------------------------------");
                }
            } catch (NumberFormatException e) {
                System.out.println("잘못된 숫자 입력");
                System.out.println("-----------------------------------------");
            }

        }
    }

    public String findByRecordUserId(int id) {
        return this.recordService.findByRecordUserId(id);
    }
}
