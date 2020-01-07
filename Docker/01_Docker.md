## Docker

#### Docker

* 컨테이너 기반의 오픈소스 가상화 플랫폼
* 백엔드 프로그램, 데이터베이스, 메시지 큐 -> 컨테이너로 추상화 가능
* **일반 PC, AWS, Azure, Google cloud 등에서 실행 가능**



#### Docker를 사용하는 의의

* 변화하지 않은 실행 환경으로 멱등성 확보
* 코드를 통한 실행환경 구축 및 어프리케이션 구성 (IaC)
* 실행 환경과 어플리케이션 일체화로 이식성 향상
* 시스템을 구성하는 어플리케이션 및 미들웨어의 관리 용이성



#### Docker의 특징

* 기존 가상화 방식 -> OS를 가상화
  * VMWare, VirtualBox **(Host OS위에 Guest OS전체를 가상화)**
  * **무겁고 느림**
* CPU의 가상화 기술 이용 방식 -> **Kernel-based Virtual Machine**
  * 전체 OS를 가상화하지 않음, 호스트 형식에 비해 속도 향상
  * OpenStack, AWS등의 클라우드 서비스
  * **추가적인 OS**는 여전히 필요, 성능 문제
* 프로세스 격리 -> 리눅스 컨테이너
  * CPU나 메모리는 프로세스에 필요한 만큼만 추가로 사용
  * 성능 손실 거의 없음
  * 컨테이너들 사이는 서로 영향을 주지 않음
  * 컨테이너 생성 속도 빠름 (1~2초 내)



#### Docker Image

* 컨테이너 실행에 필요한 파일과 설정 값 등을 포함 -> **상태값X, Immutable**
* 실체화 -> **Container**



#### Docker Container

* 도커 이미지를 기반으로 생성되며, 파일 시스템과 어플리케이션이 구체화 되어 실행되는 상태



#### 코드로 관리하는 인프라 와 불변 인프라

* Infrastructure as Code & Immutable Insfrastructure



#### Docker활용

1. 일단 개발..
2. `npm install`, `npm start`
3. 



## 명령어

* 기본명령어

  > ```shell
  > $ docker login // 로그인
  > $ docker version // 버전 확인
  > ```

* 이미지

  > ```shell
  > $ docker image ls	// 이미지 리스트 확인
  > $ docker image pull ~	// ~로부터 이미지 당겨오기
  > ```

* 컨테이너

  > ```shell
  > $ docker contianer ls	// 컨테이너 리스트 확인
  > $ docker ps	//컨테이너 리스트 확인
  > 		-a // 모든 컨테이너 리스트 확인
  > 		-q // 컨테이너 이름만 확인
  > $ docker container rm	// 컨테이너 삭제 (먼저 stop되어 있어야 함)
  > $ docker container prun	// stop되어 있는 컨테이너 모두 삭제
  > ```

* 실행 & 종료

  > ```shell
  > $ docker run ~	// ~이미지 실행하여 컨테이너로 생성
  > 		-p [port]	// [port]번호로 포트 연결 (앞에 값이 없으면 호스트 포트 알아서 연결) \
  > 					// [host포트]:[contianer포트] 호스트 포트와 컨테이너 포트 연결
  > 		--name [name]	// 컨테이너 이름 지정
  > 		-d			// 백그라운드로 실행
  > $ docker stop ~ // ~에 해당하는 이름 혹은 ID의 컨테이너 종료 (뒤에 여러 개 올 수 있음)
  > 
  > ```

* 기타

  > ```shell
  > $ docker stop $(docker ps -q)	// 실행중인 모든 컨테이너 중지
  > $ docker rm $(docker ps -qa)	// 모든 컨테이너 제거
  > ```
  >
  > 



