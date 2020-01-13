## 사용법 예시

> ```Javscript
> const express = require('express');
> const app = express();
> 
> app.get('/',(req,res) => res.send("Hello World"));
> 
> app.use((req,res) => res.send(404));
> 
> 
> app.listen(3000, () => console.log('Example app listening on port 3000!'));
> ```



## QueryString말고 URL을 통해 파라미터 전달하는 방법

> ```Javscript
> app.get('/page/:pageID',(req,res) => res.send(req.params));
> ```

- http://URL/page/aaaa

- - 이렇게 접속하면 pageID인자값으로 'aaaa'가 전달됨.
  - 결과 =>  `{"pageID":"aaaa"}`

## 효과

> Express 사용 안했을 때
>
> ```Javscript
> response.writeHead(200);
> response.end(html);
> ```
>
> ```javascript
> var app = http.createServer(function (request, response) {
>     var _url = request.url;
>     var queryData = url.parse(_url, true).query;
>     var pathname = url.parse(_url, true).pathname;
>     if (pathname === '/') {
>         if (queryData.id === undefined) {
>             response.writeHead(200);
>             response.end( ... );
>         } else {
>         	//...
>         }
>     } else if (pathname === '/create') {
>         // ...
>     } else {
>         response.writeHead(404);
>         response.end('Not found');
>     }
> });
> app.listen(3000);
> ```

> Express 사용 했을 때
>
> ```Javscript
> response.send(html);
> ```
>
> ```javascript
> const express = require('express')
> const app = express()
> 
> app.get('/', (req, res) => res.send('Hello World!'))
> 
> app.listen(3000);
> ```





## 보안지침

- 최신버전의 Nodejs를 유지하라

- TLS, SSL, HTTPS를 사용하라

- Helmet을 사용하라

  > ```Javscript
  > var helmet = require('helmet');
  > app.use(helmet());
  > ```

- 쿠키를 안전하게 사용하라

- 디펜던시를 안전하게 관리하라

  > ```Javscript
  > 콘솔
  > npm i nsp -g
  > nsp check
  > ```

