## 런닝 기록, 조회 게시판

### 서비스 설명

- 회원가입기능으로 ID, 비밀번호, 이름, 현재 체중을 입력받아 계정을 생성한다.
- 회원 정보 변경으로 비밀번호, 이름, 현재 체중을 변경할 수 있고, 검색노출이나 기록공개 여부를 변경할 수 있다.
- 런닝기록 기능으로 달린 시간, 거리, 운동 강도를 입력받고, 받은값들로 소모한 칼로리를 계산하여 자동 저장한다.
- 런닝기록 조회 또는 삭제할 수 있다.
- 팔로우 기능으로 다른 유저를 팔로우할 수있다.
- 팔로우한 유저의 런닝 기록을 기록공개가 되어있다면 조회할 수 있다.
<br />

<br/>

## 🛠 개발환경

- 운영체제 - WINDOWS 10
- 에디터 - IntelliJ
- 언어 - Java
- 데이터베이스 - MySQL
- 버전관리 - Github
<br/>

<br/>

## ☁️ ERD

![사진명](https://i.imgur.com/1yXzjlU.png)
<br>

<br>

## 👀 시연영상
[![이미지 텍스트](스크린샷 이미지)](유투브링크)

[![Video Label](http://img.youtube.com/vi/'유튜브주소의id'/0.jpg)](https://youtu.be/'유튜브주소의id')

## 🔥 트러블 슈팅

### 🚨 Issue 1
### 🚧 객체 업데이트

A. 회원 정보를 수정하고 나서 조회하면 수정 이전의 정보가 보인다.

```JAVA
```
<br>
<br>
문제점 설명
<br>
## 🛑 원인
- 현재 로그인되어있는 User타입의 객체인 loginedUser가 회원정보를 수정했을때
  즉, User데이터베이스에 있는 값을 가지고 있지않고 이전값을 가지고 있기 때문.
  <br>
  <br>

## 🚥 해결
- 회원 정보를 수정하는 매서드를 실행하고 나서 loginedUser를 DB로부터 업데이트해주는 과정을 넣어주었다.

### 🚨 Issue 2
### 🚧 팔로우 목록불러오기

A. 팔로우 목록을 조회하면 NULL오류가 발생함.
<br>
<br>
문제점 설명
<br>
## 🛑 원인
- 팔로우DTO 타입이 DB에서 불러온 row의 컬럼과 맞지않아서 빈공간이 생김.
  <br>
  <br>

## 🚥 해결
- DB에 SQL 문을 적절히 사용해서 팔로우DTO타입과 맞춰주었다.



언팔로우에 팔로우만 출력됨

회원 검색에 ID도 포함

영상제출 화요일까지 
