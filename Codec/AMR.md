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

