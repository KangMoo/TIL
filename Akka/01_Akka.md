## 아카란

- 튼튼하고 회복성 있는 실시간 트랜잭션 분산 프로세싱
- Actor모델을 활용해 고수준의 추상화와 확장이 가능하면서도 탄력적이며 반응형의 어플리케이션을 구축하기 위한 기반을 제공한다
- 동시성과 내고장성을 가지면서 확장성있는 분산 구조의 어플리케이션 구현을 목표로 한다.



## Actor 모델이란

- 40년 전에 소개됐으나 당시에는 구현이 힘들어 제대로 된 가치를 인정받지 못하고 최그네 와서야 주목받은 프로그래밍 모델
- 전통적인 객체지향 프로그래밍 모델로 완벽히 해결하기 어려웠던 분산 시스템 구축에 필요한 난제를 해결할 방법으로 주목받게 되었다
- 액터 모델이 중요하게 다루는 사항은 세가지가 있다
  - 캡슐화의 문제
  - 현대 컴퓨터 아키텍처 상의 공유 메모리에 대한 착각
  - 콜스택에 대한 착각



### 캡슐화의 문제

객제지향 프로그래밍의 핵심 골격은 캡슐화이다.

캡슐화는 객체 내부 데이터는 외부에서 직접 접근할 수 없으며 허용된 메서드를 통해 호출해야만 변경할 수 있도록 강제하여 내부 데이터를 보호하도록 한다.

하지만 두개 이상의 스레드에서 하나의 오브젝트의 메서드를 동시에 호출하는 경우 예상치 못한 결과를 발생시킬 수 있다. 그리고 이런 문제를 해결하기 위해서 메서드에 락(lock)을 건다.

하지만 락(lock)은 아주 비싼 비용의 해결책이다

- 락은 동시성에 심각한 제약을 가한다. OS상에서 스레드를 일시 중지했다가 복구해야 하기 때문이다.
- 호출하는 스레드가 블록킹되고 다른작업을 할 수 없게 된다.
- 데드락이라는 새로운 위험 요소를 만들어 낼 수 있다.

정리하자면

- 객체는 단일 스레드 상황에서만 캡슐화를 보장할 수 있으며, 다중 스레드 상황에서는 캡슐화 보장이 되지 않는다.
- 락을 통해 캡슐화를 유지할 수 있지만, 락은 비용이 매우 비싸고, 데드락이라는 치명적인 문제가 발생할 가능성이 존재한다.
- 락은 로컬에서만 동작하며 분산 락이 있긴 하지만 수평 규모 확장에서는 제약이 존재한다.



### 현대 컴퓨터 아키텍처상에서 공유 메모리에 대한 착각

80-90년대의 프로그래밍 모델에서는변수에 값을 할당하는 작업은 메모리의 특정 위치에 직접 값을 쓰는 것을 개념화한 것이다. 현대의 아키텍처에서는 (단순화하면) CPU는 메모리에 직접 값을 쓰지 않고 캐시 라인에 쓴다. 캐시 라인은 CPU 코어의 로컬 영역에 해당하며, 어떤 코어가 쓴 내용이 다른 코어에서는 볼 수 없다. 이릉 위해서 로컬 변경사항을 다른 코어에서 보려면 캐시 라인의 내용을 옮겨가는 작업이 필요하다

JVM에서는 스레드들간에 공유되어야 하는 메모리의 위치를 명시적으로 표시하기 위해서 `volatile` 이나 `Atomic` 래퍼 를 사용해야 한다. 그렇다고 모든 변수를 volatile로 표시하기에는 캐시간 데이터를 옮기는 작업이 비싼 작업이기 때문이다.

정리하자면

- 더이상 진정한 의미의 공유메모리는 존재하지 않는다. CPU 코어가 데이터를 캐시라인간에 전송하는 과정은 컴퓨터들이 네트워크 상에서 데이터를 전달하는 것과 마찬가지다. 이제는 메시지를 전송하는 것이 CPU간이든 네트워크 사이에서든 일반적인 표준이다.
- 공유변수라고 표시하거나 원자 데이터 구조를 사용해서 메시지를 전송하는 형태를 숨기능 방식보다 동시성의 엔티티들 간에 명시적으로 메시지를 통해서 데이터나 이벤트를 전파하는 방법이 통제 가능하면서도 근본적인 해결방법이다.



### 콜스택에 대한 착각

콜스택은 다중 CPU시스템이 드물었던, 동시성 프로그래밍이 중요하지 않던 시절의 산물이다. 콜 스택은 여러 스레드들 사이에 걸칠 수 없기 때문에 비동기 콜 체인 모델링할 수 없다.

문제는 스레드가 백그라운드로 태스크를 위임할 때인데, 태스크를 위임한다는 것은 실제로는 다른 스레드가 그 태스크를 실행하도록 위임한다는 뜻이다. 이 과정은 단순한 메서드/함수 호출이 될 수 없다. 호출이란 엄연히 다른 스레드의 로컬 영역이기 때문이다. 일반적인 태스크를 위임하는 형태를 살펴 보면 호출자(Caller)는 객체를 메모리의 특정 위치에 두고 스레드(Callee)가 사용할 수 있도록 공유하면 워커 스레드는 나중에 이벤트 루프 내에서 이 값을 가져와서 사용하는 형태이다. 이 구조를 취함으로써 호출자 스레드는 다른 일을 할 수 있다.

여기서 문제는 호출자(Caller)가 태스크가 완료되었는지 알기 힘들다는 것이다. 더 심각한 문제는 태스크와 예외와 함께 실패한 경우이다. 이 예외는 호출자에게 전달되지 않을 것이고 호출자는 예외를 처리할 수 없게 된다.

이런 상황은 스레드 기반의 워커에 버그가 있는 겨웅에 더 악화되어 복구 불가능한 상황까지 발생시킬 수 있다.

정리하자면

- 현재 시스템 상에서 의미있는 동시성과 성능을 도출시키려면 블로킹이 없는 효율적인 방식으로 스레드에게 태스크를 위임해야 한다. 태스크 위임 방식의 동시성은 (네트워크/분산 컴퓨팅에서 더욱 중요하게) 콜 스택 기반의 에러 처리는 세분화되어 새로운 명시적인 에러 시그널링 방식이 필요하다.
- 태스크 위임의 동시성 시스템은 서비스 실패를 처리해야 하며 실패에서 복구할 근본적인 방법이 필요하다. 이러한 서비스의 클라이언트는 재시작 중에 태스크/메시지를 유실할 수 있다는 것을 알고 있어야 한다. 유실이 발생하지 않더라도 지연이 발생할 수 있으며 이런 이유로 동시성 시스템은 네트워크/분산 시스템과 마찬가지로 타임아웃과 같은 형태의 응답에 대한 기한을 정할 수 있는 체계가 필요하다



## 분산시스템과 액터 모델

액터를 사용한다는 것은

- 락에 의지하지 않고 캡슐화를 강화한다.
- 시그널에 반응하여 형태를 변경하고 서로 시그널을 보내는 협조적인 엔티티 모델을 사용하도록 어플리케이션을 진화시킨다.
- 현실 세계의 관점과 맞지 않는 실행 구조에 대해 걱정하지 않아도 된다.

### 락과 블록킹 없는 메시지 전송

엑터들은 메시지를 호출하지 않고 대신 서로 간에 메시지를 보낸다. 메시지 전송은 발신자가 수신자에게 실행 스레드를 전달하지 않는다. 액터는 블록킹 없이 메시지를 계속 보낼 수 있다. 따라서 액터는 같은 시간에 더 많은 일을 할 수 있다.

메서드는 객체를 리턴할 때 실행 스레드의 제어권도 반환한다. 이런 점에서 액터는 객체와 비슷하게 행동한다. 메시지를 수신하면 현재의 메시지를 처리한 후 실행 권한을 반환한다.

메시지 전송과 메서드 호출의 중요한 차이점은 메시지에는 반환 값이 없다는 것이다. 메시지를 보냄으로써 액터는 해야할 일을 다른 액터로 위임하는 것이다. 콜 스택에 대한 착각에서 살펴봤듯이 만약 액터가 반환 값을 기다린다면 발신자 액터는 블로킹되거나 다른 액터의 작업을 같은 스레드에서 실행해야만 한다. 그러므로 액터모델에서는 메시지를 수신한 액터가 처리 결과를 응답 메시지에 담아서 전송하는 방법을 사용한다.

두 번째 주요 변화는 캡슐화에 대한 재고이다. 객체가 자신의 메서드가 호출되었을 때 "반응"하는 것처럼 액터는 메시지에 반응한다. 차이점은 액터 내부로 다중 스레드가 밀고 들어오는 것을 (락을 사용하여) 방어할 필요가 없도록 수신된 메시지를 순차적으로 하나씩 처리한다는 점이다. 각 액터는 메시지를 순차적으로 처리하지만 서로 다른 액터들은 동시적으로 동작함으로써 하나의 액ㄹ터 시스템이 동시에 많은 메시지를 처리할 수 있다.

정리하자면 액터가 메시지를 수신하면 다음과 같은 순서로 처리된다.

1. 액터가 큐의 마지막에 메시지를 추가한다
2. 액터가 실행되도록 예약되어 있지 않다면 실행될 준비 상태로 표시한다
3. 스케줄러 엔티티가 해당 액터를 가져와 실행하기 시작한다
4. 액터가 큐의 가장 앞에서 메시지를 꺼내온다
5. 액터가 내부 상태를 변경하거나 다른 액터로 메시지를 보내는 작업 등을 수행한다
6. 액터가 비예약 상태로 바뀐다.

이러한 동작을 하기 위해서 액터는 다음과 같은 것들이 필요하다

- 메일박스 (메시지가 쌓이는 큐)
- 행위 (액터의 상태, 내부 변수 등)
- 메시지 (시그널을 표시하는 데이터, 메서드 호출의 파라미터와 유사)
- 실행 환경 (메시지를 가진 액터가 반응하고 처리하는 코드를 구동하는 구조)
- 주소

메시지는 액터의 메일박스로 들어가고, 액터의 행위는 액터가 메시지에 어떻게 응답해야 할지를 표현한 실행코드라고 보면 된다. 실행 환경은 스레드 풀을 조정해서 완전히 투명하게 실행되도록 조율한다.

액터 모듈은 단순하지만 이전에 나열한 문제들을 해결한다.

캡슐화는 실행(스레드)이 시그널과 분리됨으로써 유지된다. (메서드 호출 방식에서는 스레드를 메시지와 함게 전달하므로 불가능하다 - 멀티 스레드 문제)

락이 필요없다. 액터의 내부상태를 변경하는 것은 메시지를 통해서만 가능하고, 스레드는 한번에 하나의 메시지만 처리하므로 스레드 경합이 발생하지 않는다.

어디에도 락을 사용하지 않는다. 발송자는 블록킹되지 않고, 수백만의 액터가 수십개의 스레드에서 효율적으로 스케쥴링되며 최신의 CPU의 모든 역량을 충분히 활용할 수 있다. 메시지를 통한 태스크 위임은 액터의 기본 속성이다.

액터의 상태는 로컬이며, 공유되지 않으며 메시지를 통해서만 변경되고 전파될 수 있다. 이런 구조는 현대의 컴퓨터 아키텍처에서 메모리 계층 구조가 동작되는 방식과 동일하다. 즉 메시지에 포함된 어떤 데이터를 다른 코어의 캐시 라인으로 전달하면서 동시에 원래의 코어의 데이터 캐시에 저장된 로컬 상태와 데이터는 유지된다는 것을 의미한다.

### 액터의 오류 상황에 대한 처리

액터들이 상호 메시지를 주고 받는 과정에서 더 이상 콜 스택을 공유할 수 없으므로 에러를 다른 방식으로 처리해야한다. 여기서 고려해야 할 에러는 두 가지 종류가 있다.

1. 위임한 태스크가 내부에서 발생한 에러로 인해 대상 액터에서 실패하는 경우.
   - 이 경우 액터에 의해 캡슐화된 서비스는 손상되지 않고 해당 태스크 자체만 실패한다. 이 경우 특별할 것 없이 평범한 메시지로 처리하면 된다.
2. 서비스 자체에 내부적인 에러가 발생하는 경우
   - 프로세스와 유사하게 액터가 실패하면 부모 액터에게 통보되고, 부모 액터가 해당 실패에 대해 대응할 수 있다. 또한 부모 액터가 중지되면 자식 액터들은 재귀적으로 종료된다. 이런 동작을 아카의 감독이라고 부르며 아카의 핵심이다.
     - 부모가 특정 형태의 실패를 발생시킨 자식을 재시작하거나 중지시킬 수 있고, 자식 액터는 절대 아무도 모르게 죽을 수 없다.
     - 부모 액터는 형상 관리 대상인 자식 액터에 대한 책임을 다해야 한다
     - 액터가 재시작하더라도 이 액터에 메시지를 보내는 다른 액터는 계속해서 메시지를 보낼 수 있다. 즉 액터의 외부에서는 액터의 재시작을 알 수 없다.

## 아카의 특징

### 액터

- 분산, 동시성, 병렬화를 위한 간결하면서도 고수준의 추상화
- 비동기, 논블록킹, 고성능의 메시지 기반 프로그래밍 모델
- 경량의 이벤트 기반 프로세스

### 내고장성

- 수퍼바이저 계층 구조로 "let-it-crash" 사상을 채용했다.
- 진정한 내고장성을 위해 액터 시스템을 다수의 JVM으로 확장할 수 있다.
- 스스로 치유되며 중단 없는 내고장성 시스템을 작성하는데 탁월하다.

### 위치 투명성

- 아카는 분산 환경에서 동작하도록 설계되었다. 액터들의 상호작욕은 오직 메시지 전달을 통한 비동기 방식으로 동작한다

### 지속성 (persistence)

- 액터의 상태 변화는 액터가 시작하고 재시작할 때 저장되고 재생될 수 있다. 이를 통해 JVM이 비정상 종료하거나 액터가 다른 노드로 이동했을 때 액터는 이전의 상태를 복구할 수 있다.



액터는 객체와 다르게 액터 내부의 값을 직접 읽거나 쓸 수 없으며 메서드 호출도 할 수 없다. 외부에서 액터와 통신할 수 있는 방식은 메시지를 전달하는 방법밖에 없다. 어떤 액터로 메시지를 전송하고 액터는  메시지를 수신한 후 여기로 답장 메시지로 응답하는 현식이다. 이러한 방식이 앞선 객체지향 프로그래밍과 근본적으로 다른 점은 메시지를 보내고 수신한 메시지를 처리하고 답장 메시지를 보내는 모든 과정이 비동기로 일어난다는 점이다.

액터는 한 번에 하나의 메시지를 처리한다. 메시지를 처리하는 동안 액터는 자신의 내부 상태를 변경하거나 다른 액터를 생성할 수 있고, 다른 액터로 메시지를 보낼 수도 있다.

액터는 다음과 같은 런타임과 프로그래밍 모델딜이 하나로 통합된 형태이다.

- 수직 확장 (아카의 동시성)
- 수평 확장 (아카의 리모팅)
- 내고장성

아카는 확장성을 보장하는 소프트웨어다. 성능도 우수하며 어플리케이션 크기도 작기 때문에 매우 편리하게 활용할 수 있다. 아카의 핵심 모듈은 크기가 매우 작을 뿐만 아니라 비동기적이라서 기종 프로젝트에 없는 락 동시성이 필요할 때 쉽게 추가할 수 있다.

어플의 피료에 따라 아카의 일부만 선택할 수도 있다. 최근 CPU에는 더 많은 코어들이 푸가되고 있으므로 아카는 단일 머신에서 운영되는 경우에도 뛰어난 성능을 제공할 수 있다. 또한 아카는 동시성의 패러다임을 지원하는 다양한 도구를 제공하므로 사용자는 업무에 맞는 적절한 도구를 골라 사용할 수 있다.