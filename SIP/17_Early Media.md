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

## 게이트웨어 모델과 어플리케이션 서버 모델

- SIP는 다른 시그널링 프로토콜과 달리 UAC가 로컬 링백을 재생하지 않도록 할 수 있는 Early Media indicator가 없다.
- UAS가 원격 링백톤이나 announcement를 제공하려는 의도를 UAC에게 전달할 수 없다.
- SIP와 Media의 경로가 서로 달라서 SIP시그널링보다 Media가 먼저 도착할 가능성이 있다.
- 위의 문제를 해결하기 위해서 RFC 3960 Early Media and Ringing Tone Generation in the SIP에서는 링백톤 재생을 위한 게이트웨이 모델과 어플리케이션 모델을 제시한다

### 게이트웨이 모델

- 게이트웨이 모델은 단말(UA)이 Early Media와 Regular Media를 구문할 수 없을 때만 사용된다.
- PSTN Gateway 또는 Voice Gateway에 주로 사용한다.
- 게이트웨이는 PSTN과 IP네트워크를 서로 연결해주지만 미디어의 내용을 알 수 없으므로 Early Media와 Regular Media간의 전환을 정확히 인지하지 못한다.
- 게이트웨이 모델은 Call Forking 시 Media Clipping이 발생하는 점과 미디어 검출을 통해 로컬 링백을 적당하게 재생해야 하는 문제가 있다 RFC 3959에서 게이트웨이 모델의 Early Media 문제를 언급한다.
  - Call Forking의 경우 UAC는 Early Media를 보내는 UAS의 미디어를 모두 재생하지 않고 랜덤하게 한 개의 Early Media만을 재생하고 나머지는 묵음 처리한다.
  - 만일 200 OK를 내보내는 UAS가 묵음처리되었다면 묵음 해제를 위한 새로운 Offer/Answer가 필요하므로 Media Clipping이 발생한다
  - 따라서 Call Forking 상황에서 미디어 클리핑 문제를 해결하기 위해서는 어플리케이션 서버 모델이 필요하다

### 어플리케이션 서버 모델

- Call Forking 상황에서 UAS들은 Regular Media를 위한 SDP Offer/Answer 교환과는 독립적인 Early Media를 위한 Offer/Answer 교환이 필요하다. 어플리케이션 서버 모델은 Early Media SEssion을 설립하기 위해 UAS가 어플리케이션 서버의 역할을 한다. UAS와 UAC는 Regular Media Session과 Early Media Session을 위한 SDP Offer/Answer협상을 각각 진행한다. UAC는 정확한 시점에 Early Media와 Regular Media로 전환한다.



### 어플리케이션 서버 모델 구현

- 어플리케이션 서버 모델을 구현하기 위한 가장 효과적인 방법은 기존 다이얼로그와 다른 다이얼로그를 생성하는 것이다. 두 개의 다이얼로그가 생서오디므로 라우팅 가능한 별도의 URI가 필요하지만 UAC는 Early Media 다이얼로그를 정확한 시점에 Regular Media다이얼로그와 연결한다.
- 하나의 다이럴로그에서 두 개의 미디어 채널에 대한 SDP Offer/Answer 교환을 하는 것으 여러 가지 장점이 있다.
  - 호 처리 절차가 단순하다
  - 동기화 문제가 없다. 세션 생성이 완료될 때 Early Media 다이얼로그가 종료된다
  - Early Media를 위한 라우팅 가능한 URI가 필요없다
  - 부가 서비스 적용 시 문제를 일으키지 않는다
  - 방화벽 투과 및 관리가 쉽다
- 결국 어플리케이션 서버 모델을 이용하여 하나의 다이얼로그로 Early Media Sesison과 Regular Media Session을 함께 처리하는 것이 가장 현실적인 방안이다. 어플리케이션 서버 모델을 구현하기 위해 새로운 SIP헤더를 사용하더라도 SDP Answer가 빨라도 200OK이다
- Early Media 를 전송하기 위해 180 Ringing과 동시에 개방되어야 하지만 현재의 CAll Flow로는 불가능하고 새로운 SIP 메서드가 필요하다

