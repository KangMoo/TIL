## SIP

#### SIP의 개요

- SIP란
  - SIP는 Session Initiation Protocl의 약자로 응용계층의 호 Signaling Protocol이다.
  - 하나 이상의 참가와 멀티미디어 세션의 생성, 변경, 종료에 대해 정의한다

- SIP의 패킷 구조
  - ![SIP_PACKET](images/SIP_Packet.png)

- SIP의 멀티미디어 통신을 생성하고 종료하기 위한 5가지 요소
  - User Location : 통신에 참가할 단말을 결정
  - User Availability : 통신에 참여할 수신 측의 통화 가능여부 결정.
  - User Capabilities : 통신간에 사용될 미디어 및 미디어 Parameter 결정
  - Session Setup : 수신 측 및 송신 측에 Session Parameter 생성
  - Session Management : Session의 종료, 전환, Session Parameter 변경, 부가 서비스 연동

- SIP 구성요소
  - UA (User Agent)
    - UA는 다른 UA와 직접 연결을 설정하거나 Proxy/Redirect Server들의 도움으로 다른 UA와 연결을 설정하며, 호 상태를 저장하고 관리
    - UAC (UA Client) : End System, SIP Request 전송, SIP Transaction 개시
    - UAS (UA Server) : End System, SIP Request accept, refuse, redirect
  - Proxy Server
    - UA로부터 수신한 접속 요청 메시지를 다른 도메인의 Proxy또는 Redirect Server로 전달하거나, 해당 도메인 내의 UA로 전달하는 기능 수행 및 과금을 위한 정보 유지
    - 종류
      - Stateless Proxy
        - Request를 받고, 위치를 확인한 이후 Request를 전송
        - Request를 받고 전송한 이후에는 아무런 정보가 남지 않음
        - Response를 받으면 Via를 이용하여 어디로 전송할 지를 판단
        - 메모리 need가 없고 Socket need가 작음
      - Stateful Proxy
        - 받아서 전송한 Request에 대한 정보를 기억 
        - Response가 돌아오면 기억한 정보를 이용해 추가적인 처리 가능
        - Dialog 상태 유지 
        - 서비스 제공에 더 적합
  - Redirect Server
    - 수신한 Request를 다른 UA나 Proxy Server에게 직접 전달하지 않고, Request를 보내 오 ㄴ해당 UA나 Proxy Server에게 그들이 Request를 재전송할 UA나 Proxy Serverㄹ의 주소를 알려주는 역할
  - Registrar
    - REGISTER request메시지를 수용하는 서버
    - 사용자의 현재 위치 등록, 수정, 삭제 및 현재 리스트를 보여주는 기능 수행
    - User Agent의 Registration 메시지를 받아서 DB에 저장
    - 가입자의 현재 위치를 파악하여 Routing에 사용

- SIP는 서비스를 제공하지 않고, 서비스를 구현하기 위해 사용될 Primitives(매개 변수)를 제공함. 예를 들면 "발신자 정보 표시 서비스"가 구현될 때, SIP가 서비스를 ㅈ공하는 것이 아니라 Primitives에 의해 단순히 SDP를 사용해 Session 정보를 전송만 함.
