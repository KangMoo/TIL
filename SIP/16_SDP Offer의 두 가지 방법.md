## SDP의 협상방식

SDP는 단말 간의 멀티미디어 세션과 관련된 미디어 타입 및 포맷을 협상하는 프로토콜이며 제안 및 수락 모델로 작동한다.

SDP는 단독으로 전달될 수 없으며 SIP메시지 바디에 포함되어 협상한다.

SDP Offer가 어떤 SdIP메시지에서 전달되느냐에 따라 협상 방식을 두가지로 정의한다.



1. Early Offer

![Early Offer](./image/16_1.png)

SDP Early Offer는 SIP INVITE메시지와 SDP Offer를 함께 전달하는 방식이다. SDP Answer는 200 OK나 180Ringing과 함께 전달된다. Early Offer는 발신자가 SDP협상의 주도권을 가진다. 대부분의 장비들이 Early Offer를 사용한다.

2. Delayed Offer

![Delayed Offer](./image/16_2.png)

SDP Delayed offer는 INVITE메시지에 SDP Offer를 전달하지 않고 180 Ringing이나 200 OK에 SDP Offer를 전달하는 방식이다. SDP Answer는 ACK와 함께 전달한다. Delayed Offer는 수신자가 SDP 협상의 주도권을 갖는다. 시스코 제품들은 Delayed Offer를 사용한다.

### 비교

**Early Offer**

- 미디어 채널의 협상이 빠르다.
- 200 OK 응답 이전에 미디어 채널 협상을 완료하여 사용하고자 할 때 유용하다.

**Delayed Offer**

- 코덱 협상이 확실하다.
- 수신자의 Capability를 확인 후에 협상을 완료할 수 있으므로 Capability 재협상이 수행되지 않는다. 



