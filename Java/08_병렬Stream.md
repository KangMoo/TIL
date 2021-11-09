## 병렬처리

Java8부터 병렬 스트림을 제공하여 컬렉션의 전체 요소를 병렬로 처리할 수 있게 되었다

- 동시성 : 멀티 스레드 환경에서 스레드가 번갈아 가면서 실행하는 성질 (싱글코어 CPU)
- 병렬성 : 멀티 스레드 환경에서 코어들이 스레드를 병렬적으로 실행하는 성질 (멀티코어 CPU)

### 병렬성

- 데이터 병렬성
  - 데이터 병렬성은 한 작업 내에 있는 전체 데이터를 쪼개 서브 데이터로 만들고 서브 데이터를 병렬 처리해서 작업을 빨리 끝내는 것을 의미한다
- 작업 병렬성
  - 작업 병렬성은 서로 다른 작업을 병렬처리하는 것을 말한다
  - 작업 병렬성의 대표적인 예는 웹서버이다. 웹 서버는 각각의 브라우저에 요청한 내용을 개별 스레드에서 병렬로 처리한다

## 병렬 Stream

병렬 스트림은 데이터 병렬성을 구현하였다

- 멀티코어의 수만큼 대용량 요소를 서브 요소들로 나누고, 각각의 서븡 요소들을 분리된 스레드에서 병렬 처리시킨다.
- 예를 들면 쿼드 코어CPU의 경우 4개의 서브요소로 나누고 4개의 스레드가 각각의 서브 요소들을 병렬로 처리한다
- 병렬스트림은 내부적으로 포크조인 프레임워크를 사용한다



### 포크조인(ForkJoin) 프레임워크

- 포크단계
  - 데이터를 서브 데이터로 반복적으로 분리한다
  - 서브 데이터를 멀티코어에서 병렬로 처리한다
- 조인단계
  - 서브 결과를 결합해서 최종 결과를 만들어낸다

![ForkJoin](./image/forkjoin.png)

- 포크조인풀
  - 각각의 코어에서 서브 요소를 처리하는 것은 개별 스레드가 해야하므로 스레드 관리가 필요하다
  - 포크조인 프레임워크는 ExecutorService의 구현 객체인 ForkJoinPool을 사용해서 작업 스레드를 관리한다

## 병렬 스트림 생성

| 인터페이스             | 리턴타입     | 메서드          |
| ---------------------- | ------------ | --------------- |
| java.util.Collection   | Stream       | parallelStrem() |
| java.util.Stream       | Stream       | parallel()      |
| java.util.IntStream    | IntStream    | parallel()      |
| java.util.LongStream   | LongStream   | parallel()      |
| java.util.DoubleStream | DoubleStream | parallel()      |

- parallelStream() : 컬렉션으로부터 병렬 스트림을 바로 리턴

- parallel() : 순차 처리 스트림을 병렬 스트림으로 변환해서 리턴

- 병렬 스트림의 예

  1. 수집 예시 : 사용자 정의 컨테이너에 수집하기 (순차 처리 시스템)

     - MaleStudent 객체는 하나만 생성

     - 남학생일 경우 accumulate()가 호출되어 MaleStudent 객체 내부에 계속 누적

     - combine()메서드는 호출되지 않음

       ```java
       MaleStudent maleStudent = totalList.Stream()
         .filter(s -> s.getSex() == Student.Sex.MALE)
         .collect(MaleStudent::new, MaleStudent::accumulate, MaleStudent::combine);
       ```

  2. 병렬 스트림으로 수정

     - 코어의 개수만큼 정체 요소는 서브 요소로 나뉘어지고, 해당 개수 만큼 스레드가 생성된다.

     - 각 스레드는 서브 요소를 수집해야 하므로 4개의 MaleStudent 객체를 생성하기 위해 collect()의 첫 번째 메서드 참조인 MaleStudent::new를 4번 실행시킨다

     - 각 스레드는 MaleStudent 객체에 남학생 요소를 수집하기 위해 collect()의 두번째 메서드 참조인 MaleStudnet::accumulate를 매번 실행시킨다

     - 수집 완료된 MaleStudent는 (코어개스 -1)번의 결합으로 최종 수집된 MaleStudent로 만들어진다. 따라서 collect()의 세번째 메서드 참조인 MaleStudent::combile()이 (코어개수 -1)번 실행된다

       ```java
       MaleStudent maleStudent = totalList.parallelStream()
         .filter(s -> s.getSex() == Student.Sex.MALE)
         .collect(MaleStudent::new, MaleStudent::accumulate, MaleStudent::combine);
       ```



## 병렬처리 성능

병렬처리가 순차처리보다 항상 빠른건 아니다. 병렬처리에 영향을 미치는 3가지 요인이 있다

- 요소의 수와 요소당 처리 시간
  - 컬렉션에 요소의 수가 적고 요소당 처리시간이 짧으면 순차처리가 오히려 병렬처리보다 빠를 수 있다. 병렬처리는 스레드풀 생성, 스레드 생성이라는 추가적인 비용이 발생하기 때문이다
- 스트림 소스의 종류
  - ArrayList, 배열은 랜덤 액세스를 지원(인덱스로 접근)하기 때문에 포크 단계에서 쉽게 요소를 분리할 수 있어서 병렬처리 시간이 절약된다. 반면에 HashSet, TreeSet은 요소를 분리하기 쉽지 않고, LinkedList는 랜덤 액세스를 지원하지 않아 링크를 따라가야 하므로 역시 요소를 분리하기 쉽지 않다. 또한 BufferedReader.lines()은 전체 요소의 수를 알기 어렵기 때문에 포크 단계에서 부분요소로 나누기 어렵다. 따라서 이들 소스들은 ArrayList, 배열보다는 상대적으로 병렬처리가 늦다
- 코어의 수
  - 싱글코어 CPU일 경우에는 순차처리가 더 빠르다. 병렬처리를 할 경우 스레드의 수만 증가하고 번갈아가며 스케줄링을 해야하므로 좋지 못한 결과를 준다. 코어의 수가 많으면 많을수록 병렬 작업 처리속도는 빨라진다



## 병렬스트림 (Parallel Stream) 사용시 고려사항

Parallel Stream을 사용 시 아래의 내용을 염두해두어야 한다.

- **Parallel Stream은 Thread pool을 공유한다**
- ForkJoinPool의 특성상 나누어지는 job은 균등하게 처리되어야 한다
- 병렬로 처리되는 작업이 독립적이지 않다면, 수행 성능에 영향이 있을 수 있다

**Parallel()은 공유된 thread pool을 사용하기 때문에 심각한 성능장애를 일으킬 수 있으므로 사용시 주의해야한다.** 

일반적으로 Parallel Stream은 ForkJoinPool 방식을 이용하기 때문에 분할이 잘 이루어질 수 있는 데이터 구조이거나, 작업이 독립적이면서 CPU 사용이 높은 작업에 적합하다.

