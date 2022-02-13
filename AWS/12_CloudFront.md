# CloudFront

---

### AWS CloudFront

- Content Delevery Network (CDN)
- 읽기 성능 향상시키고 콘텐츠가 엣지에서 캐시되도록 해준다
- 전 세계적으로 216개의 엣지 로케이션이 있다
- DDos 보호, shield 및 웹 애플리케이션 방화벽과의 통합을 제공
- 외부 HTTPS를 노출하고 내부 HTTPS 백엔드와 통신할 수 있다

---

### CloudFront - Origins

- S3 Bucket
  - 파일을 분산하고, 엣지로 캐시화하기 위해
  - CloudFront의 Origin Access Identity(OAI)로 강화된 보안
  - CloudFront는 입구로서 사용될 수 있다

- Custom Origin (HTTP)
  - Application Load Balancer
  - EC2 instance
  - S3 Website (먼저 버킷을 정적 S3 웹사이트로 활성화시켜야 한다)
  - 원하는 모든 HTTP 포트

---

> **CloudFront at high level**
> 
> ![CloudFront at high level](./images/12_01.png)

---

> **CloudFront - S3 as an Origin**
> 
> ![CloudFront - S3 as an Origin](./images/12_02.png)

---

> **CloudFront - ALB or EC2 as an origin**
>
> ![CloudFront - ALB or EC2 as an origin](./images/12_03.png)

---

### CloudFront 지역 제한

- 액세스할 수 있는 사람을 제한할 수 있다
  - 화이트리스트 : 사용자가 승인된 국가 목록에 있는 경우에만 콘텐츠에 액세스 할 수 있다
  - 블랙리스트 : 사용자가 금지된 국가의 블랙리스트 중 하나에 있는 경우 콘텐츠에 액세스를 할 수 없다

- "국가"는 서드파티의 Geo-IP 데이터베이스를 사용하여 결정한다
- 사용 사례 : 콘텐츠에 대한 접근 통제를 위한 저작권법

---

### CloudFront vs S3 Cross Region Replication

- CloundFront
  - 글로벌 엣지 네트워크
  - TTL에 대해 캐시된다 (보통 하루)
  - **어디서나 사용할 수 있어야 하는 컨텐츠에 적합**

- S3 Cross Region Replication
  - 복제는 각 리전에 대해 설정 필요
  - 파일은 거의 실시간으로 업데이트
  - 읽기 전용이다
  - **일부 지역에서 빠르게 제공되어야 하는 콘텐츠에 적합**

---

### CloudFront Caching

- Cache 체크 기준
  - 헤더
  - 세션 쿠키
  - 쿼리 문자열 매개변수
- 캐시는 각 CloudFront의 **엣지 로케이션에 저장**된다
- 캐시 적중률을 최대화하여 origin에 대한 요청을 최소화 가능
- TTL 설정가능하다 (0초 ~ 1년). Cache-Control헤더, Expires 헤더를 사용하여 origin 에서 설정 가능
- CreateInvalidation API를 사용하여 캐시의 일부 무효화 가능

![CloudFront Caching](./images/12_04.png)

> **정적/동적 배포를 분리하여 캐시 히트 최대화**
> 
> ![](./images/12_05.png)

---

### CloudFront Geo Restriction

- 