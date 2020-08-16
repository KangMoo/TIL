## spread

### spread 사용 전

```javascript
const slime = {
  name: '슬라임'
};

const cuteSlime = {
  name: '슬라임',
  attribute: 'cute'
};

const purpleCuteSlime = {
  name: '슬라임',
  attribute: 'cute',
  color: 'purple'
};

console.log(slime);
console.log(cuteSlime);
console.log(purpleCuteSlime);
```

### spread 사용 후

```javascript
const slime = {
  name: '슬라임'
};

const cuteSlime = {
  ...slime,
  attribute: 'cute'
};

const purpleCuteSlime = {
  ...cuteSlime,
  color: 'purple'
};

console.log(slime);
// {name: "슬라임"}
console.log(cuteSlime);
// {name: "슬라임", attribute: "cute"}
console.log(purpleCuteSlime);
// {name: "슬라임", attribute: "cute", color: "purple"}
```

### spread 예시

```javascript
const animals = ['개', '고양이', '참새'];
const anotherAnimals = [...animals, '비둘기'];
console.log(animals); // ["개", "고양이", "참새"]
console.log(anotherAnimals); // "개", "고양이", "참새", "비둘기"]
```

```javascript
const numbers = [1, 2, 3, 4, 5];

const spreadNumbers = [...numbers, 1000, ...numbers];
console.log(spreadNumbers); // [1, 2, 3, 4, 5, 1000, 1, 2, 3, 4, 5]
```



## rest

- rest는 생김새는 spread 랑 비슷한데, 역할이 매우 다르다
- rest는 객체, 배열, 그리고 함수의 파라미터에서 사용이 가능하다
- rest 는 객체와 배열에서 사용 할 때는 이렇게 비구조화 할당 문법과 함께 사용된다
- 

### 객체에서의 rest

```javascript
const purpleCuteSlime = {
  name: '슬라임',
  attribute: 'cute',
  color: 'purple'
};

const { color, ...rest } = purpleCuteSlime;
console.log(color);	// purple
console.log(rest);	// {name: "슬라임", attribute: "cute"}
```

- 추출한 값의 이름이 꼭 rest 일 필요는 없다

```javascript
const purpleCuteSlime = {
  name: '슬라임',
  attribute: 'cute',
  color: 'purple'
};

const { color, ...cuteSlime } = purpleCuteSlime;
console.log(color);	// purple
console.log(cuteSlime);	// {name: "슬라임", attribute: "cute"}

const { attribute, ...slime } = cuteSlime;
console.log(attribute);	// cute
console.log(slime);	// {name: "슬라임"}
```

### 배열에서의 rest

```javascript
const numbers = [0, 1, 2, 3, 4, 5, 6];

const [one, ...rest] = numbers;

console.log(one);	// 0
console.log(rest);	// [1, 2, 3, 4, 5, 6]
```



### 함수 파라미터에서의 rest

```javascript
function sum(...rest) {
  return rest.reduce((acc, current) => acc + current, 0);
}

const result = sum(1, 2, 3, 4, 5, 6);
console.log(result); // 21
```

### 함수 인자와 spread

```javascript
function sum(...rest) {
  return rest.reduce((acc, current) => acc + current, 0);
}

const numbers = [1, 2, 3, 4, 5, 6];
const result = sum(...numbers);
console.log(result);	// 21
```

```javascript
function max(...rest) {
  return rest.reduce((a,b)=>a>b?a:b,0);
}

const result = max(1, 2, 3, 4, 10, 5, 6, 7);
console.log(result);	// 10
```

