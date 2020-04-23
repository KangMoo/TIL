출처 :[https://zetawiki.com/wiki/%EB%A6%AC%EB%88%85%EC%8A%A4_$JAVA_HOME_%ED%99%98%EA%B2%BD%EB%B3%80%EC%88%98_%EC%84%A4%EC%A0%95](https://zetawiki.com/wiki/리눅스_$JAVA_HOME_환경변수_설정)



## 1. 확인

> `# echo $JAVA_HOME`
>
> - 

> `# javac -version`
>
> - `javac 1.8.0_222`



## 2. Javac 위치 확인

> `# which javac`
>
> - `/usr/bin/javac`

> `# readlink -f /usr/bin/javac`
>
> - `/usr/lib/jvm/java-1.8.0-openjdk-1.8.0.222.b10-0.el7_6.x86_64/bin/javac`

- javac의 실제 위치는 `/usr/lib/jvm/java-1.8.0-openjdk-1.8.0.222.b10-0.el7_6.x86_64/bin/`
- $JAVA_HOME은  `/usr/lib/jvm/java-1.8.0-openjdk-1.8.0.222.b10-0.el7_6.x86_64/` 로 설정해줘야 함



## 3. $JAVA_HOME 설정 방법

1. 일시적으로 적용하는 방법

> `# export JAVA_HOME=/usr/lib/jvm/java-1.8.0-openjdk-1.8.0.222.b10-0.el7_6.x86_64/`

1. 환경변수를 설정하는 방법
   - `/etc/profile` 하단에 내용 추가

> `# vi /etc/profile`
>
> 하단에 `export JAVA_HOME=/usr/lib/jvm/java-7-openjdk-amd64` 내용 추가



## 4. 확인2

- echo로 확인

> `# echo $JAVA_HOME`
>
> - `/usr/lib/jvm/java-1.8.0-openjdk-1.8.0.222.b10-0.el7_6.x86_64/`
>
> `$JAVA_HOME/bin/javac -version`
>
> - `javac 1.8.0_222`

