## ZGC

- JDK 11부터 사용 가능한 GC중 하나로 확장 가능한 낮은 레이턴시의 GC

  - 다만 JDK 11에서는 실험적으로 도입되어 Default GC옵션을 사용하지 않으면 G1GC를 기본으로 사용하게 된다.
  - ZGC를 사용하려면 다음 두 옵션을 켜야 한다 `-XX:+UnlockExperimentalVMOptions -XX:+UseZGC`



#### 특징

- Low Latency: GC 일시 정지 시간이 10ms 미만
- Scalable: heap 사이즈나 라이브셋의 사이즈가 커져도 일시 정지 시간이 늘어나지 않는다.

#### 목표

**ZGC의 목표는 G1보다 더 짧은 latency를 가지면서 G1보다 크게 뒤쳐지지 않는 처리량을 갖는 것**

- GC 일시 정지 시간이 10ms를 초과하지 않아야 한다.
- 비교적 작은(수백 MB) 크기에서 매우 큰(수 TB) 사이즈의 heap을 다룰 수 있어야 한다.
- G1 보다 애플리케이션 처리량이 15% 이상 떨어지지 않을 것.
- colored pointers, load barriers를 사용하여 미래의 GC를 위한 기능/최적화 기반을 마련한
- 최초 지원 플랫폼은 Linux/x64.
- GC 일시 정지를 최소화
- 대량의 메모리를 최대한 효율적으로 사용



### 옵션

**ZGC 사용 옵션**

JDK 11에서는 ZGC를 사용하려면 다음 두 옵션을 켜야 한다

- `-XX:+UnlockExperimentalVMOptions`
- `-XX:+UseZGC`

**일반 GC 옵션**

- `-XX:MinHeapSize`, `-XX:InitialHeapSize`, `-Xms`
  - ZGC의 최소 Heap 크기를 설정하는 옵션
- `-XX:MaxHeapSize`, `-Xmx`
  - **ZGC의 최대 Heap 크기를 설정하는 옵션 (중요 옵션)**
- `-XX:SoftMaxHeapSize`
  - 소프트 힙의 제한을 설정
- `-XX:ParallelGCThreads`
  - STW 상황에서 GC를 수행하는 스레드 개수. CPU core 수가 8개 이하인 경우 core 수와 동일하게 설정하는 것이 좋음
- `-XX:ConcGCThreads`
  - Concurrent Mark 를 수행하는 스레드 개수. ParallelGCThreads의 25% 로 설정하는 것이 좋음
- `-XX:UseLargePages`
  - Large Page 활성화. 루트 권한이 필요하기 때문에 기본 옵션이 아니며 응용 프로그램에 대해 활성화하지 못할 수 있음. 자세한 내용은 메뉴얼 참고
- `-XX:UseTransparentHugePages`
  - Transpargetn Huge Page 활성화. 
- `-XX:UseNUMA`
  - NUMA 지원 활성화
- `-XX:SoftRefLRUPolicyMSPerMB`
- `-XX:AllocateHeapAt`

**ZGC 옵션**

- `-XX:ZAllocationSpikeTolerance`
- `-XX:ZCollectionInterval`
- `-XX:ZFragmentationLimit`
- `-XX:ZMarkStackSpaceLimit`
- `-XX:ZProactive`
- `-XX:ZUncommit`
  - ZGC가 사용하지 않는 메모리를 커밋 해제하여 운영 체제에 다시 제공하는 기능을 비활성화
- `-XX:ZUncommitDelay`

**ZGC 특수 옵션**

- `-XX:ZStatisticsInterval`
- `-XX:ZVerifyForwarding`
- `-XX:ZVerifyMarking`
- `-XX:ZVerifyObjects`
- `-XX:ZVerifyRoots`
- `-XX:ZVerifyViews`