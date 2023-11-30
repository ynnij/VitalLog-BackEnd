# Vital Log Backend

## 개발환경

개발기간 : 23.11.14 ~ 

- IDE : Spring Tool Suite 4
- JAVA : jdk 17.0.8
- SpringBoot : 3.1.5
- MySQL 

## 주요기능

1. JWT 로그인

2. 사용자별 운동 로그 추가, 조회, 삭제

3. 게시판 CRUD

4. 추천 운동 조회하기

## ERD 및 REST API 설계

1. ERD


2. REST API


## 개발 과정

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
