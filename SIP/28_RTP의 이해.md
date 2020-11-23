## RTP의 개요

RTP는 Real-time Transport Protocol의 약어로 실시간 음성, 영상 및 데이터를 IP 네트워크로 전송하는 표준 프로토콜이다. RTP는 IETF RFC 3350 A Transport Protocol for Real-Time Applications 권고안에서 정의한다. RTP는 RTCP (Real-time Control Protocol)를 이용하여 데이터의 전달 상황을 감시 및 최소한의 제어 기능과 미디어 식별 등을 제공한다. RTCP의 사용은 옵션이므로 설정에 따라 사용할 수 있다.

## RTP의 전송 프로토콜

RTP는 전송 프로토콜로 UDP (User Datagram Protocol)과 네트워크 프로토콜로 IP를 이용한다. RTP가 신뢰할 수 있는 TCP를 이용하지 않고 UDP를 이용하는 이유는 실시간 음성 및 영상으 패킷 에러나 패킷 손실이 발생하더라도 TCP 재전송 메커니즘을 활용할 수 없기 때문이다. 재전송된 패킷은 수신 단말이 재생해야 할 시간을 이미 지나가 버린 이후가 될 확률이 높아 패킷을 폐기한다.

![IP/UDP/RTP 헤더 크기](./image/28_1.png)

