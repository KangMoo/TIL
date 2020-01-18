# INDEX

1. [쓰레드 개체 만들기](#쓰레드-개체-만들기)
2. [뮤텍스](#뮤텍스)
3. [조건 변수](#조건-변수)



## 레드 개체 만들기

#### 쓰레드 지원 라이브러리

* 일단 간단하게만 살펴보자
  * 더 자세히 알고 싶으면 나중에 깊이 파서 보자...
* 일단...
  * 쓰레드
  * 뮤텍스
  * 조건 변수
    * 등등 더 있으나 위의 세개만 보자



#### C++ 이전의 멀티 쓰레딩

* C++11 전까지 표준 멀티쓰레딩 라이브러리가 없었음
* OS마다 멀티쓰레딩 구현이 달랐음
  * 리눅스/유닉스:POSIX 쓰레드
  * 윈도우 쓰레드



#### 윈도우 쓰레드와 POSIX 쓰레드

* 윈도우 쓰레드

  > ```cpp
  > #include <Winsows.h>
  > #include ...
  > 
  > DWORD WINAPI PrintMessage()
  > {
  >     ...
  > }
  > int main()
  > {
  >     DWORD myThreadId;
  >     HANDLE myHandle = CreateThread(0, 0, PrintMessage, NULL, 0, &myThreadId);
  >     
  >     WaitForSingLeObject(myHandle, INFINITE);
  >     CloseHandle(myHandle);
  >     
  >     return 0;
  > }
  > ```

* POSIX 쓰레드

  > ```cpp
  > #include ...
  > #include <pthread.h>
  > 
  > void *printMessage()
  > {
  >     ...
  > }
  > int main()
  > {
  >     pthread_t thread = 0;
  >     int result_code = pthread_create(&thread, NULL, printMessage, NULL);
  >     
  >     result_code = prhtread_join(thread,NULL);
  >     return 0;
  > }
  > ```



#### std::thread

* 표준 C++쓰레드
* 이동(move) 가능
* 복사 불가능



#### std::thread 생성자 1

> ```cpp
> thread() noexcept;
> 
> template<class Function, class.. Args>
> explicit thread(Function&& f, Args&&.. args);
> ```

* 새 쓰레드 개체를 만든다

  > ```cpp
  > std::thread emptyThread;
  > std::thread printThread(PrintMessage, 10); // void PrintMessage(int count);
  > ```



#### std::thread 생성자 2

* 이동 생성자

> ```cpp
> thread(thread&& other) noexcept;
> ```

* delete 처리된 복사 생성자

> ```cpp
> thread(const thread&) = delete;
> ```

> ```cpp
> std::thread printThread(PrintMessage, 10); // void PrintMessage(int count);
> std::thread movedThread(std::move(printThread)); // OK. printThread는 더 이상 쓰레드가 아님
> std::thread copiedThread = movedThread; //컴파일 에러
> ```



#### std::thread::join()

> `void join()`

* 쓰레드 개체가 끝날 때까지 현재 쓰레드를 멈춰 놓는다

* 이 함수를 호출한 후 쓰레드 개체를 안전하게 소멸시킬 수 있음

  > ```cpp
  > std::thread thread(PrintMessage); // void PrintMessage() {}
  > thread.join();
  > ```



#### 예시 : 쓰레드 개체 만들기

> ```cpp
> #include <iostream>
> #include <string>
> #include <thread>
> void PrintMessage(const std:;string& message)
> {
>     std::cout << message << std::endl;
> }
> int main()
> {
>     std::thread thread(PrintMessage, "Message from a child thread.");
> 
>     PrintMessage("Message from a main thread.");
> 
>     thread.join(); // 자식 쓰레드가 끝날 때까지 기다림
> 
>     return 0;
> }
> ```



#### std::thread::get_id()

> `std::thread::id get_id() const noexcept;`

* 쓰레드 ID를 반환한다

> ```cpp
> std::thread thread(PrintMessage); // void PrintMessage() {}
> std::thread::id threadID = thread.get_id();
> ```



#### 예시 : 쓰레드 ID 구하기

> ```cpp
> #include <iostream>
> #include <sstream>
> #include <string>
> #include <thread>
> 
> void PrintMessage(const std:;string& message)
> {
>     std::cout << message << std::endl;
> }
> 
> int main()
> {
>     std::thread thread(PrintMessage, "Message from a child thread.");
> 
>     std::thread::id childThreadID = thread.get_id();
>     std::stringstream ss;
>     ss << childThreadID;
>     std::string childThreadIDStr = ss.str();
> 
>     PrintMessage("Waiting the child thread(ID: "+childThreadIDStr+")");
> 
>     thread.join(); // 자식 쓰레드가 끝날 때까지 기다림
> 
>     return 0;
> }
> ```



#### std::thread::detach()

> `void detach();`

* 쓰레드 개체에서 쓰레드 개체를 때어낸다
* 떼어진 쓰레드는 메인 쓰레드와 무관하게 독립적으로 실행됨

> ```cpp
> std::thread thread(PrintMessage); // void PrintMessage() {}
> thread.detach();
> ```

* 떼어진 쓰레드는 join할 수 없다



#### 예시 : 쓰레드 떼어내기

> ```cpp
> #include <iostream>
> #include <string>
> #include <thread>
> void PrintMessage(const std:;string& message)
> {
>     for(int i =0; i<count; ++i)
>     {
>         std::cout << i + 1 << " : " message << std::endl;
>     }
> }
> int main()
> {
>     std::thread thread(PrintMessage, "Message from a child thread."),10;
>     
>     PrintMessage("Waiting the child thread...",1);
>     
>     thread.detach();
> 
>     return 0;
> }
> ```
>
> Result
>
> ![image](images/thread_1.png)



#### 예시 : 떼어진 쓰레드 join 방지하기

> ```cpp
> #include <iostream>
> #include <string>
> #include <thread>
> void PrintMessage(const std:;string& message, int count)
> {
>     for(int i =0; i<count; ++i)
>     {
>         std::cout << i + 1 << " : " message << std::endl;
>     }
> }
> int main()
> {
>     std::thread thread(PrintMessage, "Message from a child thread.",10);
> 
>     PrintMessage("Waiting the child thread...",1);
> 
>     thread.detach();
>     // ...
>     if (thread.joinable())
>     {
>         thread.join();
>     }
>     return 0;
> }
> ```



#### 예시 : 람다와 쓰레딩

> ```cpp
> #include <iostream>
> #include <string>
> #include <thread>
> int main()
> {
>     auto printMessage = [](const std::string& messgae)
>     {
>         std::cout << message << std::endl;
>     };
> 
>     std::thread thread(PrintMessage, "Message from a child thread.");
> 
>     printMessage("Waiting the child thread...");
>     thread.join();
> 
>     return 0;
> }
> ```



#### std::ref()

> ```cpp
> template<class T>
> std::reference_wrapper<T> ref(T& t) noexcept;
> ```

* T의 참조를 내포한 reference_wrapper 개체를 반환한다
* `<functional>`헤더에 정의돼 있음
  * `<thread>`를 인클루드하면, `<fucntional>`을 인클루드 할 필요 없음

> ```cpp
> //void Sum(const std::Vector<int>& list, int& result);
> std::thread thread(Sum, list, std::ref(result));
> ```



#### 예시 : 매개변수 참조로 전달하기

> ```cpp
> #include <iostream>
> #include <string>
> #include <thread>
> #include <vector>
> 
> int main()
> {
>     std::vector<int> list(100, 1);
>     int result = 0;
> 
>     std::thread thread([](const std::vector<int>& v, int& result)
>                        {
>                            for(auto item : v)
>                            {
>                                result += item;
>                            }
>                        }, list, std::ref(result));
> 
>     thread.join();
> 
>     std::cout << "Result:" << result << std::endl;
> 
>     return 0;
> }
> ```



#### std::this_thread

* 도우미 함수들
* 네임스페이스 그룹
* 현재 쓰레드에 적용되는 도우미 함수들이 있음
  * get_id()
  * sleep_for()
  * sleep_unitl()
  * yield()



#### std::this_thread::sleep_for()

> ```cpp
> template<class Rep, class Period>
> void sleep_for(const std::chrono::duration<Rep, Perio>& sleep_duration);
> ```

* 최소 sleep_duraction만큼의 시간 동안 현재 쓰레드의 실행을 멈춘다

> `std::this_thread::sleepfor(std::chrono:seconds(1));`



#### 예시 : 쓰레드 실행 잠시 멈추기

> ```cpp
> #include <iostream>
> #include <chrono>
> #include <string>
> #include <thread>
> void PrintCurrentTime() { /* 현재 시간 출력 */}
> void PrintMessage(const std::string& message)
> {
>     std::cout << "Sleep now...";
>     PrintCurrentTime();
> 
>     std::this_thread::sleep_for(std::chrono::seconds(3));
> 
>     std::cout<<message<<" ";
>     PrintCurrentTime();
> }
> int main()
> {
>     std::thread thread(PrintMessage, "Message from a child thread.");
> 
>     PrintMessage("Message from a main thread");
> 
>     thread.join();
>     return 0;
> }
> ```



#### std::this_thread::yield()

> `void yield() noexcept;`

* sleep과 비슷하나, sleep이 스레드를 일시정지 상태로 만드는 반면, yield는 계속 실행 대기 상태

> `std::this_thread::yield();`



#### 예시 : 다른 쓰레드에게 양보하기

> ```cpp
> #include <iostream>
> #include <chrono>
> #include <string>
> #include <thread>
> 
> void PrintMessage(const std::string& message, std::chrono::seconds delay)
> {
>     auto end = std::chrono::high_resolution_clock::now() + delay;
> 
>     while(std::chrono::high_resolution_clock::now() < end)
>     {
>         std::this_thread::yield();
>     }
> }
> int main()
> {
>     std::thread thread(PrintMessage, "Message from a child thread.", std::chrono::seconds(3));
> 
>     PrintMessage("Message from a main thread", std::chrono::seconds(2));
> 
>     thread.join();
> 
>     return 0;
> }
> ```



## 뮤텍스

#### 뮤텍스

* 공유자원 문제를 해결하기 위한 방법



#### 뮤텍스 생성자

> `std::mutex mutex;`

* 뮤텍스 만들기

  > `constexpr mutex() noexcept;`

* 복사 생성자 delete처리

  > `mutext(const mutex&) = delete;`



#### std::mutex::lock()

> `void lock();`

* 뮤텍스를 잠근다
* 동일한 ㅆ르ㅔ드에서 두 번 잠그만 데드락 발생
  * 꼭 그렇게 해야 된다면, std::recursive_mutex를 사용

> ```cpp
> std::mutex mutex;
> mutex.lock();
> ```



#### std::mutex::unlock()

> `void unlock();`

* 뮤텍스 잠금을 푼다
* 현재 쓰레드에서 잠긴 적이 없을 때의 행동은 정의되지 않음

> ```cpp
> std::mutex mutex;
> 
> // mutex.lock();
> // ...
> mutex.unlock();
> ```



#### 예시 : 공유자원 잠그기

> ```cpp
> #include <iostream>
> #include <mutex>
> #include <string>
> #include <thread>
> 
> void PrintMessage(const std::string& message)
> {
>     static std::mutex sMutex;
> 
>     sMutex.lock();
>     std::cout << message << std::endl;
>     sMutex.unlock();
> }
> 
> int main()
> {
>     std::thread thread(PrintMessage, "Message from a child thread.");
> 
>     PrintMessage("Messge from a main thread");
> 
>     thread.join();
> 
>     return 0;
> }
> ```



#### std::scoped_loc(C++17)

> ```cpp
> template<class... MutexTypes>
> class scoped_lock;
> ```

* 매개변수로 전달된 뮤텍스(들)을 내포하는 개체를 만듦

* 개체 생성시에 뮤텍스를 잠그고 범위를 벗어나 소멸될 때 품

* 데드락 방지

* C++14의 경우 std::lock_guard를 사용할 수 있으나 이 때는 뮤텍스는 하나만 전달 가능

  > ```cpp
  > std::scoped_lock<std::mutex>, lock(mutex);
  > std::scoped_lock<std::mutex, std::mutex> locks(mutex1, mutex2);
  > std::scoped_lock<std::mutex, std::mutex, std::mutex> locks(mutex1, mutex2, mutex3);
  > ...
  > ```



#### 예시 : scoped_lock 1

> ```cpp
> #include <iostream>
> #include <mutex>
> #include <string>
> #include <thread>
> void PrintMessage(const std::string& message)
> {
>     static std::mutex sMutex;
> 
>     std::scoped_lock<std::mutex> lock(sMutex);
>     std::cout << message << std::endl;
> }
> int main()
> {
>     std::thread thread(PrintMessage, "Message from a child thread.");
> 
>     PrintMessage("Messge from a main thread");
> 
>     thread.join();
> 
>     return 0;
> }
> ```



#### 예시 : scoped_lock 2

> ```cpp
> #include <iostream>
> #include <mutex>
> #include <string>
> #include <thread>
> 
> void PrintMessage(const std::string& message)
> {
>     static std::mutex sMutex;
> 
>     {
>         std::scoped_lock<std::mutex> lock(sMutex);
>         std::cout << "Message from thread ID " << std::this_thread::get_id() << std:;endl;
>     }
>     // 스코프를 벗어나면서 lock이 풀림!!
> 
>     {
>         std:: scoped_lock<std::mutex> lock(sMutex);
>         std::cout << message << std::endl;
>     }
> }
> int main()
> {
>     std::thread thread(PrintMessage, "Message from a child thread.");
> 
>     PrintMessage("Messge from a main thread");
> 
>     thread.join();
> 
>     return 0;
> }
> ```



## 조건 변수

#### 조건 변수

* lock만 쓰는 것은 충분하지 않음
* 조건변수가 신호를 받기 전에 대기 상태에 들어가는 것을 보장할 수 없다면 항상 bool 조건과 lock을 같이 사용할 것



#### std::condition_variable

* 이벤트 개체
* 신호를 받을 때까지 현재 쓰레드의 실행을 멈춤
* `notify_one()`, `notiry_all()`
  * 멈춰놓은 쓰레드 하나 또는 전부를 다시 실행시킴
* `wait()`, `wait_for()`, `wait_until()`
  * 조건 변수의 조건을 충족시킬 때까지 또는 일정 시간 동안 현재 쓰레드의 실행을 멈춤
* std::unique_lock을 사용해야 함



#### std::unique_lock

* 기본적으로 scoped lock
* 생성시에 lock을 잠그지 않을 수도 있음 (두 번재 매개변수로 `std::defer_lock`을 전달할 것)
* `std::recursive_mutex`와 함께 써서 재귀적으로 잠글 수 있음
* **조건 변수에 쓸 수 있는 유일한 lock**



#### std::condition_variable::wait

> `void wait(std::unique_lock<std::mutex>& lock);`

* 현재 쓰레드 뮤텍스의 잠금을 풀고 `notify_one()` 또는 `notify_all()`을 기다린다
* 깨어나면 뮤택스를 다시 잠근다.
* **다시 말해, `notify_xxx()`가 `wait()`보다 먼저 호출되면, 해당 쓰레드는 영원히 기다린다!!**



#### 예시 : 잘못된 조건변수 사용

> ```cpp
> #include <iostream>
> #include <mutex>
> #include <queue>
> 
> static std::mutex sQueueLock;
> static std::condition_variable sEvent;
> static std::queue<int> sQueue;
> 
> void Consume()
> {
>     while(true)
>     {
>         int val;
>         {
>             std::unique_lock<std::mutex> lock(sQueueLock);
>             sEvent.wait(lock);
> 
>             val = sQueue.front();
>             sQueue.pop();
>         }
>         std::cout << val << std::endl;
>     }
> }
> 
> void Produce()
> {
>     std::unique_lock<std::mutex> lock(sQueueLock);
>     sQueue.push(1);
> 
>     sEvent.notify_one();
> }
> 
> int main()
> {
>     std::thread producer(Produce);
>     std::thread consumer(Consume);
> 
>     consumer.join();
>     producer.join();
> 
>     return 0;
> }
> // sEvent.notify_one()이 sEvent.wait(lock)보다 먼저 실행되면 무한대기...
> ```



#### std::condition_variable::wait

> ```cpp
> template< class Predicate >
> void wait(std::unique_lock<std::mutex>& lock, Predicate pred);
> ```

* 아래와 같음

  > ```cpp
  > while(!pred())
  > {
  >     wait(lock);
  > }
  > ```

* 두 가지 용도로 사용

  * 잘못 깨어날 위험을 줄임
  * `pred()`는 잠긴 두 쓰레드 모두에서 접근할 수 있는 bool 변수의 역할을 함



#### 예시 : 잘못된 조건변수 활용 해결책

> ```cpp
> #include <iostream>
> #include <mutex>
> #include <queue>
> 
> static std::mutex sQueueLock;
> static std::condition_variable sEvent;
> static std::queue<int> sQueue;
> 
> void Consume()
> {
> 	while(true)
> 	{
> 		int val;
> 		{
> 			std::unique_lock<std::mutex> lock(sQueueLock);
> 			sEvent.wait(lock, [] { return !sQueue.empty(); });
> 		
> 			val = sQueue.front();
> 			sQueue.pop();
> 		}
> 		std::cout << val << std::endl;
> 	}
> }
> 
> void Produce()
> {
> 	std::unique_lock<std::mutex> lock(sQueueLock);
> 	sQueue.push(1);
> 	
> 	sEvent.notify_one();
> }
> 
> int main()
> {
> 	std::thread producer(Produce);
> 	std::thread consumer(Consume);
> 	
> 	consumer.join();
> 	producer.join();
> 	
> 	return 0;
> }
> ```

* 둘이 같다고 생각하면 이해하기 쉬울 듯

  > ```cpp
  > sEvent.wait(lock, [] { return !sQueue.empty(); });
  > ```
  >
  > ```cpp
  > while(sQueue.empty())
  > {
  > 	sEven.wait(lock);
  > }
  > ```
  >
  > 
