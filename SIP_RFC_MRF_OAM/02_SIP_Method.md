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


#### REGISTER

- 정의
  - UA가 Registrar Server에 등록하기 위한 Method

- 특징
  - UA가 Registrar Server에 등록하는 이유는 통신망에서 통신을 위한 Routing등에 필요한 단말의 현재 위치 정보가 포함된 기본 정보를 등록하기 위함
    - 단말이 RERGISTER를 요청할 기지국에 대한 정보는 USIM칩에 있다고 함
  - Contact와 Expires 헤더 값을 통해 등록에 대한 다양한 처리를 요구할 수 있음.
  - REGISTER는 새로운 Transaction으로 분류되기 때문에 CSeq값이 증가함
  - Request-URI에는 Registrar Server 도메인 정보만 포맣하는데 항상 최종 목저지가 Registrar Server임을 나타냄
  - REGISTER의 200 OK Response에는 항상 현재 등록된 주소 정보를 포시
  - REGISTER의 200 OK Response에 포함된 헤더 중 Service-Route 헤더가 있는 데 이는 등록과정을 통해 서비스한 경로 정보를 얻기 위함으로 3GPP환경에서 SIP단말이 Session을 생성하고자 할 경우, Home 서비스의 Proxy에 대한 정보를 미리 알아야 할 필요가 있기 때문에 사용
  - REGISTER Request에는 Message Body를 포홤할 수도 있지만 이와 관련해선 규격상에 정의되어 있지 않음


#### PRACK

- 정의
  - Provisional Response Acknowledgement의 약자
  - 아직 성립되지 않은 Session에 대한 신뢰할 수 잇는 Provision ACK를 제공하기 위함

- 특징
  - INVITE의 Response로 101 ~ 199 Response에 대해서만 PRACK을 제공함
     100 Trying의 경우 Hop-by-Hob방식으로 처리됨으로 제공하지 않음
  - PRACK도 새로운 Transaction으로 분료되기 때문에 CSeq값 증가
  - PRACK에선 Response 식별을 위한 목적으로 추가로 RSeq 헤더도 포함
    - 이 경우는 시나리오에 따라 달라질 수 있음
  - PRACK을 사용하기 위해선 Supported, Requires 헤더에 "100rel"값을 설정해야 함

**유의사항**
- CSeq의 값은 PRACK 사용 시 증가하는데 INVITE에 대해 200OK를 받고 Session이 성립되는 시점에서 INVITE요청 시 생성한 CSeq값으로 초기화 해야 함
  - 초기화 하지 않으면 이후 호는 모두 실패 처리됨
- RAck의 경우엔 RSeq, CSeq의 값을 조합하여 쓰는데 이 값에 문ㄷ제가 있으면 매칭되는 Dialog를 찾지 못해서 481 Call/Transaction Does Not Exist 응답 받고 호 종료됨
- 1xx 계열의 Provisional Response를 여러 번 전송하면 전송할 때마다 RSeq 값은 1씩 증가되어야 함
  - 실제 망에선 이런 경우는 없다고 하나 혹시 모름
- Session 성립 이후 1xx Response를 받으면 무시함
  - UDP를 사용할 경우에 발생할 수 있는데 이럴 땐 무시하고 PRACK 전송 안함
- UAC에서 PRACK를 전송하지 않아 UAS가 해당 Transaction에 대해 Expire가 발생할 경우 5xx Response로 응답하고 호 실패 처리함
  - Expire 시간은 규격상 64 * T1(500ms) 즉 32sec 라고 보면 됨
  - 여기선 Expire라고 표현했지만 규격서 상에는 PRACK를 수신하지 못해서 Provisional Response를 재전상하는 경우라고 되어 있으니 유의
- UAS에서 100rel에 대해서 지원하지 않을 경우 420 Bad Extension Response의 Unsupported 헤더에 "100rel"의 options tag를 포함하여 전송
- 수신한 PRACK에 대해 매칭되는 Provisional Response의 Transaction을 찾지 못한 경우 481 Response를 전송


**Offer/Answer Model과 PRACK**

- 일반적으로 UAC가 INVITE with offer를 제공하면 UAS의 경우 Provisional Response with answer로 응답하여 Final Response 이전에 Session Parameter에 대한 협상 진행함
- UAS에서 Provisional Response with offer를 한다고 가정한 경우 (UAC가 INVITE without offer를 전송한 경우) 이 때는 반드시 PRACK with answer로 처리되어야 함
  - 이는 Provisional Response에 offer가 포함된 경우에만 한 해 적용됨을 유의
- PRACK with offer인 경우라면 PRACK에 대한 200 OK Response에 반드시 answer가 포함되어야 함
  - 이는 PRACK에 offer가 포함된 경우에만 한 해 적용됨을 유의
- 이는 Offer/Answer Model을 기초한 내용이며 PRACK를 통해 다양한 상황에서 Session Parameter의 협상이 가능함을 나타냄



#### SUBSCRIBE / NOTIFY

**SUBSCRIBE**

- Subscriber가 특정 사용자의 등록 상태와 상태 정보 업데이트를 요청하여 향후 이벤트 발생 시에 통지해 줄 것을 요청
- 쉽게 설명하면 특정 이벤트를 살펴보기 위한 Method
- 등록 후 등록 해제는 헤더에 포함되어 있는 Expires의 값(Sec)에 따라 결정됨
  - Expires의 값을 갱신하고자 할 때는 SUBSCRIBE Message를 사용해야 함. 여기서 주의할 점은 갱신은 주기적으로 이루어져야 ㅓ하며 동일ㄴ한 Dialog정보를 가져야 함
- SUBSCRIBE Request와 그에 대한 2xx Response에는 Expires 헤더가 포함되어야 함
  - 2xx Response의 경우 Expires의 값이 Request Expires의 값보다 작을 수 있지만 절대 크면 안됨
- Subscribe Request에 대한 성공 Response
  - 200 OK : 등록 및 사용자 인증 모두 성공
  - 202 Accepted : 등록엔 성공했지만, 인증 성공 유무는 경우에 따라 나타냄 (200 OK를 받지 않으면 NOTIFY가 전송 되지 않음을 유의)


**NOTIFY**

- SUBSCRIBE에 의해 요청된 이벤트 발생시 통지하거나, Subscription(신청)된 단말의 상태 변화를 Notifier가 Subscriber에게 통지
- 주의할 점은 SUBSCRIBE Method 말고도 REFER Method 에서도 사용됨
- 쉽게 설명하면 특정 이벤트 발생 시 사용자의 상태 정보를 통보하기 위한 Method
- SUBSCRIBE를 통해 등록된 대상에게만 전송함
- SUBSCRIBE를 통해 등록된 이후 전송되는 최초 Notifiy는 초기화 목적으로 현재 상태 전송, 이후 전송되는 Notify는 이벤트 발생 시마다 상태 정보를 전송함
- NOTIFY Request에 대해 481 Call/Transaction Does Not Exist로 응답 받은 경우 등록 해지를 해서 더 이상 NOTIFY를 전송하지 않음



#### INFO

- 정의
  - SIP Message Session 성립과 종료에 대한 메시지들 중 Session이 성립된 이후 (200 OK ~ BYE  수신) 두 UA간에는 Session 제어 정보 교환을 할 수 없음
  - 이에 Session 성립 이후 Session 제어 정보를 전송하는 것을 목적

- 특징
  - 다양한 정보 변경이 가능하지만 아래의 경우에 대해선 정보 변경 불가
    - SIP호의 상태 변경
    - 초기 설정된 Session Parameter
      - 위의 경우 상태 정보 변경을 하려면 UPDATE, re-INVITE등을 이용해야 함
  - INFO Request를 받으면 무조건 response를 보내야 함
  - SIP Signaling Path를 통해 Application 관련 제어 정보를 전송할 때 이용됨
    - SIP Signaling Path는 Call Set up 결과로서 생성되는 경로
  - INFO의 헤더를 통해 정보 전달를 할 수 있지만 일반적으론 Message Body를 통해 전달함
  - INFO Method도 별개의 Transaction으로 구분하기 때문에 CSeq값을 증가함
  - INFO 가 전송하는 주요 정보는 아래와 같음
    - PSTN Gateway 간의 PSTN Signaling 메시지 전송
    - DTMF Digits 전송
    - Wireless Mobility Application 지원을 위해 무선 신호의 세기를 전송
      - 예시) 선불식 카드의 경우 결제 금약, 남은 액수, 결제 위치, 시간 등의 정보를 전송한다 함
    - Session 참가자 간의 이미지 or 비 스트리밍 정보 전송

   
