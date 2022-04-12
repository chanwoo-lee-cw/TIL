## 1. Java에서 MongoDb를 통해 주입

### 1.1. find

```java
mongoTemplate.findOne(query, Class.class, "Collection_name");
```

findOne을 사용해 해당 `Collection_name`이름을 가진 콜렉션에서 `query` 를 사용하여 나온 결과를 `Class`에 넣는다.

- 예시
    
    ```java
    Query query = new Query(Criteria
                    .where("shopType").is(shopType)
                    .and("prdInfo.prdNm").is(prdNm)
                    .and("prdInfo.prdNo").is(prdNo)
                    .and("prdInfo.ordNm").is(ordNm));
    
    GoodsflowProductName goodsflowProductName = mongoTemplate.findOne(query, PrdInfo.class, "prd_info");
    ```
    
    ```jsx
    // 쿼리
    db.prd_info.find({
    	"shopType" : shopType, 
    	"prdInfo.prdNm": prdNm, 
    	"prdInfo.prdNm" : prdNo, 
    	"prdInfo.ordNm" : ordNm
    })
    ```
    

### 1.2 aggregation

```jsx
AggregationResults<Class> prdInfo = mongoTemplate.aggregate(aggregation, "Collection_name", Class.class);
```

해당 `Collection_name`이름을 가진 콜렉션에서 `aggregation` 를 사용하여 나온 결과를 `Class`에 넣는다.

- 예시
    
    ```jsx
    Criteria matchCriteria = new Criteria();
    
    matchCriteria.andOperator(
        new Criteria("shopType").is(shopType),
        new Criteria("prdInfo.prdNm").is(prdNm),
        new Criteria("prdInfo.prdNo").is(prdNo),
        new Criteria("prdInfo.ordNm").is(ordNm)
    );
    
    MatchOperation match = Aggregation.match(matchCriteria);
    
    Aggregation aggregation = Aggregation.newAggregation(match);
    
    Sort sort = Sort.by(
            Sort.Order.desc("prdNo"));
    
    aggregation.add(sort(sort));
    
    AggregationResults<PrdInfo> prdInfo = mongoTemplate.aggregate(aggregation, "prd_info", PrdInfo.class);
    ```