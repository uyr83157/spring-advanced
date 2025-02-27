# SPRING ADVANCED

### 레벨 1-1: Early Return
- **기존**
비밀번호 암호화 -> `userRole` 검증 -> 이메일 검증
=> 무조건 비밀번호 암호화 진행

- **리팩터링**
이메일 검증 - > 비밀번호 암호화 -> `userRole` 검증
=> 상황에 따라 비밀번호 암호화 진행

- 좀 더 나은 리팩터링을 위해선 `userRole` 검증도 앞으로 땡겨와도 될 거 같다고 판단됨.

---

### 레벨 1-2: 불필요한 if-else 피하기

- **기존**
if문이 2중으로 중첩된 상황
=> 알고리즘 구조상 중첩할 필요 없음.

- **리팩터링**
if문 - if문 으로 중첩 해제

- 좀 더 나은 리팩터링을 위해선 `userRole` 검증도 앞으로 땡겨와도 될 거 같다고 판단됨.

---

### 레벨 1-3: Validation

- **기존**
서비스 로직에서 if문을 활용해 리퀘스트 데이터를 검증

- **개선**
Validation를 이용해 리퀘스트 DTO 단위에서 리퀘스트 데이터를 검증하도록 개선
=> ` @Pattern`과 정규표현식을 활용

---

### 레벨 2. N+1 문제


- **기존**
N+1 문제를 방지하기 위해, `JOIN FETCH`를 이용하여 즉시 로딩 중

- **변경**
`@EntityGraph(attributePaths = {"users"})` 를 이용하여, `JOIN FETCH` 없이 즉시 로딩

---

### 레벨 3. 테스트 코드 연습 - 1
- **기존**
실제 메서드는 `matches(String rawPassword, String encodedPassword)` 으로, `rawPassword`, `encodedPassword` 순서로 인자를 받고 있지만,
테스트 코드에서는 반대 순서로 인자를 넣음

- **변경**
`boolean matches = passwordEncoder.matches(rawPassword, encodedPassword);` 원래 순서로 변경

---

### 레벨 3. 테스트 코드 연습 - 2.1
- **기존**
`managerService.getManagers` 메서드가 `InvalidRequestException` 예외를 던지면서 `"Todo not found"` 메세지를 반환
=> 하지만 테스트 코드에선 `"Manager not found"` 메세지로 검증한 상태.

- **변경**
`assertEquals("Todo not found", exception.getMessage());` 으로 변경

---

### 레벨 3. 테스트 코드 연습 - 2.2

- **기존**
`saveComment` 메서드에서`InvalidRequestException` 예외를 던지고 있는 상태.
=> 하지만 테스트 코드에선 `ServerException` 예외로 검증한 상태.

- **변경**
`InvalidRequestException exception = assertThrows(InvalidRequestException.class, () -> {
            commentService.saveComment(authUser, todoId, request);` 으로 변경
            
          
---

### 레벨 3. 테스트 코드 연습 - 2.3

- **기존**
`getUser` 값이 `null` 로 반환 되는데, `getUser().getId())` 여기서 `null` 에 대한 처리가 없어서 NPE 발생

- **변경**
` if(todo.getUser()==null || !ObjectUtils.nullSafeEquals(user.getId(), todo.getUser().getId()))` 으로 변경해서 `null` 처리

- **주의**
`todo.getUser()==null` 를 앞에 배치해야, 먼저 널 처리가 됨.
=> 만약 뒤에 배치하면 `!ObjectUtils.nullSafeEquals(user.getId(), todo.getUser().getId())` 조건 검증 중에 NPE 발생



