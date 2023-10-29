-- https://school.programmers.co.kr/learn/courses/30/lessons/131116
-- 서브 쿼리를 사용하지 않고 조회할 경우 상품명이 최대 가격인 상품이 나오지 않기 때문에 서브 쿼리로 조회 필수
SELECT CATEGORY, PRICE AS "MAX_PRICE", PRODUCT_NAME
FROM FOOD_PRODUCT
WHERE (CATEGORY, PRICE) IN (
    SELECT CATEGORY, MAX(PRICE) AS "MAX_PRICE"
    FROM FOOD_PRODUCT
    GROUP BY CATEGORY
    HAVING CATEGORY IN ('과자', '국', '김치', '식용유') 
)
ORDER BY MAX_PRICE DESC