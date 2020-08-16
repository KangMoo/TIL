## async/await

- ES8에 해당하는 문법으로, Promise를 더욱 쉽게 사용할 수 있게 해준다

```javascript
function sleep(ms) {
  return new Promise(resolve => setTimeout(resolve, ms));
}

async function process() {
  console.log('안녕하세요!');
  await sleep(1000); // 1초쉬고
  console.log('반갑습니다!');
}

process();

// 안녕하세요
// [1초 쉬고]
// 반갑습니다
```

```javascript
function sleep(ms) {
  return new Promise(resolve => setTimeout(resolve, ms));
}

async function process() {
  console.log('안녕하세요!');
  await sleep(1000); // 1초쉬고
  console.log('반갑습니다!');
}

process().then(() => {
  console.log('작업이 끝났어요!');
});

// 안녕하세요
// [1초 쉬고]
// 반갑습니다
// 작업이 끝났어요!
```



#### async함수에서 에러를 발생시킬 때는 throw를 사용하고, 에러를 잡아낼 때는 try/catch문을 사용한다.

```javascript
function sleep(ms) {
  return new Promise(resolve => setTimeout(resolve, ms));
}

async function makeError() {
  await sleep(1000);
  const error = new Error();
  throw error;
}

async function process() {
  try {
    await makeError();
  } catch (e) {
    console.error(e);
  }
}

process();
// Error
```



## Promise.all

- 여러개의 Promise를 등록해서 실행했을 때 모두 끝난 뒤의 결과를 가져온다

```javascript
function sleep(ms) {
  return new Promise(resolve => setTimeout(resolve, ms));
}

const getDog = async () => {
  await sleep(1000);
  return '멍멍이';
};

const getRabbit = async () => {
  await sleep(500);
  return '토끼';
};
const getTurtle = async () => {
  await sleep(3000);
  return '거북이';
};

async function process() {
  const results = await Promise.all([getDog(), getRabbit(), getTurtle()]);
  console.log(results);
}

process();
// ["멍멍이", "토끼", "거북이"]
```

- Promise.all을 사용할 때는, 등록한 Promise중 하나라도 실패하면 모든게 실패한 것으로 간주한다.



## Promise.rase

- Promise.all과 달리, 여러개의 Promise를 등록해서 실행했을 때 가장 빨리 끝난 것 하나만의 결과값을 가져온다.