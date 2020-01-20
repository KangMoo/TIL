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
| Deployment           | 레플리카 세트의 리비전을 관리                                |
| Service              | 파드의 집합에 접근하기 위한 경로를 정의                      |
| Ingress              | 서비스를 쿠버네티스 클러스터 외부로 노출시킴                 |
| Configmap            | 설정 정보를 정의하고 파트에 전달                             |
| 퍼시스턴트볼륨       | 파드가 사용할 스토리지 크기 및 종류를 정의                   |
| 퍼시스턴트폴륨클레임 | 퍼시스턴트 볼륨을 동적으로 확보                              |
| Storageclass         | 퍼시스턴트 볼륨이 확부되는 스토리지의 종류를 정의            |
| Statefulset          | 같은 스펙으로 모두 동일한 파드를 여러 개 생성하고 관리       |
| Job                  | 상주 실행을 목적으로 하지 않는 파드를 여러 개 생성하고 정상적인 종료를 보장 |
| 크론잡               | 크론 문법으로 스케줄링되는 잡                                |
| 시크릿               | 인증 정보 같은 기밀 데이터를 정의                            |
| 롤                   | 네임스페이스 안에서 조작 가능한 쿠버네티스 리소스의 규칙 정의 |
| 롤바인딩             | 쿠버네티스 리소스 사용자와 롤을 연걸지음                     |
| 크러스터롤           | 클러스터 전체적으로 조작 가능한 쿠버네티스 리소스의 규칙을 정의 |
| 클러스터롤바인딩     | 쿠버네티스 리소스 사용자와 클러스터롤을 연걸지음             |
| 서비스 계정          | 파드가 쿠버네티스 리소스를 조작할 때 사용하는 계정           |



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



## Kubernetes-Pod

* 컨테이너가 모인 빕합체 단위, 하나 이상의 컨테이너로 구성
* Docker|Kubernetes에서는 하나의 컨테이너라도 반드시 Pod에 포함하여 배포
* 컨테이너에서는 서로 연결할 수 있는 **중복되지 않는** Post 제공
* 고유의 IP할당
  * Cluster내부에서 접속(외부에서 접속 안됨)
* Pod에 문제가 생길 시 Pod삭제 후 자동으로 다시 생성
  * IP변경됨



## 명령어

> ```shell
> $ kubectl get pods
> $ kubectl get services
> 
> $ kubectl exec -it [PodName] [ShellScirpt]
> 
> $ kubectl logs -f [PodName] -c [ContianerNaime]
> 
> $ kubectl delete pod [PodName]
> ```