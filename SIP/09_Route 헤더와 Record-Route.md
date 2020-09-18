## 모든 SIP 메시지가 SIP Proxy서버를 경유하게 하려면

Dialog Stateful SIP Proxy를 사용한다. SIP 프로토콜은 새롭게 두 개의 SIP헤더를 추가하여 이 문제를 해결한다.

1. Record-Route Header
   - SIP Proxy를 경유하는 다이얼로그에 대한 요청과 응답에 사용된다.
   - 여러 대의 SIP Proxy를 경우할 경우 ','
2. Route Header