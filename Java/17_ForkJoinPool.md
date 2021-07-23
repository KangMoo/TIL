## ForkJoinPool

- ForkJoinPool은 ExecutorService와 비슷하게 Thread Pool을 생성하여 여러 작업을 병렬처리로 수행할 수 있다. 다른 부분은 Task의 크기에 따라 분할(Fork)하고 분할된 Task가 처리되면 그것을 합쳐(Join) 리턴해준다.
  - 분할정복법과 유사하게 동작한다



### 