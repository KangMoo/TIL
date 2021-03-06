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
    > `v=<protocol version>`
    >
    > - protocol version : SDP 프로토콜 버전 (0 버전만 존재하며, minor 버전 존재하지 않음)
  > - ex) `v=0`
  
- Origin
  - Username, Session-ID, Version, Network Type, Address Type, Unicast Address 등의 기본 정보
  - 형식
    > `o=<username> <sess id> <sess-version> <nettype> <addrtype> <unicast-address>`
    >
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
    > `t=<start-time> <stop-time>`
    >
    > - 전화통화 같은 경우 start-time, stop-time 둘 다 0ㅇ로 설정. (즉시 시작해서 언제 끝날 줄 모름)
    > - ex) `t= 0 0`
    > - ex) `t= 3073397496 3073404696`


** Media Level Part**
- Media Announcements
  - media, port, proto, fmt 정보 "m="부터 SDP의 끝까지 Media Level Part
  - 형식
    > `m=<media> <port> <protocol> <format>`
    >
    > - media : media 유형. RTP 프로토콜의 페이로드가 무엇인지를 선언
    >   - audio, video, text, application, message
    > - port : media를 전송하기 위한 port.
    > - protocol : transport protocol (SAVP에서 S는 암호화를 의미함), (AVP는 Audio Video Profile의 약자)
    >   - UDP, RTP/AVP, RTP/SAVP
  > - format : media format (Payload Type) 리스트. 미디어의 포맷을 서브 필드 (a=)로 표시함을 의미.
    >   - Payload Type 0 8 18의 순서는 코덱 협상의 우선순위를 표시.
    >   - Payload Type 101은 DTMF이벤트를 정의
    > - ex) `m=audio 35906 RTP/AVP 100 96`
    > - ex) `m=video 50298 RTP/AVP 104 103 105 102 34`
  
- Bandwidth
  - 단말이 최대 지원할 수 있는 대역폭을 표시
  - 이 Bandwidth는 Session Level과 Media Level에 존재할 수 있음
  - Session level에서의 Bandwidth는 Session 전체에 대한 요구 대역폭을 정의
  - Media level에서의 Bandwidth는 각 Media채널 별 요구 대역폭을 정의
  - 형식
    > `b=<bwtype>:<bandwidth>`
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
- Attributes 별 사용법
  - Media Direction 형식
    > `a=<Media Direction>`
    > - `a=recvonly` : 단말은 수신만을 할 수 있으며, 송신하지 않음
    > - `a=sendonly` : 단말은 송신만을 할 수 있으며, 수신하지 않음
    > - `a=sendrecv` : 단말은 Media를 송신 및 수신할 수 있음
    > - `a=inactive` : 단말은 송신 및 수신을 할 수 없음 (Hold 버튼 누른 경우)
    > - ex) `a=sendrecv`
  - framerate 형식
    > `a=framerate:<frame rate>`
    > - Video의 Max Framerate를 나타나며, frame/sec로 펴시
    > - ex) `a=framerate:15`  
  - ptime 형식
    > `a=ptime:<packet time>
    > - packet time : 한 패킷에 있는 Media에 대한 시간 길이를 ms단위로 표현
    > - ex) `a=ptime:20`
  - rtpmap 형식
    > `a=rtpmap:<payload type> <encoding name>/<clock rate>/[encoding parameters]` 
    > - payload type : m필드의 fmt Payload 값
    > - encoding name : 해당 payload의 encoding name
    > - clock rate : 해당 payload의 clockf rate
    > - encoding parameters : 해당 payload의 encoding parameters로 생략 가능
    > - ex) `a=rtpmap:101 telephone-event/8000` 
  - fmtp 형식
    > `a=fmtp:<format> <format specific parameters>`
    > - format : 해당하는 이벤트 타입의 payload type
    > - format specific parameters : SDP가 이해하지 못하는 방향으로 전달될 수 있다는 것을 표시
    > - ex) `a=fmtp:101 0-11`
    >   - 0~11까지 작동할 수 있다는 것을 말해줌. 0~9, *, #


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
- b=(zero or more)

  - bandwidth 정보
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





### SDP

**Session description**
v=  (protocol version)
o=  (originator and session identifier)
s=  (session name)
i=* (session information)
u=* (URI of description)
e=* (email address)
p=* (phone number)
c=* (connection information -- not required if included in all media)
b=* (zero or more bandwidth information lines)
One or more time descriptions ("t=" and "r=" lines; see below)
z=* (time zone adjustments)
k=* (encryption key)
a=* (zero or more session attribute lines)
Zero or more media descriptions

**Time description**
t=  (time the session is active)
r=* (zero or more repeat times)

**Media description, if present**
m=  (media name and transport address)
i=* (media title)
c=* (connection information -- optional if included at session level)
b=* (zero or more bandwidth information lines)
k=* (encryption key)
a=* (zero or more media attribute lines)



## 예시

```
v=0
o=alice 2890844526 2890844526 IN IP4 atlanta.com
s=
c=IN IP4 10.1.3.33
t=0 0
m=audio 49172 RTP/AVP 0
a=rtpmap:0 PCMU/8000
```

>**v=0 (필수)**
>SDP 프로토콜의 버전을 표시한다. SDP 버전은 0이다.
>**o=alice 2890844526 2890844526 IN IP4 atlanta.com (필수)**
>SDP 메시지를 생성한 Owner/Creator 를 표시한다. 순서대로 Username, Session-ID, Session Version, Network Type, Address Type, Unicast Address 를 표시한다.
>**s= (필수)**
>세션 이름을 표시한다.
>**i=(optional)**
>Session information.
>**u=(optional)**
>URI of description.
>**e=(optional)**
>Email address - contact detail.
>**p=(optional)**
>Phone number - contact detail.
>**c=IN IP4 10.1.3.33 (optional)**
>순서대로 Network Type, Address Type, Connection-Address 를 나타내며 미디어의 주소를 정의한다.
>**t=0 0 (필수)**
>Timing 으로 Start-time과 End-Time을 표시한다. 0 0 은 고정 세션을 의미한다.

```
m=audio 16444 RTP/AVP 0 8 18 101
a=rtpmap:0 PCMU/8000
a=ptime:20
a=rtpmap:8 PCMA/8000
a=ptime:20
a=rtpmap:18 G729/8000
a=ptime:20
a=sendrecv
a=rtpmap:101 telephone-event/8000
a=fmtp:101 0-15 
```

>**m= (미디어 설명)**
>Media Description으로 Media, Port, Protocol, Format 을 정의한다.
>Media : audio, video, text, application, message 로 표시.
>Port : 미디어가 전송될 전송포트(Transport port) 표시.
>Protocol : UDP, RTP/AVP, RTP/SAVP로 표시하며 AVP는 Audio Video Profile의 약자이다.
>Format : 미디어의 포멧을 서브필드 (a=)로 표시함을 의미.
>Payload Type 0 8 18 의 순서는 코덱 협상의 우선순위를 나타내며, Payload Type 101은 DTMF 이벤트를 정의한다. 각 포멧에 대한 상세 설명은 a= 에 표시된다.
>**a= (미디어 속성)**
>미디어 속성(attributer)을 정의한다.
>a=rtpmap : Payload type, encoding name/clock rate 를 표시
>a=ptime : Packet time으로 미디어 패킷 한 개가 포함된 시간 정보로 ms로 표시 (보통 20ms).
>a=fmtp : 미디어 포멧에 대한 파라미터를 정의
>**a= (미디어의 방향)**
>미디어 속성 뿐만 아니라 미디어 방향도 표시한다. 미디어의 방향은 아래와 같이 4가지로 나타낸다.
>a=sendrecv : 단말은 미디어를 송신 및 수신할 수 있음.
>a=recvonly : 단말은 수신만을 할 수 있으며 송신하지 않음.
>a=sendonly : 단말은 송신만을 할 수 있으며 수신하지 않음.
>a=inactive : 단말은 송신 및 수신을 할 수 없음 (Hold 버튼을 누를 경우)
>별도의 언급이 없을 경우에는 a=sendrecv 로 설정되었다고 가정한다. 미디어의 방향은 다양한 부가 서비스 구현시 유용하다.
>**a= (DTMF 협상)**
>DTMF 전달에 관한 협상도 진행한다.
>a=rtpmap:101 telephone-event.8000 : RFC 2833에 의한 In-band DTMF를 의미.
>a=fmtp 101 0-15 : DTMF Tone 은 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 0, *, #, A, B, C, D 총 15 가지를 송수신 함.





