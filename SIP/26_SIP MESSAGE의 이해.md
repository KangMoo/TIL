## MESSAGE 메서드

SIP MESSAGE메서드는 근 실시간으로 사용자 간에 메시지를 주고받기 위해 사용하며, RFC 3428 SIP for Instant Messaging에서 정의한다.

MESSAGE 요청은 다음과 같은 응답이 발생할 수 있다

- 200 OK

  사용자가 메시지를 읽었다는 것을 의미하는 것이 아니라 MESSAGE요청을 수신

- 4xx or 5xx

  MESSAGE 요청이 성공적으로 전달되지 않음

- 6xx

  MESSAGE 요청이 전달되었으나 사용자가 수신을 거절



## SIP MESSAGE 메시지 분석

SIP MESSAGE 요청과 응답을 정리

![SIP MESSAGE](./image/26_1.png)

1. 앨리스의 MESSAGE

   앨리스와 밥은 별도의 다이얼로그를 생성하지 않고 앨리스는 SIP MESSAGE 요청을 전송한다

   ```sip
   MESSAGE sip:bob@biloxi.com  SIP/2.0 
   Via: SIP/2.0/TCP pc33.atlanta.com;branch=z9hG4bK776asegma
   Max-Forwards: 70
   To: Bob <sip:bob@biloxi.com>
   From: Alice <sip:alice@atlanta.com>;tag=1928301774
   Call-ID:a84b4c76e66710@pc33.atlanta.com
   CSeq: 22756 MESSAGE
   Content-Type: text/plain
   Content-Disposition: render
   Content-Length: 37
   
   
   Hello! Bob
   ```

   SIP 메시지 바디에 던송되는 메시지의 크기는 1300 Bytes를 초과할 수 없다.

2. 밥의 200 OK

   ```sip
   SIP/2.0 200 OK
   Via: SIP/2.0/TCP pc33.atlanta.com;branch=z9hG4bKnashds7 ;received=10.1.3.33
   To: Bob <sip:bob@biloxi.com>
   From: Alice <sip:alice@atlanta.com>;tag=1928301774
   Call-ID:a84b4c76e66710@pc33.atlanta.com
   CSeq: 22756 MESSAGE
   Content-Length: 0
   ```

   