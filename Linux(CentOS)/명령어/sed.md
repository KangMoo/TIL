## sed

- Stream EDitor의 약자로, 텍스트를 파싱하고 변형하는 텍스트 편집 도구
- 

### sed 명령어 사용법

- `$ sed [옵션] 스트립트 입력파일1 [입력파일2 ...]`

### 옵션

| 옵션 / 스위치       | 별칭               | 기능                                                         |
| ------------------- | ------------------ | ------------------------------------------------------------ |
| `-n`                | –quiet, –silent    | 읽어들인 라인을 암시적으로 자동출력하는 것을 중단한다.       |
| `-e`                | -expression=script | 실행될 명령에 스크립트를 추가한다                            |
| `-f script_file`    | –file=script_file  | 스크립트 파일의 내용을 가져와서 추가로 실행한다.             |
| `–follow-`symlinks` |                    | 제자리 처리시에 심볼릭 링크를 따르도록 한다. 하드링크는 깨진다. |
| `-i[SUFFIX]`        | –in-place[=SUFFIX] | 파일을 제자리 처리한다. (즉 변경된 내용을 파일에 적용한다.)  |
| `-c`                |                    | 제자리 처리시에 사본을 이용한다.                             |
| `-l N`              | –copy              | 한줄의 길이를 정의한다.                                      |
|                     | –line-length=N     | 모든 GNU확장을 제외한다.–line-length=N                       |
| `-r`                | –posix             | 확장된 정규식 패턴을 사용한다.                               |
| `-s`                | –regexp-extended   | 파일을 하나의 긴 스트림이 아닌 분리된 데이터들로 처리한다.   |



### 찾기, 출력

- `sed -n '/abd/p' list.txt`  list.txt 파일을 한줄씩 읽으면서(-n : 읽은 것을 출력하지 않음) abd 문자를 찾으면 그 줄을 출력(p)


### 치환

- `sed 's/addrass/address/' list.txt` : addrass를 address로 치환하여 출력
- `sed 's/addrass/address/' list.txt > list2.txt` : addrass를 address로 치환하여 저장
- `sed 's/\t/\ /' list.txt` : 탭문자를 스페이스로 치환


### 삭제

- `sed '/TD/d' list.txt` TD 문자가 포함된 줄을 삭제하여 출력
- `sed '/Src/!d' list.txt` Src 문자가 있는 줄만 지우지 않음
- `sed '1,2d' list.txt` 처음 1줄, 2줄을 삭제
- `sed '/^$/d list.txt` 공백라인을 삭제하는 명령 (★★★)

