## SIP주소체계

PTSN전화망은 E.164주소 체계를 사용하고, 인터넷망은 IP주소체계를 사용한다. SIP는 처음부터 다양한 환경에서 사용할 수 있도록 여러 주소체계를 지원한다

- FQDN

  Fully-Qualified Domain Names. 웹 브라우저에서 입력하는 도메인 주소체계. 도ㅠ메인의 앞자리에 사용자명 또는 단말기의 호스트명을 붙여서 사용

  ex) `sip:bob.cisco.com` or `sip:alice.abc.com`

- URI

  Unified Resource Identifier. RFC2368에 정의된 주소체계. 이메일 주소체계. 웹브라우저에서 사용하는 URL주소체계도 포함.

  ex) `sip:bob@sisco.com` or `sip:alice@abc.com`

- E.164 주소를 포함한 URI주소체계

  사용자 이름 부분에 전화번호를 사용하는 URI주소

  ex) `sip:14085551234@cisco.com; user=phone` or `sip:1001@abc.com; user=phone`

- IP주소를 포함한 URI주소체계

  도메인 네임 부분에 IP주소를 사용하는 URI주소

  ex) `sip:14085551234@10.1.1.1; user=phone` 또는 `sip: alice@10.1.1.1`

## 주요 SIP헤더 분석

```SIP
INVITE sip:bob@biloxi.com SIP/2.0
Via: SIP/2.0/UDP pc33.atlanta.com;branch=z9hG4bK776asdhds
Max-Forwards: 70
To: Bob <sip:bob@biloxi.com>
From: Alice <sip:alice@atlanta.com>;tag=1928301774
Call-ID: a84b4c76e66710@pc33.atlanta.com
CSeq: 314159 INVITE
Contact: <sip:alice@pc33.atlanta.com>
Content-Type: application/sdp
Content-Length: 142
```

1. `INVITE sip:bob@biloxi.com SIP/2.0`
   - 메시지의 첫 줄에는 Method와 메시지를 수신하는 최종 단말의 주소 와 버전이 명시된다. SIP method는 이 메시지의 목적이 무엇인지 설명한다. 첫 줄은 세 부분으로 되어있다
     - `INVITE` : 요청한 메서드
     - `sip:bob@biloxi.com` : Request URI. 요청 메시지의 최종 목적지
     - `SIP/2.0` : 버전
   - Request-URI는 일반적으로 To 필드의 URI값을 참조하고, `Biloxi.com` 도메인에 있는 밥에게 전화를 걸기 위해 `INVITE` 요청을 보낸다 
2. `Via: SIP/2.0/UDP pc33.atlanta.com;branch=z9hG4bK776asdhds`
   - Via 헤더는 INVITE 요청에 대한 응답을 위한 경로를 나타낸다. branch는 시공간에서 유일한 값을 가지는 트랜잭션 식별자이다. 트랜잭션은 호 설정 또는 호 종료와 같은 단위작업을 의미하며 User Agent간에 생성된다
   - 이 줄은 SIP INVITE요청에 대한 응답인 200OK를 앨리스에게 바로 전송하지 말고  pc33.atlanta.com을 경유할 것을 요청한다
3. `Max-Forwards: 70`
   - 시그널링 경로 상에 SIP 서버 최대 홉 수.
   - IP네트워크의 TTL (Time To Live)와 같다
4. `To: Bob <sip:bob@biloxi.com>`
   - SIP트랜젝션의 출발지를 나타내지만, 실제 SIP메시지의 라우팅에 사용되지 않으며 Display Name의 의미를 가진다
5. `From: Alice <sip:alice@atlanta.com>;tag=1928301774`
   - SIP 트랜젝션의 목적지를 나타내지만 실제 SIP메시지의 라우팅에 사용되지 않으며 Display Name의 의미를 가진다
6. `Call-ID: a84b4c76e66710@pc33.atlanta.com`
   - 세션에 대한 global unique identifier로 사용되며, 호스트네임 또는 IP address와 시간을 조합하여 생성된다. To / From / Call-ID가 결합한 것으로 앨르사와 밥 간의 P2P SIP관계를 정의한다. Call-ID가 같을 경우 하나의 다이얼로그로 인식하므로 세션의 설립과 종료 사이의 모든 SIP메시지는 동일한 Call-ID를 가진다
7. `CSeq: 314159 INVITE`
   - Command sequence 또는 Sequence Number는 정수와 메서드 이름으로 나타낸다. 새로운 요청을 생성할 때마다 1씩 증가시킨다. 이 요청에 대한 응답인 200OK에서도 같은 값을 확인할 수 있다. 하나의 트랜잭션인 쵸엉과 응답은 CSeq값을 가진다
8. `Contact: <sip:alice@pc33.atlanta.com>`
   - Contact 헤더는 요청을 보낸 사용자에 대한 직접적인 경로를 나타내며, FQDN이나 IP주소를 선호한다
   - Via헤더 필드가 요청에 대한 응답 경로를 나타내고, Ciontact 필드는 새로운 요청을 송신할 경로를 나타낸다. 예를 들어, INVITE요청에 대한 응담은 Via 헤더 필드를 참조하고 호를 종료하기 위한 BYE요청은 Contact 헤더를 참조한다
9. `Content-Type: application/sdp`
   - SIP메시지가 바디가 포함될 경우 메시지 바디의 타입을 정의한다. application/sdp이므로 SIP 메시지의 바디는 SDP메시지로 구성되어 있다
10. `Content-Length: 142`
    - 메시지 바디의 크기를 옥텟(바이트)으로 표시한다. 메시지 바디가 142 바이트로 구성되어있다는걸 의미한다.

