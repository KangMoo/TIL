## tmux

tmux는 리눅스에서 하나의 창이 아닌 여러 창을 함께 사용할 때 유용한다. 리눅스 원격 연결이 꺼져도 서버가 꺼지지 않는 이상 tmux로 돌려놓은 코드는 다운되지 않는다.



### tmux 구성요소

- session : tmux가 관리하는 가장 큰 실행 단위. tmux는 세션에 attach/detach 할 수 있다. tmux가 detach한 세션은 종료되지 않고 백그라운드에서 실행을 계속 할 수 있다. 세션은 여러 윈도우로 구성된다.

- window : 사용자가 보는 터미널 화면, 세션에서 여러 개의 윈도우가 탭처럼 존재한다. 세션에서 윈도우를 전환하면 새로운 윈도우로 화면이 전환된다.

  ![tmux_window](./img/tmux_01.png)

- pane : 하나의 윈도우를 분할한 단위. 윈도우 하나를 여러번 분할해서 여러개의 팬을 갖게 할 수 있다. 가로 혹은 세로로 화면을 분할해가면서 팬을 생성한다. 윈도우를 전환하면 팬 구성도 새로운 윈도우의 구성으로 전환된다.

  ![tmux_pane](./img/tmux_02.png)



### 세션 관련 명령어

**세션 생성**
- `tmux`

**이름을 지정하여 새션 셍성**
- `tmux new -s <session_name>`
- `tmux new-session -s <session_name>`

**세션 이름 수정**
- `[Ctrl] + b, $`

**세션 detach**
- `[Ctrl] + b, d`

**세션 리스트 확인**
- tmux attach -t <session number 혹은 session name>`

**세션 종료, 세션의 마지막 윈도우, 마지막 팬에서 실행**
- `exit`

**세션 종료, 세션 밖에서 실행**

- `tmux kill-session -t session_name`

