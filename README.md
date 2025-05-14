# 프로젝트 구조

## ✅ Controller (컨트롤러)
- `/todos` 요청을 받아서 화면에 할 일 목록을 보여줍니다.
- `localhost:8080` 으로 접속하든, `localhost:8080/todos` 로 접속하든 `localhost:8080/todos` 로 접속되도록 리다이렉트 설정이 되어 있습니다.

## ✅ Service (서비스)
- Controller와 Repository 사이에서 중간 처리 역할을 수행합니다.

## ✅ Repository (리포지토리)
- DB와의 직접적인 데이터 접근 및 조작을 담당합니다. (CRUD)
- JPA 등의 기술을 통해 DB 쿼리를 수행합니다.
- 할 일 목록을 생성일 순으로 조회합니다.

## ✅ Model (모델)
- 할 일(Todo)의 제목, 설명, 마감일 등의 데이터를 정의합니다.
