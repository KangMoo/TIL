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



### thenAccpet() 리턴 값이 없는 작업 수행

`thenAccept()`도 `thenApply()`와 비슷하다. 하지만, 인자는 있지만 리턴값이 없는 Lambda를 처리할 수 있습니다.

다음은 `thenAccept()`를 사용하는 예제입니다. 리턴 값이 없기 때문에 `thenAccept()`는 `CompletableFuture<Void>`를 리턴하게 된다.

```java
CompletableFuture<String> future1 = CompletableFuture
        .supplyAsync(() -> "Hello");

CompletableFuture<Void> future2 = future1.thenAccept(
        s -> log(s + " World"));
log("future1.get(): " + future1.get());
log("future2.get(): " + future2.get());
```

```log
16:02:05.452 (main) Hello World
16:02:05.453 (main) future1.get(): Hello
16:02:05.453 (main) future2.get(): null
```



### thenCompose() : 여러 작업을 순차적으로 수행

`thenCompose()`는 chain처럼 두개의 CompletableFuture를 하나의 CompletableFuture으로 만들어주는 역할을 한다. 첫번째 CompletableFuture의 결과가 리턴되면 그 결과를 두번째 CompletableFuture으로 전달하며, 순차적으로 작업이 처리된다.

다음은 `thenCompose()`는 Lambda로 구성된 CompletableFuture를 인자로 받는다. 여기서 `s`는 `supplyAsync()`에서 리턴되는 String입니다.

```java
CompletableFuture<String> future = CompletableFuture
        .supplyAsync(() -> "Hello")
        .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + " World"));
log(future.get());
```

```log
16:07:33.196 (main) Hello World
```



연속적으로 `thenCompose()`를 적용할 수도 있다.

```java
CompletableFuture<String> future = CompletableFuture
        .supplyAsync(() -> "Hello")
        .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + " World"))
        .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + " Java"));

log(future.get());
```

```log
16:08:18.859 (main) Hello World Java
```



### thenCombind() : 여러 작업을 동시에 수행

`thenCompose()`가 여러개의 CompletableFuture를 순차적으로 처리되도록 만들었다면, `thenCombine()`는 여러 CompletableFuture를 병렬로 처리되도록 만든다. 모든 처리가 완료되고 그 결과를 하나로 합칠 수 있습니다.

다음은 `thenCombine()`으로 이 두개의 future가 병렬로 진행되도록 하고 그 결과를 하나로 합치도록 한다.

```java
CompletableFuture<String> future1 = CompletableFuture
        .supplyAsync(() -> "Future1")
        .thenApply((s) -> {
            log("Starting future1");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return s + "!";
        });

CompletableFuture<String> future2 = CompletableFuture
        .supplyAsync(() -> "Future2")
        .thenApply((s) -> {
            log("Starting future2");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return s + "!";
        });

future1.thenCombine(future2, (s1, s2) -> s1 + " + " + s2)
        .thenAccept((s) -> log(s));
```

```log
16:12:03.569 (main) Starting future1
16:12:05.571 (main) Starting future2
16:12:07.573 (main) Future1! + Future2!
```



### thenApply() vs thenApplyAsync()

`thenApply()` 대신에 `thenApplyAsync()`를 사용하면 다른 쓰레드에서 동작하도록 만들 수 있다.

```java
CompletableFuture<String> future1 = CompletableFuture
        .supplyAsync(() -> "Future1")
        .thenApplyAsync((s) -> {
            log("Starting future1");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return s + "!";
        });

CompletableFuture<String> future2 = CompletableFuture
        .supplyAsync(() -> "Future2")
        .thenApplyAsync((s) -> {
            log("Starting future2");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return s + "!";
        });

future1.thenCombine(future2, (s1, s2) -> s1 + " + " + s2)
        .thenAccept((s) -> log(s));

Thread.sleep(5000);
```

두개의 작업이 다른 쓰레드에서 처리되어 2초만에 처리되는 것을 볼 수 있다.

```log
16:15:39.532 (ForkJoinPool.commonPool-worker-2) Starting future2
16:15:39.537 (ForkJoinPool.commonPool-worker-1) Starting future1
16:15:41.537 (ForkJoinPool.commonPool-worker-1) Future1! + Future2!
```



### anyOf()

`anyOf()`는 여러개의 CompletableFuture 중에서 빨리 처리되는 1개의 결과만을 가져오는 메서드다.

다음과 같이 `anyOf()`를 사용할 수 있으며, 3개의 future 중에 가장 먼저 처리되는 1개의 결과만 `thenAccept()`으로 전달된다. 물론 3개의 future는 모두 실행이 되지만, `thenAccept()`에 전달되는 것이 1개일 뿐이다.

```java
CompletableFuture<String> future1 = CompletableFuture
        .supplyAsync(() -> {
            log("starting future1");
            return "Future1";
        });

CompletableFuture<String> future2 = CompletableFuture
        .supplyAsync(() -> {
            log("starting future2");
            return "Future2";
        });

CompletableFuture<String> future3 = CompletableFuture
        .supplyAsync(() -> {
            log("starting future3");
            return "Future3";
        });

CompletableFuture.anyOf(future1, future2, future3)
        .thenAccept(s -> log("Result: " + s));
```

```log
16:19:56.826 (ForkJoinPool.commonPool-worker-2) starting future2
16:19:56.826 (ForkJoinPool.commonPool-worker-1) starting future1
16:19:56.826 (ForkJoinPool.commonPool-worker-3) starting future3
16:19:56.826 (ForkJoinPool.commonPool-worker-2) Result: Future2
```



### allOf

`allOf()`는 모든 future의 결과를 받아서 처리할 수 있다.
`anyOf()`와는 다르게 Stream api를 사용하여 ㄱ ㅕㄹ과를 처리할 수 있다. `get()`은 null 을 리턴한다.

```java
CompletableFuture<String> future1 = CompletableFuture
        .supplyAsync(() -> "Future1");

CompletableFuture<String> future2 = CompletableFuture
        .supplyAsync(() -> "Future2");

CompletableFuture<String> future3 = CompletableFuture
        .supplyAsync(() -> "Future3");

CompletableFuture<Void> combinedFuture
        = CompletableFuture.allOf(future1, future2, future3);

log("get() : " + combinedFuture.get());
log("future1.isDone() : " + future1.isDone());
log("future2.isDone() : " + future2.isDone());
log("future3.isDone() : " + future3.isDone());

String combined = Stream.of(future1, future2, future3)
        .map(CompletableFuture::join)
        .collect(Collectors.joining(" + "));
log("Combined: " + combined);
```

```log
16:22:26.615 (main) get() : null
16:22:26.615 (main) future1.isDone() : true
16:22:26.615 (main) future2.isDone() : true
16:22:26.616 (main) future3.isDone() : true
16:22:26.620 (main) Combined: Future1 + Future2 + Future3
```



### async method

`thenApply()`와 `thenApplyAsync()`처럼 뒤에 asyncc가 붙은 메서드들이 항상 존재한다. 위의 예제에서 동일한 스레드를 사용하지 않고 다른 스레드를 사용하여 처리하고 싶을 때 async가 붙은 메서드를 사용하면 된다

예를 들어 `thenAccept()`는 `thenAsyncAccpet()`라는 메서드를 가지고 있으며, 대부분 asyn가 붙은 메서드들이 pair로 존재한다
