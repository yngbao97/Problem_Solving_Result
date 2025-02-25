SELECT a.ID, CASE
                 WHEN b.n = 1 THEN 'CRITICAL'
                 WHEN b.n = 2 THEN 'HIGH'
                 WHEN b.n = 3 THEN 'MEDIUM'
                 ELSE 'LOW' 
             END AS COLONY_NAME
FROM ECOLI_DATA AS a, (SELECT ID, NTILE(4) OVER(ORDER BY SIZE_OF_COLONY DESC) AS n
                        FROM ECOLI_DATA) AS b
WHERE a.ID = b.ID
ORDER BY a.ID