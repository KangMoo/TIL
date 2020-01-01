# INDEX

1. GDI+
2. Color 구조체
3. Pen
4. Brush



## GDI+

#### GDI+

- GDI : OS의 영역
- GDI+ : .NET은 Graphics가 담당

- Graphics 사용 방법 2가지

- - `e.Graphics()` 사용 (`paint()` 이벤트)
  - `CreateGraphics()` 사용

- `CreateGraphics()` 언제 사용? -> `paint`이벤트 외의 메서드

 

#### `CreateGraphics()` 사용

- 원형

  > ```c#
  > public Graphics CreateGraphics()
  > 
  > namespace -> System.Winodws.Forms
  > ```
  >
  > 사용한 후에는 되도록 `Graphics.Dispose()`호출

- 사용하는 방법

- - `Graphics` 객체를 참조
  - `Graphics` 메서드로 출력



####  `CreateGraphics()` 사용 예시

> ```c#
> List<Point> ptCircle = new List<Point>();
> public Form1()
> {
>     InitializeComponent();
> }
> 
> private void Form1_MouseClick(object sender, MouseEventArgs e)
> {
>     ptCircle.Add(e.Location);
> 
>     Graphics g = CreateGraphics();
>     foreach (Point x in ptCircle)
>     {
>         g.DrawEllipse(Pens.Black, x.X - 10, x.Y - 10, 20,20);
>     }
>     g.Dispose();
> }
> ```



## Color 구조체

#### Color 구조체

- 펜과 브러시의 색상 설정에 사용

- Color Struct

- - ARGB

- Object ->     ValueType -> Color



## Pen

### Pen vs Pens

- Pen -> 생성
- Pens -> 기본 제공 펜

- Pen 생성자 : `Pen(Brush)`, `Pen(Color)`, `Pen(Brush, Single)`, `Pen(Color, Single)`

- Pen 해제 : `Dispose()`

 

#### 펜 스타일

- DashStyle     속성 사용

  >`public System.Drawing.Drawing2D.DashStyle DashStyle{ get; set; }`

- `using System.Drawing.Drawing2D` 선언

- `DashStyle` 열거형 리턴

  >Custom
  >
  >Dash
  >
  >DashDot
  >
  >DashDotDot
  >
  >Dot
  >
  >Solid
  >
  >Width
  >
  >등등

  

#### Pen을 사용한 DrawLine 예제 코드

> ```c#
> public Form1()
> {
>     InitializeComponent();
> }
> 
> private void Form1_Paint(object sender, PaintEventArgs e)
> {
>     Pen newPen = new Pen(Color.FromArgb(200, 150, 100));
>     newPen.Width = 4.0f;
>     e.Graphics.DrawLine(newPen, 10, 10, 100, 10);
>     newPen.Dispose();
> }
> ```



#### 펜 스타일 적용한 예제 코드

> ```c#
> 	private void Form1_Paint(object sender, PaintEventArgs e)
> 	{
> 	    Pen newPen = new Pen(Color.FromArgb(200, 150, 100));
> 	    //using System.Drawing.Drawing2D 추가하면     newPen.DashStyle = Dashstyle.DashDot;으로 사용 가능
> 	    newPen.DashStyle = System.Drawing.Drawing2D.DashStyle.DashDot;
> 	    newPen.Width = 4.0f;
> 	    e.Graphics.DrawLine(newPen, 10, 10, 100, 10);
> 	    newPen.Dispose();
> 	}
> 
> ```



## Brush

#### 역할

- 도형 내부를 색 또는 패턴으로 채우는 역할

#### 브러시 종류

- `SolidBrush`, `HatchBrush`, `TextureBrush`…등

#### 브러시를 요구하는 메서드의 공통점

- Fill~~로 시작

 

#### 브러시를 사용한 도형 색칠 예시

> ```c#
> private void Form1_Paint(object sender, PaintEventArgs e)
> {
>     SolidBrush tempBrush = new SolidBrush(Color.Blue);
>     e.Graphics.FillEllipse(tempBrush, 10, 10, 100, 100);
>     tempBrush.Dispose();
> }
> ```

> 결과
>
> ![image](images/브러시_1.png)

