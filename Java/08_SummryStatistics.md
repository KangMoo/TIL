## SummaryStatistics

- count, min, max, sum, average등의 statistics정보를 계산해주는 클래스.
- 스트림과 함께 사용할 수 있도록 디자인되었다
- Int, Long, Double의 자료형과 함께 사용할 수 있다.
  - IntSummaryStatistics
  - LongSummaryStatistics
  - DoubleSummaryStatistics

> 자료형만 다를 뿐 사용법은 동일하니 IntSummary Statistics를 기준으로 설명

### IntSummaryStatistics

- `IntSummaryStatistics.accept()` : 새로운 값 추가
- `IntSummaryStatistics.getMax()` : 최대값을 리턴
- `IntSummaryStatistics.getMin()` : 최소값을 리턴
- `IntSummaryStatistics.getAverage()` : 평균값을 리턴
- `IntSummaryStatistics.getCount()` : 스트림의 아이템 전체 개수를 리턴
- `IntSummaryStatistics.combine()` :  두 개의 statistics 객체를 합친다



### IntSummaryStatistics 예제

**기본 사용 예제**
스트림과 함께 사용하는 IntSummaryStatisticd 예제

```java
List<String> langs =
  Arrays.asList("java", "kotlin", "haskell", "ruby", "javascript");
IntSummaryStatistics stats = langs.stream()
  .mapToInt((lang) -> (lang.length()))
  .summaryStatistics();

System.out.println("Max: " + stats.getMax());
System.out.println("Min: " + stats.getMin());
System.out.println("Average: " + stats.getAverage());
System.out.println("Count: " + stats.getCount());
```

```java
Max: 10
Min: 4
Average: 6.2
Count: 5
```



**combine() 예제**

```java
List<String> langs =
  Arrays.asList("java", "kotlin", "haskell", "ruby", "javascript");
IntSummaryStatistics stats = langs.stream()
  .mapToInt((lang) -> (lang.length()))
  .summaryStatistics();

List<String> companies =
  Arrays.asList("google", "apple", "google", "apple", "samsung");
IntSummaryStatistics stats2 = companies.stream()
  .mapToInt((lang) -> (lang.length()))
  .summaryStatistics();

stats.combine(stats2);

System.out.println("Max: " + stats.getMax());
System.out.println("Min: " + stats.getMin());
System.out.println("Average: " + stats.getAverage());
System.out.println("Count: " + stats.getCount());
```

```java
Max: 10
Min: 4
Average: 6.0
Count: 10
```



## Collectors.summarizingInt

collect와 `Collectors.summarizingInt`를 이용하면 `summaryStatistics()`를 사용하지 않고 SummaryStatistics 객체를 만들 수 있다.

```java
List<String> langs =
  Arrays.asList("java", "kotlin", "haskell", "ruby", "javascript");
IntSummaryStatistics stats =
  langs.stream()
  .collect(Collectors.summarizingInt(String::length));

System.out.println("Max: " + stats.getMax());
System.out.println("Min: " + stats.getMin());
System.out.println("Average: " + stats.getAverage());
System.out.println("Count: " + stats.getCount());
```

```java
Max: 10
Min: 4
Average: 6.2
Count: 5
```
