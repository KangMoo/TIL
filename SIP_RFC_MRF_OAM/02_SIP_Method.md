#### 기본 6개 메서드

- RFC 3261
  - INVITE : 멀티미디어 세션에 챀가시키기 위한 서비스나 사용자를 초대하기 위한 메서드
  - ACK : INVITE메서드의 '200 OK'를 수신했음을 통지하기 위한 메서드
  - BYE : 기존 세선을 종료하기 위한 메서드
  - CANCEL : 최종응답 200OK를 받기 전에 기존의 요청을 취소하기 위한 메서드
  - OPTIONS : 서버의 Capability를 요청하기 위한 메서드
  - REGISTER :USER AGENT가 Registrasr Server에 등록하기 위한 메서드

#### 추가된 8개 메서드

- RFC 3262
  - PRACK : UAC가 암묵적으로 Response를 승인하기 위한 메서드
- RFC 3265
  - SUBSCRIBE : 특정 이벤트를 원격 노드에 요청하기 위한 메서드
  - NOTIFY : 특정 이벤트 발생 시 응답하기 위한 메서드
- RFC 2976
  - INFO : 기존에 설립된 세션이나 다이얼로그 내에서 추가적인 정보전송을 위한 메서드
- RFC 3311
  - UPDATE : 세션 설정 파라미터 업데이트를 위한 메서드
- RFC 3515
  - REFER : 호 전환(Call-Transfer)를 위한 메서드
- RFC 3903
  - PUBLISH : Presence Server에 UA의 상태정보를 전송하기 위한 메서드
- RFC 3438
  - MESSAGE : 채팅과 같은 단문 메시지를 전달하기 위한 메서드



#### INVITE

- 정의 : 멀티미디어 Session에 참가시키기 위한 서비스 or 사용자를 초대하기 위한 Method
- 특징 
  - INIVTE에 대한 Final Response(200 OK)를 수신 시 그에 대한 응압으로 ACK를 전송
  - 일반적으로 Message Body에는 발신자의 Media 정보 (Media 정보 외 Session의 다른 정보도 가능은 함)를 포함하고 있음. 단, INVITE가 포함하지 않은 경우엔 ACK가 포함하고 있음
  - INVITE Request에 대한 200 OK로 Response를 받고, Session 종료를 위해 BYE가 전송되기 전까지는 Session을 유지함
  - INVITE 요청 시 Session에 대한 Unique한 Call-ID를 생성하고 CSeq 값을 초기화함.
    - CSeq값은 정수값이기만 하면 되고, 반드시 1이 아니어도 됨.
    - 같은 Call-ID를 가지고 있어도 새로운 Request의 경우엔 증가

#### ACK

- 정의 : 사용자가 INVITE에 대한 Final Response를 받았음을 확인하기 위한 Method
- 특징
  - INVITE를 지외한 다른 Method에 대한 응답으로 ACK를 사용하지 않음
  - ACK는 INVITE와 동일한 CSeq, Call-ID등 INVITE를 식별할 수 있는 주요 헤더 값의 경우 INVITE와 동일한 값을 가짐
    - ACK는 새로운 Transaction으로 포지 않기 때문에 CSeq는 증가하지 않음
  - ACK의 Message Body에는 SDP 메시지를 포함할 수 있음
    - INVITE Call Flow 내에 Offer에 대한 Answer가 처리되지 않은 경우, ACK를 통해 보내는 경우도 있을 수 있음. 이는 하나의 예시일 뿐이고, 시나리오에 따라 달라질 수 있음
  - Response Code 중 2xx 계열의 경우 E2E방식이지만 다른 계열(3xx ~ 6xx)의 경우 Dialog 상태 유지를 위해 Proxy를 사용할 경우 Hop-by-Hop방식으로 처리
    - 실질적으론 Hop-by-Hop방식으로 동작한다 함

#### BYE

- 정의 : 성립된 Session을 종료하기 위한 Method
- 특징
  - Session에 참여한 UA(Caller/Callee)에서만 정송되며 Proxy나 제 3자가 전송하지 않음
  - Transaction이 존재하지 않는 알 수 없는 Dialog에 대해서만 BYE대신 481 Call/Transaction Does Not Exist로 응답
  - UAC의 경우
    1. Session종료와 Media에 대한 Sending/Listening을 중단하고 BYE 전달
    2. BYE에 대한 응답으로 아래와 같은 Code를 받거나 경우가 발생할 경우 반드시 Session 종료
      - 481 Call/Transaction Does Not Exist
      - 480 Request Timeout
      - Transaction의 Timeout(Expire) 발생으로 UAS로부터 Response를 받지 못한 경우
  - UAS의 경우
    1. BYE를 받으면 매칭되는 Dialog를 찾음. 단, 찾지 못한 경우 481 Call/Transaction Does Not Exist로 응답.
    2. 정상적으로 BYE에 대해 처리한 후 종료된 Session과 연관된 UAC의 Session의 종료 여부와 관계없이 2xx계열의 Response 전달
    3. 종료된 Session에 대해서도 수신대기 중인 Request에 대해 응답을 해야하는 데 규격서에서는 487 Request Terminated로 응답을 줄 것을 권고하고 있음
      - 권고사항이라 장비/회사마다 다르겠지만, 회사 IVCF의 경우 487을 전송함


#### CANCEL

- 정의 : INVITE 요청을 취소하기 위한 Method
- CANCEL을 사용할 경우는 아래와 같음
  - 발신자가 전화번호를 누른 후 Ring Back Tone을 듣다가 바로 수화기를 내려놓는 경우
  - Call Forking과 같은 기능 사용 시 받지 않는 나머지 전화에 대한 INVITE요청 취소
  - 상대방이 일정시간 동안 전화를 받지 않는 경우

- 특징
  - ACK와 마찬가지로 새로운  Transaction으로 분류되지 않기 때문에 CSeq를 증가시키지 않음
  - INVITE 요청에 대해나 취소 요청이므로 해당 INVITE를 식별할 수 있는 Call-ID, CSeq등의 주요 헤더값은 INVITE와 동일함
  - INVITE요청에 대해 Provisional Code(1xx)를 받지 못한 경우 CANCEL을 요청할 수 없음
  - UAC나 PRroxy Client가 어느 때나 발생 가능
    - Proxy는 Forking된 Request 중 하나로부터 2xx 혹은 6xx Response를 수신한 경우, Final Response가 없는 경우 CANCEL을 발생
    - Via 헤더는 CANCEL을 요청한 발신자의 주소로 초기화 (Response 경로를 제한하기 위한 목적)
  - CANCEL 수신 시 동작
    - Stateful Proxy의 경우 CANCEL을 수신한 즉시 200 OK로 응답하고 모든 Pending Request에 새로운 CANCEL 생성함
    - UA 또는 Redirect Server 경우는 아래와 같음
      - INVITE에 대해 Final Response를 보낸 경우
        - 영향 없음
      - INVITE에 대해 Final Response를 보내지 않은 경우
        - CANCEL에 대해 200 OK로 응답
        - INVITE에 대해 487 Request Terminated로 응답하고 이에 대해 ACK 수신
      - CANCEL에 일치되는 Call이 없는 경우
        - 481 Call/Transaction Does Not Exist로 응답
  - BYE와 마찬가지로 경우에 따라 E2E, Hop-by-Hob 방식으로 ㅈ처리됨

**Call Forking에서의 CANCEL**

- Call Forking 서비스는 한 번에 다수의 UA에 INVITE를 동시 또는 순차적으로 전달하는 것을 의미. 이 중 하나의 UA에서 200 OK를 전송하면 나머지 UA에게 CANCEL를 전송하여 요청을 취소하는 서비스

- 예시
  - 콜 센터의 경우 대표 전번호를 1개로 두고 내부직원들이 여러 전화로 공유할 경우 전화가 오면 내부 전화가 울리는 데 직원 중 한 사람이 받으면 나머지 전화에 대해 끊어야 할 상황에 사용될 수 있음


#### OPTIONS

- 정의
  - UA또는 Server의 기본적인 Capability나 현재 Capability를 요청하기 위한 Method
  - 타 UA의 사용 가능한 자원 정보에 대한 요청

- 특징
  - OPTIONS는 Message Body를 포함하지 않음
  - OPTIONS에 대한 Response에 요청한 정보가 포함됨
  - Proxy의 경우 OPTIONS Response를 전송하지 않은
  - OPTIONS도 INVITE, CANCEL등 과 동일하게 RESPONSE를 받음. 단, 실패 시에는 Redirect관련 3xx를 제외한 나머지 4xx ~ 6xx사이의 Code만 사용
  - 성공 시엔 200 OK로 응답을 받으며 Allow, Accept, Accept-Encoding, Accept-Lanugage등의 헤더에 요청한 정보가 있음
  - Contact 헤더에는 audio, video, isfocus와 같은 tag가 포함되어야 함




