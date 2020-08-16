### switch-case문 형식

```shell
case $VAR in
	$CASE1)
    수행문1
		;;
	$CASE2)
    수행문2
    ;;
	$CASE3)
    수행문3
    ;;
*)
	수행문4
esac
```



### 예시

```shell
#!/bin/bash

echo "Enter a number between 1 and 10. "
read NUM

case $NUM in
1) echo "one" ;;
2) echo "two" ;;
3) echo "three" ;;
4) echo "four" ;;
5) echo "five" ;;
6) echo "six" ;;
7) echo "seven" ;;
8) echo "eight" ;;
9) echo "nine" ;;
10) echo "ten" ;;
*) echo "INVALID NUMBER!" ;;
esac 
```

> ```shell
> Enter a number between 1 and 10.
> 10
> ten
> ```