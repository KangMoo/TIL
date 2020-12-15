## Go 패키지

Go는 패키지를 통해 코드의 모듈화, 코드의 재사용 기능을 제공한다.

Go는 패키지를 사용해서 작은 단위의 컴포넌트를 작성하고 이런 작은 패키지를 활용해서 프로그램을 작성할 것을 권장한다

Go는 실제 프로그램 개발에 필요한 많은 패키지들을 표준 라이브러리로 제공한다. 이러한 표준 라이브러리 패키지들은 `GOROOT/pkg`안에 존재한다.

GOROOT 환경변수는 Go설치 디렉토리를 가리키는데 보통 Go설치 시 자동으로 추가된다. 즉 윈도우즈에서 Go를 설치했을 경우 디폴트로 `C:\go` 에 설치되며, GOROOT는 `C:\go` 를 가리킨다.

Go에 사용하는 표준 패키지는 https://golang.org/pkg에 자세히 설명되어 있다.



## Main 패키지

일반적으로 패키지는 라이브러리로서 사용되지만 "main"이라고 명명된 패키지는 Go Compiler에 의해 특별하게 인식된다. 패키지명이 main인 경우 컴파일러는 해당 패키지를 공유 라이브리가 아닌 실행 (executable) 프로그램으로 만든다. 그리고 이 main 패키지 안의 main() 함수가 시작점 즉 Entry Point가 된다. 패키지를 공유 라이브러리로 만들 때는 main패키지나 main함수를 사용해서는 안된다.



## 패키지 Import

다른 패키지를 프로그램에서 사용하기 위해서는 import를 사용하여 패키지를 포함시킨다. 예를 들어 Go의 표준 라이브러리인 fmt 패키지를 사용하기 위해, `import "fmt"` 와 같이 해당 패키지를 포함시킬 것을 선언해준다. Import 후에는 아래 예제처럼 fmt 패키지의 Println()함수를 호출하여 사용할 수 있다.

```go
package main
 
import "fmt"
 
func main(){
 fmt.Println("Hello")
}
```

패키지를 import할 때, Go 컴파일러는 GORROT 혹은 GOPATH환경변수를 검색하는데, 표준 패키지는 GOROOT/pkg에서 그리고 사용자 패키지나 3rd Party 패키지의 경우 GOPATH/pkg에서 패키지를 찾게 된다.

GOROOT환경변수는 Go 설치시 자동으로 시스템에 설정되지만, GOPATH는 사용자가 지정해 주어야 한다. GOPATH 환경변수는 3rd Party 패키지를 갖는 라이브러리 디렉토리나 사용자 패키지가 있는 작업 디렉토리를 지정하게 되는데, 복수 개일 경우 세미콜론(윈도우즈의 경우)를 사용하여 연결한다.



## 패키지 Scope

패키지 내에서는 함수, 구조체, 인터페이스 메서드 등이 존재하는데, 이들의 이름 (Identifier)이 첫문자를 대문자로 시작하면 public으로 사용할 수 있다. 즉, 패키지 외부에서 이들을 호출하거나 사용할 수 있게 된다. 반면 이름이 소문자로 시작하면 이는 non-public으로 패키지 내부에서만 사용될 수 있다.



## 패키지 init 함수와 alias

개발자가 패키지를 작성할 때, 패키지 실행 시 처음으로 호출되는 init() 함수를 작성할 수 있다. 즉, init함수는 패키지가 로드되면서 실행되는 함수로 별도의 호출 없이 자동으로 호출된다.

```go
package testlib
 
var pop map[string]string
 
func init() {   // 패키지 로드시 map 초기화
    pop = make(map[string]string)
}
```

경우에 따라 패키지를 import하면서 단지 그패키지 안의 init() 함수만을 호출하고자 하는 케이스가 있다. 이런 경우는 패키지 import시 `_` 라는 alias를 지정한다.

```go
package main
import _ "other/xlib"
```

만약 패키지 이름이 동일하지만, 서로 다른 버전 혹은 서로 다른 위치에서 로딩하고자 할 때는, 패키지 alias를 구분할 수 있다.

```go
import (
    mongo "other/mongo/db"
    mysql "other/mysql/db"
)
func main() {
    mondb := mongo.Get()
    mydb := mysql.Get()
    //...
}
```



## 사용자 정의 패키지 설정

개발자는 사용자 정의 패키지를 만들어 재사용 가능한 컴포넌트를 만들어 사용할 수 있다. 사용자 정의 라이브러리 패키지는 일반적으로 폴더를 하나 만들고 그 폴더 안에 `.go` 파일들을 만들어 구성한다. 하나의 서브디렉토리안에 있는 `.go` 파일들은 동일한 패키지명을 가지며, 패키지명은 해당 폴더의 이름과 같게 한다. 즉, 해당 폴더에 있는 여러 `*.go` 파일들은 하나의 패키지로 묶인다.

```go
package testlib
 
import "fmt"
 
var pop map[string]string
 
func init() {
    pop = make(map[string]string)
    pop["Adele"] = "Hello"
    pop["Alicia Keys"] = "Fallin'"
    pop["John Legend"] = "All of Me"
}
 
// GetMusic : Popular music by singer (외부에서 호출 가능)
func GetMusic(singer string) string {
    return pop[singer]
}
 
func getKeys() {  // 내부에서만 호출 가능
    for _, kv := range pop {
        fmt.Println(kv)
    }
}
```



