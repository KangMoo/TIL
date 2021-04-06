## Future

- Future는 비동기 처리 결과를 표현하기 위해 사용된다. 멀티스레드 환경에서 처리된 데이터를 다른 스레드로 전달할 수 있다.
- 비동기 처리가 완료됐는지 확인하고, 처리 완료를 기다리고, 처리 결과를 리턴하는 메서드를 제공한다.
- 비동기 결과는 처리가 완료되면 `get` 메서드를 사용해서 얻을 수 있다.
  -  `get` 메서드는 비동기 처리가 완료될 때까지 blocking된다
- 비동기 작업을 취소하려면 `cancel` 메서드를 사용하면 된다. 비동기 작업이 정상적으로 완료되었는지 혹은 지연되었는지 확인하기 위한 메서드도 제공된다.
- 또한 비동기 작업이 완료된 경우에는 작업을 취소하는 것은 불가능하다.
- 만약 Future를 비동기 작업의 취소의 용도로 사용하고자 한다면(비동기 적업의 결과는 필요없음), `Future<?>` 형식으로 타입을 선언하면 된다. 그리고 비동기 작업의 결과로는 null을 리턴하도록 하면 된다.
- `Future`은 내부적으로 `Thread-Safe` 하도록 구현되어있기 때문에 `synchronized block`을 사용하지 않아도 된다.



### 예제1

```java
ExecutorService executor
  = Executors.newSingleThreadExecutor();

Future<Integer> future = executor.submit(() -> {
  System.out.println(LocalTime.now() + " Starting runnable");
  Integer sum = 1 + 1;
  Thread.sleep(3000);
  return sum;
});

System.out.println(LocalTime.now() + " Waiting the task done");
Integer result = future.get();
System.out.println(LocalTime.now() + " Result : " + result);
```



### 예제2 (Timeout)

```java
ExecutorService executor
        = Executors.newSingleThreadExecutor();

Future<Integer> future = executor.submit(() -> {
    System.out.println(LocalTime.now() + " Starting runnable");
    Integer sum = 1 + 1;
    Thread.sleep(4000);
    System.out.println(LocalTime.now() + " Exiting runnable");
    return sum;
});

System.out.println(LocalTime.now() + " Waiting the task done");
Integer result = null;
try {
    result = future.get(2000, TimeUnit.MILLISECONDS);
} catch (TimeoutException e) {
    System.out.println(LocalTime.now() + " Timed out");
    result = 0;
}
System.out.println(LocalTime.now() + " Result : " + result);
```

