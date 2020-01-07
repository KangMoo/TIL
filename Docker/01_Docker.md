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



#### 코드로 관리하는 인프라 와 불변 인프라

* Infrastructure as Code & Immutable Insfrastructure







