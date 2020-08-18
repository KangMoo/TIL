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

**style과 className**

JAX에서 태그에 style과 CSS class를 설정하는 방법은 HTML에서 설정하는 방법과 다르다.

- style

  인라인 스타일은 객체 형태로 작성해야 하며, `background-color`처럼 `-` 로 구분되어있는 이름들은 `backgoundColor` 처럼 camelCase형태로 네이밍 해줘야 한다

> ```react
> import React from 'react';
> import Hello from './Hello';
> 
> function App() {
>   const name = 'react';
>   const style = {
>     backgroundColor: 'black',
>     color: 'aqua',
>     fontSize: 24, // 기본 단위 px
>     padding: '1rem' // 다른 단위 사용 시 문자열로 설정
>   }
> 
>   return (
>     <>
>       <Hello />
>       <div style={style}>{name}</div>
>     </>
>   );
> }
> 
> export default App;
> ```

- class

  CSS class를 설정할 때에는 `class=`가 아닌 `className=`으로 설정해줘야 한다.

- 주석

  주석의 형태는 `{/* 이런 형태로 */}` 작성해야 한다

  열리는 태그 내부에서는 `//`를 사용해 주석을 작성한다

> ```react
> import React from 'react';
> import Hello from './Hello';
> import './App.css';
> 
> 
> function App() {
>   const name = 'react';
>   const style = {
>     backgroundColor: 'black',
>     color: 'aqua',
>     fontSize: 24, // 기본 단위 px
>     padding: '1rem' // 다른 단위 사용 시 문자열로 설정
>   }
> 
>   return (
>     <>
>       {/* 주석은 화면에 보이지 않습니다 */}
>       /* 중괄호로 감싸지 않으면 화면에 보입니다 */
>       <Hello 
>         // 열리는 태그 내부에서는 이렇게 주석을 작성 할 수 있습니다.
>       />
>       <div style={style}>{name}</div>
>       <div className="gray-box"></div>
>     </>
>   );
> }
> 
> export default App;
> ```
>
> 