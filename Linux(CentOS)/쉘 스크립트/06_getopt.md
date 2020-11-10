### getopt

1. 다양한 입력 값이 존재할 경우 사용자와 개발자의 편의를 보장해준다
2. 스크립트를 보다 체계적으로 관리할 수 있게 해준다

### getopt 예시

```shell
#!/bin/sh
 
## 도움말 출력하는 함수
help() {
    echo "splt [OPTIONS] FILE"
    echo "    -h         도움말 출력."
    echo "    -a ARG     인자를 받는 opt."
    echo "    -b ARG     인자를 받는 opt2."
    exit 0
}
while getopts "a:b:h" opt
do
    case $opt in
        a) arg_a=$OPTARG
          echo "Arg A: $arg_a"
          ;;
        b) arg_b=$OPTARG
          echo "Arg B: $arg_b"
          echo "$arg_b"
          ;;
        h) help ;;
        ?) help ;;
    esac
done
 
# getopt 부분 끝나고 난 후의 인자(FILE) 읽기
shift $(( $OPTIND - 1))
file=$1
```

- getopt는 첫번째 파라미터로 옵션으로 사용될 문자열을 입력받고 다음에는 옵션으로 활용되는 변수를 사용한다
  - `"a:b:h"`
- getopt를 사용할 때 주의해야 할 점은 `:` 이다. 기본적으로 getopt는 한 개의 문자만을 구분자로 사용하며 사용할 문자열 뒤에 `:` 을 붙이게 되면 뒤에 Value가 붙게 된다는 것을 의미한다

 