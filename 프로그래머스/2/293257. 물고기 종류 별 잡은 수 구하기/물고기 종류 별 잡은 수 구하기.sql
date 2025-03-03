SELECT COUNT(*) AS FISH_COUNT, ni.FISH_NAME AS FISH_NAME
FROM FISH_INFO AS fi
    JOIN FISH_NAME_INFO AS ni
    ON fi.FISH_TYPE = ni.FISH_TYPE
GROUP BY ni.FISH_NAME
ORDER BY FISH_COUNT DESC