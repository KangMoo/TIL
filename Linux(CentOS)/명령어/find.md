## find

- 파일 및 디렉토리를 찾을 때 사용

```shell
# 파일 이름이 숫자.txt 인 것들
$ find . -regex ".*/[0-9]+.txt"
```



### 옵션

- `-mtime`, `-atime`, `-ctime` 등을 사용하면 접근/생성/변경 등의 날짜를 기준으로 파일을 찾을 수 있다.

```shell
# 30일 보다 이전에 수정된 파일들
$ find . -mtime +30
 
# 딱 30일 전에 수정된 파일
$ find . -mtime 30
 
# 시간 비교시 해당 일의 시작 시간(00:00) 부터
$ find . -mtime 30 -daystart
 
# 30일 전 이후(오늘 부터 30일 전까지 사이)에 수정된 파일
$ fine . -mtime -30
```

- depth

```shell
-maxdepth 1 : 최대 디렉토리 한 단계만
-mindepth 1 : 명령행 지정된 경로(한 단계)를 제외하고 찾기
```

- type

```shell
-type d : 디렉토리
-type f : 일반 파일
-type l : 심볼릭 링크
-type s : socket
```



### 예시

- 파일 이름만 가져오기

```shell
$ find ./dir1 -type f -exec basename {} \;
$ find /dir1 -type f -printf "%f\n"
```

- 동일 이름 파일 목록 얻기

```shell
$ find . -type f -printf "%f\n" | sort | uniq -c | grep -v ' 1 '
```

- 하위 디렉토리에 존재하는 파일 갯수

```shell
$ find */ | cut -d/ -f1 | uniq -c
```

