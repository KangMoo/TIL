## SDP

#### **정의**
- Session Description Protocol. Media의 속성을 정의하고, 단말간의 Multimedia Session과 관련된 Parameter를 협상
  - 즉, 멀티미디어 전송을 위한 RTP 프로토콜에 대한 새부적인 내용 협상
- SDP는 현재 RFC 2327을 개정한 RFC 4566이 표준화되어 있으며, SIP뿐만 아니라 MGCP.MEGACO에서도 Multimedia Session을 설정.

#### **SDP Message**

- 단말들이 IMS망에서 Multimedia 통신을 위해서는 각자의 Media Capability르 ㄹ 상호 교환하여 협상해야 함
- SDP 는 SIP Message Body에 해당하며, 단말의 Media Capability와 기타 정보를 텍스트 기반으로 표현되어 있음
- SDP Message 구성은 아래와 같음
  - Session Level Part : "v=" 부터 Media level part 전까지
  - Medai Description : "m="부터 SDP 끝까지
- SDP 표현의 기본 형식은 다음과 같음
  - <type>=<value>
    - "="양쪽 모두 공백 사용 불가

** Session Level Part**
- Version
  - SDP 프로토콜의 버전을 의미 "v="부터 media level part 전까지 Session Level Part
  - 형식
    > `v=<protocol version>
    > - protocol version : SDP 프로토콜 버전 (0 버전만 존재하며, minor 버전 존재하지 않음)
    > - ex) `v=0`

- Origin
  - Username, Session-ID, Version, Network Type, Address Type, Unicast Address 등의 기본 정보
  - 형식
    > `o=<username> <sess id> <sess-version> <nettype> <unicast-address>
    > - username : username or '-'로 표시
    > - sess id : 단말에서 랜덤으로 생성하는 고유 식별 숫자
    > - sess-version : SDP Version이 아닌 단말에서 랜덤으로 생성하는 고유 식별 숫자
    > - nettype : 현재 정의되어 있는 것은 INTERNET(IN).
    > - addrtype : IP4 or IP6
    > - unicast-address : Global Unique Address
    > - ex) `o=LGU+IBCF 1355236520 1391439347 IN IP4 203.83.243.197` 

- Session Name
  - Session을 나타내는 이름을 의미
  - 형식
    > `s=<session name>`
    > - session name : Session에 의미있는 이름이 없는 경우 "s= "이라도 반드시 사용 (비어 있으면 안됨)
    > - ex) `s=-`

- Session Information
  - Session에 관한 Information을 의미
  - 형식
    > `i=<session description>`
    > - session description : text기반으로 Session에 대한 정보 제공
    > - ex) `i=A VT Session`

- Connection Information
  - Session Level Part 또는 Media Level Part에 해당 필드가 있어야 함
  - 모두 있을 경우 Media Level Part의 Connection 정보가 우선시
  - 형식
    > `c=<nettype> <addrtype> <connection-address>`
    > - Nettype : INTERNET(IN)
    > - Addrtype : IP4 or IP6
    > - connection-address : Connection address
    > - ex) `c=IN IP4 203.83.243.197`

- Timing
  - Session에 대한 start-time과 stop-time 정보
  - 형식
    > `t=<start-time> <stop-time>
    > - 전화통화 같은 경우 start-time, stop-time 둘 다 0ㅇ로 설정. (즉시 시작해서 언제 끝날 줄 모름)
    > - ex) `t= 0 0`
    > - ex) `t= 3073397496 3073404696`


** Media Level Part**
- Media Announcements
  - media, port, proto, fmt 정보 "m="부터 SDP의 끝까지 Media Level Part
  - 형식
    > `m=<media> <port> <proto> <fmt>
    > - media : media 유형 (audio, video, text, application, message)
    > - port : media를 전송하기 위한 port
    > - proto : transport protocol (UDP, RTP/AVP, RTP/SAVP) (SAVP에서 S는 암호화를 의미함)
    > - fmt : media format (Payload Type) 리스트
    > - ex) `m=audio 35906 RTP/AVP 100 96`
    > - ex) `m=video 50298 RTP/AVP 104 103 105 102 34`

- Bandwidth
  - 단말이 최대 지원할 수 있는 대역폭을 표시
  - 이 Bandwidth는 Session Level과 Media Level에 존재할 수 있음
  - Session level에서의 Bandwidth는 Session 전체에 대한 요구 대역폭을 정의
  - Media level에서의 Bandwidth는 각 Media채널 별 요구 대역폭을 정의
  - 형식
    > `b=<bwtype>:<bandwidth>
    > - bwtype의 종류
    >   - TIAS
    >     - Transport Independent Application Specific으로 bit/sec로 표시
    >     - Transport Layer의 오버헤드를 고려하지 않은 대역폭으로 RTP만의 대역폭 의미
    >   - AS
    >     - Application Specific Bandwidth로 kbps로 표시
    >     - TCP/UDP와 같은 Transport Layer의 오버헤드를 고려한 대역폭으로 RTP/UDP/IP의 대역폭
    >   - CT
    >     - Conference Total로 kbps로 표시
    >     - 다자간 회의 Session을 사용할 경우 최대 대역폭
    >   - RS : RTCP for Senders로 bit/sec로 표시 
    >   - RR : RTCP for Receivers로 bit/sec로 표시
    > - ex) `b=AS:41`
    > - ex) `b=RS:0`
    > - ex) `b=RR:1000` 
- Attributes
  - 각 Medai에 해당하는 Attributes를 말한다. 여러 Attributes가 존재한다
  - 형식
    > `a=<attribute>`
    > `a=<attribute>:<value>`


**예시**
```
v=0
o=alice 2890844526 2890844526 IN IP4 atlanta.com
s=
c=IN IP4 10.1.3.33
t=0 0
m=audio 49172 RTP/AVP 0
a=rtpmap:0 PCMU/8000
```


- v=0 (필수)
  - SDP 프로토콜의 버전을 표시한다. SDP 버전은 0이다.
- o=alice 2890844526 2890844526 IN IP4 atlanta.com (필수)
  - SDP 메시지를 생성한 Owner/Creator 를 표시한다. 순서대로 Username, Session-ID, Session Version, Network Type, Address Type, Unicast Address 를 표시한다.
- s= (필수)
  - 세션 이름을 표시한다.
- i=(optional)
  - Session information.
- u=(optional)
  - URI of description.
- e=(optional)
  - Email address - contact detail.
- p=(optional)
  - Phone number - contact detail.
- c=IN IP4 10.1.3.33 (optional)
  - 순서대로 Network Type, Address Type, Connection-Address 를 나타내며 미디어의 주소를 정의한다.
- t=0 0 (필수)
  - Timing 으로 Start-time과 End-Time을 표시한다. 0 0 은 고정 세션을 의미한다.


#### **Offer/Answer Model**

- Offer/Answer Model은 기존 전화망에서 사용하던 기술인데 이를 활용해 SIP에 적용함.
- Offer와 Answer의 이미
  - Offer : 호 순서와 관계 없이 Session협상을 위해 상대방에게 먼저 SDP Message를 전송한 경우
  - Answer : Offer를 받은 UA에서 그에 대한 응답으로 SDP Message를 전송한 경우
- with, without의 이미는 SDP Message의 포함 여부를 뜻함
- 주의할 점으로 Offer/Answer의 Case는 시나리오에 따라 다양하게 달라지며 경우에 맞는 Offer/Answer의 처리가 이루어져야 함.
- SIP는 Offer/Answer를 통해 SDP에 포된 Media Capability를 교환하고 , 협상을 통해 RTP가 개방될 수 있도록 함
