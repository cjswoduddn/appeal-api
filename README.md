# APPEAL PROJECT BACKEND API

## 프로젝트 환경 정보(최신화 2021-02-04)
- Group : com.appeal
- Artifact : api
- Gradle
- JAVA 11
- lombok
- Spring Web
- Spring Security
- Spring Data JPA
- H2 Database(dev)
- MySQL(real)
- Validation
- Java Mail Sender
- implementation("com.github.gavlyukovskiy:p6spy-spring-boot-starter:1.6.3") -> 쿼리 확인 배포 시 삭제예정

## 프로젝트 버젼
- v 1.0 멤버
	- 멤버 로그인, 회원가입 구현
	- 이메일 인증 서비스 구현

- v 1.1 GlobalExceptionHandler
	- 컨트롤러에서 발생하는 예외처리
	- 시큐리티 로그아웃

- v 1.2 BaseTimInfo
	- @EnableJpaAuditing
	- @MappedSuperclass
	- @EntityListeners(AuditingentityListener.class)
	- 등록일, 수정일 자동 업데이트 기능
