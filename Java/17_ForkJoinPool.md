## ForkJoinPool

- ForkJoinPool은 ExecutorService와 비슷하게 Thread Pool을 생성하여 여러 작업을 병렬처리로 수행할 수 있다. 다른 부분은 Task의 크기에 따라 분할(Fork)하고 분할된 Task가 처리되면 그것을 합쳐(Join) 리턴해준다.
  - 분할정복법과 유사하게 동작한다



### Fork

- Task를 분할하여 다른 스레드에서 처리시킨다는 의미

  ![Fork](./image/17_1.png)



### Join

- 다른 스레드에서 처리된 결과를 기다렸다가 합친다는 의미
  - Parent는 Child에서 처리되는 Task가 완료될때까지 기다린 후 결과를 합쳐 더상위의 Parent로 전달한다.

![Join](./image/17_2.png)



### RecursiveAction과 RecursiveTask

- RecursiveAction : 리턴값이 없는 Task. 리턴값이 필요하지 않는 Task라면 이 클래스로 Task를 정의하면 된다
- RecursiveTask : 리턴값이 있는 Task. Parent는 Child Task의 리턴값을 기다려 합친 후 상위 Parent로 전달한다

