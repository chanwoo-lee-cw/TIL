# MongoDB
### 1.1 Create

```jsx
db.testDb.save({alpha : "alpha"})
db.testDb.insert({beta : "beta"})

db.testDb.save([
    {alpha : "alpha"}, 
    {beta : "beta"}
])

db.testDb.insert([
    {alpha : "alpha"}, 
    {beta : "beta"}
])
```

save와 insert 둘 다 값을 삽입 가능하지만, 수정 가능 여부가 다르다.

- insert

  - 값을 삽입하는 명령어, 만약 id값을 준다면 삽입은 하지만 수정은 하지 않는다.

  ```jsx
  // 삽입 성공
  db.testDb.insert({"_id":ObjectId("333333333333"), alpha : "alpha" })
  
  // 에러 발생
  db.testDb.insert({"_id":ObjectId("333333333333"), alpha : "beta" })
  ```

- save

  - 값을 저장하는 명령어 id 값이 일치하는 것이 있다면 해당 칼럼을 수정한다.

  ```jsx
  // 삽입 성공
  db.testDb.save({"_id":ObjectId("555555555555"), alpha : "alpha" })
  
  // 수정 성공
  db.testDb.save({"_id":ObjectId("555555555555"), alpha : "beta" })
  ```

### 1.2 Read

- find

  - 해당하는 쿼리를 찾는 명령어

  ```jsx
  db.testDb.find({alpha : "beta"})
  // alpha가 "beta"인 모든 것을 찾는다.
  ```

- findOne

  - 여러개의 칼럼 중에 첫번째 칼럼을 찾는 명령어

  ```jsx
  db.testDb.findOne({alpha : "beta"})
  // alpha가 "beta"인 칼럼중에 첫번째 칼럼을 찾는다.
  ```

- projection

  - 각 칼럼중에 원하는 속성만 가져오는 명령어
  - 원하는 속성을 true로 정하면 그 속성만 가져온다.
  - 원하는 속성을 false로 하면 그 속성만 제외하고 나머지 속성을 전부 가져온다.
    - true, false 를 동시에 사용하는건 불가능 하지만, _id의 경우에는 false를 지정해서 동시에 사용할 수 있다.

  ```jsx
  db.testDb.find({shopName:"daum"})
     .projection({shopId: true})
  ```

- sort

  - 출력된 칼럼을 정렬한다.
    - -1 : 내림차 순으로 정렬한다.
    - 1 : 오름차 순으로 정렬한다.

  ```jsx
  // id를 기준으로 내림차 순으로 정렬한다.
  db.testDb.find({})
     .projection({})
     .sort({_id:-1})
  // id를 기준으로 오름차 순으로 정렬한다.
  db.testDb.find({})
     .projection({})
     .sort({_id:1})
  ```

- limit

  - 한번에 출력한 칼럼의 수를 지정한다.

  ```jsx
  // 해당 collection의 모든 항목을 id를 내림차 순으로 위에서 5개만 출력한다.
  db.testDb.find({})
     .projection({})
     .sort({_id:-1})
     .limit(5)
  ```

### 1.3 Update

- updateOne

  - 찾아진 칼럼중 하나를 수정한다.

  ```jsx
  // alpha값이 alpha인 항목을 하나 찾아서 beta로 수정한다.
  db.testDb.updateOne(
      {alpha:"alpha"}, 
      {$set: {alpha:"beta"}
  })
  ```

- updateMany

  - 찾아진 칼럼 전부를 수정한다.

  ```jsx
  db.testDb.updateMany(
      {alpha:"beta"}, 
      {$set: {alpha:"alpha"}
  })
  ```

### 1.4 Delete

- remove

  ```jsx
  // 해당 콜력센 안에 있는 모든 내용 삭제
  db.testDb.remove({ })
  
  // alpha의 속성 값이 alpha인 항목을 삭제한다.
  db.testDb.remove({alpha:"alpha"})
  ```

- deleteOne

  - 찾아진 항목 하나를 삭제한다.

  ```jsx
  db.testDb.deleteOne({alpha:"alpha"})
  ```

- deleteMany

  - 찾아진 항목 전부를 삭제한다.

  ```jsx
  db.testDb.deleteMany({alpha:"alpha"})
  ```

## **2. find와 aggregate 차이**

- `find` : 단일 쿼리를 사용한 검색을 위한 역할
  
    ```jsx
    db.test.find({username : "Tom"})
    ```
    
    db.test.find({username : "Tom"})
    
- `aggregate` : 다중 쿼리를 사용한 파이프라인을 작성해주는 역할
  
    ```jsx
    db.test.aggregate([
        {$match : {username:{$eq:"Tom"}}},
        {$match : {userstatus:{$eq:"happy"}}}
    ])
    ```
    
    - aggregate는 이런 방식으로 파이프라인을 작성할 수 있게 된다.
        - 위의 쿼리로 유저이름이 Tom인 사람을 사람을 찾고, 하단의 쿼리로 상태가 happy인 사람을 찾게 된다.

## **3. 비교 쿼리**

### **3.1 $eq**

해당 필드 값과 일치하는 값을 가진 필드를 찾는다.

```jsx
{field : {$eq: value}}

// 이것과 기능적으로는 동일함.
{field : value}
```

### **3.2 $ne**

해당 필드 값과 일치하지 않는 값을 가진 필드를 찾는다.

```jsx
{field : {$ne: value}}
```

### **3.3 $gt**

해당 필드 값보다 더 큰 값을 가진 필드를 찾는다.

```jsx
{field : {$gt: value}}
```

### **3.4 $lt**

해당 필드 값보다 더 작은 값을 가진 필드를 찾는다.

```jsx
{field : {$lt: value}}
```

### **3.5 $gte**

해당 값보다 크거나 같은 값을 가진 필드를 찾는다.

```jsx
{field : {$gte: value}}
```

### **3.6 $lte**

해당 값보다 작거나 같은 값을 가진 필드를 찾는다.

```jsx
{field : {$lte: value}}
```

### **3.7 $in**

필드의 값이 $in 안에 들어있는 값들 중 하나인 필드를 찾는다.

```jsx
{field : {$in: [value1, value2, ...]}}
```

필드의 값이 이 value list 중 단 하나라도 일치하는게 있으면 그 항목을 반환한다.

### **3.8 $nin**

필드의 값이 $nin 안에 들어있는 값들 중 일치하는것 들을 제외한 항목을 반환한다.

```jsx
{field : {$in: [value1, value2, ...]}}
```

## **4. 논리 쿼리**

### **4.1 $or**

여러 조건 중 최소 한개는 만족하는 항목

```jsx
{ $or: [{ condition1 }, { condition2 }, ...] }
```

### **4.2 $and**

여러 조건들 전부를 만족하는 항목, 보통은 필요가 없지만, or 절로 다중 조건을 검색하는 경우에 필요

```jsx
{ $and: [{ condition1 }, { condition1 }, ...] }
```

```jsx
{ $and:
  [
    { $or: [{ condition1 }, { condition2 }, ...] },
    { $or: [{ condition3 }, { condition4 }, ...] }
  ]
}
```

### **4.3 $not**

단일 조건을 만족하지 않는 항목을 찾는다.

```jsx
{ $not: { condition1 }}
```

### **4.4 $nor**

명시된 모든 조건을 모두 만족하지 않는 항목을 찾는다.

```jsx
{ $nor: [{ condition1 }, { condition2 }, ...] },
```

## **5. 요소 쿼리**

### **5.1 $exists**

해당 필드가 존재하는지 여부 검사

```jsx
// 해당 필드가 존재하는 항목 검색
{field : {$exists: true}}
// 해당 필드가 존재하지 않는 항목 검사
{field : {$exists: false}}
```

### **5.2 $type**

해당 필드의 자료형이 검색을 원하는 자료형과 일치하는지 확인

- 선택 가능한 자료형 : double, string, object, array, binData, objectId, bool, date, null, regex, dbPointer, javascript, symbol, javascriptWithScope, int, timestamp, long, minKey, maxKey

```jsx
{field : {$type: data_type}}
```

## **6. 배열 쿼리**

### **6.1 $all**

필드의 값을 모두 포함하는 배열을 찾는다.

해당 항목의 모든 값이 일치해야 하는게 아니라, 검색한 값이 해당 배열의 일부여도 찾아진다.

```jsx
{ <field>: { $all: [ <value1> , <value2> ... ] } }
```

```jsx
db.testDb.insert({beta : ["a","b","c"]})
db.testDb.find({beta : {$all : ["a","b"]}})

// 출력
{ "_id" : ObjectId("61eab5e7c7a7e3975a64e543"), "beta" : [ "a", "b", "c" ] }
```

```jsx
{ tags: { $all: [ "ssl" , "security" ] } }
```

아래와 같다.

```jsx
{ $and: [ { tags: "ssl" }, { tags: "security" } ] }
```

### **6.2 $elemMatch**

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
db.collection.insert({
    beta : [
        {"gamma" : "x"},
        {"gamma" : "y"},
        {"gamma" : "z"}
    ]
})

db.testDb.find({beta : {$elemMatch : {"gamma" : "x"}}})
```

이런 식으로 해당 값을 하나라도 포함하는 배열을 찾는 식으로 사용이 가능하다.

### **6.3 $size**

$size 연산자는 배열과 인수에 의해 지정된 요소 수를 일치시킵니다.

```jsx
db.collection.find( { field: { $size: 2 } } );
```

단, 이건 정확한 배열의 길이를 알고 있을 때의 조건이다.

배열의 길이의 범위를 정해서 찾고 싶을 때는

```jsx
db.collection.find({ $where:field.length > 2 })
```

이런 방식으로 찾아야 한다.

# 7. 평가 쿼리

## 7.1 **$regex**

정규 표현식을 사용해서, pattern과 일치하는 데이터를 찾을 수 있다.

```python
{ <field>: { $regex: /pattern/, $options: '<options>' } }
{ <field>: { $regex: 'pattern', $options: '<options>' } }
{ <field>: { $regex: /pattern/<options> } }
```

- pattern
    - `^` : 시작하는 문자
    - `$` : 끝나는 문자
    - `.` : 개행식을 제외한 모든 문자
- option

| Option | Description | Syntax Restrictions |
| --- | --- | --- |
| i | 대소문자를 구별하지 않는다. |  |
| m | ^ 이나 $ 을 사용할 때, 다중 행을 가진 칼럼인 경우, 각 행의 처음과 끝에 매칭 시킨다. |  |
| x | 공백 문자와 주석(#)을 무시한다. | $options 구문이 있는 $regex 필요 |
| s | . 을 사용하는 경우, \n를 포함하여 매치 | $options 구문이 있는 $regex 필요 |
- example

```jsx
//데이터 삽입
db.testDb.insert([
    {"text" : "abc123"},
    {"text" : "해피"},
    {"text" : "abc123\n해피"},
    {"text" : "해피 뉴이어"},
  ])
```

```jsx
db.testDb.find({"text" : {$regex : /해피/}})
// 출력
{
	"_id" : ObjectId("61fb8c43c7a7e3975a651260"),
	"text" : "해피"
},
{
	"_id" : ObjectId("61fb8c43c7a7e3975a651261"),
	"text" : "abc123\n해피"
},
{
	"_id" : ObjectId("61fb8c43c7a7e3975a651262"),
	"text" : "해피 뉴이어"
}
```

```jsx
db.testDb.find({"text" : {$regex : /^해피/}})
// 출력
{
	"_id" : ObjectId("61fb8c43c7a7e3975a651260"),
	"text" : "해피"
},
{
	"_id" : ObjectId("61fb8c43c7a7e3975a651262"),
	"text" : "해피 뉴이어"
}

db.testDb.find({"text" : {$regex : /^해피/m}})
// 출력
{
	"_id" : ObjectId("61fb8c43c7a7e3975a651260"),
	"text" : "해피"
},
{
	"_id" : ObjectId("61fb8c43c7a7e3975a651261"),
	"text" : "abc123\n해피"
},
{
	"_id" : ObjectId("61fb8c43c7a7e3975a651262"),
	"text" : "해피 뉴이어"
}

db.testDb.find({"text" : {$regex : /ab.+?3/s}})
// 출력
{
	"_id" : ObjectId("61fb8c43c7a7e3975a65125f"),
	"text" : "abc123"
},
{
	"_id" : ObjectId("61fb8c43c7a7e3975a651261"),
	"text" : "abc123\n해피"
}
```

## 7.2 $where

Javascript의 표현식 또는 함수 전체를 쿼리에 전달할 수 있게 한다.

단, 각 항목당 Javascript의 쿼리문을 처리하기 때문에 속도가 굉장히 느려진다.

```jsx
db.testDb.insert([
    {"alpha" : "A", "beta": "B"},
    {"alpha" : "A", "beta": "A"},
    {"alpha" : "B", "beta": "B"},
    ])
 
db.testDb.find({
        $where : function() { return this.alpha == this.beta}
    })
db.testDb.find({
        $where : "this.alpha == this.beta"
    })

// 결과
{
	"_id" : ObjectId("61fc7c14c7a7e3975a651266"),
	"alpha" : "A",
	"beta" : "A"
},
{
	"_id" : ObjectId("61fc7c14c7a7e3975a651267"),
	"alpha" : "B",
	"beta" : "B"
}

db.testDb.find( {$expr: { $function: {
      body: function(alpha) { return alpha == "A" },
      args: [ "$alpha" ],
      lang: "js"
} } } )

// 결과
{
	"_id" : ObjectId("61fc7c14c7a7e3975a651265"),
	"alpha" : "A",
	"beta" : "B"
},
{
	"_id" : ObjectId("61fc7c14c7a7e3975a651266"),
	"alpha" : "A",
	"beta" : "A"
}
```

# 8. Projection Operators

## 8.1 $

위치 $ 연산자는 배열의 쿼리 조건과 일치하는 첫 번째 요소를 반환하도록 array의 내용을 제한한다.

만약 여러 개의 항목을 찾고 싶으면 `$filter` 를 사용하면 된다.

```jsx
// 배열의 지정된 쿼리 조건과 일치하는 첫 번째 배열 요소를 반환한다.
db.collection.find( { <array>: <condition> ... },
                    { "<array>.$": 1 } )
db.collection.find( { <array.field>: <condition> ...},
                    { "<array>.$": 1 } )
```

## 8.2 $elemMatah

$elemMatch 연산자는 쿼리 결과의 <array> 필드 내용을 $elemMatch 조건과 일치하는 첫 번째 요소만 포함하도록 제한한다.

```jsx
// 입력
db.players.insertOne( {
   name: "player1",
   games: [ { game: "abc", score: 8 }, { game: "xyz", score: 5 } ],
   joined: new Date("2020-01-01"),
   lastLogin: new Date("2020-05-01")
} )

// 검색 쿼리
db.players.find( {}, { games: { $elemMatch: { score: { $gt: 5 } } }, joined: 1, lastLogin: 1 } )
// players collection 안의 모든 항목에선 점수가 5점 이상인 배열과 lastLogin,joined 와 함께 뽑는다.

// 출력
{
  "_id" : ObjectId("5edef91e76ddff7d92f118e1"),
  "games" : [ { "game" : "abc", "score" : 8 } ],
  "joined" : ISODate("2020-01-01T00:00:00Z"),
  "lastLogin" : ISODate("2020-05-01T00:00:00Z")
}
```

## 8.3 $slice

$slice 투영 연산자는 배열에서 조회 결과에 반환할 요소 수를 지정합니다.

```jsx
db.collection.find(
   <query>,
   { <arrayField>: { $slice: [ <number>, <number> ] } }
);
```

```jsx

{ item: "socks", qty: 100, details: { colors: [ "blue", "red" ], sizes: [ "S", "M", "L"] } }
```

```jsx
db.inventory.find( { }, { qty: 1, "details.colors": { $slice: 1 } } )
// 출력
{ "_id" : ObjectId("5ee92a6ec644acb6d13eedb1"), "qty" : 100, "details" : { "colors" : [ "blue" ] } }
```

# 9. Update Operator

## 9.1 $currentDate

$currentDate 연산자는 필드 값을 현재 날짜(날짜 또는 타임스탬프)로 설정합니다. 기본 유형은 날짜입니다.

```jsx
{ $currentDate: { <field1>: <typeSpecification1>, ... } }
```

예제

```jsx
// 데이터 생산
db.customers.insertOne(
   { _id: 1, status: "a", lastModified: ISODate("2013-10-02T01:11:18.965Z") }
)
// 데이터 업데이트
db.customers.updateOne(
   { _id: 1 },
   {
     $currentDate: {
        lastModified: true,
        "cancellation.date": { $type: "timestamp" }
     },
     $set: {
        "cancellation.reason": "user request",
        status: "D"
     }
   }
)
```

```jsx
// 결과
{
   "_id" : 1,
   "status" : "D",
   "lastModified" : ISODate("2020-01-22T21:21:41.052Z"),
   "cancellation" : {
      "date" : Timestamp(1579728101, 1),
      "reason" : "user request"
   }
}
```