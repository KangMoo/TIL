## STUN

- Session Traversal Utilities for NAT
- IETF RFC 5389에 정의된 네트워크 프로토콜/패킷 포맷
- **Public 관점에서 종단에 Access 가능한 IP:Port를 발견하는 작업**
- 대부분의 WebRTC 호출은 STUN을 이용한 연결을 성공적으로 만들어낸다

#### STUN의 역할

- 어떤 종단이 NAT/Firewall 뒤에 있는지 판단하게 해준다.
- 어떤 종단에 대한 Public IP Address를 결정하고 NAT/Firewall의 유형에 대해 알려준다.

![STUN](C:/Users/허강무/TIL/WebRTC/images/02_1.png)



#### STUN 동작

![STUN 동작](C:/Users/허강무/TIL/WebRTC/images/02_2.png)

#### STUN의 한계

- 두 단말이 같은 NAT 환경에 있을 경우 STUN은 동작하지 않는다.
- Symmetric NAT로 동작하는 사설망 환경에서는 애플리케이션이 다르면 NAT 매핑 테이블이 바뀌기 때문에 사용할 수 없다.
- STUN 메시지로 확인한 STUN 클라이언트의 Reflexive Transport Address가 다른 애플리케이션인 SIP 시그널링과 RTP 프로토콜을 사용할 때는 주소가 바뀐다.



> **Symmetric NAT**
>
> NAT에는 Cone NAT과 Symmetric NAT가 있다. Cone NAT과 Symmetric NAT의 다른 점은 도착지 주소가 mapping에 주요한지 아닌지에 따라 달라진다. Cone NAT는 internal ip & port 만이 mapping에 주요하나, Symmetric NAT은 destination 주소가 다르면 다른 mapping table에 적힌다.
>
> - Cone NAT에서는 특정 PC의 내부의 IP:Port가 외부 IP:Port로 변환될 때 Destination에 상관 없이 외부 Port가 항상 일정하다.
>
> ![Cone NAT](C:/Users/허강무/TIL/WebRTC/images/02_3.png)
>
> - Symmetric NAT은 특정 PC의 내부의 IP:Port가 외부 IP:Port로 변환될 때 Destination에 따라 다른 외부 Port가 사용된다. 즉 peer들이 오직 이전에 연결한 적 있는 연결들만 허용한다
>
> ![Symmetric NAT](C:/Users/허강무/TIL/WebRTC/images/02_4.png)

