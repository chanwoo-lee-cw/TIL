-- 코드를 입력하세요
SELECT car_table.car_id,     CASE
        WHEN rentaled_car_table.car_id IS NULL THEN "대여 가능"
        ELSE "대여중"
    END as AVAILABILITY
FROM (
    SELECT car_id, start_date, end_date
    FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
    WHERE START_DATE <= '2022-10-16' AND '2022-10-16' <= END_DATE
) AS rentaled_car_table RIGHT JOIN (
    SELECT DISTINCT car_id
    FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
) AS car_table
ON rentaled_car_table.car_id = car_table.car_id
ORDER BY car_id DESC