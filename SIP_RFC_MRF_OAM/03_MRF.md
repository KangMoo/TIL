## MRF

#### 개요
- MRF는 어플리케이션 타입에 상관없이 각 IMS 어플리케이션 서버와 I/S-CSCF에 미디어의 수평적 처리를 제공한다
- MRF는 IMS Application Server 통제 하에서 CSCF에 멀티미디어 트랜스코딩, 멀티파티 멀티미디어 컨퍼런스, 네트워크 어나운스먼트/톤, 인터랙티브 보이스/비디오 서비스 등은 물론 기본적인 미디어 처리기능을 제공한다.
- MRF컨트롤 단은 3GPP Mr, Mr' 인터페이스로 정의되어 있고, RTP/RTCP 멀티미디어 전송을 위한 MRF 미디어 단은 3GPP Mb 인터페이스로 정의되어 있다.

- MRFC(Controller)와 MRFP(Processor)의 구성
  - MRFC (Media Resource Function Controller)
    - SIP시그널/세션 컨트롤
    - MRFP 먼트롤(MEGACO로 정보 연결)
  - MRFP (Media Resource Function Processor)
    - 서비스와 미디어 처리
    - 멀티 파티, 안내방송 재생 등
    - 컨퍼런스 콜, 오디오 트랜스코딩 등

#### MRF 솔루션의 주요 기능
- SIM/IMS 호 처리
  - IMS 망 연동 기능
  - AS 미디어 제어 : MSCML, MGCP AAP, Netann
- MEGACO 처리기능
  - MRFC의 MRFP 제어
- 톤,안내방송 기능
  - 고정형/편집형/주문형 안내방송
  - 미디어 녹음 및 재생
  - 다양한 Tone 생성
  - 다양한 범용 파일포맷 지원 : PCM, WAV, AVI, 3GP, MP4, ASF, 3G2, SRF
- DTMF 기능
  - 안내방송 중 DTMF에 대한 Barge-in, Non-Barge-in 기능
  - SIP-INFO 메시지를 이용한 DTMF 수집 및 생성
  - RFC 2833을 이용한 DTMF 수집 및 생성
  - In-band DTMF 수집 및 생성
- 미디어 Relay/Trancoding
  - 두 단말간 미디어 Bearer연결 기능
  - 두 단말간 코덱이 다를 경우 코덱 변환 기능
- 회의 통화 기능
  - 4자 이상 회의 통화
  - 화면 분할 기능
  - 회의통화 시 Transcoding 기능
  - 참여자 위치 고정 기능
- 코덱 지원(오디오)
  - AMR-NB, AMR-WB, EVRC, G.723.1, G711
- 코덱 지원(비디오)
  - H.264, MPEG4, H.263
  - VGA (640* 480), QVGA, CIF, QCIF
  - 최대 30FPS
- 음성 합성
  - TTS
  - 확장 완성형(통합 완성형) 한글/영어 코드 지원
  - 숫자, 기호, 외래어 등에 대한 문장 전처리 기능
- 음성 인식
  - 화자 독립 기능
  - 안내방송 중 음성 인식 기능 제공
  - 음성인식 중 DTMF 처리기능

#### 지원기능
- 톤 재생
- 안내방송 재생
- 멀티미디어 재생
- 오디오 녹음
- 멀티미디어 녹화
- DTMF 수집
- 음성 회의
- 멀티미디어 회의
- 음성 트랜스코딩
- 영상 트랜스코딩

## 컨퍼런스 콜

- 정의
  - 3인 이상이 전화로 회의하는 것을 의미함

- 음성 컨퍼런스 : 최대 8명의 사용자가 하나의 컨퍼런스 룸에 참여할 수 있다.
- 멀티미디어 컨퍼런스 : 최대 8명의 사용자의 하나의 컨퍼런스 룸에 참여할 수 있다.

## OAM

#### OAM이란
- Operation Administration and Management
- 시스템이 운영, 관리, 유지보수를 포함한 프로세스, 활동, 도구, 표준이라고 정의할 수 있음

- OAM 서버
  - 성능 관리
  - 오류 관리
  - 추적 관리
- OAM 클라이언트
  - 각 서버의 상태 표시
  - 알람 통보
  - 구성 관리


