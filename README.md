# Vital Log Backend

> 사용자별 운동을 기록할 수 있고, 나이 ▪ 성별 등에 따라 운동을 추천 받을 수 있는 웹 사이트입니다.

- [프로젝트 시연 영상](https://youtu.be/on6aPi652lw?si=2TCU0CRRZPPYE1cL)

## 개발환경

개발기간 : 23.11.14 ~ 23.12.12

|FE|BE|
|:---:|:---:|
|박민호|김혜진|
|[FE 저장소](https://github.com/777Mino777/VitallogFrontEnd)|[BE 저장소](https://github.com/ynnij/VitalLog-BackEnd)|

- IDE : Spring Tool Suite 4
- JAVA : jdk 17.0.8
- SpringBoot : 3.1.5
- SpringSecurity 6.1.5
- MySQL 8.0.34

## 화면

### 메인페이지

<p align ="center">
    <img src="https://github.com/ynnij/VitalLog-BackEnd/assets/87576974/3c21aa12-c6d8-40e5-bbcc-258be318041a">
</p>

### 추천운동페이지

<p align ="center">
    <img src="https://github.com/ynnij/VitalLog-BackEnd/assets/87576974/445792e0-1e0c-459b-afd7-f2e01a42c378">
</p>

### 마이페이지

<p align ="center">
    <img src="https://github.com/ynnij/VitalLog-BackEnd/assets/87576974/4be56f1c-273d-4ae2-9480-7ccd8cdd55ca">
</p>

#### 커뮤니티

<p align ="center">
    <img src="https://github.com/ynnij/VitalLog-BackEnd/assets/87576974/bcbcffd1-9110-40c8-8ebd-b478093c9a79">
</p>

## 주요기능

1. JWT 로그인

2. 사용자별 운동 로그 추가, 조회, 삭제

3. 게시판 CRUD

4. 추천 운동 조회하기

## ERD 및 REST API 설계

### 1. ERD
   
<p align ="center">
    <img src="https://github.com/ynnij/VitalLog-BackEnd/assets/87576974/ab2c62f0-b4bb-4fd9-a0d6-b0ba9a121b2f">
</p>

### 2. REST API

로그인, 회원가입, 메인페이지, 추천운동

<p align ="center">
    <img src="https://github.com/ynnij/VitalLog-BackEnd/assets/87576974/ba1b2c3a-90f6-4b3e-bad2-827b37372762">
</p>

마이페이지

<p align ="center">
    <img src="https://github.com/ynnij/VitalLog-BackEnd/assets/87576974/9694efa2-8217-4410-ae50-06ed1992800c">
</p>

커뮤니티

<p align ="center">
    <img src="https://github.com/ynnij/VitalLog-BackEnd/assets/87576974/b7524cf9-80e3-4e8f-b826-43ab560bdbf5">
</p>

## 개발 과정

|BE 저장소|마이페이지|recoil 및 디자인|
|:---:|:---:|:---:|
|[<img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white">](https://github.com/ynnij/VitalLog-BackEnd)|[<img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white">](https://github.com/ynnij/VitalLog-FrontEnd)|[<img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white">](https://github.com/ynnij/VitallogFrontEnd)|

**23.11.14(화) ~ 23.11.15(수)** 
- 주제 선정 및 화면 설계

**23.11.16(목)** 
- REST API 작성
- 공공데이터 csv 다운 및 전처리 후 및데이터베이스 생성

**23.11.17(금)** 
- 추천 운동 정보 구현(information)
- JWT 로그인 구현

**23.11.20(월)** 
- 회원가입 구현
- 로그인 관련 오류 수정
- userlog 관련 기능 구현 중 → 로그 조회를 위한 logsView 생성

**23.11.21(화)** 
- userlog 관련 기능(로그 추가, 삭제, 조회) 구현 완료
- 메인 페이지 데이터 조회 구현
- 게시판 관련 테이블(board, reply) 생성

**23.11.22(수)**
- 전체 로그 조회 및 기간별 로그 조회 관련 return 형태 변경 및 트랜잭션 추가
    - 날짜별 시간 및 에너지 소모량 total 계산 후 Map 형태로 반환
- 커뮤니티 게시글 관련 기능 구현(조회, 추가, 수정, 삭제)

**23.11.23(목)**
- 커뮤니티 댓글 기능 구현(조회, 추가, 수정, 삭제)
- board, reply 테이블에 update_date 필드 추가
    - 게시글 또는 댓글 수정 시 수정된 날짜 같이 기록

**23.11.24(금)**
- 유저의 기록이 없는 경우 발생하는 메인페이지 데이터 조회 오류 수정  

**23.11.27(월)**
- [FE] 마이페이지 사이드바 생성 및 관련 페이지 연결

**23.11.28(화)**
- [BE] 댓글 추가 삭제 시 댓글 리스트를 반환하도록 수정
- [FE] 오늘의 로그 기록하기 기능 및 페이지 구현

**23.11.29(수)**
- [BE] cors 오류 수정(CorsConfigurationSource로 구현)
- [FE] 전체 로그보기 구현(그래프 그리기)

**23.11.30(목)**
- [FE] 기간별 로그보기 및 오늘의 로그보기 구현, 로그 삭제 기능 구현

**23.12.04(월)**
- [FE] recoil을 사용한 로그인 상태관리 및 네비게이션 수정

**23.12.05(화)**
- [FE] 추천 운동 페이지 크기 수정 및 마이페이지 오류 수정, 유저 메인 페이지 디자인 수정 

**23.12.06(수)**
- [BE] ip로 접속 시 발생하는 cors 에러 수정
- [FE] 새로고침 시 LoginStateAtom 값 변경되는 오류 수정

**23.12.11(월)**
- [BE] 회원가입 시 아이디 중복 검사 추가
