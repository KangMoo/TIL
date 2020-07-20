## RPM

- Rad Hat Pacakger Manager

### RPM 설치/확인/제거

- 설치

  ```shell
  $ rpm -ivh [패키지명]
  ```

- 확인

  ```shell
  $ rpm -qa | grep [패키지명]
  ```

- 제거

  ```shell
  $ rpm -ev [패키지명]
  ```

- 업그레이드

  ```shell
  $ rpm -Uvh [패키지명]
  ```



### 기타

- 파일이 속한 패키지 찾기

  ```shell
  $ rpm -qf 파일
  ```

- rpm 패키지 정보 보기 (info)

  ```shell
  $ rpm -qi 설치된패키지명
  ```

  ```shell
  $ rpm -qip 파일명.rpm
  ```

- rpm 내부 파일목록 보기

  ```shell
  $ rpm -ql 설치된패키지명
  ```

  ```shell
  $ rpm -ql 설치된패키지명
  ```

- rpm 내부 설정파일 확인

  ```shell
  $ rpm -ql 설치된패키지명
  ```

  ```shell
  $ rpm -ql 설치된패키지명
  ```

- rpm 내부 문서파일 확인

  ```shell
  $ rpm -ql 설치된패키지명
  ```

  ```shell
  $ rpm -ql 설치된패키지명
  ```

- rpm 내부 스크립트 확인

  ```shell
  $ rpm -ql 설치된패키지명
  ```

  ```shell
  $ rpm -ql 설치된패키지명
  ```

  