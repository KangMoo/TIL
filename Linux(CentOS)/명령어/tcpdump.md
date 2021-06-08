## tcpdump

- tcpdump는 주어진 조건식을 만족하는 네트워크 인터페이스를 거치는 패킷들의 헤더들을 출력하는 프로그램이다.
- 네트워크 패킷들을 다 조사하기 때문에 root권한이 필요하다



### 사용법

`tcpdump [options] [expression] [host]`



**OPTION**

| 옵션             | 설명                                                         |
| ---------------- | ------------------------------------------------------------ |
| `-i [interface]` | 패킷들의 내용을 출력하고 싶은 인터페이스의 이름을 옵션으로 준다. 'any'사용시 모든 인터페이스를 조회한다 |
| `-w [file name]` | 패킷들의 내용을 파일로 저장하고 싶을 때 사용한다             |
| `-nn`            | 출력 결과의 포트를 변환 없이 그대로 출력한다. 옵션을 적용하지 않으면 가장 대중적인 프로토콜로 보여준다. ex) 80 -> http, 443 -> https |
| `-vvv`           | 패킷의 모든 정보 (TTL 등을 보여준다. 단, 데이터는 볼 수 없다. Request Body, Response Body 등) |
| `-A`             | 패킷의 내용을 ASCII 형태로 출력한다. 데이터가 암호화 되지 않았다면, 데이터도 보여준다. (Request Body, Response Body 등) |
| `-G`             | 패킷 정보를 저장하는 파일을 **일정 주기**로 갱신한다. 기본 단위는 s (초) |
| `-C`             | 패킷 정보를 저장하는 파일을 **일정 크기**로 갱신한다. 기본 단위는 MB |
| `-w`             | 패킷 정보를 저장할 파일 (`.pcap`)을 지정한다. 와이어샤크 등으로 해당 파일을 분석할 수 있다. |
| `-Z`             | tcpdump를 실행시킬 권한을 설정해준다. 만약 패킷 저장을 위한 파일의 권한을 다른 사용자로 주고 싶다면 사용한다. |
| `host`           | 출발지, 목적지 관계 없이 해당 **호스트 정보**를 가진 패킷만 수집한다. |
| `port`           | 출발지, 목적지 관계 없이 해당 **포트 정보**를 가진 패킷만 수집한다. |



### 예시

`$ tcpdump -i eth0` : 인터페이스 eth0 을 보여줌

`$ tcpdump -w tcpdump.log` :  결과를 파일로 저장, txt 가 아닌 bin 형식으로 저장됨

`$ tcpdump -r tcpdump.log` : 저장한 파일을 읽음

`$ tcpdump -i eth0 -c 10` : => 카운터 10개만 보여줌

`$ tcpdump -i eth0 tcp port 80` : tcp 80 포트로 통신하는 패킷 보여줌

`$ tcpdump -i eth0 tcp port 80` : tcp 80 포트로 통신하는 패킷 보여줌

`$ tcpdump -i eth0 src 192.168.0.1` : source ip 가 이것인 패킷 보여줌

`$ tcpdump -i eth0 dst 192.168.0.1` : dest ip 가 이것인 패킷 보여줌

`$ tcpdump -i eth0 src 192.168.0.1 and tcp port 80` : source ip 가 이것이면서 tcp port 80 인 패킷 보여줌. `and` 옵션으로 여러가지 조건의 조합 가능

`$ tcpdump -i eth0 dst 192.168.0.1` : dest ip 가 이것인 패킷 보여줌

`$ tcpdump host 192.168.0.1` : host 를 지정하면, 이 ip 로 들어오거가 나가는 양방향 패킷 모두 보여줌

`$ tcpdump src 192.168.0.1` : host 중에서 src 가 이것인것 만 지정

`$ tcpdump dst 192.168.0.1` :  host 중에서 dst 가 이것인것 만 지정

`$ tcpdump net 192.168.0.1/24` : CIDR 포맷으로 지정할 수 있다.

`$ tcpdump tcp` :  TCP 인것만

`$ tcpdump udp` :  UDP 인것만

`$ tcpdump port 3389` : 포트 양뱡향으로 이것인 것.

`$ tcpdump src port 3389` : src 포트가 이것인 것.

`$ tcpdump dst port 3389` : dst 포트가 이것인 것.

`$ tcpdump -nn -vvv -A -G 3600 -w /home/ec2-user/dump_%Y%m%d-%H%M%S.pcap -Z ec2-user host github.com and port 443` : 3600초 (1시간) 단위로 https://github.com 과 주고 받는 패킷을 남김. 만약 pcap 파일명에 타임스탬프 ( `%Y%m%d-%H%M%S` ) 를 추가하지 않으면 3600초 마다 파일에 새로운 내용으로 덮어쓰게 된다.

