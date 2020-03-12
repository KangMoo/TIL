## RTP 개요
- RTP는 Real-time Transport Protocol의 약어로 실시간 음성, 영상 및 데이터를 IP네
    트워크로 전송하는 표준 프로토콜이다
- RTP는 IETF RFC 3350 A Transport Ptorocol for Real-Time Application권고안에서
    정의
- RTP는 RTCP(Real-time Control Protocol)를 이용하여 데이터의 전달 상황을 감시 및
    최소한의 제어 기능과 미디어 식별 등을 제공
- RTCP의 사용은 옵션



## RTP의 전송 프로토콜
- RTP는 전송 프로토콜로 UDP와 네트워크 프로토콜로 IP를 이용한다
- RTP가 신뢰할 수 있는 TCP를 이용하지 않고 UDP를 이용하는 이유는, 실시간 음성 및
    영상은 패킷 에러나 손실이 발생하더라도 TCP재전송 메커니즘을 활용할 수 없기
    때문
- 재전송된 패킷은 수신 단말이 재생해야 할 시점을 이미 지나가 버린 이후가 될 확률
    이 높아 패킷을 폐기
- 오버헤드는 40바이트로 IP(20 바이트), UDP(8바이트), RTP(12바이트)이다


## RTP 헤더
- V (version) : 2bit
  - RTP의 Version표시 (현재 버전은 2)
- P (padding) : 1bit
  - 패킷의 마지막 부분에 하나 이상의 패딩 바이트 무 표시
  - 패딩 비트는 의미가 없는 비트로 헤더나 패킷의 크기를 일정하게 유지하기 위해서
      사용하는 비트
- X (Extension) : 1bit
  - 고정 헤더 이후의 하나 이상의 확장 헤더 유무 표시
- CC (CSRC Count) : 4bit
  - RTP 12바이트 고정 헤더 뒤에 CSRC identifier의 수 표시
- M (Marker) : 1bit
  - 패킷 내에서 프레임 경계와 같은 중요한 이벤트들을 표시
  - Payload Type필드의 확장을 위해 무시되기도 함
- PT (Payload Type) : 7bit
  - 페이로드의 타입은 RTP가 전송하고 있는 실시간 데이터의 압축 코덱을 명시
  - 페이로드 타입은 Capability Exchange 협상에서 상호 인지 필수
- Sequence number : 16bit
  - 보안을 이유로 랜덤 번호에서 시작하고 패킷이 늘어날 때마다 1씩 증가
  - 수신 측이 패킷 손실 여부 확인 가능
- Timestamp : 32bit
  - RTP 패킷의 첫 번째 바이트의 샘플링 순간을 표시
  - 초기값은 랜덤 넘버로 결저오디지만 샘플링 레이트에 따라 증가량은 상이
- SSRC (Synchronized Source) Idendifier : 32bit
  - 동기화 소스로 랜덤 넘버로 결정
- CSRC (Contributing Source) Identifiers : 32bit
  - 다수의 음원이 Audio Mixer를 통해 하나로 통합될 경우 원래 음원의 목록을 표시



