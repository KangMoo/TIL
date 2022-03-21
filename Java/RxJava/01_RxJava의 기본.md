# RxJava와 리액티브 프로그래밍

## RxJava란

- RxJava는 자바에서 **리액티브 프로그래밍**을 구현하는데 사용하는 라이브러리다
- 이벤트처리와 같은 비동기 처리에 최적화되었다
- 2.0버전부터 Reactive Stream 사양을 구현한다
  - Reactive Stream 사양 : 어떤 라이브러리나 프레임워크라도 데이터스트림을 비동기로 처리하는 공통 메커니즘을 인터페이스로 제공한다
  - RxJava 2.0버전부터는 Reactive Streams API를 의존하고 그 외에 다른 라이브러리는 의존하지 않는다
- RxJava는 엄밀히 말해 **함수형 리액티브 프로그래밍은 아니다**
  - 하지만 함수형 프로그래밍의 영향을 받아 함수형 인터페이스를 인자로 전달받는 메서드가 많다
  - 이러한 함수형 인터페이스를 사용하는 메서드들을 조합하면 함수형 프로그래밍을 하는 것처럼 데이터를 필터링하거나 변환하는 등의 처리를 유연하게 할 수 있다

> Simple RxJava Example
> ```java
> public static void main(String[] args){
>   // 데이터를 통지하는 생산자를 생성한다
>   Flowable<String> flowable = Flowable.just("Hello", "World"); // <- just 메서드 인자로 전달된 데이터를 통지하는 생산자 (Flowable)를 생성한다
>   // 통지받은 데이터를 출력한다
>   flowable.subscribe(data -> System.out.println(data)); // <- 생산자가 데이터를 통지하면 소비자가 받은 데이터를 출력한다
> }
> ```
> 
> 실행 결과
> ```
> Hello
> World
> ```

## 리액티브 프로그래밍이란

- 리액티브 프로그래밍 : 데이터가 통지될 때마다 관련 프로그램이 반응하여 데이터를 처리하는 프로그래밍 방식
- 리액티브 프로그래밍은 데이터 스트림으로 데이터를 전달받은 프로그램이 그때마다 적절히 처리할 수 있도록 구성되어있는 프로그래밍이다
  - 데이터 스트림 : 이미 생성된 집합인 리스트같은 컬렉션과는 다르게 앞으로 발생할 가능성이 있는 데이터까지도 포함하는 데이터 집합체
    - 이벤트도 발생할 때마다 데이터를 전송하는 데이터 스트림으로 다룰 수 있다
- 리액티브 프로그래밍에서 데이터를 생산하는 측은 데이터를 전달하는 것까지 책임지며, 데이터를 생산하는 측은 데이터를 소비하는 측이 전달받은 데이터로 무엇을 하는지는 몰라도 되며 데이터를 소비하는 측의 처리를 기다릴 필요가 없다
- 시스템 구축 관점에서 볼 때도 마이크로서비스와 같이 분산 시스템으로 프로그램을 구현하는데 적합하다

## RxJava의 개요

- .NET 프레임크의 실험적인 라이브러인 Reactive Extension(Rx)를 2009년 MS에서 공개하고, 2013년 넷플릭스에서 자바로 이식한 것이 RxJava의 시작
- Reactive Extensions를 다루는 라이브러리는 **ReactiveX**라는 오픈소스 프로젝트로 바뀌어 자바나 .NET뿐만 아니라 자바스크립트나 스위프트 등 여러 프로그래밍 언어를 지원하는 라이브러리를 제공한다
- RxJava1 때는 Reactive Extensions를 이식하는 개발이 진행되었고, 리액티브 프로그래밍 개념이 널리 알려지면서 Reactive Extensions와 별도로 여러 업체에서 데이터 스트림을 비동기로 다루는 라이브러리와 프레임워크를 출시했고, 라이브러리나 프레임워크 차이 때문에 서로 다르게 구현하는 문제가 발생했다. 그래서 관련 단체들이 데이터 스트림을 비동기로 다루는 최소한의 API를 정하고 제공했고, RxJava 2.0부터 이 API를 지원하게 되었다
- RxJava2.0은 1.x 버전보다 성능이 개선되었고, 배압 사양에 따라 사용하는 API가 변경됐다
  - 따라서 1.x버전에서 2.x버전으로 전환할 때는 단순하게 패키지나 클래스 이름을 바꾸는것 외에도 이러한 API 변경 사항도 바녕해야 한다
  - 2.x버전부터 Reactive Streams를 구현하므로 다른 라이브러리를 의존할 필요가 없던 1.x버전과는 다르게 Reactive Streams의 jar 반드시 필요하다


## RxJav의 특징

- RxJava는 디자인 패턴인 **옵저버 패턴**을 잘 확장했다
  - 옵저버 패턴은 감시 대상 객체의 상태가 변하면 이를 관찰하는 객체에 알려주는 구조다
  - 이 패턴의 특징을 살리면 데이터를 생성하는 측과 소비하는 측으로 나눌 수 있기 때문에 쉽게 데이터 스트림을 처리할 수 있다
  - RxJava는 옵저버 패턴에 완료와 에러를 통지할 수 있어서 모든 데이터 통지가 끝나거나 에러가 발생하는 시점에 별도로 대응할 수도 있다
- RxJava는 쉬운 비동기처리를 할 수 있다
  - Reactive Streams 규칙의 근간이 되는 **Observable 규약**이라는 RxJava 개발 가이드라인을 따른 구현이라면 직접 스레드를 관리하는 번거로움에서 해방될 뿐 아니라 구현도 간단하게 할 수 있다
- RxJava는 동기 처리나 비동기 처리나 구현 방법에 큰 차이가 없다
- RxJava는 함수형 프로그래밍의 영향을 받아 함수형 인터페이스를 인자로 전달하는 메서드를 사용해 대부분의 처리를 구현한다
  - 입력과 결과만 정해져 있다면 구체적인 처리는 개발자에게 맡길 수 있으므로 더욱 자유로운 구현이 가능함을 의미한다
  - 처리작업의 영향 범위를 좁힐 수 있고 동시에 복잡하지 않게 비동기 처리를 할 수 있다

---

# Reactive Streams

## Reactive Streams란

- Reactive Streams란 라이브러리나 프레임워크에 상관없이 데이터 스트림을 비동기로 다룰 수 있는 공통 메커니즘으로 이 메터니즘을 편리하게 사용할 수 있는 인터페이스를 제공한다
  - RxJava 버전이 1.x에서 2.x로 올라간 배경에는 Reactive Streams가 있다

## Reactive Streams 구성

- Reactive Streams는 데이터를 만들어 통지하는 Publisher(생산자)와 통지된 데이터를 받아 처리하는 Subscriber(소비자)로 구성된다
  - Publisher : 데이터를 통지하는 생산자
  - Subscriber : 데이터를 받아 처리하는 소비자
- Subscriber가 Publiser를 구독(subscribe)하면 Publisher가 통지한 데이터를 Subscriber가 받을 수 있다
- Publiser가 데이터를 통지한 후 Subscriber가 이 데이터를 받을 때까지의 데이터 흐름
  1. 먼저 Publisher는 통지 준비가 끝나면 이를 Subscriber에 통지(onSubscription) 한다
  2. 해당 통지를 받은 Subscriber는 받고자 하는 데이터 개수를 요청한다
     - 이 때 Subscriber가 자신이 통지받을 데이터 개수를 요청하지 않으면 Publisher는 통지해야 할 데이터 개수 요청을 기다리게 되므로 통지를 시작할 수 없다 
  3. Publisher는 데이터를 만들어 Subscriber에 통지(onNext)한다
     - Publisher는 요청 받은 만큼의 데이터를 통지한 뒤 Subscriber로부터 다음 요청이 올 때까지 데이터 통지를 중단한다
  5. 이 데이터를 받은 Subscriber는 받은 데이터를 사용해 처리 작업을 수행한다
  6. Subscriber가 처리 작업을 완료하면 다음 받을 데이터 개수를 Publihser에 요청한다
     - 이 요청을 보내지 않으면 pUBLISHER는 요청 대기상태가 되어 Subscriber에 데이터를 통지할 수 없다
  7. Publisher는 Subscriber에 모든 데이터를 통지하고 마지막으로 데이터 전송이 완료돼 정상 종료 됐다고 통지(onComplete)한다
  8. 완료 통지를 하고 나면 Publisher는 이 구독 건에 대해 어떤 통지도 하지 않는다. 또한 Publisher는 처리 도중에 에러가 발생하면 Subscriber에 발생한 에러 객체와 함께 애러를 통지(onError)한다
- Subscriber가 Publisher에 통지받을 데이터 개수를 요청하는 것은 Publisher가 통지하는 데이터 개수를 제어하기 위함이다
  - 이를 배압(Back pressure)이라고 한다

### Reactive Streams가 제공하는 프로토콜 및 인터페이스

- Reactive Streams가 제공하는 프로토콜
  |프로토콜|설명|
  |---|---|
  |onSubscribe|데이터 통지가 준비됐음을 통지|
  |onNext|데이터 통지|
  |onError|에러(이상 종료)통지|
  |onComplete|완료(정상 종료)통지|

- Reactibe Streams 인터페이스
  |인터페이스|설명|
  |---|---|
  |Publisher|데이터를 생성하고 통지하는 인터페이스|
  |Subscriber|통지된 데이터를 전달받아 처리하는 인터페이스|
  |Subscription|데이터 개수를 요청하고 구독을 해지하는 인터페이스|
  |Processor|Publisher와 Subscriber의 기능이 모두 있는 인터페이스|

## Reactive Streams의 규칙

- Reactive Streams는 앞서 소개한 인터페이스로 데이터를 통지하는 구조를 제공하지만, 이 구조가 제대로 작동하려면 Reactive Streams의 규칙을 따라야 한다
  - RxJava를 사용할 때는 각 통지 메서드와 Subscription의 메서드를 호출할 때 동기화가 이뤄지므로 처리 자체가 스레드 안전한지 신경써야 하며, 이 부분이 제대로 처리되지 않으면 정확한 통지가 안 될 가능성이 크다
- Reactive Streams의 기본 규칙
  - 구독 시작 통지(onSubscriber)는 해당 구독에서 한 번만 발생한다
  - 통지는 순차적으로 이루어진다
  - null을 통지하지 않는다
  - Publisher의 처리는 완료(onComplete)또는 에러(onError)를 통지해 종료한다
  - Subscription은 데이터 개수 요청에 `Long.MAX_VALUE`를 설정하면 데이터 개수에 의한 통지 제한은 없어진다
  - Subscription의 메서드는 동기화된 상태로 호출해야 한다

**구독 시작 통지(onSubscriber)는 해당 구독에서 한 번만 발생한다**
- Subscriber의 onSubscribe 메서드는 구독을 시작해 통지 준비가 끝났을 때 한번만 실행된다

**통지는 순차적으로 이루어진다**
- 여러 통지를 동시에 할 수 없다
- 이는 Reactive Stremas 사양에 큰 영향을 미친 RxJava의 `Observable 규약`이라는 규칙에 따른 것으로, 데이터가 동시에 통지돼 불일치가 발생하는것을 방지한다

**null을 통지하지 않는다**
- null을 통지하면 Reactive Stremas에서 NPE를 발생시킨다
  - 이는 데이터를 통지할 때만 아니라 에러를 통지할 때도 마찬가지다

**Publisher의 처리는 완료(onComplete)또는 에러(onError)를 통지해 종료한다**
- 완료나 에러를 통지하면 Publisher가 처리를 끝마친것으로 판단한다
- 완료나 에러 통지를 마친 구독은 더 이상 통지하지 않는다는 의미다
  - 완료를 통지한 뒤 에러를 발생했다면 이 에러는 통지하지 않으므로 정상종료됐다고 생각할 위험성이 있다

**Subscription은 데이터 개수 요청에 `Long.MAX_VALUE`를 설정하면 데이터 개수에 의한 통지 제한은 없어진다**
- 이 요청을 전송한 후에는 데이터 개수 요청을 보내지 않아도 데이터 통지를 계속해서 받을 수 있다
  - 즉, 데이터 개수 요청을 받을 때마다 기존 개수에 더해져 통지 가능한 데이터 개수가 증가한다
  - 그래서 이 더해진 결과가 `Long.MAX_VALUE`에 도달하면 통지 가능한 데이터 개수 제한이 없어진다

**Subscription의 메서드는 동기화된 상태로 호출해야 한다**
- Subscription의 메서드를 동시에 호출해서는 안된다

