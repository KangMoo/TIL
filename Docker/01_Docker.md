﻿## Docker

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



#### Dockerfile

* Docker Image를 생성하기 위한 스크립트 파일
* 자체 DSL(Domain-Specific language) 언어 사용 -> 이미지 생성과정 기술
  * 서버에 프로그램을 설치하는 과정을 메모장이 아닌 Dockerfile로 관리
  * 소스와 함께 버전 관리가 되며, 누구나 사용 가능

* 대소문자 구분 해야함
* 확장자 없음
* Dockerfile하나 당 컨테이너 하나! (FROM 하나!)
  * 추가적인 설치는 RUN명령어 등을 사용



#### Dockerfile 사용법 예제

* 일반적인 개발 방식 예시

  > 1. Node 설치
  > 2. 개발
  > 3. `npm install`
  > 4. `npm start`

* docker를 사용한 개발 방식 예시

  > 1. Dockerfile 생성
  >
  > >Dockerfile 예제1
  > >
  > >```dockerfile
  > >FROM alpine
  > >WORKDIR [dir]
  > >COPY [소스][대상]
  > >RUN curl -o- https://raw.githubusercontent.com/creationix/nvm/v0.33.11/install.sh | bash
  > >RUN npm install
  > >CMD ["npm", "start"]
  > >```
  >
  > > Dockerfile 예제2
  > >
  > > ```dockerfile
  > > FROM node:alpine
  > > WORKDIR [dir]
  > > COPY [소스][대상]
  > > RUN npm install
  > > CMD ["npm", "start"]
  > > ```
  >
  >   2. Docker이미지 생성
  >
  > > 예시
  > >
  > > `docker build -t hkm0629/simpleweb:latest .`
  >
  > 3. 컨테이너 생성
  >
  > > 예시
  > >
  > > `docker run -d -p 8080 hkm0629/simpleweb:latest`

  



#### 코드로 관리하는 인프라 와 불변 인프라

* Infrastructure as Code & Immutable Insfrastructure





## 명령어

* 기본명령어

  > ```shell
  > $ docker login # 로그인
  > $ docker version # 버전 확인
  > ```

* 이미지

  > ```shell
  > $ docker images		# 이미지 리스트 확인
  > $ docker image ls	# 이미지 리스트 확인
  > 
  > $ docker image pull [ImageName]	# [ImageName]로부터 이미지 pull
  > $ docker image push [ImageName]	# [ImageName]로 이미지 push
  > 
  > $ docker image build -t [저장소이름] [디렉토리]	# 이미지 빌드
  > 
  > $ docker image rm [ImageName]	# 이미지 삭제
  > $ docker rmi [ImageName]		# 이미지 삭제
  > ```

* 컨테이너

  > ```shell
  > $ docker contianer ls	# 컨테이너 리스트 확인
  > 
  > $ docker ps	#컨테이너 리스트 확인
  > 		-a # 모든 컨테이너 리스트 확인
  > 		-q # 컨테이너 이름만 확인
  >         
  > $ docker restart [ContainerID]	# 컨테이너 재시작
  > $ docker container rm	# 컨테이너 삭제 (먼저 stop되어 있어야 함)
  > 
  > $ docker container prun	# stop되어 있는 컨테이너 모두 삭제
  > 
  > $ docker exec -i -t [ContainerID] [command]	# 컨테이너의 Shell에 [command] 명령 전달&수행
  > $ docker exec -it [ContainerID] [command]	# 컨테이너의 Shell에 [command] 명령 전달&수행
  > $ docker exec -it [ContainerID] sh	# 컨테이너에 입력모드로 터미널 연결
  > ```

* 컨테이너 실행 & 종료

  > ```shell
  > $ docker run [ImageName]	# [ImageName]이미지 실행하여 컨테이너로 생성
  > 		-p [port]	# [port]번호로 포트 연결 (앞에 값이 없으면 호스트 포트 알아서 연결) \
  > 					# [host포트]:[contianer포트] 호스트 포트와 컨테이너 포트 연결
  > 		--name [name]	# 컨테이너 이름 지정
  > 		-d			# 백그라운드로 실행
  > 		-v [host경로]:[Container경로]	# 볼륨 마운트 (공유폴더). (컨테이너를 마운트 시킬 수도 있음)
  > $ docker stop [ContainerID] # [ContainerID] 에 해당하는 컨테이너 종료 (뒤에 여러 개 올 수 있음)
  > ```

* 기타

  > ```shell
  > $ docker tag [ImgName]:[tag] [ImgName]:[NewTag]	# 태그 추가
  > 
  > $ docker logs [ContainerName]	# 컨테이너의 로그 확인
  > 
  > $ docker stop $(docker ps -q)	# 실행중인 모든 컨테이너 중지
  > 
  > $ docker rm $(docker ps -qa)	# 모든 컨테이너 제거
  > 
  > $ docker inspect [ContainerName]	# 컨테이너의 정보 확인
  > ```



#### docker-compose

* 도커 어플리케이션을 정의하고 실행하는 도구
  * 도커 명령어를 손 쉽게 관리하게 해줌
    * docker명령어가 아니다!
  * 각 컨테이너별로 별도의 Docker 명령어 실행
  * 한 번에 여러 개의 컨테이너 동시 실행

* yml파일의 내용을 해석하여 실행

  * 들여쓰기 딱딱 맞아야됨

* yml파일 구성

  > ```yml
  > version: '3.1'
  > services:
  >     servicename:
  >         image: #optional
  >         ports: #optional
  >         environment: #optional
  >         volumes: #optional
  >     servicename2:
  > volumes: #optional
  > network: #optional
  > ```
  >
  > 들여쓰기 딱딱 맞아야됨.
  >
  > services의 내용만 바꿀 수 있음

  > 예시
  >
  > ```yml
  > version: "3"
  > services:
  >     mysql:
  >         image: example/echo:latest
  >         ports:
  >             - 9000:8080
  > ```

  > docker-composer를 통한 실행
  >
  > `$docker compose up`
  >
  > * -d 를 이용하여 백그라운드로 실행 가능
  >
  > docker-compose를 통한 종료
  >
  > `$ docker -compose down`

* 장점

  * 서비스를 여러개 만들 수 있음
    * 그러면서 속성값도 변경해줄 수 있음



#### 기타사항

* 컨테이너의 `CONTAINER ID`는 Host name이다
