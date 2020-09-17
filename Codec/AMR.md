## AMR

음성부호화에 최적화된 특허가 있는 오디오 데이터 압축 코덱

AMR음성 코덱은 7.4kbps에서 시작하는 시외전화 품질 음성으로 4.75에서 12.2 kbps의 가변 비트레이트의 협대역 신호(200 - 3400 HZ)를 인코딩하는 다중 속도 협대역 음성 코덱으로 이루어진 음성 코덱이다.

AMR은 12.2, 10.2, 7.95, 7.40, 6.70, 5.90, 5.15, 4.75 kbit/s 비트레이트의 8가지 소스 코덱을 사용한다



## RTP Payload Format

![RTP HEADER according to RFC 3550](./image/AMR_RTP_Header.png)

- Version (V) : 2bits. RTP Version으로 2로 고정
- Padding (P) : 1bit. 설정된 경우 1개 이상의 추가 padding octests가 끝에 포함됨을 의미한다
- Extension (X) : 1bit. 설정된 경우 한 개의 header extension이 포함된다
- CSRC count (CC) : 4bits. CSRC identifier의 개수
- Maker bit (M): 1bit.
  - 1 : talkspurt의 첫번째 sppech frame을 표시 (다시말해, 첫 번째 SID 이 후의 음성의 시작을 의미)
  - 0 : 그 외의 모든  packet
- Payload type (PT) : 7bits. 96 ~ 127 사이의 Dynamic Range를 사용한다. (Nego 과정에서 값이 결정된다)

- Sequence number (SN) : 16bits. RFC3550(RTP)에 따르며, decoding 순서를 결정한다

- Timestamp : 32bits. Frame당 samples 단위로 증가한다. (AMR은 160, AMR-WB는 320 samples)
- SSRC : 32bits. Synchrosization Source Identifier (RTP session에서 source를 구분하는 고유 식별자)
- CSRC list : 0 to 15 items, 32bits each. 다수의 음원이 mixer로 통합될 때 원음의 식별자 리스트



## AMR In SDP

### 주요 Parameter

m=audio 10048 RTP/AVP 100 98

> "m=" attribute에 Media Type name과 payload type(PT)을 정의한다
>
> Media type : audio
>
> PT = AMR-WB:100, AMR:98 (96 ~ 127 의 Dynamic range에서 nego된다)

a=rtpmap:100 AMR-WB/16000/1

> "a=rtpmap:{PT}" attribute에 해당 payload type에 대한 Media subtype name, RTP clock rate, channels를 정의한다
>
> Media subtype name은 AMR or AMR-WB로 표기한다
>
> RTP clock rate는 AMR=8000, AMR-WB=16000이다
>
> Channles는 audio channel 개수로 1 ~6의 range를 갖는다 (default = 1)

a=ptime:20

a=maxptime:120

> option parameter중 ptime과 maxptime은 "a=" attribute로 별도 지정한다
>
> ptime: packet당 media가 나타내는 시간으로 20으로 고정되며, audio에서는 해석이 무의미하다
>
> maxptime: 하나의 packet에 포함할 수 있는 최대 media 시간 (ms). frame size의 정수 배로(SHOULD) 없는 경우 임의의 개수를 포함 가능하다.

a=fmtp:100 mode-set=8; octet-align=1

> "a=fmtp:{PT}" attribute에 해당 payload type에 대한 기타 optional parameter를 정의한다
>
> 각 parameter는 parameter=value 쌍으로 열거하며, semicolon으로 구분한다
>
> octet-align: operation mode로 각각의 방식에 따른 packet field 및 지원 기능이 달라진다
>
> - 0: badwidth-efficient mode
> - 1: octet-aligned mode
>
> mode-set: 허용하는 modeindication의 열거형으로 comma로 구분한다.
>
> ​	Mode-set이 없는 경우 모두 허용한다. Mode-indication 이 외의 Frame Type의 경우 SID, Speech lost, No Data만 사용할 수 있다 그 외 모든 패킷 수신 시 폐기한다

