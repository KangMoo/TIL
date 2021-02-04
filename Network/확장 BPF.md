## BPF (Berkey Packet Filter)

- 패킷을 걸러내는 필터이다.
- BSD에서의 BPF는 네트워크 탭 (리눅스의 PF_PACKET) 까지 아우르는 개념
- 예전에 유닉스에서는  CSPF(CMU/Stanford Packet Filter)라는게 있었는데, BPF라는 새 구조가 이를 대체했다. 이후 리눅스에서는 네트워크 탭을 나름의 방식으로 구현하고 패킷 필터 부분만 가져왔다.
- 리묵스 패킷 필터를 리눅스 소켓 필터링 (Linux Socket Filtering)이라고도 한다.



