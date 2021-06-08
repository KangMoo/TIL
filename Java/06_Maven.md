## Maven

- 자바용 프로젝트 관리도구로, Apache Ant의 대안으로 만들어졌다
- Maven은 Ant와 마찬가지로 프로젝트의 전체적인 라이프 사이클을 관리하는 도구이다
- Maven은 필요한 라이브러리를 특정 문서 (pom.xml)에 정의해 놓으면 내가 사용할 라이브러리 뿐만 아니라 해당 라이브러리가 작동하는데 필요한 다른 라이브러리들까지 관리하여 네트워크를 통해 자동으로 다운받아준다
- Maven은 중앙 저장소를 통한 자동 의존선 관리를 중앙 저장소는  라이브러리를 공유하는 파일 서버라고 볼 수 있고, 메이븐은 자기 회사만의 중앙 저장소를 구축할 수도 있다
- 간단한 설정을 통한 배포 관리가 가능하다



### LiveCycle

- LifeCycle이란
  - 미리 정해진 빌드 순서
  - 메이븐은 프레임워크이기 때문에 동작 방식이 정해져있고, 미리 정의하고 있는 빌드 순서가 있다. 이를 라이프사이클이라 한다.
- LifeCycle
  - Default (Build) : 일반적인 빌드 프로세스를 위한 모델이다
  - Clean : 빌드시 생성되었던 파일들을 삭제하는 단계
  - Validate 프로젝트가 올바른지 확인하고 필요한 모든 정보를 사용할 수 있는지 확인하는 단계
  - Compile : 프로젝트의 소스코드를 컴파일하는 단계
  - Test : 유닛(단위) 테스트를 수행하는 단계(테스트 실패시 빌드 실패로 처리, 스킵 가능)
  - Verify : 통합 테스트 결과에 대한 검사를 실행하여 품질 기준을 충족하는지 확인하는 단계
  - Install : 패키지를 로컬 저장소에 설치하는 단계
  - Site : 프로젝트 문서와 사이트 작성, 생성하는 단계
  - Deploy : 만들어진 package를 원격 저장소에 release하는 단계
- 최종 빌드 순서 : Compile -> Test -> Package
  - Compile : src/main/java 디렉토리 아래의 모든 소스 코드가 컴파일된다
  - Test : src/test/java, src/test/resource 테스트 자원 복사 및 테스트 소스 코드 컴파일된다
    - junit : 단위테스트 프레임워크, 테스트 단계를 거치기 위해 의존 설정을 해준다
  - Pcakaging : 컴파일과 테스트가 완료된 후, jar, war같은 형태로 압축한다

### Pahse (단계)

- Build Lifecycle의 각 단계를 Phase라고 한다
- Phase는 의존 관계를 가지고 있어 Phase가 수행되려면 이전 단계의 Pahse가 모두 수행되어야 한다.
- 즉,  모든 빌드단계는 이전 단계가 성공적으로 실행되었을 때 실행되는 것이 Dependency이다

### Gaol

- 특정 작업, 최소한의 실행 단위 (taks)
- 하나의 플러그인에서는 여러 작업을 수행할 수 있도록 지원하며, 플러그인에서 실행할 수 있는 각각의 기능(명령)을 Goal이라고 한다
- 플러그인의 goal을 실행하는 방법은 다음과 같다

> `mvn groupId:artifactId:version:goal` 
>
> or
>
> `mvn plugin:goal`



## Maven 설정파일

### settings.xml

- 메이븐 빌드 툴과 관련한 설정파일
  - 메이븐을 빌드할 때 의존관계에 있는 라이브러리, 플러그인을 중앙 저장소에서 개발자 PC로 다운로드하는 위치(fhzjfwjwkdth)의 기본 설정 `USER_HOME/.m2/repository`인데 settings.xml에 의해 원하는 로컬 저장소의 경로를 지정, 변경할 수 있다
- MAVEN_HOME/conf디렉토리에 위치 (메이븐 설치 시 기본제공)
- settings.xml의 설정

### POM(프로젝트 객체 모델)

- POM은 pom.xml파일을 말하며, pom.xml은 메이븐을 이용하는 프로젝트의 root에 존재하는 xml파일이다
  - 하나의 자바 프로젝트 빌드 툴을 maven으로 설정하면 프로젝트 최상위 디렉토리에 pom.xml이라는 파일이 생성된다
- Maven의 기능을 이용하기 위해 POM이 사용된다
- 파일은 프로젝트마다 1개이며, pom.xml만 보면 프로젝트의 모든 설정, 의존성 등을 알 수 있다
- 다른 파일이름으로 지정할 수 있다. (`mvn -f 파일명.xml test`) 하지만 pom.xml로 사용하기를 권장한다

#### pom.xml의 요소

- modelVersion : POM model의 버전
- parent : 프로젝트 계층 정보
- groupId : 프로젝트를 생성하는 조직의 고유 아이디를 결정한다
- atrifactId : 프로젝트 빌드시 파일 대표이름이다. groupId 내에서 유일해야 한다.
- version : 프로젝트의 현재 버전, 개발중일때는 SNAPSHOT을 접미사로 사용.
- packaging : 패키징 유형 (jar, war, ear 등)
- name : 프로젝트, 프로젝트 이름
- description : 프로젝트에 대한 간략한 설명
- url : 프로젝트에 대한 참고 Reference 사이트
- properties : 버전 관리시 용이하다. 예) 해당 자바 버전을 선언하고  dependencies에서 다음과 같이 활용 가능하다. `<version>${java.version}</version>`
- dependencies : dependencies태그 안에는 프로젝트와 의존 관계에 있는 라이브러리들을 관리한다
- build : 빌드에 사용할 플러그인 목록



