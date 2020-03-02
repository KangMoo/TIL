#### Sipsignal init

- sipFactory에서 인스턴스 받음
- new Properties
- setProprery
  - IP주소, stack이름, 디버그 로그, 서버 로그 등
- property를 토대로 sipStack 생성
- SipStack Create
- SipProvider Create
  - 이벤트 리스너 등의 
- Sipfactory Create



#### SIP INVITE

**Process Request**

- Request 확인 (INVITE의 경우..)
  - ProcessRequestInvite()
    - Inbound Setting ....
    - Outbound Invite Setting ()
      - Outgoing Setting .....
      - outgoing Process()



**Process Response**

- if(OK)
  - if(Request == INVITE)
    - Process Response OK()
      - Outbound Dialog 생성 + Send ACK
      - sdp 생성
      - outgoing Process Response OK ()
        - signal, dialog, respons, cseq 로 ackRequest생성
        - dailog.sendAck(ackRequest)
      - sendResponse()
- else if(BYE)
  - 미구현
- else
  - 그냥 로그만 남김







