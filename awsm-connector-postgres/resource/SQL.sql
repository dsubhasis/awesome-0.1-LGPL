
CREATE LANGUAGE plpython3u

CREATE or REPLACE FUNCTION pymax (count bigint, dist bigint)
  RETURNS DOUBLE PRECISION
AS $$
  x = dist / count
  return x
$$ LANGUAGE plpython3u;

CREATE or REPLACE FUNCTION eachColmn(colmName text)
RETURNS DOUBLE PRECISION
  AS $$
  ratio  = public.pymax(count(colmName), count(DISTINCT colmName))
  return ratio
  $$ LANGUAGE plpython3u;




with col AS (select  column_name as name
from INFORMATION_SCHEMA.COLUMNS where table_name = 'ted_main');

;

SELECT pymax(count(languages), count(DISTINCT languages)) as languages, pymax(count(ratings), count(DISTINCT ratings)) from
                                                                ted_main;


SELECT DISTINCT(languages) from ted_main;
