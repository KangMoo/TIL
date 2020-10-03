## Early Media Session 설립을 위한 새로운 SIP 헤더

Early Media Session을 기존 다이얼로그에서 생성하기 위해 Content-Disposition헤더를 이용한다.

Content-Disposition 헤더는 'early-session'이라는 새로운 disposition type을 정의하고, Require과 Support헤더에 'early-session'이라는 옵션 태그를 정의하여 early session disposition type을 표시한다

## Content-Disposition 헤더

RFC 3959 The Early Session Disposition Type for the SIP권고안은 Early Media Session과 Regular Media Session이 같은 코덱을 사용할 것을 권고한다.

### 예제

![PRACK 메서드를 이용한 Early Media 협상](./image/17_1.png)

PRACK은 Provisional Response ACKnowledgement의 준말로 아직 설립되지 않은 세션에 신뢰할 수 있는 최종 응답을 제공한다.