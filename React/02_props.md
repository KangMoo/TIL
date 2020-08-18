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
>   return (
>     <Hello name="react" />
>   );
> }
> 
> export default App;
> ```
>
> ```react
> import React from 'react';
> 
> function Hello(props) {
>   return <div>안녕하세요 {props.name}</div>
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
>   return (
>     <Hello name="react" color="red"/>
>   );
> }
> 
> export default App;
> ```
>
> ```react
> import React from 'react';
> 
> function Hello({ color, name }) {
>   return <div style={{ color }}>안녕하세요 {name}</div>
> }
> 
> export default Hello;
> ```
>
> 