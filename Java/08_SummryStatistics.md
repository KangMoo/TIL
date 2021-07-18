## SummaryStatistics

- count, min, max, sum, average등의 statistics정보를 계산해주는 클래스.
- 스트림과 함께 사용할 수 있도록 디자인되었다
- Int, Long, Double의 자료형과 함께 사용할 수 있다.
  - IntSummaryStatistics
  - LongSummaryStatistics
  - DoubleSummaryStatistics



### IntSummaryStatistics 예제

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



