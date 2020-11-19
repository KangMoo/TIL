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

## 