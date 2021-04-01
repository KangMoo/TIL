## 지연실행 defer

- go 언어의 defer 키워드는 특정 문장 혹은 함수를 나중에 실행하게 된다 (defer를 호출하는 함수가 리턴하기 직전에)
- 일반적으로 defer는 C#, Java언어의 finally 블럭처럼 마지막에 Clean-up 작업을 위해 사용된다