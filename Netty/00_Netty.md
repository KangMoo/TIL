## Netty

- 비동기시 이벤트 기반의 네트워크 어플리케이션 프레임워크
- 모든 구현이 Channel, ChannelPipeline, ChannerHandler 인터페이스를 기준으로 정의된다.
- 네티가 제공하는 전송
  - NIO : 논블록킹 입출력. selector 기반 방식
  - Epoll : 논블록킹 입출력. 리눅스에서만 이용. NIO전송보다 빠르고 완번한 논블록킹
  - OIO : 블록킹 스트림 이용
  - 로컬(Local) : VM에서 파이프를 통해 통신하는 데 이용
  - 임베디드(Embeded) : 실제 네트워크 기반 전송 없이 ChannelHandler를 이용할 수 있게 해줌. ChannelHandler 구현을 테스트하는데 유용



## Selector 클래스

- 자바의 논블록킹 입출력 구현의 핵심으로서, 논블록킹 Socket의 집합에서 입출력에서 입출력이 가능한 항목을 지정하기 위해 이벤트 통지 API를 이용한다
- 적은 수의 스레드로 더 많은 연결을 처리할 수 있으므로 메모리 관리와 컨텍스트 전환에 따르는 오버헤드가 감소한다
  - 적은 수의 스레드로 여러 연결에서 이벤트를 모니터링 할 수 있게 해준다
- 입출력을 처리하지 않을 때ㅣ는 스레드를 다른 작업에 활용할 수 있다



## Channel

- 자바 NIO(비동기 입출력 방식)의 기본 구조
- 정의 : 하나 이상의 입출력 작업을 수행할 수 있는 하드웨어 장치, 파일, 네트워크 소켓, 프로그램 컴포넌트와 같은 엔티티에 대한 열린 연결
- Socket에 해당
- Channel의 메서드
  - eventLoop()
  - pipeline()
  - isActive()
  - localAddress()
  - remoteAddress()
  - write()
  - flush()
  - writeAndFlush()



## EvnetLoop

- 용도 : 제어흐름, 멀티스레딩, 동시성 제어
- 하나 이상의 Channerl에 할당 가능

