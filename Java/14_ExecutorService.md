## ExecutorService

- 병렬 작업 시 여러개의 작업을 효율적으로 처리하기 위해 제공되는 JAVA 라이브러리
- 흔히 말하는 ThreadPool구현을 매우 용이하게 할 수 있으므로, Java에서 스레드 풀을 사용하고자 할 때 사용한다
- Runnable, Callable 중 하나를 상속하여 구현한 클래스를 인자로 받아서 처리할 수 있다.
  - Runnable : return 값이 없는 스레드
  - Callable : return 값이 있는 스레드



### ExecutorService의 종류

- `CachedThreadPool` : 스레드를 캐싱하는 스레드 풀. 필요할 때 필요한 만큼 스레드 풀을 생성한다. 이미 생성된 스레드를 재활용할 수 있기 때문에 성능상의 이점이 있을 수 있다 (여기서 쓰이는 캐싱의 의미는 일정시간동안 스레드를 검사한다는 뜻이다. 60초동안 작업이 없으면 Pool에서 제거한다)
- `ScheduledThreadPool` : 일정 시간 뒤에 실행되는 작업이나 주기적으로 수행되는 작업에 사용된다.
- `FixedThreadPool` : 고정된 개수를 가진 스레드풀
- `SingleThreadExecutor` : 한 개의 스레드로 작업을 처리하는 스레드풀. 스레드 풀이라기보단 TaskPool의 개념이 더 적합하다



![ExecutorService](./image/14_1.jpg)

- Customer은 Application에서 ExecutorService를 사용하는 클래스
- 해당 클래스에서 ExcutorService에 작업을 submit하게 되면 ExecutorService 내부에서 해당 작업을 내부적으로 스케쥴링 하면서 일을 처리한다
  - 이 때 Task를 가진 Queue에서 ThreadPool에 있는 스레드를이 각기 본인의 Task를 가지고 작업을 처리하기 때문에 개발자 입장에서는 해당 스레드들의 생명주기를 따로 관리할 필요가 없다



#### ExecutorService 생성 예시

```java
ExecutorService executor = Executors.newFixedThreadPool(4);
```

