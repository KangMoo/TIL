## CompletableFuture

- Future를 좀 더 추상화 한 Java8에서 나온 비동기 방식
- CompleteableFuture는 Future와 CompletionStage를 구현한 클래스



### Future로 사용하는 방법

```java
CompletableFuture<String> future
        = new CompletableFuture<>();
Executors.newCachedThreadPool().submit(() -> {
    Thread.sleep(2000);
    future.complete("Finished");
    return null;
});

log(future.get());
```

```
22:58:40.478 (main) Finished
```



이미 값을 알고 있다면 스레드를 만들지 않고 `completedFuture()`로 값을 할당할 수 있다.

```java
Future<String> completableFuture =
        CompletableFuture.completedFuture("Skip!");

String result = completableFuture.get();
log(result);
```

```
22:59:42.553 (main) Skip!
```



### Cancel에 대한 예외처리

스레드에서 `cancel()`이 호출될 수 있다. 이 때, `get()`에서 `CancellationException`이 발생하기 때문에 예외처리를 해주어야 한다.

```java
CompletableFuture<String> future
        = new CompletableFuture<>();
Executors.newCachedThreadPool().submit(() -> {
    Thread.sleep(2000);
    future.cancel(false);
    return null;
});

String result = null;
try {
    result = future.get();
} catch (CancellationException e) {
    e.printStackTrace();
    result = "Canceled!";
}

log(result);
```

```
java.util.concurrent.CancellationException
	at java.util.concurrent.CompletableFuture.cancel(CompletableFuture.java:2276)
	at CompletableFutureExample.lambda$ex3$1(CompletableFutureExample.java:47)
	at java.util.concurrent.FutureTask.run(FutureTask.java:266)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at java.lang.Thread.run(Thread.java:748)
23:02:53.074 (main) Canceled!
```



### supplyAsync(), runAsync()

CompletableFuture는 `supplyAsync()`와 `runAsync()`를 제공하여 직접 스레드를 생성하지 않고 작업을 async로 처리할 수 있고, 이런 식으로 `supplyAsync()`에 Lambda로 인자를 전달할 수 있다. 이때 전달된 Lambda는 다른 스레드에서 처리된다.

```java
CompletableFuture<String> future
        = CompletableFuture.supplyAsync(() -> "future example");

log("get(): " + future.get());
```

```
15:43:02.923 (main) get(): future example
```

