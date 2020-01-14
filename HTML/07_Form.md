# Form



- 사용자가 서버로 정보를 전송할 때 사용

- 사용자가 웹사이트에 데이터를 전송하는 것을 허용하는 태그

- 일반적으로 데이터는 웹 서버로 전송되지만 웹페이지가 데이터를 사용하기 위해 사용할 수도 있음

- - method =      "get"

  - - 서버로부터 사용자가 데이터를 가져올 때 사용
    - url로 데이터 전송

  - method =      "post"

  - - 서버의 데이터를 수정, 삭제, 생성할 때 사용

- 예시

  > ```HTML
  > <form action = "https://localhost:3000/process_create" method = "post">
  >     <p><input type="text" name = "title"></p>
  >     <p>
  >         <textarea name = "description"></textarea>
  >     </p>
  >     <p>
  >         <input type="submit">
  >     </p>
  > </form>
  > ```

  