
# 개인지출관리 프로젝트

### Project
    
    Gradle Project

### Language

    Java 17

### SpringBoot 
    
    3.2.2

### Project Metadata :

    group : com.spring

    artifact : board

### Dependencies :

     Spring Web : 스프링 웹 MVC

     Spring Security

     Spring Boot Mail

     log4j2

     Lombok

     thymeleaf: 템플릿 엔진

     MYSQL Driver


### 기능정리 :

    로그인
    - 사용자가 로그인하면, 어플리케이션은 사용자 정보를 데이터베이스에서 가져온다.
 
    수입/지출 기록
    - 사용자가 카테고리에 맞는 수입/지출 내역을 추가하면, 해당 내역이 데이터베이스에 저장된다.

    카테고리 설정
    - 사용자가 카테고리를 추가 또는 수정하면, 해당 변경 사항이 데이터베이스에 반영된다.

    지출 통계 조회
    - 사용자가 통계를 조회하면, 어플리케이션은 데이터베이스에서 필요한 정보를 가져와서 캘린더 상 화면에 표시한다.
