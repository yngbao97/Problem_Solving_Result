SELECT a.CAR_ID, CASE
                WHEN a.CAR_ID IN (SELECT CAR_ID
                                FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
                                WHERE START_DATE <= '2022-10-16' 
                                AND END_DATE >= '2022-10-16') THEN '대여중'
                ELSE '대여 가능'
                END AS AVAILABILITY
FROM (SELECT DISTINCT CAR_ID
     FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY) AS a
ORDER BY CAR_ID DESC
