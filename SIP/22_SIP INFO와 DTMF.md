## SIP INFO의 이해

SIP 세션과 관련된 SIP메서드는 다음과 같이 나눈다.

- SIP 세션 설립을 위한 메서드 : INVITE / 200 OK / ACK
- SIP 세션 종료를 위한 메서드 : BYE
- SIP 세션 변경을 위한 메서드 : UPDATE / re-INVITE



UA가 설립된 SIP 세션에 대한 정보를 요청할 필요가 있다. SIP OPTIONS메서드는 SIP세션이 아니라 UA에 대한 Capability 정보를 요청하는 것이므로 적절하지 않다. 새로운 SIP메서드가 필요하다. SIP INFO는 RFC 2976 The SIP INFO Method에서 정의하고, 설립된 SIP 세션 정보에 대한 요청과 어플리케이션 레벨의 단순 정보 선송에 사용한다. SIP INFO가 전송하는 정보는 다음과 같다

- PSTN 게이트웨이 간에 PSTN Signaling 메시지 전송
- DTMF Digits(숫자) 전송
- 무선 모빌리티 어플리케이션 지원을 위한 무선 신호의 세기 전송
- 은행 계좌 잔액을 조회하는 정보
- 통화자 간에 이미지나 텍스트와 같은 비스트리밍 정보를 전송



## SIP INFO 메시지 분석

SIP INFO는 전송할 정보를 SIP INFO의 헤더가 아닌 SIP 메시지 바디를 사용한다.SIP INFO는 은행의 자동응답 시스템 (ARS)과 연결하여 계좌번호나 비밀번호 등을 전달한다.

![SIP INFO](./image/22_1.png)

앨리스는 은행 ARS 시스템에 접속하여 요청받은 비밀번호를 전화기 키패드에서 숫자를 눌러 전달한다. 실제는 다수의 SIP INFO와 200 OK로 구성된다.



1. 앨리스의 INFO

   앨리스는 은행 ARS 자동응답 시스템에 접속한 후에 요청받은 정보를 전달한다.

   ```sip
   INFO sip:ARS@192.168.10.20 SIP/2.0
   Via: SIP/2.0/TCP pc33.atlanta.com;branch=z9hG4bK776asegma
   Max-Forwards: 70
   To: Bank <sip:Bank@Bank_URI.com>
   From: Alice <sip:alice@atlanta.com>;tag=1928301774
   Call-ID:a84b4c76e66710@pc33.atlanta.com
   CSeq: 22756 INFO
   Contact: <sip:alice@pc33.atlanta.com>
   Content-Type: text/plain
   Content-Length: 16
   
   3 1 8 1 9 6 2
   ```

   은행은 기존 Call-ID와 동일한 SIP INFO를 수신한다. Content-Type을 text/plain으로 하여 3181962번호를 전달한다.

2. 응답

   SIP INFO 메시지를 수신한 후 200 OK외에 다음과 같은 응답을 사용할 수 있다.

   - 481 Call leg / Transaction Dose not Exist
     수신된 INFO가 기존의 Call Leg와 매치가 되지 않음 

   - 415 Unsupported Media Type
     UAS가 이해할 수 없는 메시지 바디를 포함하므로 처리 없음

   - 487 Request Terminated
     SIP INFO 요청을 처리 중에 CANCEL 메쏘드를 받음

