## grep

- grep 명령은 파일 내에서 지정한 패턴이나 문자열을 찾은 후에, 그 패턴을 포함하고 있는 모든 행을 표준 출력해준다. 물론 한 디렉토리 내에서 지정한 패턴을 포함하는 파일을 출력할 수도 있다.



### 사용법

```shell
grep [OPTION...] PATTERN [FILE...]
-E        : PATTERN을 확장 정규 표현식(Extended RegEx)으로 해석.
-F        : PATTERN을 정규 표현식(RegEx)이 아닌 일반 문자열로 해석.
-G        : PATTERN을 기본 정규 표현식(Basic RegEx)으로 해석.
-P        : PATTERN을 Perl 정규 표현식(Perl RegEx)으로 해석.
-e        : 매칭을 위한 PATTERN 전달.
-f        : 파일에 기록된 내용을 PATTERN으로 사용.
-i        : 대/소문자 무시.
-v        : 매칭되는 PATTERN이 존재하지 않는 라인 선택.
-w        : 단어(word) 단위로 매칭.
-x        : 라인(line) 단위로 매칭.
-z        : 라인을 newline(\n)이 아닌 NULL(\0)로 구분.
-m        : 최대 검색 결과 갯수 제한.
-b        : 패턴이 매치된 각 라인(-o 사용 시 문자열)의 바이트 옵셋 출력.
-n        : 검색 결과 출력 라인 앞에 라인 번호 출력.
-H        : 검색 결과 출력 라인 앞에 파일 이름 표시.
-h        : 검색 결과 출력 시, 파일 이름 무시.
-o        : 매치되는 문자열만 표시.
-q        : 검색 결과 출력하지 않음.
-a        : 바이너리 파일을 텍스트 파일처럼 처리.
-I        : 바이너리 파일은 검사하지 않음.
-d        : 디렉토리 처리 방식 지정. (read, recurse, skip)
-D        : 장치 파일 처리 방식 지정. (read, skip)
-r        : 하위 디렉토리 탐색.
-R        : 심볼릭 링크를 따라가며 모든 하위 디렉토리 탐색.
-L        : PATTERN이 존재하지 않는 파일 이름만 표시.
-l        : 패턴이 존재하는 파일 이름만 표시.
-c        : 파일 당 패턴이 일치하는 라인의 갯수 출력.
```

### 정규표현식

| 메타문자   | 기능                                                         | 예시           | 예 설명                                                      |
| ---------- | ------------------------------------------------------------ | -------------- | ------------------------------------------------------------ |
| ^          | 행의 시작 지시자                                             | '^hello'       | hello로 시작하는 모든 행                                     |
| $          | 행의 끝 지시자                                               | 'hello$'       | hello로 끝나는 모든 행                                       |
| .          | a single character 하나의 문자와 대응                        | 'h...o'        | h와 o사이에 딱 세 글자가 있어야 함                           |
| ? (egrep)  | the preceding character matches 0 or more 1 times only 앞 문자가 0 또는 한개로 된 것을 의미 | 'hel?o'        | hello, helo 등을 검색                                        |
| .*         | Nothing or any number of charaters 0이거나 그 이상의 문자 (=all) | 'hel*o'        | helo, helao, helfdfadfo 등 검색                              |
| *          | zero or more occurrences of the previous character 셸 상태에서의 *와 다르게 정규표현식에서 *는 바로 앞 문자와 연계되어 있다. 앞 문자가 하나이거나 반복되어진 것을 의미 | 'hel*o'        | helo, hello, helllo, hellllo 등을 검색함.                    |
| \| (egrep) | pipe 기호랑 똑같은 기호. \|가 정규식에서 사용되면 or(또는)이라는 뜻을 가짐 | 'or\|is\|go'   | oranges, Lisa, mangoes 등등 셋 중 하나라도 들어있으면 검색함 |
| []         | []는 문자 리스트 중의 한 문자를 나타낸다.                    | 'New[abc]'     | Newa, Newb, Newc 등을 검색함                                 |
| [0-9]      | 0,1,2,3,4,5,6,7,8 또는 9                                     | '[0-9]'        | 0,1,2,3,4,5,6,7,8 또는 9                                     |
| [^]        | []안에 들어가면 ^는 not 즉 제외의 의미를 갖는다.             | '[^1-3]'       | 1,2,3을 제외한 모든 문자 1,2,3으로 조합하지 않은 문자가 하나라도 있는 경우 |
| \          | Ignores the special meaning of the character following it. 지정문자 특징같은거 무시해준다 | 'New\.\[abc\]' | New.[abc]와 같은 말을 찾는다 .이나 []의 패턴식을 무시하고 문자 그대로를 의미한다. |
| \<         | 단어의 시작 지시자                                           | '\<hello'      | hello로 시작하는 단어를 포함하는 행과 대응                   |
| \>         | 단어의 끝 지시자                                             | 'hello\>'      | hello로 끝나는 단어를 포함하는 행과 대응                     |
| x\{m\}     | 문자 x를 m번 반복한다                                        | 'o\{5w}'       | 문자 o가 5회 연속적으로 나오는 모든 행과 대응                |
| x\{m,\}    | 적어도 m번 반복한다.                                         | 'o\{5,\}'      | 문자 o가 최소한 5회 반복되는 모든 행과 대응                  |

### 사용 예

| grep 사용 예                                                 | 명령어 옵션                |
| ------------------------------------------------------------ | -------------------------- |
| 대상 파일에서 문자열 검색                                    | `grep "STR" [FILE]`        |
| 현재 디렉토리 모든 파일에서 문자열 검색                      | `grep "STR" *`             |
| 특정 확장자를 가진 모든 파일에서 문자열 검색                 | `grep "STR" *.ext`         |
| 대소문자 구분하지 않고 문자열 검색                           | `grep -i "STR" [FILE]`     |
| 매칭되는 PATTERN이 존재하지 않는 라인 선택                   | `grep -v "STR" [FILE]`     |
| 단어(Word) 단위로 문자열 검색                                | `grep -w "STR" [FILE]`     |
| 검색된 문자열이 포함된 라인 번호 출력                        | `grep -n "STR" [FILE]`     |
| 하위 디렉토리를 포함한 모든 파일에서 문자열 검색             | `grep -r "STR" *`          |
| 최대 검색 결과 갯수 제한                                     | `grep -m 100 "STR" FILE`   |
| 검색 결과 앞에 파일 이름 표시                                | `grep -H "STR" *`          |
| 문자열 A로 시작하여 문자열 B로 끝나는 패턴 찾기              | `grep "A.*B" *`            |
| 0-9 사이 숫자만 변경되는 패턴 찾기                           | `grep "STR[0-9]" *`        |
| 문자열 패턴 전체를 정규 표현식 메타 문자가 아닌 일반 문자로 검색하기 | `grep -F "*[]?..." [FILE]` |
| 정규 표현식 메타 문자를 일반 문자로 검색하기                 | `grep "\*" [FILE]`         |
| 문자열 라인 처음 시작 패턴 검색하기                          | `grep "^STR" [FILE]`       |
| 문자열 라인 마지막 종료 패턴 검색하기                        | `grep "$STR" [FILE]`       |

### 기타

#### 컬러 출력

- --color=auto
- --color=always
- --color=never

`grep --color` 앞부분에 `GREP_COLOR="1;32"`로 패턴과 매칭되는 부분의 색깔을 지정할 수 있다

- `GREP_COLOR="1;32"` : 초록색
- `GREP_COLOR="1;34"` : 보라색
- `GREP_COLOR="1;36"` : 하늘색

### Context Line Control

| 옵션   | 내용                                                  |
| ------ | ----------------------------------------------------- |
| -B NUM | 패턴 매칭된 이전 행도 NUM 개수만큼 출력               |
| -A NUM | 패턴 매칭된 이후 행도 NUM 개수 만큼 출력              |
| -C NUM | 패턴 매칭된 행을 중심으로 앞/뒤 행도 NUM개수만큼 출력 |

