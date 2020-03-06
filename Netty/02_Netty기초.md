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
- 입출력을 처리하지 않을 때는 스레드를 다른 작업에 활용할 수 있다



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
- `ServerBootstrap` : 프로세스로 지정된 포트로 바인딩하는 어플리케이션의 네트워크 레이어를 구성. 들어오는 연결을 수신하는 동작
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



## ByteBuffer

- 자바 NIO의 데이터 컨테이너
- 사용법이 복잡하다



## ByteBuf

- 네티의 데이터 컨테이너
- 읽기와 쓰기를 위한 고유한 두 인덱스를 제공한다. ByteBuf에서 데이터를 읽으면 읽은 바이트 수만큼 readerIndex가 증가하고, ByteBuf에 데이터를 기록하면 기록한 수만큼 writeIndex가 증가한다
- 기본 읽기와 쓰기 작업 외에도 데이터를 수정하는 데 이용할 수 있는 다양한 메서드를 제공한다
- ByteBuf는 readerIndex 위치, writerIndex위치에 따라 "폐기할 수 있는 바이트 영역", "읽을 수 있는 바이트 영역", "기록할 수 있는 바이트 영역"으로 분할될 수 있다
- `discardReadByte()`를 호출하면 readerIndex는 0이되고, writerIndex는 감소하며, "폐기할 수 있는 바이트 영역"은 폐기 및 회수되고, "읽을 수 있는 바이트 영역"은 버퍼의 시작 부분으로 옮겨지며(옮겨지기 위해 메모리 복사 필요), "기록할 수 있는 바이트 영역"은 확장된다.
- `clear()`를 호출하면 readerIndex와 writerIndex는 0이되고 메모리의 내용은 지우지 않으며, 전체 영역이 "기록할 수 있는 바이트 영역"이 된다. `clear()`는 인덱스만 재설정하므로 `discardReadBytes()`에 비해 실행 비용이 훨씬 낮다
- 읽기/쓰기 작업은 두가지 범주가 있다
  - `get()` & `set()` 지정한 인덱스에서 시작하며 인덱스를 변경하지 않는다
  - `read()` & `write()` 지정한 인덱스에서 시작하며 접근한 수 만큼 인덱스를 증가시킨다



#### ByteBuf사용 패턴

- heap buffer (힙 버퍼)
  - ByteBuf에 보조 배열이 있으면 힙 버퍼
  - JVM에 힙 공간에 데이터를 저장
  - 이 패턴은 풀링이 사용되지 않는 경우 빠른 할당과 해제 속도를 보여준다
  - 레거시 데이터를 처리하는 경우에 적합하다
- direct buffer (다이렉트 버퍼)
  - ByteBuf에 보조 배열이 없으면 다이렉트 버퍼
  - 다이렉트 버퍼의 내용은 일반적인 가비지 컬렉션이 적용되는 힙 바깥에 위치한다. (다이렉트 버퍼가 네트워크 전송에 이상적이기 때문)
  - 데이터 힙할당 버퍼에 있는 경우에는 JVM으 소켓을 통해 전송하기 전에 내부적으로 버퍼를 다이렉트 버퍼로 복사해야 한다
  - 힙 기반 버퍼보다 할당과 해제의 비용 부담이 약간 더 크다
  - 레거시 데이터를  이용하는 경우에는 데이터가 힙에 있지 않기 때문에 복사본을 만들어야 할 수 있다
- complex buffer (복합 버퍼)
  - 여러 ByteBuf의 집합적 뷰에 해당
  - 네티는 여러 버퍼가 병합된 가상의 단일 버퍼를 제공하는 ByteBuf의 하위 클래스인 CompositeByteBuf를 이용해 이 패턴을 구현한다
  - 헤더와 본문의 두 부분으로 구성되는 메시지를 http를 통해 전송하는 경우에 CompoiteByteBuf를 이용하면 메시지마다 두 버퍼를 다시 할당할 필요가 없어 편리하며, 공통 ByteBuf API를 노출할 때 불필요하게 복사할 필요가 없게 해준다.
  - CompositeByteBuf는 보조 배열에 대한 접근을 허용하지 않을 수 있으므로 CopositeByteBuf의 데이터에 접근하려면 다이렉트 버퍼와 비슷한 방법을 이용한다
  - 네티는 CompositeByteBuf를 이용하는 소케 ㅅ입출력 작업을 최적화해 JDK의 버퍼 구현이 이용될 때 발생하는 성능과 메모리 소비 패턴을 최소화한다
  - CompositeByteBuf API는 ByteBuf로부터 상속받은 메소드 외에도 다양한 추가 기능 제공



#### 파생 버퍼

- ByteBuf의 내용을 특수한 방법으로 나타내는 뷰를 제공
- 파생 버퍼의 내부 저장소는 JDK ByteBuffer와 마찬가지로 공유된다
  - 생성하는 비용은 낮지만 파생 버퍼의 내용을 수정하면 원본 인스턴스까지 수정된다는 데 주의해야 한다



#### ByteBufHolder 인터페이스

- 네티는 실제 데이터 payload와 함께 다양한 속성값을 저장해야 하는 경우에 대해 ByteBufHolder를 제공한다
- ByteBufHolder는 ByteBuf를 풀에서 가죠오고 필요할 때 자동으로 해제할 수 있는 버퍼 풀링과 같은 네티의 고븍 기능도 지원한다
- ByteBufHolder는 ByteBuf안에 Payload를 저장하는 메시지 객체를 구현하려고 할 때 좋은 선택이다



#### ByteBufAllocator 인터페이스

- 네티는 메모리 할당과 해제 시 발생하는 모든 오버헤드를 줄이기 위해 ByteBufAllocator 인터페이스를 통해 모든 종류의 ByteBuf인트섵스를 할당하는데 이용할 수 있는 풀링을 구현한다

- ByteBufAllocator의 참조는 Channel에서 얻거나(각각의 고유 인스턴스를 가짐) ChannelHandler에 바인딩된 ChannelHandlerContext를 통해 얻을 수 있다

- 네티는 ByteBufAllocator의 구현을 PooledByteBufAllocator와 UnpooledByteBufAllocator로 제공된다

- 네티는 기본적으로 BooledByteBufAllocator를 이용하지만 ChannelConfig API를 통하거나 어플리케이션을 부트스트랩할 때 다른 할당자를 지정할 수 있다

- PooledByteBufAllocator : ByteBuf인스턴스를 풀링해 성능을 개선하고 메모리 단편화를 최소화하며, 여러 최신 운영체제에 도입된 jemallac이라는 고효율 메모리 할당 방식을 이용한다

- UnpooledByteBufAllocator : ByteBuf인스턴슬 뤂링하지 않고 호출될 때마다 새로운 인스턴스를 반환한다

- 네티는 ByteBufAllocator의 참조가 없는 상황에 대해 풀링되지 않는 ByteBuf인스턴스를 생성하는 정적 도우미 메서드를 제공하는 Unpooled라는 유틸리티 클래스를 제공한다

  - Unpooled클래스는 다른 네티 컴포넌트가 필요없는 네트워킹과 무관한 프로젝트에 ByteBuf를 제공해 확장성 높은 고성능 버퍼 API를 이요할 수 있게 해준다

    

#### ByteBufUtil 클래스

- ByteBuf를 조작하기 위한 정적 도우미 메소드를 제공한다. 이 API는 범용이며 풀링과는 관계가 없으므로 이러한 메서드는 해당 클래스와 무관하게 구현된다



#### 참조 카운팅

- 다른 객체에서 더이상 참조하지 않는 객체가 보유한 리소스를 해제해 메모리 사용량과 성능을 최적화하는 기법
- 특정 객체에 대한 활성 참조의 수를 추적하는 데 바탕을 둔다
- ReferenceCounted 인터페이스를 구현한 인스턴스는 일반적으로 참조 카운트 1을 가지고 시작하며, 참조 카운트가 1 이상이면 객체가 해제되지 않지만, 0으로 감소하면 인스턴스가 해제된다. 해제된 객체는 더이상 이용할 수 없게 된다
- 참조 카운팅은 PooledByteBufAllocator와 같은 풀링 구현에서 메모리 할당의 오버헤드를 줄이는 데반드시 필요하다
- 특정한 객체가 각자의 고유한 방법으로 해제 카운팅 계약을 정의할 수 있다. 일반적으로 개개체에 마지막으로 접근하는 측에 해제할 책임이 있다



#### 실시간 웹

- 사용자 또는 사용자의 소프트웨어가 주기적으로 업데이트를 확인하지 않고도 저자가 정보를 게시하는 즉시 정보를 받을 수 있는 기술과 방식을 적용한 네트워크 웹을 의미



#### 웹 소켓

- 웹소켓 프로토콜은 웹의 양방향 데이터 전송 문제에 대한 실용적인 솔루션을 제공하기 위해 클라이언트와 서버가 언제든지 메세지를 전송할 수 있게 하용하고 결과적으로 메시지 수신을 비동기적으로 처리하게 요구하도록 설계되었다