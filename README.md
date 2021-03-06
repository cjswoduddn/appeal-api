# APPEAL PROJECT BACKEND API
![logo](./images/logo.PNG)
- SERVICE URL : <http://www.appeal.icu/>
- 테스트 이메일 : ma8511@naver.com
- 테스트 비밀번호 : 123412
- 서비스 소개 : 서비스 템플릿으로 개인 포트폴리오를 작성할 수 있습니다 ^^

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
![er_diagram](./images/er_diagram4.PNG)


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

2.2(2021-02-25)
- MemberController ControllerAdvice연계 테스트 작성
- PortfolioDto 관련 생성 테스트 작성

3.0(2021-03-01)
- Portfolio - Template 설계 분리
- Template dto, service, reposritoy, controller 테스트 완료

3.1(2021-03-02)
- TemplateTwo에 대한 read구현(fetch join 적용)
- Portfolio에 Member를 set할 때 세션 값을 가져오는 역할 변경

3.2(2021-03-23)
- Template delete구현

4.0(2021-04-13)
- ErrorResponse 및 ErrorCode 설정으로 body에 모든 에러정보 포함
- SuccessResponse 및 SuccessCode 설정으로 body에 모든 응답정보 포함
todo: TemplateService설계 문제 해결하고 Response 추가 적용 예정

5.0(2021-04-28)
- spring cloud를 활용해서 마이크로서비스로 이전 작업 중
- github URL : [여기](https://github.com/cjswoduddn/appeal-msa)

5.1(2021-05-24)
- MemberService signUp()에서 redis와 hibernate 트랜잭션 동기화

5.2(2021-05-27)
- appeal-redis 라이브러리 모듈 추가 및 RedisService 분리
- code key에 대한 expire 12시간으로 명시

6.0(2021-05-27)
- batch service 추가
    - 매일 새벽 3시 인증되지 않고 만든 지 12시간이 지난 계정은 테이블에서 삭제
- spring boot 버전 업 2.4.2 -> 2.4.5
    - spring data redis 버전 호환성 이슈 해결(자세한 원인 파악 중)
    


