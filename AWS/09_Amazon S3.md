# Amazon S3

---

### Amazon S3 개요 - Bucket

- Amazon S3는 버킷(디렉토리)에 객체(파일)을 저장할 수 있게 해준다
- Bucket은 **전역적으로 고유한 이름**을 가져야 한다
- 버킷은 리전에 종속되어있다
- 버킷의 명명규칙은 다음과 같다
  - 대문자 금지
  - 밑줄(_) 금지
  - 3-36개의 문자 길이
  - IP 금지
  - 소문자 또는 숫자로 시작해야함

---

### Amazon S3 개요 - Objects

- Object(객체, 파일)는 키를 가지고 있다
- 키는 prefix + object 이름의 조합으로 만들어지며, 키 값은 전체 경로이다
  - ex. s3://my-bucket/my_file.txt
    - prefix : my-bucket
    - object 이름 : my_file.txt
  - ex. s3://my-bucket/my_folder1/another_folder/my_file.txt
    - prefix : my-bucket/my_folder1/another_folder
    - object 이름 : my_file.txt
- 버킷 안에는 디렉토리라는 개념이 없다
  - UI에 "/"가 있어 디렉토리가 있는것처럼 보이지만 실제로는 그렇지 않다. "/"가 포함된 매우 긴 키 이름일 뿐이다.
- Object 값은 내용물이다
  - 최대 object 크기는 5TB
  - 5GB이상의 크기를 업로드할 경우, "multi-part upload"기능을 사용해야한다
- Metata - 텍스트 키/값 쌍의 목록. 시스템이나 사용자 메타데이터
- Tags - 유니코드 키/값 페어. 최대 10개. 보안/수명주기 관리에 유용
- Version ID - 버전관리가 활성화된 경우 사용

---

### Amazon S3 - Versioning

- Amazon S3에 있는 파일에 버전을 부여할 수 있다
- 버전 부여 작업은 **Bucket level** 에서만 가능하다
- 같은 키로 덮어씌울 경우 버전이 1, 2, 3, ... 으로 순차적으로 증가한다
- 버킷의 버전 관리 모범 사례
  - 의도치않은 삭제로부터 보호 (버전을 저장하는 기능)
  - 이전 버전으로 롤백하기 쉽다
- 주의사항
  - 버전 관리를 활성화 하기 전에 버전이 지정되지 않은 모든 파일의 버전은 "null"이다
  - 버전 관리를 일시 중단해도 이전 버전은 삭제되지 않는다

---

### S3 Objects 암호화

- S3의 Object를 암호화하는 4가지 방법
  - SSE-S3 : AWS에서 처리및 관리하는 키를 사용하여 S3 객체 암호화
  - SSE-KMS : AWS Key Management Service를 활용하여 암호화 키 관리
  - SSE-C : 자신의 암호화 키를 관리하려는 경우 사용
  - 클라이언트 측 암호화

---

### SSE-S3

- SSE-S3 : Amazon S3에서 처리 및 관리하는 키를 사용한 암호화
- Object가 서버 측에서 암호화됨
- AES-256암호화 유형
- 다음과 같은 헤더를 설정해야 한다. ex. “x-amz-server-side-encryption": "AES256"

![SSE-S3](./images/08_10.png)

---

### SSE-KMS

- SSE-KMS : KMS에 의해 관리되는 키를 사용한 암호화
- KMS의 장점 : 사용자 제어 + 감사 추적 가능
- Object가 서버 측에서 암호화됨
- 다음과 같은 헤더를 설정해야 한다. "x-amz-server-side-encryption": "aws:kms"

![SSE-KMS](./images/08_11.png)

---

### SSE-C

- SSE-C : AWS외부에서 고객이 완전히 관리하는 데이터 키를 사용한 서버측 암호화
- Amazon S3는 사용자가 제공한 암호화 키를 저장하지 않는다
- **HTTPS를 사용해야 한다**
- 모든 HTTP 요청에 대해 HTTP 헤더에 암호화 키를 제공해야 한다

![SSE-C](./images/08_12.png)

---

### 클라이언트 측 암호화

- Amazon S3 암호화 클라이언트와 같은 클라이언트 라이브러리
- 클라이언트는 데이터를 S3로 보내기 전에 데이터를 자체적으로 암호화 해야 한다
- 클라이언트는 S3에서 데이터를 검색한 뒤 자체적으로 해독해야 한다
- Consumer가 키와 암호화 주기를 완전히 관리해야 한다

![Client_Side_Encryption](./images/08_13.png)
