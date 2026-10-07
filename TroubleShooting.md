# [Troubleshooting] JPA 양방향 연관관계에서 발생한 JSON 순환 참조 문제

## 1. 발생 상황

`Menu`와 `MenuOption`을 1:N 양방향 연관관계로 설정한 후,
`Menu` Entity를 그대로 REST API의 응답으로 반환하게끔 작업.

그 과정에서 JSON 변환 중 다음과 같은 오류가 발생.

```text
Document nesting depth (501) exceeds the maximum allowed
```

## 2. 원인

`Menu`가 `MenuOption`을 가지고 있고, `MenuOption`도 다시 `Menu`를 참조하고 있던 상황.

즉, Entity를 JSON으로 변환하는 과정에서 다음과 같이 서로를 계속 참조.

```text
Menu
 └─ MenuOption
     └─ Menu
         └─ MenuOption
             └─ Menu
                 ...
```

결과적으로 JSON 변환이 끝나지 않고 최대 중첩 깊이를 초과하는 문제를 야기.

## 3. 해결 과정

처음에는 `@JsonIgnore`를 사용해서 한쪽의 연관관계를 JSON 변환 대상에서 제외하는 방법을 고려했으나
Entity는 DB와의 관계를 표현하는 역할을 하고 API 응답은 별도의 형태로 관리하는 것이 좋다고 판단.

그래서 Entity를 API 응답으로 직접 반환하지 않고 DTO를 만들어 반환하도록 수정.

```text
기존

Menu Entity
    ↓
JSON 변환
    ↓
Menu → MenuOption → Menu → MenuOption ...


변경 후

Menu Entity
    ↓
DTO로 변환
    ↓
MenuDto
    ↓
JSON 응답
```

`MenuDto`와 `MenuOptionDto`를 만들고, API 응답에 필요한 데이터만 DTO에 담도록 변경.

또한 Service 계층에서 Entity를 DTO로 변환한 후 Controller에서 반환하도록 수정.

## 4. 결과

Entity를 직접 JSON으로 변환하지 않게 되면서 `Menu → MenuOption → Menu`로 이어지는 순환 참조가 발생하지 않았고, API가 정상적으로 응답.
