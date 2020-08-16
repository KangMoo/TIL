### for문 형식

1. ```shell
    for (( i=0 ; 조건문 ; 증감식 ))
    do
       수행문
    done
   ```

2. ```shell
   for i in  '1' '2' ...
    do
       수행문
    done
   ```



### 예시

```shell
#/bin/bash
for (( i=0;i<10;i++ ))
do
        echo "i = $i"
done
```

> ```shell
> i = 0
> i = 1
> i = 2
> i = 3
> i = 4
> i = 5
> i = 6
> i = 7
> i = 8
> i = 9
> ```

```shell
#/bin/bash
for string in "cat" "dog" "caw"
do
        echo "string = $string"
done
```

> ```shell
> string = cat
> string = dog
> string = caw
> ```



