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

## 

## Map 사용

새로운 데이터 추가를 하려면 `map변수[키] = 값` 의 형태로 사용한다.만약 `키` 가 중복된다면 추가대신 값을 갱신한다

새로운 값을 읽을 땐 `map변수[키]`를 읽으면 된다

