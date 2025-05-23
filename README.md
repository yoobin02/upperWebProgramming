# 프로젝트 구조

## ✅ Controller
- 요청을 받아서 화면에 할 일 목록을 보여줍니다.
- 할일 생성, 삭제, 변경, 조회 기능 구현

## ✅ DTO
- 데이터를 주고받는 역할

## ✅ Service
- Controller와 Repository 사이에서 중간 처리 역할을 수행합니다.
- dto를 통해 접근

## ✅ Repository
- DB와의 직접적인 데이터 접근 및 조작을 담당합니다. (CRUD)
- JPA 등의 기술을 통해 DB 쿼리를 수행합니다.
- Entity로 접근