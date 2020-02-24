## Early Media

- 정의
  - 사용자의 Call에 의해 Regular Session이 허용되기 전 Early Session을 통해 교환되는 Media를 뜻함

- 개요
  - Early Media는 Initial INVITE가 생성되는 순간부터 발생하여 UAS가 Final Response를 발생시킬 때까지 유지
  - 기본적인 SIP 규격은 매우 단순한 Early Media 메커니즘만을 제공하며, Forking과 Security에 대해 문제를 가지고 있으며, 대부분의 어플리케이션 서버들의 요구를 충족시키지 못함
  - Early Media는 Gateway Model과 Application Model로 나뉨
  - 예 : 전화통화가 되기 전 나오는 컬러링, 음성안내 등
    

**Regular Session, Early Session**
- Early Session
  - 전화 연결 중임을 나타내는 전화 연결음이나 컬러링 등이 울리는 구간.
  - Early Session에서 들리는 전화연결음이나 컬러링을 Early Media라고 함
  - SIP를 기준으로 설명하면 INVITE에 대해 Final Response가 처리되기 이전에 성립된 Session
- Regular Session
  - 상대방이 전화를 받아 실질적으로 상호간 통화가 이루어지는 구간
  - SIP를 기준으로 INVITE에 대해 Final Response(200 OK)가 처리 완료된 후 성립된 Session
  - 예를 들어 시나리오나 Session의 타입에 따라 교환되는 Early Media도 달라짐을 유의해야 함. (시나리오 예시 : 콜센터에 전화를 걸며ㄴ 들리는 안내방송이나 전화 연결 실패 시 나오는 음상사서함 메시지 등)



#### Early Media Model

** Gateway Model**
- Gateway Model은 SIP Signaling상에서 Early Media에 대한 특별한 언급 없이 동작
- Early Media와 Regular Media를 구분 못함
- Forking 시나리오에서 Media Clipping 및 Security 문제가 있음
- Ringing tone 발생
  - PSTN(Public Switched Telephone Network)상에서 수신자가 알림 메시지를 보내면 Ring Back Tone은 PBX(Private Branch Exchange)에 의해 재생
    - SIP의 경우 UAS에 의해 Ring Back Tone이 재생되지 않으면, UAC에서 자체적으로 Ring Back Tone을 재생하기로 되어있음
    - 만일, Announcement 또는 Special Ringing Tone이 UAS에 재생이 되면, UAC는 자체적인 재생을 중단하고 UAS로부터 오는 Media를 재생하는 것이 일반적임
    - 단, UAS가 Early Media 전송하려는 의도 없이 Early Offer를 진행하기도 하고, Reliable Provisional Response 없이 Early Media를 전송하기도 하기 때문에 UAC는 쉽게 Local Ring Back Tone을 재생해야 할 지 말아야 할지를 결정할 수 있음
    - Local Ringing Tone 재생과 관련된 정책은 다음과 같다
      - 180 Ringing을 받지 않았다면, Local Ringing을 재생하지 않음
      - 180 Ringing을 받았으나 수신되는 Media가 없다면, Local Ringing을 재생
      - 180 Ringing을 받았으며 수신되는 Media가 있다면, Local Ringing을 중단하고 수신 받은 Media(Remote Ringing Tone) 재생

** Application Model **

- SIP 시그널 시, Early Media에 대한 Offer/Answer를 가능하게 함.
  - Concept-Disposition 헤더 Session 또는 Early-Session의 새로운 Dispostion Type을 설정하여 Early Media가 가능하게 함.
- Forking 시 Media Clipping을 방지
  - Gateway Model의 문제점 해결
- Early Media Session과 Regular Media Session을 동시에 Offer/Answer를 통해 처리
  - Early Media Session에서 Regular Media Session으로 전환
- Offer/Answer를 통해 Local Ring Back Tone을 생성 여부를 판단할 수 있음
- Content-Disposision 헤더를 통해 Early Session과 Regular Session을 구분

** Early Media Disposition Type **
- Early Session과 Regular Session을 구분하기 위해서 Content-Disposision헤더 사용
- Early Session에서 Regular Session 전환 시, Codec의 변환을 하지 않기 위해 Early Session과 Regular Session이 동일한 Codec을 사용하도록 권고
- Early Session과 Regular Session Body의 Transport IP/port를 다르게 쓸 것을 권고. (Regular Media가 시작되는 것을 쉽게 감지할 수 있음)


