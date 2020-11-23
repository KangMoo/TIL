## MESSAGE 메서드

SIP MESSAGE메서드는 근 실시간으로 사용자 간에 메시지를 주고받기 위해 사용하며, RFC 3428 SIP for Instant Messaging에서 정의한다.

MESSAGE 요청은 다음과 같은 응답이 발생할 수 있다

- 200 OK

  사용자가 메시지를 읽었다는 것을 의미하는 것이 아니라 MESSAGE요청을 수신

- 4xx or 5xx

  MESSAGE 요청이 성공적으로 전달되지 않음

- 6xx

  MESSAGE 요청이 전달되었으나 사용자가 수신을 거절

