## Java Thread 내에서 발생한 Exception 처리

- 별도의 스레드에서 발생한 예외는 main 스레드에서 잡히지 않는다.
- 별도의 스레드에서 발생한 예외를 main 스레드에서 잡으려면 크게 3가지 방법이 있다.
  1. `Future.get()`을 사용한다.
  2. `CompletableFuture`의 예외 처리 가능 메서드들(`exceptionally()`, `whenComplete()`, `handle()` 등)을 사용한다.
  3. `UncaughtExceptionHandler` 구현체를 스레드 핸들러로 등록한다.



### Future.get()을 사용한 Exception Handling

- ExecutorService.submit() + Future.get()

  ```java
  public class ExecutorSubmitAndGetWithTryCatch {
  
    public static void main(String[] args) throws ExecutionException, InterruptedException {
      ExecutorService executorService = null;
      try {
        executorService = Executors.newCachedThreadPool();
        Future<?> future = executorService.submit(new ExceptionProducingRunnable());
        Object result = future.get();
        System.out.println(OOO_MAIN_THREAD_정상_종료_OOO);
      } catch (Exception e) {
        System.out.println(CCC_THREAD_내에서_발생한_예외_CATCH_CCC);
        throw e;
      } finally {
        if (Objects.nonNull(executorService))
          shutdownAndAwaitTermination(executorService);
      }
    }
  }
  ```

  ```java
  
  // 결과
  OOO ExceptionProducingRunnable 실행 OOO
    CCC Thread 내에서 발생한 예외 catch CCC
      Exception in thread "main" java.util.concurrent.ExecutionException: java.lang.NullPointerException: XXXXX ExceptionProducingRunnable 예외 발생 in thread [pool-1-thread-1]
        at java.base/java.util.concurrent.FutureTask.report(FutureTask.java:122)
        at java.base/java.util.concurrent.FutureTask.get(FutureTask.java:191)
        at io.homo_efficio.scratchpad.thread.exception.runner.ExecutorSubmitAndGetWithTryCatch.main(ExecutorSubmitAndGetWithTryCatch.java:21)
        Caused by: java.lang.NullPointerException: XXXXX ExceptionProducingRunnable 예외 발생 in thread [pool-1-thread-1]
          at io.homo_efficio.scratchpad.thread.exception.runnable.ExceptionProducingRunnable.run(ExceptionProducingRunnable.java:14)
          at java.base/java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:514)
          at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264)
          at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1167)
          at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:641)
          at java.base/java.lang.Thread.run(Thread.java:844)
  ```

  

