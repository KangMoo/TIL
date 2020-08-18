## React

- 페이스북에서 제공하는 프론트엔드 라이브러리

- 컴포넌트 기반으로 되어있어서 컴포넌트에 데이터를 내려주면 개발자가 설계한대로 UI가 만들어져 사용자에게 보여진다

- 특징

  - 컴포넌트
  >  리액트는 컴포넌트 기반의 라이브러리로, 작은 컴포넌트들이 모여서 만들어진다. 이 작은 컴포넌트들은 쪼개져 있기 때문에, 코드 전체를 파악하기가 상대적으로 쉽고, 재사용성을 가지고 있어 효율적이다
  >
  > 컴포넌트는 클래스형과 함수형으로 나누어진다

  - One Wat Data flow
  > 리액트는 데이터의 흐름이 한 방향으로만 흐른다. 데이터가 내려가기만 하지 밑에서 올라올 수는 없다. 따라서 부모의 데이터를 바꿔주기 위해서는 state를 이용해야한다

  - Props and State

  > props란 부모 컴포넌트에서 자식 컴포넌트로 전달해주는 데이터를 말한다. `읽기 전용 데이터`라고 생각하면 된다
  >
  > 자식 컴포넌트에서 전달받는 props를 변경하는건 불가능하고 props를 전달해준 최상위 부모 컴포넌트만 prorps의 변경이 가능하다
  >
  > state는 동적인 데이터를 다룰 때 사용한다. 사용자와의 상호작용을 통해 데이터를 동적으로 변경해야할 때 사용한다. state는 클래스혀 컴포넌트에서만 사용할 수 있는데 state는 독립적이라 다른 컴포넌트의 직접적인 접근은 불가능하다. 그러나 자신보다 상위에 있는 state는 변경이 가능한데 state를 변경해주는 함수를prorp으로 받는다면 state의 변경이 가능하다. 주의해야할 점은 props로 넘겨줄 때에 this의 binding을 신경써줘야 한다.

  - Virtual Dom

  > VIrtual Dom은 가상의 DOM을 의미한다. 일반적으로 html코드를 짜고 웹 브라우저에서 html파일을 열게 되면, html코드들이 DOM을 만들게된다. 그리고 만약 html코드의 특정 한 부분이 변경되게 된다면 전체 DOM을 새롭게 만들게 되어 비효율적인 구조이다. 이런 문제점들은 리액트에서 해결된다. 리액트는 가상의 DOM을 만들어서 진짜 DOM과 비교하여 변경 사항이 있을 경우 전체를 새롭게 만드는 게 아니라 변경된 부분만 진짜 DOM의 반영하는 방식으로 작업을 수행한다.



## JSX

- JSX는 리액트에서 생김새를 정의할 때 사용하는 문법이다
- 얼핏 HTML처럼 생겼지만 JavaScript이다
- 리액트 컴포넌트 파일에서 XML 형태로 코드를 작성하면 babel이 JSX를 JavaScript로 변환해준다.

**Babel**

>  Babel은 자바스크립트의 문법을 확장해주는 도구이다. 아직 지원되지 않는 최신 문법이나 편의상 사용하거나 실험적인 자바스크립트 문법들을 정식 자바스크립트 형태로 변환해줌으로써 구형 브라우저같은 환경에서도 제대로 실행 할 수 있게 해주는 역할을 한다

**규칙**

- 꼭 닫혀야 하는 태그

  - 태그는 꼭 닫혀있어야 한다.
    - HTML에서는 닫히지 않는 태그여도 리엑트에선 닫혀야 한다
      - `<br />`
      - `<input />`

- 꼭 감싸져야 하는 태그

  - 두개 이상의 태그는 무조건 하나의 태그로 감싸져 있어야 한다

    ```react
    import React from 'react';
    import Hello from './Hello';
    
    function App() {
      return (
        <Hello />
        <div>안녕히계세요.</div>
        // Error!
      );
    }
    
    export default App;
    ```

    

    ```react
    import React from 'react';
    import Hello from './Hello';
    
    function App() {
      return (
        <>
          <Hello />
          <div>안녕히계세요</div>
        </>
      );
    }
    
    export default App;
    ```

**JSX안에 자바스크립트 값 사용하기**

-  JSX내부에 자바스크립트 변수를 보여줘야 할 때는 `{}`로 감싸서 보여준다

> ```react
> import React from 'react';
> import Hello from './Hello';
> 
> function App() {
>   const name = 'react';
>   return (
>     <>
>       <Hello />
>       <div>{name}</div>
>     </>
>   );
> }
> 
> export default App;
> ```



