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
  - Redirect Server
  - Registrar
