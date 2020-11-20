## REFER Method의 이해

SIP REFER메서드는 제공하는 자원을 UA가 참조하게 하고 RFC 3261 SIP 및 RFC 3515 The Refer Method에서 정의한다. SIP REFER메서드의 Refer-To 헤더가 지정하는 자원을 활용하기 위해 UA는 제 3의 UA로 INVITE를 발행한다. SIP REFER메서드를 사용하는 부가 서비스로는 호 전환 (Call Transfer)이 있다.

![REFER](./image/23_1.png)

SIP REFER요청의 Refer-To헤더는 UA가 INVITE요청을 제대로 발행하도록 정확한 목적지 주소를 사용해야 한다. Refer-To 헤더의 주소는 다양한 형식의 URI를 지원한다.

- Refer-To: sip:alice@atlanta.example.com
- Refer-To: <sip:bob@biloxi.example.net?Accept-Contact=sip:bobsdesk.biloxi.example.net&Call-ID%3D55432%40alicepc.atlanta.example.com>
- Refer-To: <sip:dave@denver.example.org?Replaces=12345%40192.168.118.3%3Bto-tag%3D12345%3Bfrom-tag%3D5FFE-3994>
- Refer-To: <sip:carol@cleveland.example.org;method=SUBSCRIBE>
- Refer-To: http://www.ietf.org

SIP REFER요청을 받은 UA는 반드시 2020 Accepted로 응해야 한다.

## 이벤트 처리의 결과를 SIP NOTIFY로 통보

SIP REFER 요청을 수신한 UA는 요청의 처리 결과를 통보하기 위해 SIP NOTIFY메서드를 사용한다. SIP NOTIFY요청의 메시지 바디에는 다음과 같은 정보가 표신된다.

- SIP/2.0 100 Trying
  현재 REFER에 의해 요청된 이벤트 처리 중
- SIP/2.0 200 OK
  현재 REFER에 의해 요청된 이벤트 정상 처리 완료
- SIP/2.0 503 Service Unavailable
  현재 REFER에 의해 요청된 이벤트 실패 
- SIP/2.0 603 Declined
  현재 REFER에 의해 요청된 이벤트 거절 

## SIP REFER 메시지 분석

RFC 3515 The Refer Method에서 설명된 REFER Call Flow

![SIP NOTIFY](./image/23_2.png)

1) 앨리스의 REFER

통화중에 앨리스는 밥에게 회원번호를 요청하고 수집용 서버로 호 전환을 시도한다. 앨리스 전화기는 밥에게 REFER 요청을 발행ㅎ나다

```sip
REFER sip:b@atlanta.example.com SIP/2.0 
Via: SIP/2.0/UDP agenta.atlanta.example.com;branch=z9hG4bK2293940223
To: <sip:b@atlanta.example.com>
From: <sip:a@atlanta.example.com>;tag=193402342
Call-ID:898234234@agenta.atlanta.example.com
CSeq: 93809823 REFER
Max-Forwards: 70
Refer-To: (whatever URI, 서버의 주소)
Contact: sip:a@atlanta.example.com
Content-Length: 0
```

Refer-To 헤더는 서버의 URI 주소를 명기한다. 밥은 새로운 INVITE요청을 발행한다.

2) 밥의 202 Accepted

밥은 REFER요청을 수신하고 처리했다는 의미로 2020 Accepted로 응답한다.

```sip
SIP/2.0 202 Accepted
Via: SIP/2.0/UDP agenta.atlanta.example.com;branch=z9hG4bK2293940223
To: <sip:b@atlanta.example.com>;tag=4992881234
From: <sip:a@atlanta.example.com>;tag=193402342
Call-ID:898234234@agenta.atlanta.example.com
CSeq: 93809823 REFER
Contact: sip:b@atlanta.example.com
Content-Length: 0
```

3) 밥의 NOTIFY

밥은 SIP REFER에 의한 이벤트 진행 상황을 SIP NOTIFY로 앨리스에게 통보한다. SIP 메시지 바디의 'SIP/2.0 100 Trying'는 요청된 이벤트를  처리 중임을 의미한다

```sip
NOTIFY sip:a@atlanta.example.com SIP/2.0
Via: SIP/2.0/UDP agentb.atlanta.example.com;branch=z9hG4bK9922ef992-25
To: <sip:a@atlanta.example.com>;tag=193402342
From: <sip:b@atlanta.example.com>;tag=4992881234
Call-ID:898234234@agenta.atlanta.example.com
CSeq: 1993402 NOTIFY
Max-Forwards: 70
Event: refer
Subscription-State: active;expires=(depends on Refer-To URI)
Contact: sip:b@atlanta.example.com
Content-Type: message/sipfrag;version=2.0
Content-Length: 20

SIP/2.0 100 Trying
```

