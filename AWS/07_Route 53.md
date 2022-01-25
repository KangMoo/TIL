# Route 53

## Route 53 - Records

- 도메인에 대한 트래픽 라우팅 방법
- 각 레코드에 포함되는 정보
  - Domain/subdomain Name - ex. example.com
  - Record Type - ex. A or AAAA
  - Value - ex. 12.34.56.78
  - Routing Policy - Route 53이 쿼리에 응답하는 방식
  - TTL - 레코드가 DNS 확인
- Route 53은 다음 DNS 레코드 타입을 지원한다
  - 필수 : A / AAAA/ CNAME / NS
  - 고급 : CAA / DS / MX / NAPTR / PTR / SOA / TXT / SPF / SRV

> DNS 용어
> 
> - Domain Registrar : 도메인 등록기관. Amazon Route 53, GoDaddy, ...
> - DNS Records : A, AAAA, CNAME, NS
> - Zone File : DNS 레코드 포함
> - Name Server : DNS 쿼리 확인
> - Top Level Domain(TLD) : 최상위 도메인. .com, .us, .in, .gov, .org, ...
> - Second Level Domain(SLD) : 2단계 도메인. amazin.com, google.com, ...
> 
> ![](./images/07_01.png)
> ![](./images/07_02.png)


### Route 53 - Record Types

- A : 호스트네임을 IPv4에 매핑
  - IP주소와 도메인 주소를 매핑할 때 사용하는 레코드로써 하나의 도메인에 여러 개의 IP 주소를 등록할 수 있습니다.
  - ex. naver.com의 A레코드를 조회하였을 때 125.209.222.141, 125.209.222.142, ...
- AAAA : 호스트네임을 IPv6에 매핑
- CNAME : 호스트네임을 다른 호스트네임에 매핑
  - 대상은 A 도는 AAAA 레코드가 있어야 하는 도메인 이름
  - DNS 네임스페이스의 최상위 노드(Zone Apex)에 대한 CNAME 레코드는 생성할 수 없음
  - ex. example.com에 대해서는 생성할 수 없지만 www.example.com에 대해서는 생성할 수 있음
- NS : 호스팅 영역의 이름. (Name Server)
  - 도메인에 대한 트래픽 라우팅 방법 제어
  - ex. naver.com의 NS record = e-ns.naver.com, ns1.naver.com, ns2.naver.com
- SOA : 도메인 정보를 가지고 있는 레코드
  - ex. naver의 경우 ns1.naver.com webmaster.naver.com 2021012809 21600 1800 1209600 180

> Zone Apex
> 
> Zone Apex는 루트 도메인, 네이키드 도메인(Naked Domain)이라고도 하며. 이름 그대로 서브 도메인이 붙지 않은 상태를 뜻한다. DNS RFC(RFC 1033)에 루트 도메인은 A 레코드만 지정할 수 있다고 정의되어 있다.

### Route 53 - Hosted Zones

- 트래픽을 도메인 및 해당 하위 도메인으로 라우팅하는 방법을 정의하는 레코드용 컨테이너
- **Public Hosted Zones** : 인터넷 (공개 도메인 이름)에서 트래픽을 라우팅 하는 방법을 지정하는 레코드를 포함
- **Private Hosted Zones** : 하나 이상의 VPC내에서 트래픽을 라우팅하는 방법을 지정하는 레코드를 포함
- Host Zone마다 요금을 지불해야한다 ($0.50 per month)

![Hosted_zones](./images/07_03.png)


### Route 53 - Records TTL (Time To Live)

- **Alias 레코드를 제외하고 각 DNS 레코드에 대해 TTL은 필수적이다**
- High TTL - ex. 24 hr
  - Route 53에 부하가 적지만, 사용자가 오래된 record를 사용할 수 있다
- Low TTL - ex. 60 sec
  - Route 53에 부하가 많지만, 사용자가 비교적 최신의 record를 사용할 수 있다
  - record를 자주 변경할 수 있다

### CNAME vs Alias

- AWS 리소스는 AWS 호스트네임을 노출시킨다
  - lb1-1234.us-east-2.elb.amazonaws.com
  - myapp.mydomain.com

**CNAME**
-  DNS 쿼리를 DNS 레코드로 리디렉션할 수 있다
  - app.mydomain.com => blabla.anything.com
- **루트 도메인이 아는 경우에만 사용 가능**

**Alias**
- 별칭 레코드
- AWS 리소스로만 리디렉션할 수 있다
  - app.mydomain.com => blabla.amazonaws.com
- **루트 도메인이든 아니든  사용 가능**
- 무료
- 네이티브 health check

### Route 53 - Alias Records

- 호스트네임을 AWS 리소스에 매핑
- DNS 기능 확장
- 리소스의 IP 주소 변경을 자동으로 인식
- CNAME과 달리 DNS 네임스페이스의 최상위 노드(Zone Apex)에서 사용 가능
  - ex. example.com
- 
