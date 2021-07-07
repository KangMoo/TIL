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

