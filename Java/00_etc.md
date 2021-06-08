## Immutable array

- 자바는 불변 배열 지원이 안된다.

> ```java
> final int[] array = new int[] {0, 1, 2, 3};
> array[0] = 42; // 수정 가능하다.
> ```

- 불변의 배열을 사용하고 싶으면 다음과 같이 사용해야한다

> ```java
> List<Integer> items = Collections.unmodifiableList(Arrays.asList(0,1,2,3));
> ```



## 자바는 Call By Refernce가 아니다

- 자바는 메서드 호출 시에 사용되는 인자가 값이 아닌 **주소**를 넘겨준다.
  - 따라서 객체를 넘겨주는 경우에 상태값을 바꾸는 경우라면 객체의 값도 바뀐다
  - 하지만 인자에 다른 객체를 넣어 바꾼다면 호출자의 값은 바뀌지 않는다
    - Call By Reference라면 호출자의 값도 바뀌어야 한다
- 예시
> ```java
> public static void callByValue(Person p) {
> 	p = new Person("kevin");
> }
> public static void main(String[] args) {
> 	Person p = new Person("wonwoo");
>   callByValue(p);
>   System.out.println("p.name: " + p.name); // wonwoo
> }
> ```

> ```java
> public static void callByValue(Person p) {
> 	p.name = "kevin";
> }
> public static void main(String[] args) {
> 	Person p = new Person("wonwoo");
>   callByValue(p);
>   System.out.println("p.name: " + p.name); // kevin
> }
> ```



