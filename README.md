# 누구에게나 열려있는 세계 시민의 자전거, **한강 살랑이**

## 💋 팀원 소개

![img.png](img.png)

## 💋 기능 목록

### ✔️ 살랑이 대여

- [ ] 유저가 살랑이를 대여할 수 있는지 확인한다.
    - [ ] 로그인한 유저만 살랑이를 대여할 수 있다.
    - [x] 유저가 이미 다른 살랑이를 대여중이면, 에러 코드 4을 반환한다.
    - [x] 유저가 이미 월별 대여 가능 회수인 2회를 초과해 대여를 시도한다면, 에러 코드 3을 반환한다.
- [x] 해당 살랑이가 대여 가능한 디바이스인지 확인한다.
    - [x] 암호화된 살랑이 아이디를 복호화를 통해 풀어낸다.
    - [x] 이미 다른 유저가 해당 살랑이를 대여중이면, 에러 코드 1를 반환한다.
    - [x] 해당 살랑이가 고장이거나, 비활성화 상태(회수 준비)면, 에러 코드 2를 반환한다.
- [ ] 위의 과정을 모두 통과한 경우에, 살랑이를 대여한다.
    - [x] 유저의 대여 가능 회수를 1회 차감한다.
    - [ ] MQTT 서버를 호출한다.
    - [x] 성공 응답을 반환한다.

### ✔️ 살랑이 반납

- [x] 살랑이를 성공적으로 반납했다는 응답을 반환한다.
- [x] 반환 기록을 `RETURN`으로 변경한다.
- [x] 살랑이의 상태를 `AVAILABLE`로 변경한다.

## 💋 API

### ✔ ️살랑이 대여

- 유저의 휴대전화에서 보내는 요청임.

#### Request

##### URL & Header

```http request
POST api.sallang-e.or.kr/rent

Authorization: Bearer b4m3wbdjwh12j3k4hj2j43mn234m_D32j4hej32j
```

##### Body

```json
{
  "cycleID": "jkl32hjherjfhuio2i2jk3kj3k"
} 
```

- `cycleId`는 `Long`을 암호화해서 서버로 전송한다.

#### Response

1. 대여에 성공한 경우

##### URL & Header

```http request
201 CREATED HTTP/1.1

Location: /rent-history/1
```

2. 대여 실패: 이미 다른사람이 대여중인 살랑이인 경우

##### URL & Header

```http request
401 BAD REQUEST HTTP/1.1
```

##### Body

```json
{
  "errorCode": 1,
  "errorMessage": "[ERROR] 해당 살랑이는 이미 다른사람이 대여중인 살랑이입니다."
}
```

3. 대여 실패: 고장인 살랑이인 경우

##### URL & Header

```http request
401 BAD REQUEST HTTP/1.1
```

##### Body

```json
{
  "errorCode": 2,
  "errorMessage": "[ERROR] 해당 살랑이는 고장났거나, 비활성화 상태입니다."
}
```

4. 대여 실패: 해당 유저에게 대여 가능 회수 없는 경우

##### URL & Header

```http request
401 BAD REQUEST HTTP/1.1
```

##### Body

```json
{
  "errorCode": 3,
  "errorMessage": "[ERROR] 살랑이 사용 가능 회수가 없는 유저입니다!"
}
```

5. 대여 실패: 해당 유저가 이미 다른 살랑이를 대여중인 경우

##### URL & Header

```http request
401 BAD REQUEST HTTP/1.1
```

##### Body

```json
{
  "errorCode": 4,
  "errorMessage": "[ERROR] 해당 유저는 이미 살랑이를 대여중입니다!"
}
```

---

### ✔ 살랑이 반납

- 살랑이 디바이스에서 보내는 요청임.

#### Request

##### URL & Header

```http request
POST api.sallang-e.or.kr/return-cycle

Authorization: Bearer b4m3wbdjwh12j3k4hj2j43mn234m_D32j4hej32j

```

##### Body

```json
{
  "cycleID": "jkl32hjherjfhuio2i2jk3kj3k"
}
```

- 마찬가지로, `cycleID는` 암호화된 형태임.

#### Response

```http request
200 OK HTTP/1.1
```

---

### ✔️ 살랑이 디바이스의 대여 여부 확인

- 살랑이 디바이스에서 보내는 요청임.
- 살랑이가 대여 가능(`AVAILABLE`)한 경우에, 어떤 유저든 대여했을 경우에 대비해 1초마다 health check를 보내는 API다.

#### Request

##### URL & Header

```http request
GET api.sallang-e.or.kr/admin/cycles/status
```

##### Body

```bash
{
	"cycleID": "jkl32hjherjfhuio2i2jk3kj3k" 
}
```

#### Response

```bash
{
	"status": "RENT"
}
```

- 해당 살랑이의 현재 상태를 `RENT`, `AVAILABLE`, `BROKEN` 중 하나로 응답한다.

---

### ✔️ 살랑이 디바이스의 상태 변경

- 살랑이 디바이스의 상태는 `AVAILABLE`에서 고장난 경우 `BROKEN`으로 변경할 수 있다.
- 고장난 살랑이를 고친 경우에 살랑이 디바이스의 상태를 `BROKEN`에서 `AVAILABLE`로 변경할 수 있다.

#### Request

##### URL & Header

```http request
PATCH api.sallang-e.or.kr/admin/cycles/change-status
```

##### Body

```json
{
  "cycleID": "jkl32hjherjfhuio2i2jk3kj3k",
  "status": "BROKEN"
}
```

- `status`는 `BROKEN`, `AVAILABLE` 중 선택 가능

#### Response

##### Header

```http request
200 OK HTTP/1.1
```




