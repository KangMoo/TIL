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
  - Transaction이 존재하지 않는 알 수 없는 Dialog에 대해서만 BYE대신 481 Call/Transaction Does Not Exsist로 응답
  - UAC의 경우
    1. Session종료와 Media에 대한 Sending/Listening을 중단하고 BYE 전달
    2. BYE에 대한 응답으로 아래와 같은 Code를 받거나 경우가 발생할 경우 반드시 Session 종료
      - 481 Call/Transaction Does Not Exist
      - 480 Request Timeout
      - Transaction의 Timeout(Expire) 발생으로 UAS로부터 Response를 받지 못한 경우
  - UAS의 경우
    1. BYE를 받으면 매칭되는 Dialog를 찾음. 단, 찾지 못한 경우 481 Call/Transaction Does Not Exsist로 응답.
    2. 정상적으로 BYE에 대해 처리한 후 종료된 Session과 연관된 UAC의 Session의 종료 여부와 관계없이 2xx계열의 Response 전달
    3. 종료된 Session에 대해서도 수신대기 중인 Request에 대해 응답을 해야하는 데 규격서에서는 487 Request Terminated로 응답을 줄 것을 권고하고 있음
      - 권고사항이라 장비/회사마다 다르겠지만, 회사 IVCF의 경우 487을 전송함


