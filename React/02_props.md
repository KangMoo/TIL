## props

- properties의 약자
- 어떠한 값을 컴포넌트에게 전달해줘야 할 때 props를 사용한다

### props의 기본 사용법

예시

> ```react
> import React from 'react';
> import Hello from './Hello';
> 
> function App() {
>     return (
>        <Hello name="react" />
>     );
> }
> 
> export default App;
> ```
>
> ```react
> import React from 'react';
> 
> function Hello(props) {
>     return <div>안녕하세요 {props.name}</div>
> }
> 
> export default Hello;
> ```

여러개의 props, 비구조화 할당 예시

> ```react
> import React from 'react';
> import Hello from './Hello';
> 
> function App() {
>     return (
>        <Hello name="react" color="red"/>
>     );
> }
> 
> export default App;
> ```
>
> ```react
> import React from 'react';
> 
> function Hello({ color, name }) {
>     return <div style={{ color }}>안녕하세요 {name}</div>
> }
> 
> export default Hello;
> ```

defaultProps로 기본값 설정

* 컴포넌트에 props를 지정하지 않았을 때 기본적으로 사용할 값을 설정하고 싶다면 컴포넌트에 `defaultProps`라는 값을 설정하면 된다.

> ```react
> import React from 'react';
> import Hello from './Hello';
> 
> function App() {
>     return (
>        <>
>        <Hello name="react" color="red"/>
>        <Hello color="pink"/>
>        </>
>     );
> }
> 
> export default App;
> ```
>
> ```react
> import React from 'react';
> 
> function Hello({ color, name }) {
>     return <div style={{ color }}>안녕하세요 {name}</div>
> }
> 
> Hello.defaultProps = {
>     name: '이름없음'
> }
> 
> export default Hello;
> ```

props.children 예시

> Wrapper.js
>
> ```react
> import React from 'react';
> 
> function Wrapper({ children }) {
>     const style = {
>        border: '2px solid black',
>        padding: '16px',
>     };
>     return (
>        <div style={style}>
>          {children}
>        </div>
>     )
> }
> 
> export default Wrapper;
> ```
>
> App.js
>
> ```react
> import React from 'react';
> import Hello from './Hello';
> import Wrapper from './Wrapper';
> 
> function App() {
>     return (
>        <Wrapper>
>          <Hello name="react" color="red"/>
>          <Hello color="pink"/>
>        </Wrapper>
>     );
> }
> 
> export default App;
> ```
>



