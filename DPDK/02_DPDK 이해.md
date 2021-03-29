## 일반적인 리눅스 커널 처리의 문제점

일반적인 Pakcet 처리를 하려면 리눅스 커널의 도움을 받아야 하는데, 이 과정에서 Overhead가 발생한다. 대표적인 오버헤드는 아래와 같다.

- system call / Context Switching
- Interrupt Handling
- Buffer copy (Kernel space -> User space)

DPDK는 위의 오버헤드로 발생하는 병목현상을 최소화한다.



**system call / Context Switching 이 Overhead를 발생시키는 이유**

- Context Switching은 하던 작업을 멈추고 다른 작업을 하는 일.
- Context Switching은 작업전환을 의미하고 작업전환 과정에서는 오버헤드가 발생한다
- 거의 모든 System call은 Context Switching을 발생시키기 때문에 system call은 Overhead가 발생한다.

**Interrupt Handling이 Overhead를 발생시키는 이유**

- 여기서 말하는 Interrupt는 외부 인터럽트로 주로 입출력장치에 의해 발생
- NIC는 패킷 수신과 같은 특정 이벤트에 대해서 외부 인터럽트를 발새시키며 그 과정에서 프로세스의 처리과정이 방해받기 대문에 Overhead가 발생한다.

**Buffer Copy가 Overhead를 발생시키는 이유**

- 커널영역의 데이터를 유저영역으로 복사하는 과정에서 Overhead가 발생한다.



**따라서 DPDK는 kernel 영역을 우회하여 패킷을 처리함으로써 오버헤드를 줄였다.**



#### 기존 Kernel 문제와 DPDK의 해결방안

| 기존 Kernel                                                 | DPDK                                                         |
| ----------------------------------------------------------- | ------------------------------------------------------------ |
| CPU 처리 속도와 Memoy/PCI 인터페이스에서 발생하는 속도 문제 | 다수의 패킷을 동시에 처리하는 I/O 배치 처리 기술을 적용하여 해결 |
| 동적으로 네트워크 패킷마다 메모리를 할당/해제하던 문제      | 네트워크 패킷에 대해 고정 길이의 메모리를 하전에 할당하여 해결 |
| 공유 자원의 접근으로 인한 병목 현상 문제                    | Lockless Queue 기술 적용으로 해결                            |
| Page table size(4k bytes)로 인해 TLB miss 발생              | Huge page로 해결                                             |
| 성능이 sclae 되지 않는 문제 (멀티 프로세스 사용)            | Run-To-Complete 모델로 Horizontal Scalability를 제공         |
| Scheduler의 Thread switching overhead 문제                  | CPU core isolation 기술로 해결                               |
| Host kernel networking stack의 performance 제약 문제        | KNI (Kernel Network Interface)를 이용하여 Kernel network stack 성능 개선 |



## DPDK의 핵심 라이브러리 및 드라이버

- Memory Manager : Object pool, huge page 메모리, Ring, 관리
- Buffer Manager : 고정 크기 버퍼를 미리 할당
- Queue Manager : Lockless Queue 제공. waiting time 회피 (spinlock 사용 X)
  - spinlock : 조금 기다리면 바로 사용할 수 있으니, 잠시 루프를 돌면서 크리티컬 섹션으로 진입 시도 (context switching으로 인한 부하 회피)
- Poll Mode Drivers : 수신 프로세스 및 사용자의 어플리케이션에서 패킷을 임의의 인터럽트 없이 신속하게 전달하는 직접적인 디스크립터
  - 인터럽트없이 직접 RX 및 TX descriptors에 액세스하여 (링크 상태 변경 인터럽트 제외) 사용자 애플리케이션에서 패킷을 빠르게 수신, 처리 및 전달
- Flow Classification : 



- Huge page
- Processor affinity
- UIO
- Polling
- Lockless synchronization
- Batch packet handling
- SSE, NUMA awareness



#### UIO

- Userspace I/O
- 하드웨어가 발생하는 인터럽트를 처리하기 위한 최소한의 커널 드라이버만 작성하고, 나머지 모든 작업은 사용자공간 프로그램으로 작성하도록 한다
- DPDK가 NIC와 User space를 연결시키기 위해 사용

#### Huge Page

- 일반적으로 4kb의 페이지를 사용하는 기존의 페이지 관리 기법에서 용량을 크게 늘려주는 방법
  - 특히 대용량 처리를 하는 DB의 튜닝을 위해 사용됨
- 최신 커널의 경우 huge page를 사용하더라도 상당수는 4k를 사용하지만 DPDK에서는 기본적으로 영구 huge 페이지가 사용되며 모든 개체, 파일시스템, 기본 요소들에 대해서 Huge Page 사용 -> Huge Page의 모든 이점을 활용
  - Huge page는 프로세스 종료시 해제된다.

### Lockless Ring

- 비잠금 인터코어 통신을 통해 서로 다른 코어 사이의 통신 제공
- 특정 코어가 새산자, 다른 특정 코어는 소비자가 되며, 누구도 해당 개체에 개입하지 않음
  - 여러 생성자가 있는 경우 여전히 lock이 필요
- 어플리케이션 수준에서 비슷한 메커니즘을 구현하려는 경우 이를 채택해서 동일한 메커니즘 사용할 수 있음 -> 유용성이 뛰어남