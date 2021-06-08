## netstat

- 네트위크 연결상태, 라우팅 테이블, 인터페이스 상태등을 보여주는 명령어

**사용법**

- `$ netstat [option]`

**옵션**

| **옵션** | **설명**                                                |
| -------- | ------------------------------------------------------- |
| -a       | 모든 네트워크 상태 출력 [-all]                          |
| -c       | 현재 실행 명령을 매 초마다 실행 [--continuous]          |
| -e       | 확장된 정보 출력 [--extend]                             |
| -g       | 멀티캐스트에 대한 그룹별 정보 출력 [--groups]           |
| -i       | 인터페이스별 통계값 출력 [--interface]                  |
| -l       | 대기중인 네트워크 [--listening]                         |
| -n       | 도메인 주소를 숫자로 출력 [--numeric]                   |
| -o       | 연결 대기 시간 출력 [--timers]                          |
| -p       | PID(프로세서 ID)와 사용중인 프로그램명 출력 [--program] |
| -r       | 라우팅 테이블 출력 [--route]                            |
| -s       | 프로토콜 요약 정보 출력 [--statistics]                  |
| -t       | TCP 프로토콜만 출력 [--tcp]                             |
| -u       | UDP 프로토콜만 출력 [--udp]                             |
| -v       | 버전 출력                                               |
| -w       | RAW 프로토콜만 출력 [--raw]                             |
| -A       | 프로토콜별로 출력 [--protocol]                          |
| -M       | 마스커레이딩 정보 출력 [--masquerade]                   |



### 사용 예

**모든 네트워크 연결 확인하기**

- `$ netstat -a`
  - `-a`옵션을 리정하지 않으면 Established 상태인 것만 나온다.

**프로토콜 별로 출력하기**

- `$ netstat -at`
  - TCP만 확인
- `$ netstat -au`
  - UTP만 확인

**Listen 상태인 포트만 출력하기**

- `$ netstat -nap | grep LISTEN`
  - 접속중인 상태 : Established
  - 대기중인 상태 : Listen

