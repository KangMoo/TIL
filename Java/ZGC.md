## ZGC

- JDK 11부터 사용 가능한 GC중 하나로 확장 가능한 낮은 레이턴시의 GC

  - 다만 JDK 11에서는 실험적으로 도입되어 Default GC옵션을 사용하지 않으면 G1GC를 기본으로 사용하게 된다.
  - ZGC를 사용하려면 다음 두 옵션을 켜야 한다 `-XX:+UnlockExperimentalVMOptions XX:+UseZGC`



#### 목표

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

