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
- 수신된 Packet을 처리하기 위해 외부 인터럽트가 발생하고, 그 과정에서 프로세스의 처리과정이 방해받기 대문에 Overhead가 발생한다.

**Buffer Copy가 Overhead를 발생시키는 이유**

- 커널영역의 데이터를 유저영역으로 복사하는 과정에서 Overhead가 발생한다.



**따라서 DPDK는 kernel 영역을 우회하여 패킷을 처리함으로써 오버헤드를 줄였다.**





