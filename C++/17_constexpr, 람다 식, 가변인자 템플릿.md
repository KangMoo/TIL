# INDEX

1. [템플릿 메타 프로그래밍](#템플릿-메타-프로그래밍)
2. [constexpr 함수, 변수](#constexpr-함수-변수)
3. [constexpr 활용](#constexpr-활용)
4. [const vs constexpr](#const-vs-constexpr)
5. [람다 식(Lambda Expression)](#람다-식Lambda-Expression)
6. [가변 인자(Variadic) 템플릿](#가변-인자variadic-템플릿)



## 템플릿 메타 프로그래밍

#### 예시 : 템플릿 메타 프로그래밍

> ```cpp
> // T로 전달된 값을 계산
> template <int T>
> struct Fibonacci
> 	enum { value = (Fibonacci<T-1>::value + Fibonacci<T-2>::value) };
> };
> 
> // 이 템플릿 메타프로그래밍에는 조건문이 없다.
> // 그 대신 구조체 오버로딩 같은 방법을 사용하여 0, 1, 2와 같은 특수 케이스를 처리.
> template<>
> struct Fibonacci<0>
> {
> 	enum { value = 1 };
> };
> 
> template<>
> struct Fibonacci<1>
> {
> 	enum { value = 1 };
> };
> 
> template<>
> struct Fibonacci<2>
> {
> 	enum { value = 1 };
> };
> ```
>
> ```cpp
> //Main.cpp
> 
> // 이렇게 하면 값을 곧바로 얻음
> // 값이 컴파일 시에 컴파일 되어서 들어옴
> int mai()
> {
> 	int x = Fibonacci<45>::value;
> 	cout << x << endl;
> }
> ```
>
> 좋긴 좋은데 어려움....



#### 왜 이런 짓을 했을까?

* 컴파일 도웅에 값을 평가하기 위해
* 최!적!화!
* 그러나! 직관적이지 않음
* 실행중에 값을 평가하려면?
  * 그것 용 함수를 따로 만들어야함 예: `Fibonacci(int i)`
* 즉 앵간에선 남용이다...



## constexpr 함수, 변수

#### constexpr

* **컴파일 도중에 <u>"반드시"</u> 값이 결정되게 하려면 constexpr 변수를 쓸 것!!**
* 컴파일 시에 평가를 강제하기 위해서 템플릿 메타 프로그래밍을 남용함
* 이러지 앟아도 컴파일러가 자발적으로 그렇게 해주는 경우도 있긴 했음
* constexpr가 **프로그래머의 의도**를 보여주는 더 나은 방법
  * 우리의 의도는 컴파일 도중에 값을 평가하는 것임을 컴파일러에게 알려줌
  * 컴파일러가 컴파일 도중에 변수들을 결정지어 줌
  * 함수는 그럴려고 최대한 노력함
    * 평가할 수 없을 경우 실행시에 그 함수가 호출 됨



#### 예시 : constexpr함수

> ```cpp
> constexpr unsigned int fibonacci (unsigned int i)
> {
>     return (i<= 1u) ? i : (fibonacci(i-1) + fibonacci(i-2));
> }
> ```



#### 예시 : 함수와 constexpr

> ```cpp
> constexpr int Factorial(int n)
> {
>     return n <= 1 ? 1: n* Factorial(n-1);
> }
> int main()
> {
>     // 사례 1
>     int value = 3;
>     int result1 = Factorial(value); // OK
> 
>     // 사례 2
>     constexpr int result2 = Factorial(value); // 컴파일 에러
> 
>     // 사례 3
>     constexpr int result3 = Factorial(3); // OK
> 
>     // …
> }
> ```



#### 예시 : 컴파일러가 거부하는 경우

> ```cpp
> constexpr int Fibonacci(int n)
> {
>     if (n == 0)
>     {
>         return 0;
>     }
>     return n <= 2 ? 1 : Fibonacci(n-1) + Fibonacci(n-2);
> }
> int main()
> {
>     constexpr int result = Fibonacci(30); // 컴파일 에러
>     // ...
> }
> ```



## constexpr 활용

#### constexpr활용

* 대표적인 활용 예시 : 해쉬함수



#### 해쉬함수

* 해쉬함수는 각 문자열마다 중복되지 않는 정수 값을 생성하려 함
  * 해쉬 충돌이 일어날 수 있음



#### constexpr를 활용한 해쉬 함수

* 코드가 문자열을 참조하는 경우 문자열을 비교해야 함
* 문자열 비교에는 비영이 든다! O(N)
* 비용을 절감하려면
  * 문자열의 해쉬를 생성할 수 있음 : O(N)
  * 그 후에 해쉬 값을 비교 : O(1)



#### 허나! 여전히 런타임 중에 실행되고 있음

* 문자열 해쉬를 컴파일 도중에 만들 수 있다면?
* 그럼 문자열 비교에 드는 런타임 비용은 어제나 O(1)
* 드디어 consexpr을 써서 컴파일 타임에 문자열 해쉬를 만들 수 있게 됐다.



#### 예시 : 컴파일 타임 문자열 해쉬

> ```cpp
> constexpr unsigned long djb2_hash_impl(const char* text, unsigned long prev_hash)
> {
>     return text[0] == '\0' ? prev_hash : djb2_hash_impl(&text[1], prev_hash*33^static_cast<unsigned long>(text[0]));
> }
> 
> constexpr unsigned long djb2_hash(const char* text)
> {
>     return djb_hash_impl(text, 5381);
> }
> 
> int main()
> {
>     constexpr unsigned long jcoco = djb2_hash("Coco");
>     // ...
> }
> ```





## const vs constexpr

#### const vs constexpr

* 같지 않다!

* | const       | constexpr                     |
  | ----------- | ----------------------------- |
  | 변경을 불허 | 컴파일 시에 평가해주면 좋겠음 |



#### const와 constexpr

* 결국 둘 다 const임



#### const와 constexpr함수

> | const                    | constexpr                            |
> | ------------------------ | ------------------------------------ |
> | 멤버 함수에만 사용 가능  | 멤버와 비멤버 함수에 둘다 사용 가능  |
> | 멤버 변수를 바꿀 수 없음 | 멤버 변수를 바꿀 수 있음 (C++14부터) |



#### 배열의 길이 정하기

> enum
>
> ```cpp
> class FixedArray
> {
> public:
> 	// ...
> private:
> 	enum { MAX = 10 };
> 	int mSize;
> 	int mArray[MAX];
> };
> ```
>
> constexpr
>
> ```cpp
> class FixedArray
> {
> public:
> 	// ...
> private:
> 	static constexpr int MAX = 10;
> 	int mSize;
> 	int mArray[MAX];
> };
> ```





## 람다 식(Lambda Expression)

#### 람다식

* 이름이 없는 함수 개체

* 내포되는 함수

  > ```cpp
  > [<captures>](<parameters>)<specifiers> -> <return_type>
  > {
  >     <body>
  > }
  > ```
  >
  > - `<captures>` : 캡쳐 블록
  > - `<parameters>` : 매개변수 목록 (선택사항)
  > - `<specifiers>` : 지정자 (선택사항)
  > - `<return_type>` : 반환형 (선택사항)
  > - `<body>` 함수 바디



#### 람다 식 이외의 방법으로 백테 정렬

* 예시 1

  > ```cpp
  > #include <algorithm>
  > #include <vector>
  > struct Comparer
  > {
  >     bool operator()(float a, float b)
  >     {
  >         return (a > b);
  >     }
  > };
  > 
  > int main()
  > {
  >     std::vector<float> scores;
  > 
  >     scores.push_back(50.f);
  >     scores.push_back(88.f);
  >     scores.push_back(70.f);
  > 
  >     Comparer comparer;
  >     std::sort(scores.begin(), scores.end(), comparer);
  >     return 0;
  > }
  > ```

* 예시 2

  > ```cpp
  > #include <algorithm>
  > #include <vector>
  > bool Comparer(float a, float b)
  > {
  >     return (a>b);
  > }
  > 
  > int main()
  > {
  >     std::vector<float> scores;
  > 
  >     scores.push_back(50.f);
  >     scores.push_back(88.f);
  >     scores.push_back(70.f);
  > 
  >     std::sort(scores.begin(), scores.end(), Comparer);
  >     return 0;
  > }
  > ```



#### 예시 : 람다식을 이용한 벡터 정렬

> ```cpp
> #include <algorithm>
> #include <vector>
> int main()
> {
>     std::vector<float> scores;
>     scores.push_back(50.f);
>     scores.push_back(88.f);
>     scores.push_back(70.f);
>     std::sort(scores.begin(), scores.end(), [](float a, float b) { return (a>b); });
> }
> ```



#### 캡쳐 블록

* 람다 식을 품는 범위 안에 있는 변수를 람다 식에게 넘겨줄 때 사용
* 캡쳐의 종류
  *  [ ]
    * 비어있음. 캡쳐하지 않음
  * =
    * 값에 의한 캡쳐. 모든 외부 변수를 캡쳐함
    * 람다 식 안에서 수정할 수 없음
  * &
    * 참조에 의한 캡쳐. 모든 외부 변수를 캡쳐함
  * <변수 이름>
    * 특정 변수를 값으로 캡쳐
    * 람다 식 안에서 수정할 수 없음
  * &<변수 이름>
    * 특정 면수를 참조로 캡쳐

#### 캡쳐 블록 예시

* 캡쳐하지 않는 경우

  > ```cpp
  > int main()
  > {
  > 	// 방법 1
  > 	auto noCapture = []() { std::cout << "No capturing example 1" << std::endl; };
  > 	noCapture();
  > 	
  > 	// 방법 2. 바로 실행
  > 	[] { std::cout << "No Capturing example 2" << std::endl; }();
  > 	// ...
  > }
  > ```

* 값에 의한 캡쳐

  > ```cpp
  > int main()
  > {
  > 	float score1 = 80.f;
  > 	float score2 = 20.f;
  > 	
  > 	auto max = [=]() { return score1 > score2 ? score1 : score2; };
  > 	
  > 	std::cout << "Max value is " << max() << std::endl;
  > 	// ...
  > }
  > ```
  >
  > ```cpp
  > int main()
  > {
  >     float score1 = 80.f;
  >     float score2 = 20.f;
  >     
  >     auto changeValue = [=]()
  >     {
  >         // 컴파일 에러, score1을 수정할 수 없음
  >         score1 = 100.f;
  >     };
  >     
  >     changeValue();
  >     // ...
  > }
  > ```

* 참조에 의한 캡쳐

  > ```cpp
  > int main()
  > {
  > 	float score1 = 80.f;
  > 	float score2 = 20.f;
  > 	
  > 	auto changeValue = [&]()
  > 	{
  > 		score1 = 60.f;
  > 		score2 = 55.5f;
  > 	};
  > 	
  > 	changeValue();
  > 	// ...
  > }
  > ```

* 값에 의한 특정 변수 탭쳐

  > ```cpp
  > int main()
  > {
  > 	float score1 = 30.f;
  > 	float score2 = 20.f;
  > 	float score3 = 100.f;
  > 	
  > 	auto printValue = [score1, score3]
  > 	{
  > 	std::cout << score1 << " / " << socre3 << std::endl;
  > 	};
  > 	
  > 	printValue();
  > 	// ...
  > }
  > ```
  >
  > ```cpp
  > int main()
  > {
  > 	float score1 = 30.f;
  > 	float score2 = 20.f;
  > 	float score3 = 100.f;
  > 	
  > 	auto printValue = [score1]
  > 	{
  > 		// 컴파일 에러, score2에 접근할 수 없음
  > 		std::cout << score1 << " / " << socre2 << std::endl;
  > 	};
  > 	
  > 	printValue();
  > 	// ...
  > }
  > ```

* 참조에 의한 특정 변수 캡쳐

  > ```cpp
  > int main()
  > {
  >     float score1 = 80.f;
  >     float score2 = 20.f;
  >     
  >     auto changeValue = [&score1]()
  >     {
  >         score1 = 60.f;
  >         //score2 = 55.5f;
  >             //score2에 접근할 수는 없음
  >     };
  >     
  >     changeValue();
  >     // ...
  > }
  > ```

* 캡쳐 옵션 섞기

  > ```cpp
  > int main()
  > {
  >     float score1 = 80.f;
  >     float score2 = 20.f;
  >     
  >     auto changeValue = [=, &score1]() // 올바름
  >     {
  >         score1 = 100.f; // 참조에 의한 캡처
  >             std::cout << score2; // 값에 의한 캡처
  >     };
  >     
  >     changeValue();
  >     // ...
  > }
  > ```



#### 매개변수 목록

* 선택사항
* 빈 괄호를 생략할 수 있음



#### 매개변수 목록 예시

* 두 값 더하기

  > ```cpp
  > int main()
  > {
  > 	int score1 = 70;
  > 	int score2 = 85;
  > 	
  > 	auto add = [](int a, int b)
  > 	{
  > 		return a + b;
  > 	};
  > 	
  > 	std::cout<<add(score1, score2) << std::endl;
  > 	// ...
  > }
  > ```

* 정렬하기

  > ```cpp
  > #include <algorithm>
  > #include <vector>
  > int main()
  > {
  > 	std:;vector<float> scores;
  > 	
  > 	scores.push_back(50.f);
  > 	scores.push_back(88.5f);
  > 	scores.push_back(70.f);
  > 	
  > 	std::sort(scores.begin(), scores.end(), [](float a, float b) { return (a > b); });
  > 	std::sort(scores.begin(), scores.end(), [](float a, float b) { return (a <= b); });
  > 	
  > 	return 0;
  > }
  > ```



#### 지정자

* 선택사항
* mutalbe
  * 값에 의해 캡쳐된 개체를 수정할 수 있게 함
  * 괜찮은 언어 디자인, 허나 C++에 너무 늦게 도입됨



#### 지정자 예시

* 지정자 사용

  > ```cpp
  > int main()
  > {
  > 	int value = 100;
  > 	
  > 	auto foo = [value]() mutable
  > 	{
  > 		std::cout << ++value << std::endl;
  > 	};
  > 	
  > 	foo();
  > 	//...
  > }
  > ```

* 지정자 미사용

  > ```cpp
  > int main()
  > {
  > 	int value = 100;
  > 	
  > 	auto foo = [value]()
  > 	{
  > 		// 컴파일 에러
  > 		std::cout << ++value << std::endl;
  > 	};
  > 	
  > 	foo();
  > 	//...
  > }
  > ```



#### 반환형

* 선택사항
* 반환 형을 적지 않으면 반환문을 통해 유추해줌
  * 으엑 자동 반환 형식



#### 람다 식의 장점

* 간단한 함수를 빠르게 작성할 수 있음



#### 람다 식의 단점

* 디버깅하기 힘들어짐
* 함수 재사용성이 낮음
* 사람들은 보통 함수를 새로 만들기 전에 클래스에 있는 기존 함수를 찾아 봄
* 람다 함수는 눈에 잘 띄지 않아서 못 찾을 가능성이 높음
  * 똑같은 함수를 다시 만들 수도 있다는 소리... -> 중복 가능성이 생김



#### 베스트 프랙티스

1. 기본적을 이름 있는 함수를 쓰자 (전통적 방식)
2. 자잘한 함수는  람다로 써도 괜찮음(한 줄짜리 함수)
3. 허나 재사용할 수 있는 함수를 찾기 좀 어려움
4. 정렬 함수처럼 STL 컨테이너에 매개변수로 전달할 함수들도 좋은 후보
   * qsort()에 함수 포인터 넘겨줘야 했는데, 람다를 사용하면 확실히 더 좋음



## 가변 인자(Variadic) 템플릿

#### 가변 인자 템플릿

* 다양한 매개변수 개수와 자료형을 지원하는 클래스 또는 함수 템플릿

* 매개변수 목록에서 생략 부호 `...`를 씀

  > ```cpp
  > template<typename ... Arguments>
  > class <클래스명>
  > {
  > };
  > ```
  >
  > ```cpp
  > template<typename ... Arguments>
  > <반환형> <함수명> (Arguments... args)
  > {
  > };
  > ```



#### 가변 인자 템플릿 활용

* 활용법을 생각하기가 정말로 어려움
* 주로 인자 전달용. 예) std;;make_unique()
* 몇몇 다른 아이디어들도 있음. 허나 대개 실용적이라는 생각이 들지 않음...



