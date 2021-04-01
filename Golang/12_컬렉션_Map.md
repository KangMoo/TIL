## Map

선언방법

- `var idMap map[int]string`

- ```go
  //리터럴을 사용한 초기화
  tickers := map[string]string{
      "GOOG": "Google Inc",
      "MSFT": "Microsoft",
      "FB":   "FaceBook",
  }
  ```



## Map 사용

새로운 데이터 추가를 하려면 `map변수[키] = 값` 의 형태로 사용한다.만약 `키` 가 중복된다면 추가대신 값을 갱신한다

새로운 값을 읽을 땐 `map변수[키]`를 읽으면 된다



## Map 키 체크

`map변수[키]` 읽기를 수행할 때 2개의 리턴값을 리턴한다. 첫번째는 키에 상응하는 값이고, 두번째는 그 키가 존재하는지 아닌지를 나타내는 bool 값이다

```go
package main
 
func main() {
    tickers := map[string]string{
        "GOOG": "Google Inc",
        "MSFT": "Microsoft",
        "FB":   "FaceBook",
        "AMZN": "Amazon",
    }
 
    // map 키 체크
    val, exists := tickers["MSFT"]
    if !exists {
        println("No MSFT ticker")
    }
}
```



## for 루프를 사용한 Map 열거

for range루프를 사용하여 Map 열거

```go
package main
 
import "fmt"
 
func main() {
    myMap := map[string]string{
        "A": "Apple",
        "B": "Banana",
        "C": "Charlie",
    }
 
    // for range 문을 사용하여 모든 맵 요소 출력
    // Map은 unordered 이므로 순서는 무작위
    for key, val := range myMap {
        fmt.Println(key, val)
    }
}
```

