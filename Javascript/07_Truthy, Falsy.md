## Truthy and Falsy

- Truthy : true 같은것
- Falsy : false 같은것

### Falsy 

- Falsy : false 같은것
  -  null
  - undefined
  - 0
  - ''
  - NaN
- **Falsy한 값 앞에 느낌표를 붙여주면  true로 전환된다!**

```javascript
console.log(!undefined);	// true
console.log(!null);				// true
console.log(!0);					// true
console.log(!'');					// true
console.log(!NaN);				// true
```



### Truthy

- Falsy가 아닌 것



### 특정 값이 Truthy한지 아닌지 확인하는 코드

```javascript
const value = { a: 1 };

const truthy = !!value;
```

