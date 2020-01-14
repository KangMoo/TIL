## Express Generator

- 기본적이 구성에 해당하는 틀 자동 생성
- 설치

1. 비어있는 디렉토리에서 에디터 실행

2. npm install     express-generator -g

3. - express-generator를 글로벌하게 설치

- 사용법

- - 'express -h'명령어로 사용법 확인
  - express로 새로운 디렉토리 및 기본파일 생성 -> 생성된 디렉토리로 이동 후 npm      install명령어를 통해 필요한 파일 설치

​         express MyApp    cd MyApp    npm install       

 

## Express 실행법

- npm start



## 404 not found

- 둘 중 하나 사용하면 됨

  >* app.get('*',(req,res) => res.send(404));
  >
  >* app.use((req,res) => res.send(404));



## Express의 핵심기능 두가지

- Route

- - 라우팅은 애플리케이션 엔드 포인트(URI)의 정의, 그리고 URI가 클라이언트 요청에 응답하는 방식을 말합니다

- 미들웨어

- - 구조 내에서 중간 처리를 위한 함수(함수들의      꾸러미가 모듈)

  - > 1) express 프레임워크에서 사용할 수 있는 중간 처리 목적의 소프트웨어 : 기본적인 express 구조 내에서 처리 목적으로 사용
    >
    > 2) 요청에 대한 응답을 완수하기 전까지 중간중간 다양한 일을 처리할 수 있음
    >
    > 3) 미들웨어 함수 생명주기 : request - response 응답을 주기로 종료 
    >
    > 4) 미들웨어 함수 우선순위 : 먼저 로드되는 미들웨어 함수가 먼저 실행됨(코드 순서 중요)

- ex) body-parser

  > ```Javscript
  > var body = '';
  > 
  > request.on('data', function(data){
  > 	body = body + data;
  > });
  > 
  > request.on('end', function(){
  > 	var post = qs.parse(body);
  > 	
  > 	......
  > 	
  > 	
  > 	response.writeHead(302, {Location: `/?id=${result.insertId}`});
  > 	response.end();
  > });
  > ```

  > ```Javscript
  > var post = request.body;
  > 
  > .........
  > 
  > response.writeHead(302, {Location: `/?id=${result.insertId}`});
  > response.end();
  > ```

