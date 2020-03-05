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



## EventLoop

- 용도 : 제어흐름, 멀티스레딩, 동시성 제어
- 하나 이상의 Channel에 할당 가능
- 등록된 `Channel`들의 모든 I/O작업을 처리
- 구현체 `NioEventLoopGroup`를 주로 사용



## ChannelFuture

- 용도 : 비동기 알림
- 미래에 실행될 작업의 결과를 위한 자리표시자
- `ChannelFutureListener`인스턴스를 하나 이상 등록할 수 있는 추가 메서드가 있다. 작업이 완료되면 리스너의 콜백 메서드인 `operationComplete()`이 호출되며, 이 시점에 리스너는 작업이 정상적으로 완료됐는지, 아니면 오류가 발생했는지 알 수 있다
- 네티의 모든 아웃바운드 입출력 작업은 `ChannelFuture`를 반환하며 진행을 블록킹하는 작업은 없다.



## ChannelPipeline

- 하나 이상의 `ChannelHandler`를 포함
- 인바운드와 아웃바운드 핸들러를 동일한 파이프라인에 설치할 수 있다
- `Channel`에 드나드는 inbound/outbound이벤트를 처리
- Intercepting Filter 패턴 처리, `ChannelHandler`리스트



## ChannelHandler

- 인바운드와 아웃바운드 데이터의 처리에 적용되는 모든 어플리케이션의 비즈니스 논리의 컨테이너 역할을 하는 컴포넌트
- 크게 `ChannelInboundHandler`, `ChannelOutboundHandler`로 나뉨
- 4가지 이벤트 유형 포함



## ChannelHandlerContext

- 이벤트를 현재 체인의 다음 행들러로 전달할 수 있다.
- `ChannelHandler`는 `ChannelHandlerContext`를 통해 다음 `ChannelHandler`에게 이
  벤트를 넘기거나 동적으로`ChannelPipeline`을 변경할 수 있음



## EventLoopGroup

- 하나 이상의 EventLoop를 포함



## Adapter

- 해당 인터페이스에 정의된 모든 메서드의 기본 구현을 제공



## Bootstrap

- 프로세스를 지정된 포트로 바인딩하거나 프로세스를 지정된 호스트의 지정된 포트에서 실행중인 다른 호스트로 연결하는 등의 일을 하는 어플리케이션 네트워크 레이어를 구성하는 컨테이너
- `ServerBootstrap` : 프로스세르르 지정된 포트로 바인딩하는 어플리케이션의 네트워크 레이어를 구성. 들어오는 연결을 수신하는 동작
- `Bootstrap` : 프로세스를 지정된 호스트의 지정된 포트에서 실행 중인 다른 호스트로 연결. 하나 이상의 포르세스로 연결하는 동작

|                     | Bootstrap                 | ServerBootstrap    |
| ------------------- | ------------------------- | ------------------ |
| 네트워크 기능       | 원격 호스트와 포트로 연결 | 로컬 포트로 바인딩 |
| EventLoopGroup의 수 | 1                         | 2                  |





## wrappedBuffer(), copiedBuffer()

- Unpooled.wrappedBuffer() : 새 복합 버퍼(composite buffer)를 생성하며, 인자로 받은 content를 실제 복사하지는 않는다. 지정한 데이터를 래핑하는 ByteBuf 반환.

- Unpooled.copiedBuffer() : 새 버퍼를 생성하여 인자로 받은 content를 복사한다. 지정한 데이터를 복사하는 ByteBuf 반환.



## 어플리케이션을 위한 최적의 전송

| **애플리케이션의 요건**               | **권장되는 전송**             |
| ------------------------------------- | ----------------------------- |
| 논블로킹 코드 기반 또는 일반적 출발점 | NIO(또는 리눅스의 경우 epoll) |
| 블로킹 코드 기반                      | OIO                           |
| 동일한 JVM 내의 통신                  | 로컬(Local)                   |
| ChannelHandler 구현 테스트            | 임베디드(Embeded)             |



## 전송 네트워크와 프로토콜 지원

| **전송**          | **TCP** | **UDP** | **SCTP****(Stream Control Trasmission Protocol)** | **UDT** |
| ----------------- | ------- | ------- | ------------------------------------------------- | ------- |
| NIO               | O       | O       | O                                                 | O       |
| Epol(리눅스 전용) | O       | O       | X                                                 | X       |
| OIO               | O       | O       | O                                                 | O       |

