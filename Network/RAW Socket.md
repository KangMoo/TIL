## RAW Socket

- **어느 특정한 프로토콜의 용의 전송 계층 포매팅 없이 인터넷 프로토콜 패킷을 직접적으로 주고 받게 해주는 소켓**
- **헤더 정보들에 대해 프로그래머가 직접 제어할 수 있게 해줌**
  - 새로운 프로토콜을 사용하거나, 헤더의 정보를 이용하여 보안 프로그램과 같은 것을 구현하는 경우엔 ㄴ헤더를 직접 제어해줘야 하는데 이 때 유용함.
  - 즉 RAW소켓은 IP헤더와 TCP헤더를 직접 제어할 수 있다

#### RAW Socket의 특징

- 응용 계층과 전송 계층, 네트워크 계층에서 모두 접근이 가능
- 네트워크 계층 헤더와 전송 계층 헤더를 직접 제어 가능
- 네트워크 계층으로 전송되는 모든 패킷을 전부 모니터링 및 감지 가능
- IP Secgment에서 암호화된 악송코드가 있는 경우, IP계츠엥서 이를 필터링 못해 문제가 발생할 수 있다

#### 그 외
- RAW 소켓을 구현하기 위해서는 TCP/IP스택을 제어해야 하는데, TCP/IP스택은 커널에서 제공하므로 커널에 접근이 가능한 root계정으로만 RAW소켓의 생성이 가능하다
- 스니핑 기법
  - RAW소켓을 사용하는 경우 네트워크 계층으로 오는 모든 패킷을 모니터링 및 감지 가능하다
  - 스니핑 기법으로 구현된 대표적인 프로그래밈이 '와이어샤크'