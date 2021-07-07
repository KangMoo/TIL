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



결과가 완료될때까지 `future.get()`은 blocking한다

```java
CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            log("Starting....");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Finished works";
        });

log("get(): " + future.get());
```

```
15:46:09.715 (ForkJoinPool.commonPool-worker-1) Starting....
15:46:11.716 (main) get(): Finished works
```



`runAsync()`도 사용법은 동일하지만, 리턴 값이 없다는 차이가 있다.
따라서 `CompletableFuture<Void>`로 선언해야 하며. 결과가 완료될때까지 `future.get()`은 blocking되고, null을 리턴한다.

```java
CompletableFuture<Void> future
        = CompletableFuture.runAsync(() -> log("future example"));

log("get(): " + future.get());
```

```
15:47:01.328 (ForkJoinPool.commonPool-worker-1) future example
15:47:01.328 (main) get(): null
```



처리가 완료될 때까지 기다리지 않아도 된다면 다음과 같이 짧게 구현할 수도 있다

```java
CompletableFuture.runAsync(() -> log("future example"));
```



### Exception handling

`CompletableFuture`에서 작업을 처리하는 중에 Exception이 발생할 수 있다. 이런 경우 `handle()`로 예외를 처리할 수 있다.

```java
CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
    String name = null;
    if (name == null) {
        throw new RuntimeException("Computation error!");
    }
    return "Hello, " + name;
}).handle((s, t) -> s != null ? s : "Hello, Stranger!");

log(future.get());
```

```
15:51:35.385 (main) Hello, Stranger!
```



### thenApply() : 리턴 값이 있는 작업 수행

`supplyAsync()`로 어쩐 작업이 처리되념 그 결과를 가지고 다른 작업도 수행하도록 구현할 수 있다.

`thenApply()`메서드는 인자와 값이 있는 Lambda를 수행하며, 여기서 인자는 `supplyAsync()`에서 리턴되는 값이 된다.

```java
CompletableFuture<String> future1
        = CompletableFuture.supplyAsync(() -> "Future1");

CompletableFuture<String> future2 = future1.thenApply(
        s -> s + " + Future2");

log("future1.get(): " + future1.get());
log("future2.get(): " + future2.get());
```

```
15:56:26.203 (main) future1.get(): Future1
15:56:26.203 (main) future2.get(): Future1 + Future2
```



위의 future2는 다음과 같이 한번에 정의할 수도 있다.

```java
CompletableFuture<String> future = CompletableFuture
        .supplyAsync(() -> "Future1")
        .thenApply(s -> s + " + Future2");

log("future.get(): " + future.get());
```

```log
15:57:49.343 (main) future.get(): Future1 + Future2
```



`thenApply()` 또한 리턴값이 있기 때문에, 연달아 `thenApply()`를 적용할 수 있다.

```java
CompletableFuture<String> future = CompletableFuture
        .supplyAsync(() -> "Hello")
        .thenApply(s -> s + " World")
        .thenApply(s -> s + " Future");

log("future.get(): " + future.get());
```

```log
16:00:00.513 (main) future.get(): Hello World Future
```