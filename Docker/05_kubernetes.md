## Kubernetes 개요

* Docker Container 운영을 자동화하기 위한 컨테이너 오케스트레이션 툴
  * 컨테이너 배포 및 배치 전략
  * Scale in/Scale out
  * Service dicrovery
  * 기타 운용
* 구글의 Brog 프로젝트에서 시작
* 2017 Docker에 정식으로 통합된 사실상 표준

* Swarm보다 충실한 기능을 갖춘 컨테이너 오케스트레이션 시스템



## Kubernetes주요 개념

| 리소스               | 용도                                                         |
| -------------------- | ------------------------------------------------------------ |
| Node                 | 컨테이너가 배치되는 서버                                     |
| Namespace            | 쿠버네티스 클러스터 안의 가상 클러스터                       |
| Pod                  | 컨테이너 집합 중 가장 작은 당뉘. 컨테이너의 실행 방법 정의   |
| Replica set          | 캍은 스펙을 갖는 파드를 여러 개 생성하거 관리하는 역할       |
| 디플로이먼트         | 레플리카 세트의 리비전을 관리                                |
| Service              | 파드의 집합에 접근하기 위한 경로를 정의                      |
| Ingress              | 서비스를 쿠버네티스 클러스터 외부로 노출시킴                 |
| 컨피그맵             | 설정 정보를 정의하고 파트에 전달                             |
| 퍼시스턴트볼륨       | 파드가 사용할 스토리지 크기 및 종류를 정의                   |
| 퍼시스턴트폴륨클레임 | 퍼시스턴트 볼륨을 동적으로 확보                              |
| 스토리지클래스       | 퍼시스턴트 볼륨이 확부되는 스토리지의 종류를 정의            |
| 스테이트풀세트       | 같은 스펙으로 모두 동일한 파드를 여러 개 생성하고 관리       |
| 잡                   | 상주 실행을 목적으로 하지 않는 파드를 여러 개 생성하고 정상적인 종료를 보장 |
| 크론잡               | 크론 문법으로 스케줄링되는 잡                                |



## Kubernetes 설치

* Minikube
  * 이전에는 로컬 환경에서 Kubernetes를 구축하기 위해 사용
* 윈도우 설치
  * 바탕화면 트레이 -> 도커 아이콘 -> Setting -> Kubernetes 탭 -> Enable Kubernetes 체크 (Deploy docker stacks to Kubernetes by default & Show system containers도 체크)



#### kubectl

* Kubernetes를 다루기 위한 Command Line Interface
* 윈도우 설치
  * https://storage.googleapis.com/kubernetes-release/release/v1.17.0/bin/windows/amd64/kubectl.exe
  * 다운로드, PATH 추가
* 최신 도커는 굳이 설치할 필요 없음... (이미 설치 되어있음)





## Dashboard 설치

* Kubernetes에 배포된 컨테이너 등에 대한 정보를 보여주는 관리 도구
* http://localhost:8001/api/v1/namespaces/kub-system/services/https:kubernetes-dashboard:/proxy/
* 실 운영상태에선 사용하지 않음
  * 암호가 도출될 수 있기 때문에
  * 배포용으로 사용하고 실 운영시에는 Dashboard사용 X

> `$ kubectl apply -f https://raw.githubusercontent.com/kubernetes/dashboard/v1.8.3/src/deploy/recommended/kubernetes-dashboard.yaml`
>
> 



