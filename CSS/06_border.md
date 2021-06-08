## 요점

- 박스 모델에 들어가는 단축 스타일의 네 방향의     순서 - 시계방향
- 단축 스타일에서 값을 4개 입력 시 - 'top' 'right' 'bottom' 'left'
- 스타일 값 3개 입력 시 - 'top' 'right/left' 'bottom'
- 스타일 값 2개 입력 시 - 'top/bottom' 'right/left'
- border-width - 선 굵기
- border-style - 테두리 형태
- border-color - 선의 색상
- border 속성은 border-left, border-left-width와 같은 방식으로 속성명을 조합하여 사용.
- border 만 선언할 경우 단축 속성

 

## 요소의 네 가지 방향

- 기본적으로 모든 요소는 사각혀으이 형테를 띄고 있음.
- top, right, bottom, left 까지의 방향을 'border-top', 'border-right'와 같은 방식으로 선언해 줄 수 있음

 

## 테두리를 구성하는 세가지 요소

- 굵기(width)
- 선의 형태(style)
- 선의 색상(color)

 

## 선의 굵기 (border-width)

- px이나 e등의 단위를 사용

- - 보통 px사용

> ```CSS
> div {
>     border-top-width : 3px;
> }
> ```



## 선의 형태 (border-style)

- 실선이나 점선 등으로 바꿔줄 수 있으며, 아예 없앨 수도 있음.

> ```CSS
> div {
>     border-right-style : dotted;
> }
> ```

- 사용 가능 속성

- - **none**

  - - 선을 없앱니다. 대부분 요소의 기본 값입니다.

  - **solid**

  - - 실선의 형태로 적용합니다.

  - **dotted**

  - - 점선 형태로 적용합니다.

  - **dashed**

  - - 바느질선 같은 형태의 점선입니다.

  - **double**

  - - 이중 선의 형태입니다. 굵기가 3px 이상이 되어야 볼 수 있습니다.

  - **groove**

  - - 입체적으로 선을 홈이 들어간 것처럼 보여줍니다. 최소 2px은 되야 확인 가능합니다.

  - **ridge**

  - - groove와 비슷하지만, 음영 값이 반대라 선이 돌출되어 보입니다.

  - **inset**

  - - 요소 전체가 안으로 들어가 보입니다.

  - **outset**

  - - inset의 반대입니다. 요소 전체가 밖으로 돌출되어 보입니다.

  - **inherit**

  - - 부모의 속성을 상속받습니다.



## 선의 색상 (border-color)

- 선의 색상을 결정

> ```CSS
> div {
>     border-bottom-color : #aaa;
> }
> ```



## 테두리 속성의 조합

> ```CSS
> div {
>     border-top-width : 3px;
>     border-top-style : dotted;
>     border-top-color : red;
> }
> ```

> ```CSS
> div {
>     border-top : 3px dotted red;
> }
> ```

> ```CSS
> div{
>     border-width: 3px;
> }
> ```



## 네 방향의 조합

- top, right, botton, left순

  > ```CSS
  > div {
  >     border-width: 3px 2px 1px 2px;
  > }
  > ```

* 만약 부족할 경우?

  > ```CSS
  > div {
  >     border-width: 3px 1px 2px;
  > }
  > ```

  - 위의 경우 top,(right+left), bottom으로 인식

  - - top-bottom / left-right끼리 쌍

      > ```CSS
      > div {
      >     border-color : red #333;
      > }
      > ```

    - 위의 경우 (top+botton), (right+left)로 인식



## border속성

- 줄인 속성
- 모든 변에 동일하게만 적용 가능

> ```CSS
> div {
>     border: 1px solid red;
> }
> ```