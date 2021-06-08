### while문 형식

```shell
while [ 조건문 ]
 do
   수행문
 done
```



### 예시

```shell
#!/bin/bash
i=1
while [ $i -le 5 ]
do
	echo $i
	i=$(($i+1))
done
```

> ```shell
> 1
> 2
> 3
> 4
> 5
> ```

```shell
#!/bin/bash
i=1
while [ $i -le 5 ]
do
	echo $i
	i=$(($i+1))
	if [ $i -eq 3 ]
	then
		break
	fi
done
```

> ```shell
> 1
> 2
> ```





