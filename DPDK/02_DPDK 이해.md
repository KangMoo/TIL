## 일반적인 리눅스 커널 처리의 문제점

일반적인 Pakcet 처리를 하려면 리눅스 커널의 도움을 받아야 하는데, 이 과정에서 Overhead가 발생한다. 대표적인 오버헤드는 아래와 같다.

- System Call / Context Switching
- Interrupt Handling
- Data copy (Kernel space -> User space)

DPDK는 위의 오버헤드로 발생하는 병목현상을 최소화한다.



