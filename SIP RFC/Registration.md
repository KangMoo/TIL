## Registration

- 개요
  - 단말의 숫자가 증가할 ㄱㅇ우 모든 경로에 대한 정보를 단말이 보유하는 것은 불가능하므로 일반적으로 SIP Proxy Server를 사용하며, 단말들은 등록과정을 진행
  - 등록과정을 통해 SIP Proxy Server는 단말의 현재 위치를 확인할 수 있음
  - 단말은  Registration 메시지를 SIP Proxy Server에 전송하여 200OK를 수신하는 것으로 등록 과정이 진행
  - 등록 시 사용자는 집 전화와 사무실 전화 등 여러 단말을 동시에 등록할 수 있으며, 한 명 이상의 사용자가 동시에 하나의 전화기에 등록할 수 있음

- Registration메시지에 포함하는 헤더
  - Request-URI
    - Registrar Server의 도메인 정보를 포함하며 "sip:chicago.com"형식으로 표시
    - '@'나 userinfo등이 표시되면 안됨
  - To
    - 등록, 조회, 수정될 URI를 포함
    - URI는 SIP URI 형태로 표기되어야 함
  - From
    - 등록을 요청한 단말의 URI를 포함
    - third-party등록이 아니라면 일반적으로는 To헤더와 동일한 값을 가짐
  - Call-ID
    - 하나의 UAC로부터로의 요청되는 모든 등록 메시지는 같은 Call-ID를 가짐
  - CSeq
    - 등록 요청에 대한 순서를 구분하기 위함
    - 동일한 Call-ID라도 등록 요청을 할 땐 1증가해야 함
  - Contact
    - 바인딩 주소를 0개 이상 포함할 수 있음
  - Expires
    - 등록 만료 시간을 나타냄
