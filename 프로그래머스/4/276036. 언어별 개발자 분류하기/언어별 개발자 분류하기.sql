WITH TMP AS (
        SELECT CASE
                    WHEN ID IN (
                        SELECT ID
                        FROM DEVELOPERS AS d, SKILLCODES AS s
                        WHERE d.SKILL_CODE & s.CODE
                        AND d.SKILL_CODE & (SELECT CODE FROM SKILLCODES WHERE NAME = 'Python')
                        AND s.CATEGORY = 'Front End'
                    ) THEN 'A'
                    WHEN ID IN (
                        SELECT ID
                        FROM DEVELOPERS AS d, SKILLCODES AS s
                        WHERE d.SKILL_CODE & s.CODE
                        AND d.SKILL_CODE & (SELECT CODE FROM SKILLCODES WHERE NAME = 'C#')
                    ) THEN 'B'
                    WHEN ID IN (
                        SELECT ID
                        FROM DEVELOPERS AS d, SKILLCODES AS s
                        WHERE d.SKILL_CODE & s.CODE
                        AND s.CATEGORY = 'Front End'
                    ) THEN 'C'
                END AS GRADE,
                ID,
                EMAIL
        FROM DEVELOPERS
)

SELECT GRADE, ID, EMAIL
FROM TMP
WHERE GRADE IS NOT NULL
ORDER BY GRADE, ID