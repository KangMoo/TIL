## Promise

- 비동기 작업을 더 편리하게 쳐리할 수 있도록 ES6에 도입된 기능
- 이전에는 콜백함수로 처리했지만, 그럴 경우 코드가 난잡해질 수 있음



### promise 만들기

```javascript
const myPromise = new Promise((resolve, reject) => {
  // 구현..
})
```

- Promise는 성공할 수도 있고, 실패할 수도 있다
- 성공할 경우는 resolve를 호출하며, 실패할 때는 reject를 호출해주면 된다.

#### 1초 뒤 성공하는 상황

```javascript
const myPromise = new Promise((resolve, reject) => {
  setTimeout(() => {
    resolve(1);
  }, 1000);
});

myPromise.then(n => {
  console.log(n);
});
// 1 (1초 뒤 출력)
```



#### 1초 뒤 실패하는 상황

```javascript
const myPromise = new Promise((resolve, reject) => {
  setTimeout(() => {
    reject(new Error());
  }, 1000);
});

myPromise
  .then(n => {
    console.log(n);
  })
  .catch(error => {
    console.log(error);
  });
// Error {}
```



#### 1초마다 1씩 증가한 값을 출력하는 함수 & 5가되면 에러를 출력하는 함수

```javascript
function increaseAndPrint(n) {
  return new Promise((resolve, reject) => {
    setTimeout(() => {
      const value = n + 1;
      if (value === 5) {
        const error = new Error();
        error.name = 'ValueIsFiveError';
        reject(error);
        return;
      }
      console.log(value);
      resolve(value);
    }, 1000);
  });
}

increaseAndPrint(0)
  .then(increaseAndPrint)
  .then(increaseAndPrint)
  .then(increaseAndPrint)
  .then(increaseAndPrint)
  .then(increaseAndPrint)
  .catch(e => {
    console.error(e);
  });

// 1
// 2
// 3
// 4
// ValueIsFiveError
```

