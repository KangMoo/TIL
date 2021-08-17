## Vars

클로저도 다른 언어처럼 이름으로 값을 참조할 수 있다.

클로저는 심볼과 값을 직접 연결하지 않고 Var라는 것을 만들어 심볼과 값을 연결한다.

### Var 만들기

`def`구문으로 Var를 만들 수 있다.

```clojure
user=> (def a 1)
#'user/a
user=> a
1
```

간단한 `def`구문은 `(def 심볼 값)` 형식을 가진다

클로저는 심보릉 람나면 Var로 연결된 값을 찾는다. 만약 값이 연결되지 않은 심볼을 만나면 에러가 발생한다.

```clojure
user=> b

CompilerException java.lang.RuntimeException: Unable to resolve symbol: b in this context, compiling:(NO_SOURCE_PATH:0:0)
```

같은 심볼에 새로운 값으로 Var을 만들면 값이 바뀌지 않고 심볼과 새로운 값에 연결된 Var를 만든다.

```clojure
user=> (def a 1)
#'user/a
user=> a
1
user=> (def a 2)
#'user/a
user=> a
2
```

