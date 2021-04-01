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

