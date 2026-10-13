# Store order
## 식당 및 매장의 테이블에서 주문을 접수하고 내역을 관리하는 백엔드 API 서버

## 기술스택
- Language : Java
- Framework : Spring Boot
- Database : MySQL(Docker container)
- ORM : Spring Data JPA(Hibernate)

## 주요기능
- 메뉴 등록 및 조회
- 메뉴 옵션 등록 및 조회
- 주문 등록
- 주문 전체 조회
- 주문 옵션 관리
    
## 데이터 구조
- `Menu` : 메뉴 정보 관리
- `MenuOption` : 메뉴별 선택 옵션 관리
- `Order` : 주문 정보 관리
- `OrderOption` : 주문에 포함된 옵션 정보 관리

## 엔티티 관계
- `Menu` - `MenuOption` (1:N)
- `Order` - `OrderOption` (1:N)

## 개발 현황
- [x] 메뉴 등록 API
- [x] 메뉴 단건 조회 API
- [x] 메뉴 전체 조회 API
- [x] 메뉴 옵션 등록 API
- [x] 메뉴 옵션 조회 API
- [x] 주문 등록 API
- [x] 주문 전체 조회 API
- [x] 전역 예외 처리
- [ ] 주문 단건 조회 API


## API 명세
| Method | Endpoint                      | 설명           |
|--------|-------------------------------|----------------|
| POST   | `/api/menus`                  | 메뉴 등록      |
| GET    | `/api/menus/{id}`             | 메뉴 단건 조회 |
| GET    | `/api/menus`                  | 메뉴 전체 조회 |
| POST   | `/api/menus/{menuId}/options` | 메뉴 옵션 등록 |
| GET    | `/api/menus/{menuId}/options` | 메뉴 옵션 조회 |
| POST   | `/api/orders`                 | 주문 등록      |
| GET    | `/api/orders`                 | 전체 주문 조회 |


## 주요 설계 특징
### 주문 시점 가격 스냅샷
주문 등록 시 클라이언트가 전달한 메뉴 및 옵션 id를 기준으로 DB에서 원본 정보를 조회, 주문 시점의 이름과 가격을 주문 내역에 저장합니다.
이를 통해 클라이언트가 임의로 전달한 가격을 사용하지 않으며, 이후 메뉴 가격이 변경되더라도 기존 주문 내역을 유지할 수 있도록 했습니다.

### DTO 분리
양방향으로 연결된 `order`와 `orderOption` 엔티티를 직접 응답으로 반환했을 때, JSON 직렬화 과정에서 서로를 반복 참조하는 문제가 발생했습니다.
이를 해결하기 위해 엔티티를 응답 DTO로 반환하고 응답에 필요한 데이터만 구성하도록 변경했습니다.

## 트러블 슈팅
- [양방향 엔티티 관계의 JSON 직렬화 문제를 DTO 변환 방식으로 해결](troubleShooting.md)