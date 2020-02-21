## Basic Header Field

#### Request Line
- SIP Message에서 첫 라인을 뜻하며 이는 해당 SIP가 어떤 Message인지를 나타내는 역할을 함
- Request-Line에 표시되는 Method(INIVDE, BYE, SUBSCRIBE, REGISTER 등...)에 따라 Message를 구분함
- 사용 형식
  > `Method Request=RUI SIP-Version`

#### 기본적으로 사용되는 헤더

**Via**
- Request 메시지의 경로 표시
- 한 Transaction 내 Response.Request동일 경로를 보장해 줌
- 다른 SIP헤더와 달리 순서가 중요한데 항상 Request메시지를 Forwarding하게 되면 추가되는 Via 헤더는 기존 Via 헤더 리스트의 첫 번째에 추가함
- 파라미터
  - received : Via 헤더 List 중 첫 번째 헤더에 있는 address와 다른 주소로부터 받은 경우 추가되는 tag.
  - rport : Request에 포함되어 있으면 Proxy에서는 수신한  Request 메시지에 해당 값을 Setting (Real Port라는 의미로 생각하면 됨)
  - branch : Mandatory이며 반드시 Magic Coockie("z9hG4bk")로 시작해야함(To tag, From tag, Call-ID, CSeq, Proxy-Require, Route,Proxy-Authrization 등)
  - maddr, ttl : 이 두 파라미터는 Multicast 전송에 사용됨
- Message Loop를 탐지하기 위해 사용됨
  - 수신한 메시지의 Via에 자신의 주소가 이미 있는 경우 Loop
  - 이전 기록된 branch 값과 새로 생성한 branch 값 비교, 동일하면 Loop

**To**
- 요청 수신자를 나타내는 헤더
- SIP Message가 최종 도착해야 할 수신 URI
- Routing 목적으론 절대 사용하지 않음
- Parameter중 tag의 경우 Request를 수신한 UA 혹은 Proxy에서 Response를 전송할 때 반드시 추가해야 함
- SIP-URI는 `< >`으로 묶어 사용함

**From**
- Request의 발신자를 나타내는 헤더
- SIP Message를 최초 송신한 발신 URI
- tag Parameter는 반드시 포함해야 하며 Request를 발신할 UA에서 추가해서 전송. 단 tag Parameter가 없더라도 수용 가능하다고 함
- SIP-URI는 `< >`으로 묶어서 사용함

**Call-ID**
- Call을 식별할 수 있는 식별자
- 재사용하지 않으며 비교 시 대소문자를 구분해 비교함
- 일반적으로 호스트 네임 또는 IP address와 Random String을 결합하여 생성함
- Call-ID는 Request를 전송하는 UA에서 생성함
- 보통 Unique하게 사용되나, 예외적으로 UA등록 시엔 동일 Call-ID를 사용해야 하는 특징이 있음 
- 반드시 암호화된 Random한 식별자여야 함

**CSeq**
- Command Sequesce의 약어
- Transaction의 식별 및 순서 판단
- 정수와 Method명으로 구성되어 있음
  > CSeq : 숫자 Method
- 새로운 Transaction이 시작할 때 반드시 1 증가
- 재전송의 경우 같은 값 사용

**Max-Forwards**
- 최대 Proxh를 거쳐갈 수 있는 수 (0 ~ 255)(추천 초기값 : 70)

**Record-Route**
- Routing을 제어하기 위한 목적으로 사용됨
- Proxy에서 기록하고, Record-Route헤더 List에서 첫 번째 값이 최신 정보
- Proxy서버에선 아래와 같은 경우에 추가
  - Initial INVITE처리 시 기본적으로 추가됨
  - Dialog 설정 이후에도 Signaling 경로 상에 포함하고자 할 경우
- Proxy주소 뿐 아니라 `Ir` 파라미터도 반드시 추가

**Content-Length**
- SIP에 포함된 Message Body의 길이 표시
- 0 dltkddml rkqtaks dbgy

- Content-Length가 없어도 Message처리는 가능해야 함

