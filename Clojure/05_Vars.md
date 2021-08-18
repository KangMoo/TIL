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



### Docstrings

Var는 연결된 값에 대한 설명을 넣을 수 있는 기능이 있다

```clojure
user=> (def a "A value." 1)
#'user/a
```

심볼 뒤에 문자열로 설명을 넣으면 된다

Var 설명을 보려면 `doc` 함수로 볼 수 있다

```clojure
user=> (doc a)
-------------------------
user/a
A value.
nil
```

클로저 제공하는 함수는 docstring을 가지고 있기 때ㅔ문에 사용법을 보려면 `doc`함수에 실볼을 넣으면 된다.

```clojure
user=> (doc +)
-------------------------
clojure.core/+
([] [x] [x y] [x y & more])
  Returns the sum of nums. (+) returns 0. Does not auto-promote
  longs, will throw on overflow. See also: +'
nil
```



### let

Var의 범위는 전역 스코프를 가진다. 이것을 Root Var라고 한다

```clojure
user=> (def x 1)
#'user/x
user=> (def y 2)
#'user/y
user=> (+ x y)
3
```

만약 지역적으로 사용될 이름이 필요하다면 `let`구문을 쓰면 된다

```clojure
user=> (def a 1)
user=> (let [b 2 c 3] (+ b c))
5
user=> b

CompilerException java.lang.RuntimeException: Unable to resolve symbol: b in this context, compiling:(NO_SOURCE_PATH:0:
user=> c

CompilerException java.lang.RuntimeException: Unable to resolve symbol: c in this context, compiling:(NO_SOURCE_PATH:0:0)
```

`let`구문은 `(let 바인딩벡터 본문)` 형식으로 작성한다

바인딩 벡터 형식은 `[심볼 값 심볼 값 ...]` 으로 심볼 뒤에 나오는 값과 심볼이 연결된다. 따라서 벡터가 홀수개의 항목을 가질 수 없다.

Var 심볼과 같은 이름의 심볼을 `let`안에서 사용하면 Var 심볼은 가려져서 `let`안에서 사용할 수 없다. 하지만 `let` 바깥에서는 여전히 Var 심볼을 사용할 수 있다.

```clojure
user=> (def x 1)
#'user/x
user=> (def y 2)
#'user/y
user=> (let [x 3 y 4] (+ x y))
7
user=> x
1
user=> y
2
```



### Dynamic Vars

`binding` 구문으로 Var 값을 지역적으로 다른 값으로 연결해서 사용할 수 있다.

```clojure
user=> (def ^:dynamic a 1)
#'user/a
user=> (binding [a 2] (+ a 1))
3
user=> a
1
```

