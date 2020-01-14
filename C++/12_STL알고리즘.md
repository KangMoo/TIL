# INDEX

1. STL알고리즘
2. find()알고리즘
3. STL알고리즘
4. 정리



## STL알고리즘

#### STL알고리즘이란

* 요서 범위에서 쓸 수 있는 함수들
  * [처음, 마지막)
* 배열 또는 몇몇 STL컨테이너에 쓸 수 있음
* 반복자를 통해 컨테이너에 접근
* 컨테이너의 크기를 변경하지 않음 (따라서 추가 메모리 할당도 없음)



#### STL알고리즘의 유형

* 변경 불가 순차 연산
  *  `find()`, `for_each`, ...
    * `#include<algorithm>`
* 변경 가능 순차 연산
  * `copy()`, `swap()`, ...
    * `#include<algorithm>`
* 정렬 관련 연산
  * `sort()`, `merge()`, ...
    * `#include<algorithm>`
* 범용 수치 연산
  * `accumulate()`, ...
    * `#include<numeric>`



#### 예시 : vector를 다른 vector로 복사하기

> ```c++
> #include<algorithm>
> int main()
> {
>     std::vector<int> scores;
> 
>     socres.push_back(10);
>     socres.push_back(70);
>     socres.push_back(40);
> 
>     std::vector<int> copiedScores;
>     copiedScores.resize(scores.size());
> 
>     std::copy(scores.begin(), scores.end(), copiedScores.begin());
> 
>     for(std::vector<int>::iterator it = copiedScores.begin(); ++it)
>     {
>         std::cout<<*it<<std::endl;
>     }
> 
>     return 0;
> }
> ```



#### copy()

* 사용법

  > ```cpp
  > template<class _Init, class _OutIt>
  >     _OutIt copy(_InIt _First, _InIt _Last, _OutIt _Dest);
  > ```
  >
  > ```cpp
  > std::copy(scores.begin(), scores.end(), copiedScores.begin());
  > std::copy(names.begin(), names.begin() + 2, copiedScores.begin());
  > ```

* 구현

  > ```cpp
  > template<class _InIt, class _OutIt>
  >     _OutIt copy(_InIt _First, _InIt _Last, _OutIt _Dest)
  > {
  >     for(; _First != _Last; ++_Dest, (void)++_First)
  >     {
  >         *_Dest = *First;
  >     }
  >     return (_Dest);
  > }
  > ```

  

## find()알고리즘

#### find()알고리즘을 만들어보자!

> ```cpp
> #include<iostream>
> #pragma once
> namespace samples
> {
>     //포인터를 써서 Find()를 만드는 방법은?
>     namespace Algorithm
>     {
>         //자료형 2개, ITER는 반복자, T는 데이터형
>         template<typename ITER, typename T>
>         //begin/end와 찾는 값을 받음
>         const ITER* Find(const ITER* begin, const ITER* end, const T& value);
>         {
>             //begin부터
>             const ITER* p = begin;
>             //end에 이르기까지 반복
>             while(p != end)
>             {
>                 //값을 찾으면 break;
>                 if (*p == value)
>                 {
>                     break;
>                 }
>                 ++p;
>             }
>             //위치를 반환
>             return p;
>         }
>     }
> };
> ```

> ```cpp
> #include<iostream>
> #include<vector>
> #include"Algorithm.h"
> using namespace std;
> namespace samples
> {
>     void FindFunctionExample()
>     {
>         const int ARRAY_SIZE = 10;
>         int arr[ARRAY_SIZE] = { 10, 20, 13, 52, 32, 67, 89, 15, 46, 3};
> 
>         const int* ptr = Algorithm::Find(arr, arr+ARRAY_SIZE, 67);
>         cout<<*ptr;
>     }
> }
> ```



## STL알고리즘

#### STL알고리듬 목록

- 너무 많음

- 여기 참고 : http://www.cplusplus.com/reference/algorithm/

- 생각보다 별로 쓸 일 없다

- - 정렬, find정도...?



## 정리

- 여기까지 C++ 03이다!!

- C++ 03은 C++를 Java에 가깝게 만드려던 시도였음

- 하지만 사실상 컨테이너만 살아남음

- - 많은 기능들이 충분한 고려없이 나왔음
  - 그래서, 이 중 대부분이 C++ 1x에서 은퇴함
  - 아마 이것 때문에 정리하는데 8년이나      걸린듯…

- 배울게 더 남아있다는 뜻!

