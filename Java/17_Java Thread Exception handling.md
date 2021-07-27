## Java Thread 내에서 발생한 Exception 처리

- 별도의 스레드에서 발생한 예외는 main 스레드에서 잡히지 않는다.
- 별도의 스레드에서 발생한 예외를 main 스레드에서 잡으려면 크게 3가지 방법이 있다.
  1. `Future.get()`을 사용한다.
  2. `CompletableFuture`의 예외 처리 가능 메서드들(`exceptionally()`, `whenComplete()`, `handle()` 등)을 사용한다.
  3. `UncaughtExceptionHandler` 구현체를 스레드 핸들러로 등록한다.





