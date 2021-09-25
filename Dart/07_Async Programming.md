## 비동기 프로그래밍

dart에서는 비동기 작업을 구현하기 위해 두 가지 방법을 사용할 수 있다.

- `Future` API를 이용한 비동기 프로그래밍
- `async`, `await`키워드를 이용한 비동기 프로그래밍

**중요한 점**

- dart 코드는 단일 스레드에서 실행된다
- 스레드를 차단하는 코드는 프로그램을 정지시킬 수 있다.
- `future`는 `async` 코드의 결과를 나타낸다. 처리 또는 입출력은 나중에 완료된다.
- `future`가 완료될 때까지 실행을 일시 중단하려면 `async` 함수에서 `await`를 사용하면 된다.
- `async`함수에서 try-catch를 사용해서 에러를 잡는다
- 코드를 동시에 실행하려면 isolate (또는 웹 응용 프로그램의 경우 worker)를 만든다.



## future

`future`는 클래스 `Future`의 인스턴스다. `future`는 비동기 작업의 결과를 나타내며 미완료/완료 두개의 상태를 가질 수 있다.

- 미완료(Uncompleted)

  비동기 함수를 초훌하면 그 함수는 미완료된 `future`를 반환한다. 이 `future`는 함수의 비동기 작업이 끝날때까지 기다리거나 또는 에러를 발생시킨다.

- 완료(Completed)

  만일 비동기 작업이 성공하면, 그 `future`는 값을 반환하거나 에러를 발생시킬 수 있다.

  - 값을 반환하는 경우, `Future<T>`타입의 `future`는 `T`의 값을 반환하며 완료된다. 예를들어 `Future<String>`는 문자열을 생성한다. 사용할수 있는 값을 반환하지 않으면 `future`의 타입은 `Future<void>`가 된다.
  - 에러를 발생시키는 경우는 비동기 작업중 예외적인 상황이 발생한 경우이다.

**Future 예시**

```dart
Future<void> getUserOrder() {
  // Imagine that this function is fetching user info from another service or database
  return Future.delayed(Duration(seconds: 3), () => print('Large Latte'));
}

main() {
  getUserOrder();
  print('Fetching user order...');
}

/* Result
Fetching user order...
Large Latte
*/
```

```dart
Future<void> getUserOrder() {
// Imagine that this function is fetching user info but encounters a bug
  return Future.delayed(Duration(seconds: 3), () => throw Exception('Logout failed: user ID is invalid'));
}

main() {
  getUserOrder();
  print('Fetching user order...');
}

/* Result
Fetching user order...
Uncaught Error: Exception: Logout failed: user ID is invalid
*/
```



## async/await

`async`와 `await` 키워드는 비동기 함수를 정의하고 결과를 사용하기 위해 '선언을 통한 방식(declarative way)'를 사용한다.

- `async`는 함수 몸체 바로 앞에 추가할 수 있다.
- `await`는 항상 `async`함수 안에서만 사용될 수 있다.

`async`함수는 첫번째 `await`를 만날때까지 동기적으로 실행된다. 이것은 `async`함수 몸채 네부에서, 첫번째 `await`가 나타날 때까지 만나게 되는 모든 동기 코드는 즉시 실행된다는 의미이다.



**동기 함수**

```dart
String createOrderMessage() {
  var order = getUserOrder();
  return 'Your order is: $order';
}

Future<String> getUserOrder() {
  // Imagine that this function is
  // more complex and slow.
  return
    Future.delayed(
      Duration(seconds: 4), () => 'Large Latte');
}

// Synchronous
main() {
  print('Fetching user order...');
  print(createOrderMessage());
}

/* Result
Fetching user order...
Your order is: Instance of _Future<String>
*/
```



**비동기 함수**

```dart
Future<String> createOrderMessage() async {
  var order = await getUserOrder();
  return 'Your order is: $order';
}

Future<String> getUserOrder() {
  // Imagine that this function is
  // more complex and slow.
  return
   Future.delayed(
     Duration(seconds: 4), () => 'Large Latte');
}

// Asynchronous
main() async {
  print('Fetching user order...');
  print(await createOrderMessage());
}

/* Result
Fetching user order...
Your order is: Large Latte
*/
```



**async 함수를 사용한 코드 예시**

```dart
import 'dart:async';

void createOrderMessage () async {
  print('Awaiting user order...');
  var order = await getUserOrder();
  print('Your order is: $order');
}

Future<String> getUserOrder() {
  // Imagine that this function is more complex and slow.
  return Future.delayed(Duration(seconds: 3), () => 'Large Latte');
}

main() async {
  countSeconds(4);
  await createOrderMessage();
}

// You can ignore this function - it's here to visualize delay time in this example.
void countSeconds(s) {
  for( var i = 1 ; i <= s; i++ ) {
      Future.delayed(Duration(seconds: i), () => print(i));
   }
}

/* Result
Awaiting user order...
1
2
3
Your order is: Large Latte
4
*/
```



**에러 처리**

```dart
void createOrderMessage () async {
  try {
    var order = await getUserOrder();
    print('Awaiting user order...');
    print(order);
  } catch (err) {
    print('Caught error: $err');
  }
}

Future<String> getUserOrder() {
  // Imagine that this function is more complex.
  var str = Future.delayed(Duration(seconds: 4), () => throw 'Cannot locate user order');
  return str;
}

main() async {
  await createOrderMessage();
}

/* Result
Caught error: Cannot locate user order
*/
```

