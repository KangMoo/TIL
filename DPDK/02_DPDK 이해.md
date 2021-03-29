## 일반적인 리눅스 커널 처리의 문제점

일반적인 Pakcet 처리를 하려면 리눅스 커널의 도움을 받아야 하는데, 이 과정에서 Overhead가 발생한다. 대표적인 오버헤드는 아래와 같다.

- system call / Context Switching
- Interrupt Handling
- Data copy (Kernel space -> User space)

DPDK는 위의 오버헤드로 발생하는 병목현상을 최소화한다.



### system call / Context Switching

**system call / Context Switching 이 Overhead를 발생시키는 이유**

- Context Switching은 하던 작업을 멈추고 다른 작업을 하는 일.
- Context Switching은 작업전환을 의미하고 작업전환 과정에서는 오버헤드가 발생한다
- 거의 모든 System call은 Context Switching을 발생시키기 때문에 system call은 Overhead가 발생한다.

