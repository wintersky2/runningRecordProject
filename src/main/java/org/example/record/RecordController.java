package org.example.record;

import org.example.Global;

public class RecordController {
    RecordService recordService;

    public RecordController() {
        recordService = new RecordService();
    }

    public void recordMenu() {
        while (true) {
            System.out.println("== 런닝기록관련메뉴 ==");
            System.out.println("1.기록하기 2.기록수정 3.기록 삭제\n"+
                    "4.나의 기록 조회 5.팔로우한 사람의 기록 조회 0.뒤로가기");
            String category = Global.getScanner().nextLine().trim();
            switch (category){
                case "1":
                    createRecord();
                    continue;
                case "2":
                    updateRecord();
                    continue;
                case "3":
                    deleteRecord();
                    continue;
                case "4":
                    showMyRecord();
                    continue;
                case "5":
                    showFollowingUserRecord();
                    continue;
                case "0":
                    System.out.println("뒤로가기 선택");
                    System.out.println();
                default:
                    System.out.println("잘못된 숫자 입력");
                    System.out.println();
            }
        }
    }

    public void createRecord() {

        this.recordService.createRecord(runDistance, runTime, calorie);
    }
}
