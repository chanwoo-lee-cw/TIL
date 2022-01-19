# MongoDB

## 1.  find와 aggregate 차이

- `find` : 단일 쿼리를 사용한 검색을 위한 역할

  ```js
  db.test.find({username : "Tom"})
  ```

- `aggregate` : 다중 쿼리를 사용한 파이프라인을 작성해주는 역할

  ```js
  db.test.aggregate([
      {$match : {username:{$eq:"Tom"}}},
      {$match : {userstatus:{$eq:"happy"}}}
  ])
  ```

  - aggregate는 이런 방식으로 파이프라인을 작성할 수 있게 된다.
    - 위의 쿼리로 유저이름이 Tom인 사람을 사람을 찾고, 하단의 쿼리로 상태가 happy인 사람을 찾게 된다.

## 2. 비교 쿼리

### 2.1 $eq

해당 필드 값과 일치하는 값을 가진 필드를 찾는다.

```js
{field : {$eq: value}}

// 이것과 기능적으로는 동일함.
{field : value}
```

### 2.2 $ne

해당 필드 값과 일치하지 않는 값을 가진 필드를 찾는다.

```js
{field : {$ne: value}}
```

### 2.3 $gt

해당 필드 값보다 더 큰 값을 가진 필드를 찾는다.

```js
{field : {$gt: value}}
```

### 2.4 $lt

해당 필드 값보다 더 작은 값을 가진 필드를 찾는다.

```js
{field : {$lt: value}}
```

### 2.5 $gte

해당 값보다 크거나 같은 값을 가진 필드를 찾는다.

```js
{field : {$gte: value}}
```

### 2.6 $lte

해당 값보다 작거나 같은 값을 가진 필드를 찾는다.

```js
{field : {$lte: value}}
```

### 2.7 $in

필드의 값이 $in 안에 들어있는 값들 중 하나인 필드를 찾는다.

```js
{field : {$in: [value1, value2, ...]}}
```

필드의 값이 이 value list 중 단 하나라도 일치하는게 있으면 그 항목을 반환한다.

### 2.7 $nin

필드의 값이 $nin 안에 들어있는 값들 중 일치하는것 들을 제외한 항목을 반환한다.

```js
{field : {$in: [value1, value2, ...]}}
```