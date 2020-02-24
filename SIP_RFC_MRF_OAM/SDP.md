## SDP

#### **정의**
- Session Description Protocol. Media의 속성을 정의하고, 단말간의 Multimedia Session과 관련된 Parameter를 협상
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
- Origin
  - Username, Sessio-ID, Version, Network Type, Address Type, Unicast Address 등의 기본 정보
- Session Name
  - Session을 나타내는 이름을 의미
- Session Information
  - Session에 관한 Information을 의미
- Connection Information
  - Session Level Part 또는 Media Level Part에 해당 필드가 있어야 함
  - 모두 있을 경우 Media Level Part의 Connection 정보가 우선시
- Timing
  - Session에 대한 start-time과 stop-time 정보

** Media Level Part**
- Media Announcements
  - media, port, proto, fmt 정보 "m="부터 SDP의 끝까지 Media Level Part
- Bandwidth
  - 단말이 최대 지원할 수 있는 대역폭을 표시
  - 이 Bandwidth는 Session Level과 Media Level에 존재할 수 있음
  - Session level에서의 Bandwidth는 Session 전체에 대한 요구 대역폭을 정의
  - Media level에서의 Bandwidth는 각 Media채널 별 요구 대역폭을 정의
- Attributes
  - 각 Medai에 해당하는 Attributes를 말한다. 여러 Attributes가 존재한다
  - 형식
    > `a=<attribute>`
    > `a=<attribute>:<value>`

#### **Offer/Answer Model**

- SIP는 Offer/Answer를 통해 SDP에 포함된 Media Capability를 교환하고 , 협상을 통해 RTP가 개방될 수 있도록 함
