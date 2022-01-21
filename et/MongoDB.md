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

## 3. 논리 쿼리

### 3.1 $or

여러 조건 중 최소 한개는 만족하는 항목

```js
{ $or: [{ condition1 }, { condition2 }, ...] }
```

### 3.2 $and

여러 조건들 전부를 만족하는 항목, 보통은 필요가 없지만, or 절로 다중 조건을 검색하는 경우에 필요

```js
{ $and: [{ condition1 }, { condition1 }, ...] }
{ $and:
	[
		{ $or: [{ condition1 }, { condition2 }, ...] },
		{ $or: [{ condition3 }, { condition4 }, ...] }
	]
}
```

### 3.3 $not

단일 조건을 만족하지 않는 항목을 찾는다.

```js
{ $not: { condition1 }}
```

### 3.4 $nor

명시된 모든 조건을 모두 만족하지 않는 항목을 찾는다.

```js
{ $nor: [{ condition1 }, { condition2 }, ...] },
```

## 4. 요소 쿼리

### 4.1 $exists

해당 필드가 존재하는지 여부 검사

```js
// 해당 필드가 존재하는 항목 검색
{field : {$exists: true}}
// 해당 필드가 존재하지 않는 항목 검사 
{field : {$exists: false}}
```

### 4.2 $type

해당 필드의 자료형이 검색을 원하는 자료형과 일치하는지 확인

- 선택 가능한 자료형 : double, string, object, array, binData, objectId, bool, date, null, regex, dbPointer, javascript, symbol, javascriptWithScope, int, timestamp, long, minKey, maxKey

```js
{field : {$type: data_type}}
```

## 5. 배열 쿼리

### 5.1 $all

필드의 값을 모두 포함하는 배열을 찾는다.

해당 항목의 모든 값이 일치해야 하는게 아니라, 검색한 값이 해당 배열의 일부여도 찾아진다.

```jsx
{ <field>: { $all: [ <value1> , <value2> ... ] } }
```

```jsx
db.testDb.insert({beta : ["a","b","c"]})
db.testDb.find({beta : {$all : ["a","b"]}})

// 출력
{	"_id" : ObjectId("61eab5e7c7a7e3975a64e543"), "beta" : [ "a", "b", "c" ] }
```

```jsx
{ tags: { $all: [ "ssl" , "security" ] } }
```

아래와 같다.

```jsx
{ $and: [ { tags: "ssl" }, { tags: "security" } ] }
```

### 5.2 $elemMatch

`$elemMatch` 연산자는 지정된 모든 질의 조건과 일치하는 하나 이상의 요소와 배열 필드가 들어 있는 문서를 일치시킵니다.

```jsx
{ <field>: { $elemMatch: { <query1>, <query2>, ... } } }
```

`$all` 과는 다른 점은 query를 사용한다.

```jsx
db.scores.find(
   { results: { $elemMatch: { $gte: 80, $lt: 85 } } }
)
```

찾는 수의 범위를 지정하거나

```jsx
db.testDb.insert({
    beta : [ 
        {"gamma" : "x"},
        {"gamma" : "y"},
        {"gamma" : "z"}
    ]
})

db.testDb.find({beta : {$elemMatch : {"gamma" : "x"}}})
```

이런 식으로 해당 값을 하나라도 포함하는 배열을 찾는 식으로 사용이 가능하다.

### 5.3 $size