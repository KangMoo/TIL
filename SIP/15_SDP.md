## SDP

Session Description Protocol의 약어로 멀티미디어 세션 파라미터를 협상하는 포로토콜이다. SDP는 RFC 2327을 개정한 RFC45665으로 권도되었다.

H.323프로토콜 슈트에서 볼 때 H.225가 시그널링에 대해 정의하고, H.245가 Capability Exchange를 정의한다. 마찬가지로 SIP프로토콜이 시그널링에 대해 정의하고  SDP가 Capability Exchange를 정의한다. SDP는 SIP뿐만 아니라 MGCP와 Megaco에서도 사용한다

SIP는 요청과 응답 모델(Request & Response Model)로 정의하였고 SDP는 제안과 수락모델(Offer & Answer Model)로 정의한다.

SDP의 Offer/Answer Model로의 동작에 대해서는 RFC 3264 An Offer/Answer with th SDP에서 설명한다

![제안과 수락 모델](./image/15_1.png)



## SDP 메시지 분석 개요

SDP는 멀티미디어를 전달하는 RTP 프로토콜에 대한 세부적인 내용을 협상한다. SDP는 SIP와 다른 메시지 포맷을 사용하지만 텍스트 기반이므로 이해하기 쉽다.

```sdp
v=0
o=alice 2890844526 2890844526 IN IP4 atlanta.com
s=
c=IN IP4 10.1.3.33
t=0 0
m=audio 49172 RTP/AVP 0
a=rtpmap:0 PCMU/8000   
```

- v=0
  - 필수
  - SDP 프로토콜의 버전 표시. SDP 버전은 0이다
- o=alice 2890844526 2890844526 IN IP4 atlanta.com
  - 필수
  - SDP메시지를 생성한 Owner/Creator를 표시.
  - 순서대로 Username, Session-ID, Session Version, Network Type, Address Type, Unicate Address이다
- s=
  - 필수
  - 세션 이름을 표시한다
- c=IN IP4 10.1.3.33
  - 옵션
  - 순서대로 Network Type, Address Type, Connection-Address이며, RTP프로토콜이 사용할 주소를 정의한다
- t=0 0
  - 필수
  - Timing으로 start-time과, stop-time을 표시한다. `0 0`은 고정 세션을 의미한다.

