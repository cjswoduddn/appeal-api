# APPEAL PROJECT BACKEND API
![logo](./images/logo.PNG)


## 프로젝트 아키텍처
![architecture](./images/architecturev2.PNG)


## 배포 방식
![deploy](./images/deployv1.PNG)


## 도움받은 기술
![helper](./images/helperv2.PNG)

- spring web
- spring data-jpa
- spring mail
- spring validation
- p6spy(개발환경에서 쿼리와 파라미터 요청 콘솔로 확인)


## 테이블 설계
![er_diagram](./images/er_diagramv2.PNG)


## 개발환경
- java 11
- spring boot 2.4.2


## 프로젝트 버전
1.0
- OCP를 지킨 PORTFOLIO 설계
- 회원 CRUD, application수준 세션 인증
- 다중 이미지 처리

1.1
- 인증 세션 HttpSession -> Redis로 변경

2.0(2021-02-23)
- 멀티모듈 프로젝트(단일 서버, 3개의 라이브러리 모듈)
- 메일인증을 위한 redis를 MailService에서 MemberService로 옮김

2.1(2021-02-24)
- 템플릿 이름 양식 변경(임의로 지정한 이름->TemplateTwo)
- TemplateTwo 동적으로 필드 추가 가능한 버전으로 변경


## todo 리스트

### 빠른 처리
- 포트폴리오 템플릿 최대 5개까지 추가하기
- portfolio service계층 단위테스트하기
- @WebMvcTest 모든 컨트롤러에 적용해서 테스트하기

### 학습 필요
- 인증세션 어플리케이션에서 redis로 분리하기(in-memory, new server)
- MSA 구성을 위한 도메인 별 모듈화 구상하기
- CI/CD를 위해 travisCI 적용하기
- 배포를 위해 AWS EC2 확보하기


### 읽어야할 책 목록
- 토비의 스프링 v2
- realMySql
- 그외 ddd 및 tdd 책 한 권 씩은 읽자

