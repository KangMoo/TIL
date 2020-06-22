## Stream

### 스트림이란

- **데이터 처리 연산을 지원하도록 소스에서 추출된 연속된 요소**
- 스트림은 Array, Collections와 같이 연속된 형태의 객체이다. 하지만 자료구조는 아니다. **원래의 자료를 바꾸지는 못하고**, 대신 파이프라인 형태로 연결된 메소드의 결과를 제공한다. 원본 데이터를 바꾸지 못하는 특성 덕분에 side efferct를 제거할 수 있다.
- 스트림을 이용하면 선언형으로 컬렉션 데이터를 처리할 수 있다. 즉, 스트림이 데이터 컬렉션 반복을 멋지게 처리하는 기능이라고 생각하면 이해하기 쉽다
- 스트림을 이용하면 멀티스레드 코드를 구현하지 않아도 데이터를 투명하게 병렬로 처리할 수 있다
- 특징
  - 선언형 : 더 간결하고 가독성이 좋아진다
  - 조립할 수 있음 : 유연성이 더 좋아진다
  - 병렬화 : 성능이 더 좋아진다



### 스트림의 연산 순서

- 스트림 생성 -> 중간 연산자 -> 최종 연산자

  ```java
  int result = list.stream()  // 스트림 생성
          .filter( ... )      // 중간 연산
          .map( ... )         // 중간 연산
          .count();           // 최종 연산
  ```

  

# Stream 생성

### Array -> Stream

>```java
>String[] arr = new String[] {"Hello", "World", "Hell"};
>Stream<String> stream = Arrays.stream(arr); // 배열
>Stream<String> streamOfArrayPart = Arrays.stream(arr, 1, 3); // 부분 배열
>```

### Collection -> java

>```java
>List<String> list = Arrays.asList("a", "b", "c");
>Stream<String> stream = list.stream();
>Stream<String> parallelStream = list.parallelStream(); // 병렬 처리 스트림
>```

## 직접 생성

### 빈 스트림 생성 (null 대신 사용 가능)

> ```java
> Stream stream = Stream.empty();
> ```

### Build

> ```java
> Stream<String> generatedStream = Stream.<String>builder()
>         .add("Hello")
>         .add("World")
>         .build();
> ```

### generate

- 크기를 지정하지 않으면 무한하기 때문에 특정 사이즈만큼 생성하려면 반드시 limit을 통해 최대 크기를 제한해야 한다

> ```java
> Stream<String> generatedStream = Stream.generate(() -> "gen").limit(5);
> ```

### interate

- 초기 값을 시작으로 2씩 증가된 값을 생성한다. generate()와 마찬가지로 크기를 지정하지 않으면 무한하기 때문에 limit을 통해 크기를 제한해야 한다

> ```java
> Stream<Integer> iteratedStream = Stream.iterate(30, n -> n + 2).limit(5);
> ```

### 기본 타입형 스트림

- IntStream, LongStream, DoubleStream
- 제네릭을 사용하지 ㅇ낳고 기본 값을 생성하는 방법이다.
  - 제네릭을 사용하지 ㅇ낳기 때문에 불필요한 오토 박싱(audo_boxing)이 발생하지 않는다
- range는 [startPosition, endPosition)] 범위를 가진다
- ragneClosed는 [startPosition, endPosition] 범위를 가진다

> ```java
> IntStream intStream = IntStream.range(1, 5); // [1, 2, 3, 4]
> LongStream longStream = LongStream.rangeClosed(1, 5); // [1, 2, 3, 4, 5]
> ```

- 필요한 경우 Boxed메서드를 통해 Integer 형태로 박싱할 수 있다

> ```java
> Stream<Integer> boxedIntStream = IntStream.range(1, 5).boxed();
> ```

- 난수 스트림을 생성할 수도 있다

> ```java
> DoubleStream doubles = new Random().doubles(3); // 난수 3개 생성
> ```

## 

# 중간 연산

- 값을 원하는 형태로 처리하기 위한 연산자이다. 각각의 중간 연산자들의 lazy하게 실행되고, 결과로 stream을 반환한다. 그렇기 때문에 중간 연산자는 method chaineing형태로 연결하여 처리할 수 있다. 연산의 결과가 stream으로 반환되기 때문에 stream-producing연산자라고 불리기도 한다
  - Lazy한 처리 : **결과가 필요하기 전까지 실행되지 않음을 의미**한다. 연산의 시점을 최대한 늦춘다는 의미
- 함수형 인터페이스를 사용한다
  - Function
    - Input을 받아 output으로 출력하는 함수형 인터페이스. 메서드 참조를 사용할 수도 있다
  - Predicate
    - boolean을 리턴하는 함수형 인터페이스
  - Comparator
    - 두 값의 비교를 위해 사용하는 함수형 인터페이스

### filter

- 원하는 요소만 추출하기 위한 메서드. (Predicate)

> ```java
> Stream<T> filter(Predicate<? super T> predicate);
> ```

> e.g. 문자열 리스트에서 특정한 문자가 포함된 문자열 뽑아내기
>
> ```java
> List<String> names = Arrays.asList("Hello", "World", "Test", "array");
> List<String> filteredNames = names.stream()
>         .filter(it -> it.contains("e"))
>         .collect(Collectors.toList());
> ```
>
> e.g. 점수가 80점 이상인 학생만 뽑아내기
>
> ```java
> Arrays.asList(19, 86, 35, 78, 12, 45, 89, 98, 100)
>         .stream()
>         .filter(it -> it >= 80)
>         .forEach(System.out::println);
> ```

### map

- 스트림 내 요소를 가공한다. (Function)

> ```java
> <R> Stream<R> map(Function<? super T, ? extends R> mapper);
> ```

> e.g 정사각형의 한 변의 길이를 곱하여 넓이로 가공하기
>
> ```java
> Arrays.asList(3, 8, 9, 10, 20, 11, 22)
>         .stream()
>         .map(it -> it * it)
>         .forEach(System.out::println);
> ```
>
> e.g 문자열 리스트를 모두 대문자료 변환하기
>
> ```java
> Arrays.asList("Hello", "World", "Test", "array");
>         .stream()
>         .map(String::toUpperCase)
>         .forEach(System.out::println);
> ```

### flatMap

- 중첩 구조를 한 단계 제거하고 단일 컬렉션으로 만들어주는 역할을 한다.
  - 이러한 작업을 **flattening**이라고 한다 (Function)
- map과 가장 큰 차이는 함수의 반환 값이 stream형태라는 것이다. map 만으로 처리하면 복잡해지는 문제를 간결하게 만들어준다

> ```java
> <R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper);
> ```

> e.g 2차원 배열과 같이 중첩된 형태의 값을 처리할 때 map을 이용하면 2중 for문의 형태를 사용해야 하는 반면 flatMap은 배열의 각 행에 있는 요소를 stream으로 만들어 처리할 수 있다
>
> ```java
> String arr[][] = {
>     {"minus one", "zero", "one"}, 
>     {"two", "Three"}, 
>     {"Four", "Five", "Six"}, 
>     {"eight", "ten"}
> };
> 
> Stream.of(arr)
>         .flatMap(Stream::of)
>         .forEach(System.out::println);
> 
> int arr[][] = {{1, 2, 3}, {4, 8}, {9, 10, 20}, {11, 22}};
> Stream.of(arr)
>         .flatMapToInt(IntStream::of)
>         .forEach(System.out::println);
> ```

### sorted

- stream 요소를 정렬한다 (Comparator)

> ```java
> Stream<T> sorted();
> Stream<T> sorted(Comparator<? super T> comparator);
> ```

> e.g 오름차순 정렬
>
> ```java
> List<String> names = Arrays.asList("Hello", "World", "stream", "API");
> names.stream()
>         .sorted()
>         .forEach(System.out::println);
> ```
>
> e.g 내림차순 정렬
>
> ```java
> List<String> names = Arrays.asList("Hello", "World", "stream", "API")
>         .stream()
>         .sorted(Comparator.reverseOrder())
>         .forEach(System.out::println);
> ```

### distinct

- 중복된 값을 제거한다

> ```java
> List<String> names = Arrays.asList("312", "123", "123", "123", "1234");
> names.stream()
>         .distinct()
>         .forEach(System.out::println);
> ```

### peek

각 요소에 특정한 연산을 수행하는 메서드. 이 메서드가 결과에 영향을 주지는 않는다. 중간에 값을 출력해볼 때 이용할 수 있다

> ```java
> Stream<T> peek(Consumer<? super T> action);
> ```

> e.g 사용 예시
>
> ```java
> Arrays.asList("312", "123", "123", "123", "1234")
>         .stream()
>         .peek(System.out::println)
>         .map(it -> "#" + it)
>         .forEach(System.out::println);
> ```

## Substream 추출 및 Stream 결합

### limit

- 앞선 n개의 요소만 취한다

> ```java
> Arrays.asList("Hello", "World", "Test", "array", "Hell")
>         .stream()
>         .limit(2)
>         .forEach(System.out::println);
> ```

### skip

- 앞선 n개의 요소를 건너뛰고 다음에 오는 요소를 취한다

> ```java
> Arrays.asList("Hello", "World", "Test", "array", "Hell")
>         .stream()
>         .skip(2)
>         .forEach(System.out::println);
> ```

### concat

- 두 Stream을 연결한다

> ```java
> Stream<String> streamOne = Arrays.asList("Hello", "World", "Test", "array", "What").stream();
> Stream<String> streamTwo = Arrays.asList("The", "Hell").stream();
> Stream.concat(streamOne, streamTwo)
>         .forEach(System.out::println);
> ```



# 최종연산

## count, sum, min, max, average

- 차례대로 갯수, 합, 최소, 최대, 평균

> ```java
> long count = IntStream.of(1, 2, 3, 4, 5).count();
> System.out.println("Count is " + count);
>     
> long sum = IntStream.of(1, 2, 3, 4, 5).sum();
> System.out.println("Sum is " + sum);
>     
> OptionalInt min = IntStream.of(1, 2, 3, 4, 5).min();
> System.out.println("Sum is " + min.getAsInt());
>     
> OptionalInt max = IntStream.of(1, 2, 3, 4, 5).max();
> System.out.println("Sum is " + max.getAsInt());
>     
> OptionalDouble average = IntStream.of(1, 2, 3, 4, 5).average();
> System.out.println("Sum is " + average.getAsDouble());
> ```

## reduce

- 세 가지의 인자를 받아 처리할 수 있다

### accumulator

- 각 요소를 처리하는 계산 로직. 각 요소가 올 때마다 중간 결과를 생성한다

> ```java
> Optional<T> reduce(BinaryOperator<T> accumulator);
> ```

### identity

- 계산을 위한 초기값. stream이 비어서 계산할 값이 없더라도 이 값은 반환된다

> ```java
> T reduce(T identity, BinaryOperator<T> accumulator);
> ```

### combiner

- 병렬 stream에서 나눠 계산한 결과를 하나로 합쳐 반환한다

> ```java
> <U> U reduce(U identity,
>         BiFunction<U, ? super T, U> accumulator,
>         BinaryOperator<U> combiner);
> ```

## Collecting

- Stream값을 모아 Map, Set, List와 같은 컬렉션 형태로 변환해준다. (아마 가장 많이 쓰이는 최종 연산자..)

### Collectors.toList()

- 리스트 형태로 결과를 반환한다

### Collectors.joining()

-  **스트림 작업 결과를 하나의 스트링으로 연결**한다. 세 가지 인자를 입력할 수 있다
  - delimiter : 각 요소 중간에 들어가는 구분다
  - prefix : 이어 붙인 결과 맨 앞에 붙는 문자
  - suffix : 이어붙인 결과 맨 끝에 붙는 문자

### Collectors.partitioningBy()

-  Predicate로 특정 조건을 받아 해당 조건을 만족하면 true, 아니면 false 그룹으로 분류하여 Map 타입으로 반환한다

## average[typeName], summing[typeName], summarizing[typeName]

- Collect 메서드에 사용할 수 있는 메서드이다. 각 기본 타입 (int, long, double) 별로 제공된다. 아래에는 Integer를 기준으로 설명한다

### Collectors.averageingInt()

- 요소들의 평균을 Integer형태로 반환한다

### Collectors.summingInt()

- 요소들의 합을 Integer 형으로 반환한다

### Collectors.summarizingInt()

- 다양한 연산 결과를 IntSummaryStatistics 형으로 반환한다
  - 제공 메서드 : getCount(), getSum(), getAverage(), getMin(), getMax()
- 이를 이용하면 스트림을 여러번 생성하지 않고 원하는 결과를 한 번에 모두 만들 수 있다

## anyMatch(), allMatch(), noneMatch()

- Predicate로 특정 조건을 받아 해당 조건을 만족하는지 확인하여 결과를 boolean값으로 반환한다

### anyMatch

- 하나라도 조건을 만족하는 요소가 있는지 확인한다

> ```java
> boolean anyMatch(Predicate<? super T> predicate);
> ```

### allMatch

- 모두 조건을 만족하는지 확인한다

> ```java
> boolean allMatch(Predicate<? super T> predicate);
> ```

### noneMatch

- 모두 조건을 만족하지 않는지 확인한다

> ```java
> boolean noneMatch(Predicate<? super T> predicate);
> ```

## forEach()

- 요소를 순회하면서 실행되는 작업이다. 인자로 넘긴 메서드에 요소를 대입하여 호출한다. 중간연산의 peek과 유사하다고 볼 수 있다

