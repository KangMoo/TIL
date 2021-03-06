## CSCF

- CSCF는 호 및 세션처리에 관련된 부분을 담당하는 기능으로 인입호에 대한 게이트웨이로서의 기능과 호제어 기능, SPD 기능, 주소 처리 기능 등으로 구성된다
- 인입호에 대한 게이트웨이로서의 기능은 Entry Point로 동작하고 입력호에 대한 라이퉁을 수행하는 것을 의미한다. 또한 호 스크리닝 및 포워팅과 같은 입력ㄱ호에 대한 서비스 트리거링을 수행하며 HSS와의 통신을 담당한다.
- 호 제어기능은 호의 설정과 종료 및 상태/이벤트 관리, 다자간 서비스를 위한 MRF와의 상호작용, 과금을 위한 호 이벤트 보고, 응용 레벨 등록의 수신 및 처리 등을 담당한다.
- SPD는 홈 도메인의 HSS와 통신하여 사용자 프로파일 정보를 관리하며 사용자의 처음 엑세스 시 홈 도메인을 알려주는 기존망의 VLR과 유산한 기능을 수행한다.
- CSCF는 그 기능에 따라 PCSF(Proxy), I-CSCF(Interroagting), S-CSCF(Serving)로 나뉠 수 있다.

### P-CSCF

- Proxy Call State Control Function
- P-CSCF는 UE가 GPRS 엑세스를 통해 IMS에 접속할 때 처음 만나는 지점이다. 다시 말해 사용자가 IMS망에 접속하는 첫 포인트 지점이고  GGSN과 같은 도메인에 존재한다. 3GPP에서는 UE가 P-CSCF를 찾는데 DHCP를 이용하거나 PDP Context를 통해서 주소를 얻는 방법을 제시하고 있다.

> **PDP Context Activation을 통해서 P-CSCF IP 주소를 얻어내는 절차**
>
> ![P-CSCF](./images/CSCF_1.jpg)

