## SIP NOTIFY의 이해

SIP NOTIFTY는 요청된 이벤트가 발생할 경우 그 결과를 통지한다. SIP SUBSCIRB 메서드는 등록 상태 정보 이벤트를 요청하고 SIP REFER는 호 전환 이벤트를 요청한다. 두 요쳥에 포함한 이벤트의 승인은 200 OK로 응답하지만, 요청이벤트가 발생하여 상세한 업데이트는 SIP NOTIFY 메서드를 이용한다

![SIP NOTIFY](./image/25_1.png)

SIP 채팅 서버가 앨리스의 등록 상태 정보를 SIP REGISTRA 서버에게 요청하는 과정은 SIP SUBSCRIBE 요청과 200 OK 응답이다. SIP SUBSCRIBE 메서드는 이미 설명했으므로 SIP NOTIFIY의 동작에 대해 정리한다.

1. Notifier의 SIP NOTIFY

Notifier는 앨리스의 등록 상태 정보 이벤트를 인지하고, 앨리스의 등록 상태 정보 이벤트에 대한 업데이트를 요청한 신청자인 SIP 채팅 서버에게 SIP NOTIFY 요청을 발행한다.

```sip
NOTIFY sip:app_IM.atlanta.com SIP/2.0
Via: SIP/2.0/TCP server1.atlanta.com;branch=z9hG4bKnasaii
From: sip:server19@atlanta.com;tag=xyzygg
To: sip:app_IM.atlanta.com ;tag=123aa9
Max-Forwards: 70
Call-ID:9987@app_IM.atlanta.com
CSeq: 1288 NOTIFY
Contact: sip:server19.atlanta.com
Event: reg
Subscription-State: active
Content-Type: application/reginfo+xml
Content-Length: 223


<?xml version="1.0"?>
   <reginfo xmlns=
                "urn:ietf:params:xml:ns:reginfo" 
                  version="0" state="full">
   <registration aor="sip:alice@atlanta.com"
                  id="a7" state="init"/ >
</reginfo>
```

Event 헤더는 등록 상태 정보 이벤트에 대한 통지를 나타낸다. Subscription-state 헤더는 요청의 상태 정보이므로 SIP SUBSCRIBE 요청의 상태 정보를 나타낸다.

- Subscription-State:active

  Notifier가 이벤트를 승인하고 처리 중

- Subscription-State:pending

  Notifier가 요청을 소력했으나 불충분한 정책 정보로 승인 또는 거절을 결정하지 못함

- Subscription-State:terminatd;reason=noreasource

  Notifier가 요청한 이벤트 처리 완료

  Expired 헤더의 유효기간 만료일 수도 있으며 반드시 사유를 명기

SIP 메시지 바디는 등록 상태 정보를 XML 구문으로 나타낸다. AoR은 애리스를 가리키고 State="init"은 현재 등록된 단말의 Contact address가 없다는 것을 나타낸다.

2. 채팅 서버의 200 OK

채팅 서버는 SIP NOTIFY 요청을 수신한 후 200 OK로 응답한다. 만일 Subscriber에서 요청하지 않은 사용자에 대한 등록 상태 정보가 업데이트 될 경우에는 '481 Subscription does not exist'로 응답한다.

```sip
SIP/2.0 200 OK
Via: SIP/2.0/TCP server19.atlanta.com;branch=z9hG4bKnasaii ;received=10.1.3.1
From: sip:app_IM.atlanta.com ;tag=123aa9
To: sip:server19@atlanta.com;tag=xyzygg
Call-ID:9987@app_IM.atlanta.com
CSeq: 1288 NOTIFY
Contact: sip:server1.atlanta.com
Content-Length: 0
```

