<br>

# SpringBoot-Project-BLOG  
- `SpringBoot` `JSP` `JPA` `MySQL` 를 사용하여 웹 게시판 구현

<br>

## :wrench: 사용 기술
```sh
Language : JDK 1.8, JSP 2.3
Framework : SpringBoot 2.7.8
Database : MySQL 5.7
ORM : JPA(Hibernate)
IDE : sts4 4.11.0
```

<br>

## :card_file_box: ERD 설계
![blog](https://user-images.githubusercontent.com/62833757/219288979-921e5243-7b17-4979-adb9-10fbf637f47a.JPG)

<br>

## :pushpin: 구현 기능
- 회원 기능
  * 회원가입 (Spring Security)
  * 카카오 회원가입 (OAuth2)
  * 로그인/로그아웃
  * 회원 정보 수정
- 게시판 기능
  * 게시글 상세보기
  * 게시글 작성 (Summernote)
  * 게시글 수정
  * 게시글 삭제
  * 페이징
- 댓글 기능
  * 댓글 작성
  * 댓글 삭제
  
<br>

## :computer: 실행 화면
<details>
<summary>실행 화면</summary>
<div markdown="1">

## 회원 기능
### 회원가입
![회원가입](https://user-images.githubusercontent.com/62833757/219378337-f2a76490-da23-45e5-84a0-1a40a9203ff5.JPG)
![db](https://user-images.githubusercontent.com/62833757/219378284-b15c0ce5-2f9a-4968-9d33-2ab40466d78f.JPG)
* Spring Security를 적용하여 password는 고정길이의 문자열로 해시화되어 데이터베이스에 저장된다.  

![회원가입 실패](https://user-images.githubusercontent.com/62833757/219378334-5c11e80f-8be8-44af-b1e3-3e0c4a72b177.JPG)
* username에 unique제약조건을 적용해 중복 회원가입을 방지했다.

### 카카오 회원가입
![카카오로그인](https://user-images.githubusercontent.com/62833757/219378331-ef0c0f5d-a443-4789-850b-71e2cfe46a02.JPG)
* OAuth2를 적용해 카카오 로그인을 구현하였다. 

### 로그인
![로그인폼](https://user-images.githubusercontent.com/62833757/219378319-453371a3-a52f-4011-84e9-465c8da6499b.JPG)
* 로그인 시 유저 정보가 principal에 저장되고 Spring Security가 세션을 부여한다.

### 회원 정보 수정
![db](https://user-images.githubusercontent.com/62833757/219378284-b15c0ce5-2f9a-4968-9d33-2ab40466d78f.JPG)
* 데이터베이스의 oauth 값에 따라 카카오 로그인의 회원 수정을 막는다.

![회원정보수정](https://user-images.githubusercontent.com/62833757/219378308-d2ae0a91-b212-4ca0-8bf8-0e6af5f2095e.JPG)
* password와 email를 수정할 수 있다.

![카카오정보수정불가](https://user-images.githubusercontent.com/62833757/219378303-e20c6c5a-1b5d-49b4-8c18-cdd16a055994.JPG)
* 카카오 로그인은 회원 정보 수정이 불가능하다.

## 게시판 기능
### 메인 화면 
![메인화면](https://user-images.githubusercontent.com/62833757/219378322-506100a9-74b9-441b-afff-1a241ff10e24.JPG)
* 페이징 기능을 통해 한 페이지에서 최대 5개의 게시글이 조회된다.
* 비회원은 회원가입 및 로그인을 통해 Spring Security로부터 세션을 부여받아야 글 작성이 가능하다. 
* Spring Security를 적용하여 권한이 없는 사용자가 게시글을 클릭 시 자동으로 로그인 폼으로 이동하게 구현하였다.

### 게시글 상세보기
![본인게시글](https://user-images.githubusercontent.com/62833757/219378328-4fcb3137-bae3-4177-85bd-a090cd13c12b.JPG)
* 작성자 본인만 게시글 수정 및 삭제가 가능하다.

![남게시글](https://user-images.githubusercontent.com/62833757/219378296-52add267-1d74-4920-83f6-2bce5e2ff562.JPG)
* 권한이 없는 사용자는 수정 및 삭제가 불가능하다.

### 게시물 작성
![썸머노트작성폼](https://user-images.githubusercontent.com/62833757/219378316-592bc96c-c503-42d0-bf2b-e3d71acea5df.JPG)
* summernote 웹 에디터를 적용하였다. 다양한 기능과 이미지 업로드를 사용할 수 있다.

### 게시물 수정
![수정](https://user-images.githubusercontent.com/62833757/219387932-2be0f65f-f002-489f-9e25-1e8e5c104a77.JPG)

### 게시물 삭제
![삭제](https://user-images.githubusercontent.com/62833757/219387924-c1240df2-ef86-40c3-b13c-14304a25da78.JPG)

## 댓글 기능
### 댓글 작성
![본인게시글](https://user-images.githubusercontent.com/62833757/219378328-4fcb3137-bae3-4177-85bd-a090cd13c12b.JPG)
* 게시글에 댓글 기능을 구현하였다. 

### 댓글 삭제
![남게시글](https://user-images.githubusercontent.com/62833757/219378296-52add267-1d74-4920-83f6-2bce5e2ff562.JPG)
* 작성자 본인만 댓글 삭제가 가능하다.

</div>

</details>

