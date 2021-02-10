## ExecutorService

- 병렬 작업 시 여러개의 작업을 효율적으로 처리하기 위해 제공되는 JAVA 라이브러리
- 흔히 말하는 ThreadPool구현을 매우 용이하게 할 수 있으므로, Java에서 스레드 풀을 사용하고자 할 때 사용한다



### ExecutorService의 종류

- `CachedThreadPool` : 스레드를 캐싱하는 스레드 풀 (여기서 쓰이는 캐싱의 의미는 일정시간동안 스레드를 검사한다는 뜻이다. 60초동안 작업이 없으면 Pool에서 제거한다)
- `FixedThreadPool` : 고정된 개수를 가진 스레드풀
- `SingleThreadExecutor` : 한 개의 스레드로 작업을 처리하는 스레드풀. 스레드 풀이라기보단 TaskPool의 개념이 더 적합하다

![ExecutorService](./image/14_1.jpg)



