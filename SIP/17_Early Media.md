## Early Media

- 원격 링백톤 문제를 해결하기 위한 방법으로 Early Media Session으로 전달되는 미디어(음성)이다
- 최종 응답 이전에 개방되는 세션이며 최종 응답 이후는 Regular Media Session이 개방된다.
- Early Offer의 최종 응답은 200OK이고, Delayed Offer의 최종 응답은 ACK이다.

## 링백톤 재생방식

- PSTN망에서 수신자가 Alert메시지를 보내면 발신 측의 PBX가 로컬 링백톤을 재생하거나 컬러링을 이용할 경우에는 컬러링 서비스와 미디어 채널을 개방한다.

- SIP망에서 링백을 재생하는 방법은 링백톤, 전화기 디스플레이의 단순 메시지 또는 그림, 동영상 등 다양하므로 표준화된 방법은 없지만 PSTN의 방식을 준용한다.
- SIP 전화기는 180 Ringing 수신 후 원격으로부터 링백톤이나 컬러링이 수신되지 ㅇ낳을 경우 로컬 링백톤을 재생한다.
  - 만약 재생 중 Announcement 또는 컬러링이 UAS로부터 전달되면 UAC는 로컬 링백톤 재생을 중단하고 UAS로부터 전달되는 미디어를 재생한다
  - 하지만 UAS는 Early Media를 전송하려는 의도 없이 Early Media Session을 개방하거나 Early Media Session개방 전에 Early Media를 보낼 수도 있다.
- 발신 전화기인 UAC는 로컬 링백을 재생해야 할지 원격 링백을 기다려야 할지 결정할 수 없다. UAC는 링백 재생에 관한 정책이 필요하다.
  - 180 Ringing 을 수신하지 않는다면 로컬 링백을 재생하지 않는다
  - 180 Ringing을 수신했으나 미디어 패킷이 없다면 로컬 링백을 재생한다.
  - 180 Ringing을 수신하고 Media 패킷이 있다면, 미디어를 재생하고 로컬 링백을 재생하지 않는다
- 180 Ringing은 수신 측의 전화기가 울리고 있음을 의미하며 UAS는 Early Media Session의 상태화 상관없이 응답을 보내야 한다.

## 