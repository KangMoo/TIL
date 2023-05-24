

### 스트림이란

- **데이터 처리 연산을 지원하도록 소스에서 추출된 연속된 요소**
- **컬렉션, 배열 등의 저장요소를 하나씩 참조하며 함수형 인터페이스(람다식)을 적용하여 반복적으로 처리할 수 있도록 해주는 기능**
- 스트림은 Array, Collections와 같이 연속된 형태의 객체이다. 하지만 자료구조는 아니다. **원래의 자료를 바꾸지는 못하고**, 대신 파이프라인 형태로 연결된 메소드의 결과를 제공한다. 원본 데이터를 바꾸지 못하는 특성 덕분에 side efferct를 제거할 수 있다.
- 스트림을 이용하면 선언형으로 컬렉션 데이터를 처리할 수 있다. 즉, 스트림이 데이터 컬렉션 반복을 멋지게 처리하는 기능이라고 생각하면 이해하기 쉽다
- 
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

```java
String[] arr = new String[] {"Hello", "World", "Hell"};
Stream<String> stream = Arrays.stream(arr); // 배열
Stream<String> streamOfArrayPart = Arrays.stream(arr, 1, 3); // 부분 배열
```

### Collection -> Stream

```java
List<String> list = Arrays.asList("a", "b", "c");
Stream<String> stream = list.stream();
Stream<String> parallelStream = list.parallelStream(); // 병렬 처리 스트림
```

### File -> Stream

- 파일의 경우 자바 NIO 의 Files클래스를 이용해 문자열 스트림 생성이 가능

```java
Path path = Paths.get("C:/Tmp/testfile.txt");
Stream<String> streamOfStrings = Files.lines(path);
Stream<String> streamWithCharset = Files.lines(path, Charset.forName("UTF-8"));
```

## 직접 생성

### 빈 스트림 생성 (null 대신 사용 가능)

```java
Stream<Object> stream = Stream.empty();
Stream<String> streamEmpty = Stream.empty();
```

### Build

```java
Stream<String> generatedStream = Stream.<String>builder()
        .add("Hello")
        .add("World")
        .build();
```

### generate

- `generate()` 메소드는 크기를 지정하지 않으면 무한 스트림을 생성하므로, 특정 사이즈만큼 생성하려면 반드시 `limit()`을 통해 최대 크기를 제한해야 합니다.

```java
Stream<String> generatedStream = Stream.generate(() -> "gen").limit(5);
```

### iterate

- 초기 값을 시작으로 2씩 증가된 값을 생성합니다. `iterate()` 메소드도 마찬가지로 크기를 지정하지 않으면 무한 스트림을 생성하므로 `limit()`을 통해 크기를 제한해야 합니다.

```java
Stream<Integer> iteratedStream = Stream.iterate(30, n -> n + 2).limit(5);
```

### 기본 타입형 스트림

- IntStream, LongStream, DoubleStream
- 기본 타입형 스트림은 제네릭을 사용하지 않고 기본 값을 생성하는 방법입니다.
- range는 [startPosition, endPosition) 범위를 가지며, ragneClosed는 [startPosition, endPosition] 범위를 가집니다.

```java
IntStream intStream = IntStream.range(1, 5); // [1, 2, 3, 4]
LongStream longStream = LongStream.rangeClosed(1, 5); // [1, 2, 3, 4, 5]
```

- 필요한 경우 Boxed메서드를 통해 Integer 형태로 박싱할 수 있습니다.

```java
Stream<Integer> boxedIntStream = IntStream.range(1, 5).boxed();
```

- 난수 스트림을 생성할 수도 있습니다.

```java
DoubleStream doubles = new Random().doubles(3); // 난수 3개 생성
```

### 병렬스트림

- 병렬 스트림은 내부적으로 'fork & join'프레임워크를 이용해 자동적으로 연산을 병렬로 수행합니다.
- 병렬 스트림을 중단하려면 `sequentail()`을 호출하면 됩니다.

```java
Stream<String> strStream = Arrays.asList("Hello", "World", "Java").stream();
int sum = strStream.parallel()
                   .mapToInt(s -> s.length())
                   .sum();
```


# Java Stream 중간 연산

Java의 Stream API는 데이터 처리 연산을 지원하며, 이 중에서도 "중간 연산"은 Stream을 통해 데이터를 원하는 형태로 처리하는 데 사용되는 연산자입니다. 각각의 중간 연산자들은 lazy하게 실행되며, 그 결과로 Stream을 반환하므로 메서드 체이닝 형태로 연결하여 처리할 수 있습니다. 또한 이러한 연산자들은 주로 함수형 인터페이스를 사용합니다.

## 중간 연산자 종류

### filter

`filter` 메서드는 원하는 요소만 추출하기 위한 연산자로, `Predicate` 함수형 인터페이스를 사용합니다.

```java
Stream<T> filter(Predicate<? super T> predicate);
```

### map

`map` 메서드는 스트림 내 요소를 가공하는 연산자로, `Function` 함수형 인터페이스를 사용합니다.

```java
<R> Stream<R> map(Function<? super T, ? extends R> mapper);
```

### flatMap

`flatMap` 메서드는 중첩 구조를 한 단계 제거하고 단일 컬렉션으로 만들어주는 연산자로, `Function` 함수형 인터페이스를 사용합니다. `map`과의 가장 큰 차이는 함수의 반환 값이 Stream형태라는 점입니다.

```java
<R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper);
```

### sorted

`sorted` 메서드는 스트림 요소를 정렬하는 연산자로, `Comparator` 함수형 인터페이스를 사용합니다.

```java
Stream<T> sorted();
Stream<T> sorted(Comparator<? super T> comparator);
```

### distinct

`distinct` 메서드는 중복된 값을 제거하는 연산자입니다.

```java
Stream<T> distinct();
```

### peek

`peek` 메서드는 각 요소에 특정 연산을 수행하는 연산자로, 결과에 영향을 주지 않습니다. 디버깅 용도로 주로 사용됩니다.

```java
Stream<T> peek(Consumer<? super T> action);
```

### limit

`limit` 메서드는 앞에서부터 n개의 요소만 취하는 연산자입니다.

```java
Stream<T> limit(long maxSize);
```

### skip

`skip` 메서드는 스트림에서 앞선 n개의 요소를 건너뛰고 그 이후의 요소들만 선택하는 연산자입니다.

```java
Stream<T> skip(long n);
```

### concat

`concat` 메서드는 두 Stream을 연결하는 연산자입니다.

```java
static <T> Stream<T> concat(Stream<? extends T> a, Stream<? extends T> b);
```

중간 연산자들은 결과가 필요하기 전까지 실행되지 않는다는 특징을 가지고 있습니다. 이를 "lazy한 처리"라고 하며, 실제로 필요한 시점까지 연산이 지연된다는 의미입니다. 따라서, 필요한 처리만 수행하여 효율적인 연산을 지원합니다. 이는 대량의 데이터를 처리할 때 유용합니다.



# Java Stream 최종 연산

Java의 Stream API에서는 중간 연산 이외에도 최종 연산이라는 개념이 있습니다. 최종 연산은 스트림 파이프라인을 처리하고, 처리 결과를 반환하거나 또는 특정 동작을 수행합니다. 이 연산을 수행하면 스트림 파이프라인이 실행되며, 스트림이 소비되어 더 이상 사용할 수 없게 됩니다.

## 주요 최종 연산 메서드

### count, sum, min, max, average

이 메서드들은 각각 스트림의 요소 수, 합, 최소값, 최대값, 평균을 계산합니다. 

```java
long count = IntStream.of(1, 2, 3, 4, 5).count();
long sum = IntStream.of(1, 2, 3, 4, 5).sum();
OptionalInt min = IntStream.of(1, 2, 3, 4, 5).min();
OptionalInt max = IntStream.of(1, 2, 3, 4, 5).max();
OptionalDouble average = IntStream.of(1, 2, 3, 4, 5).average();
```

### reduce

`reduce`는 스트림의 요소를 축소하여 단일 결과를 생성합니다. 여기에는 세 가지 형태의 `reduce` 메서드가 있습니다.

- `Optional<T> reduce(BinaryOperator<T> accumulator)`: 스트림의 요소를 누적하는 함수를 사용하여 reduce를 수행합니다. 초기값이 없어서 반환 값이 Optional입니다.
- `T reduce(T identity, BinaryOperator<T> accumulator)`: 초기값을 받아 reduce를 수행합니다. 초기값이 제공되므로, 결과는 Optional이 아닙니다.
- `<U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator, BinaryOperator<U> combiner)`: 병렬 스트림 처리를 지원하는 reduce 메서드입니다. 병렬 처리의 경우, 누적 함수와 조합 함수를 제공해야 합니다.

### collect

`collect`는 스트림의 결과를 컬렉션으로 모읍니다. 이는 다양한 형태로 사용될 수 있습니다. 

- `Collectors.toList()`: 결과를 리스트로 반환합니다.
- `Collectors.joining()`: 스트림의 결과를 문자열로 연결합니다.
- `Collectors.partitioningBy()`: 조건에 따라 스트림의 요소를 분할합니다.
- `Collectors.averagingInt()`, `Collectors.summingInt()`, `Collectors.summarizingInt()`: 평균, 합, 요약 통계 등의 연산을 수행합니다.

### anyMatch(), allMatch(), noneMatch()

이 메서드들은 스트림의 요소가 특정 조건을 만족하는지를 확인합니다. `anyMatch`는 하나라도 조건을 만족하면 true를 반환하며, `allMatch`는 모든 요소가 조건을 만족해야 true를 반환합니다. 반면, `noneMatch`는 모든 요소가 조건을 만족하지 않을 때 true를 반환합니다.

```java
boolean anyMatch = IntStream.of(1, 2, 3, 4, 5).anyMatch(n -> n > 3);
boolean allMatch = IntStream.of(1, 2, 3, 4, 5).allMatch(n -> n > 0);
boolean noneMatch = IntStream.of(1, 2, 3, 4, 5).noneMatch(n -> n > 5);
```

#### forEach()

`forEach` 메서드는 스트림의 각 요소에 대해 동작을 수행합니다. 이 메서드는 주로 스트림의 각 요소에 대한 부수적인 동작을 수행할 때 사용됩니다.

```java
IntStream.of(1, 2, 3, 4, 5).forEach(System.out::println);
```

이 예제에서 `forEach` 메서드는 스트림의 각 요소를 화면에 출력합니다. 이처럼 `forEach`는 반환 값이 없이 스트림의 각 요소에 대한 동작만 수행합니다.


## 요약

**Stream의 특징**

- Stream은 데이터를 변경하지 않습니다.

- Stream은 1회용 입니다.

- Stream은 지연 연산을 수행합니다

- Stream은 병렬 실행이 가능합니다

**Stream의 종류**

| 스트림       | 설명                      |
| ------------ | ------------------------- |
| Stream <T>   | 범용 Stream               |
| IntStream    | 값 타입이 Int인 Stream    |
| LongStream   | 값 타입이 Long인 Stream   |
| DoubleStream | 값 타입이 Double인 Stream |



**Stream의 중간 연산 명령어**

| Stream 중간 연산                                             | 설명                                                 |
| ------------------------------------------------------------ | ---------------------------------------------------- |
| `Stream < T > distinct()`                                    | Stream의 요소 중복 제거                              |
| `Stream < T > sorted()`                                      | Stream 요소 정렬                                     |
| `Stream < T > filter(Predicate < T > predicate)`             | 조건에 충족하는 요소를 Stream으로 생성               |
| `Stream < T > limit(long maxSize)`                           | maxSize 까지의 요소를 Stream으로 생성                |
| `Stream < T > skip(ling n)`                                  | 처음 n개의 요소를 제외하는 stream 생성               |
| `Stream < T > peek(Consumer< T > action)`                    | T타입 요소에 맞는 작업 수행                          |
| `Stream < R > flatMap(Function< T, stream<? extends R>> Tmapper)` | T타입 요소를 1:N의 R타입 요소로 변환하여 스트림 생성 |
| `Stream < R > map(Function<? super T, ? extends R> mapper)`  | 입력 T타입을 R타입 요소로 변환한 스트림 생성         |
| `Stream mapToInt(),mapToLong(),mapToDobule()`                | 만약 map Type이 숫자가 아닌 경우 변환하여 사용       |





**Stream의 최종 연산 명령어**

| Stream 최종 연산                                         | 설명                                                |
| -------------------------------------------------------- | --------------------------------------------------- |
| `void forEach(Consumer <? super T> action)`              | Stream 의 각 요소에 지정된 작업 수행                |
| `long count()`                                           | Stream 의 요소 개수                                 |
| `Optional < T > sum (Comparator <? super T> comparator)` | Stream 의 요소 합                                   |
| `Optional < T > max (Comparator <? super T> comparator)` | Stream 요소의 최대 값                               |
| `Optional < T > min (Comparator <? super T> comparator)` | Stream 요소의 최소 값                               |
| `Optional < T > findAny()`                               | Stream 요소의 랜덤 요소                             |
| `Optional < T > findFirst()`                             | Stream 의 첫 번째 요소                              |
| `boolean allMatch(Pradicate < T > p)`                    | Stream 의 값이 모두 만족하는지 boolean 반환         |
| `boolean anyMatch(Pradicate < T > p)`                    | Stream 의 값이 하나라도 만족하는지 boolean 반환     |
| `boolean noneMatch(Pradicate < T > p)`                   | Stream 의 값이 하나라도 만족하지않는지 boolean 반환 |
| `Object[] toArray()`                                     | Stream 의 모든 요소를 배열로 반환                   |



**Reduce**

 Stream 의 요소를 하나씩 줄여가며 계산한다

- `Optional < T > reduce(Binary Operator<T> accumulator)`
  - `.reduce((x,y) -> x > y ? x : y );`
- `T reduce ( T identity, BinaryOperator<T> accumulator)`
  - `.reduce(1, (x,y) -> x * y)`
- `<U> U reduce (U indentity, BiFunction<U, ? super T, U> accumulator, BinaryOperator<U> combiner)`
  - `.reduce(0.0,  (val1, val2) -> Double.valueOf(val1 + val2 / 10),  (val1, val2) -> val1 + val2);`

**Collector**

Stream의 요소를 수집하여 요소를 그룹화 하거나 결과를 담아 반환하는데 사용한다.

- `Collectors.toList()`
- `Collectors.toSet()`
- `Collectors.toMap()`
- `Collectors.groupingBy`
- `Collectors.partioningBy`
- `Collectors.summarizingInt()`



## Concat

- 아이템들을 하나의 객체로 합치는데 사용된다.

```java
List<String> numbers = Arrays.asList("1", "2", "3", "4", "5");
List<String> chars = Arrays.asList("a", "b", "c", "d", "e");
Stream<String> stream1 = numbers.stream();
Stream<String> stream2 = chars.stream();
Stream<String> stream3 = Stream.concat(stream1, stream2);
stream3.forEach(System.out::println);
```

```java
1
2
3
4
5
a
b
c
d
e
```

