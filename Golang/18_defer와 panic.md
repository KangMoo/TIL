## 지연실행 defer

- go 언어의 defer 키워드는 특정 문장 혹은 함수를 나중에 실행하게 된다 (defer를 호출하는 함수가 리턴하기 직전에)
- 일반적으로 defer는 C#, Java언어의 finally 블럭처럼 마지막에 Clean-up 작업을 위해 사용된다

> ```go
> package main
> 
> import "os"
> 
> func main() {
>   f, err := os.Open("1.txt")
>   if err != nil {
>     panic(err)
>   }
> 
>   // main 마지막에 파일 close 실행
>   defer f.Close()
> 
>   // 파일 읽기
>   bytes := make([]byte, 1024)
>   f.Read(bytes)
>   println(len(bytes))
> }
> ```
>
> 파일을 Open 한 후 바로 파일을 Close하는 작업을 defer로 쓰고 있다.  이는 차후 문장에서 어떤 에러가 발생하더라도 항상 파일을 Close할 수 있도록 한다.



## panic 함수

- go 내장함수인 `panic()`함수는 현재 함수를 즉시 멈추고 현재 함수에 defer 함수들을 모두 실행한 후 즉시 리턴한다.
- panic모드 실행 방식은 다시 상위함수에도 똑같이 적용되고, 계속 콜스택을 타고 올라가며 적용된다. 그리고 마지막에는 프로그램이 에러를 내고 종료하게 된다.

> ```go
> package main
> 
> import "os"
> 
> func main() {
>   // 잘못된 파일명을 넣음
>   openFile("Invalid.txt")
> 
>   // openFile() 안에서 panic이 실행되면
>   // 아래 println 문장은 실행 안됨
>   println("Done") 
> }
> 
> func openFile(fn string) {
>   f, err := os.Open(fn)
>   if err != nil {
>     panic(err)
>   }
> 
>   defer f.Close()
> }
> ```



## recover 함수

- go의 내장함수인 `recover()` 함수는 panic 함수에 의한 패닉상태를 다시 정상상태로 되돌리는 함수이다.