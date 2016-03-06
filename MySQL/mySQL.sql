1 - mysql --help
2 - mysql -uroot -p21oct1994 -- -u{username} -p{password}
3 - QUIT -- no semicolon needed
4 - SELECT VERSION(), CURRENT_DATE, SELECT NOW(); -- semicolon is used to end command as commands can be multiline
5 - Commands can be upper or lower case
6 - Ctrl-C to cancel command
7 - "->" means waiting for next line enter ";" to end the command.
8 - Similarly '> , ">, `> means that inverted comma for string forexample has started but no ended. --'
9 - SHOW DATABASES; -- shows list of databases
10 - USE myDataBase -- semicolon not needed, Uses specified database
11 - CREATE DATABASE myDataBase; -- creates a new database
12 - SELECT DATABASE(); -- shows currently select database
13 - SHOW TABLES; -- shows list of tables
14 - CREATE TABLE pet (name VARCHAR(20), owner VARCHAR(20), species VARCHAR(20), sex CHAR(1), birth DATE, death DATE); -- creates a table pet with specified columns of specified data type.
15 - DESCRIBE pet; -- shows complete detail of table columns
16 - LOAD DATA LOCAL INFILE '/path/pet.txt' INTO TABLE pet; -- loads data from a file and saves it inside table. File must have data seprated by tabs with each row on a single line and \N can be used to specify null data.
17 - INSERT INTO pet VALUES ('Puffball','Diane','hamster','f','1999-03-30',NULL); -- adds a new row to table
18 - SELECT what_to_select FROM which_table WHERE conditions_to_satisfy;
19 - DELETE FROM pet; -- empties the table
20 - UPDATE pet SET birth = '1989-08-31' WHERE name = 'Bowser'; -- update every row which matches WHERE statement
21 - SELECT * FROM pet WHERE birth >= '1998-1-1'; -- * can be used to select all
22 - SELECT * FROM pet WHERE species = 'dog' AND sex = 'f'; -- AND refers to && condition
23 - SELECT * FROM pet WHERE species = 'snake' OR species = 'bird'; -- Similarly OR refers to ||
24 - SELECT * FROM pet WHERE (species = 'cat' AND sex = 'm') OR (species = 'dog' AND sex = 'f'); -- Use brackets for calarification otherwise AND has preference
25 - SELECT owner FROM pet; -- SELECT can be used to specify column where FROM specifies table
26 - SELECT DISTINCT owner FROM pet; -- DISTINCT removes repeating elements
27 - SELECT name, species, birth FROM pet WHERE species = 'dog' OR species = 'cat'; -- So to summarize SELECT selects the column wheras WHERE selects the rows and you can combine both to get desired combination
28 - SELECT name, birth FROM pet ORDER BY birth; -- ORDER BY specifies sorting order
29 - SELECT name, birth FROM pet ORDER BY birth DESC; -- DESC can be used for descending order
30 - SELECT name, species, birth FROM pet ORDER BY species, birth DESC; -- Here species are sorted in ascending order and then repeation in species is sorted by descending order of birth
31 - SELECT name, birth, CURDATE(), TIMESTAMPDIFF(YEAR,birth,CURDATE()) AS age FROM pet; -- TIMESTAMPDIFF(para1, para2) gives difference of time between its two parameters, CURDATE() returns current time. AS makes a column from results of TIMESTAMPDIFF() and displays it as name specified
32 - SELECT name, birth, death,
    -> TIMESTAMPDIFF(YEAR,birth,death) AS age
    -> FROM pet WHERE death IS NOT NULL ORDER BY age; -- IS NOT acts as ! and dont compare null using <> because NULL is a special value and cannot be compared using these operators
33 - SELECT name, birth FROM pet
    -> WHERE MONTH(birth) = MONTH(DATE_ADD(CURDATE(),INTERVAL 1 MONTH)); -- DATE_ADD(date, amount to add in date) is used to increment in date and MONTH(date) is used to extract month part from a date
34 - SELECT name, birth FROM pet
    -> WHERE MONTH(birth) = MOD(MONTH(CURDATE()), 12) + 1; -- Another way of doing the above thing by converting 12 to 0 using MOD function so when 1 is added it becomes 1 instead of 13
35 - We can use pattern matching in SQL to find desired results. Use "_" to match single chracter and "%" to match an number of characters including zero characters. Patterns are not case senstive by default. Use LIKE or NOT LIKE operators instead of = or <>
36 - SELECT * FROM pet WHERE name LIKE 'b%'; -- selects all names starting from b
37 - SELECT * FROM pet WHERE name LIKE '%fy'; -- selects all names ending with fy
38 - SELECT * FROM pet WHERE name LIKE '%w%'; -- selects all names containing w
39 - SELECT * FROM pet WHERE name LIKE '_____'; -- selects all 5 characters names
40 - You can also use extended regular experssions using REGEXP and NOT REGEXP or RLIKE and NOT RLIKE which are synonyms
 - SELECT * FROM tutorial.billboard_top_100_year_end WHERE "group" ILIKE 'snoop%' -- ILIKE is used to select without case senstive
 - SELECT * FROM tutorial.billboard_top_100_year_end WHERE year_rank IN (1, 2, 3) -- IN is used to define multiple values for exact match
 - SELECT * FROM tutorial.billboard_top_100_year_end WHERE year_rank BETWEEN 5 AND 10 -- BETWEEN is used for defining range of inputs
 - SELECT * FROM pet WHERE name REGEXP '^b'; -- you can read docs on extended regexp, here ^b represent all names starting with b
42 - SELECT COUNT(*) FROM pet; -- COUNT() can be used to calculate number of instances matching the experssion here it will return total table rows
43 - SELECT owner, COUNT(*) FROM pet GROUP BY owner; -- selects owner and count column so you can see how many pets each owner haves
44 - SELECT species, sex, COUNT(*) FROM pet
    -> WHERE species = 'dog' OR species = 'cat'
    -> GROUP BY species, sex; -- you can filter results of COUNT(*) using WHERE statements
45 - If you mention other columns with COUNT() then you must use GROUP BY those columns other wise error occurs.
@Example
SELECT species, sex, COUNT(*) FROM pet
    -> GROUP BY species, sex; -- here GROUP BY species, sex is mandatory
46 - SELECT pet.name, remark
    -> FROM pet INNER JOIN event
    ->   ON pet.name = event.name
    -> WHERE event.type = 'litter'; -- FROM clause combines multiple tables using ON to match certain condition. INNER JOIN allows only rows where condition from ON fullfills
47 - SELECT p1.name, p1.sex, p2.name, p2.sex, p1.species
    -> FROM pet AS p1 INNER JOIN pet AS p2
    ->   ON p1.species = p2.species AND p1.sex = 'f' AND p2.sex = 'm'; -- You can also join same table to compare different records. To use same table use AS.
48 - mysql < batch-file -- Is used for utilizing batch mode. In batch mode commands are entered in a file and then those commands are executed.
49 - mysql -e "source batch-file" -- On windows to avoid having problems with special characters you can use this command.
50 - mysql < batch-file | more -- to avoid priniting a lot of data in short time use this command
51 - mysql < batch-file > mysql.out -- save output to a file.

-- Intermediate tutorials sqlschool: https://sqlschool.modeanalytics.com/intermediate

-- AGGREGATE FUNTIONS

-- COUNT: counts the number of rows
SELECT COUNT(*)
  FROM tutorial.aapl_historical_stock_price
-- COUNT(1) is same as COUNT(*)
-- COUNT(high) selects not null rows
SELECT COUNT(high)
  FROM tutorial.aapl_historical_stock_price
-- COUNT(low) selects null rows
SELECT COUNT(low)
  FROM tutorial.aapl_historical_stock_price
-- To change name of count column use AS
SELECT COUNT(date) AS "Count Of Date"
  FROM tutorial.aapl_historical_stock_price

-- SUM: returns sum of all rows values
SELECT SUM(volume)
  FROM tutorial.aapl_historical_stock_price
-- SUM treats null as zero

-- MIN: returns minimum values, MAX: returns maximum values
SELECT MIN(volume) AS min_volume,
       MAX(volume) AS max_volume
  FROM tutorial.aapl_historical_stock_price

-- AVG: returns average of all rows
-- It ignores null completly instead of considering it 0
SELECT AVG(high)
  FROM tutorial.aapl_historical_stock_price

-- GROUP BY: allows you to separate data into groups which can be aggregated independent of one another
SELECT year,
       COUNT(*) AS count
  FROM tutorial.aapl_historical_stock_price
 GROUP BY year
-- You can also group by multiple columns
SELECT year,
       month,
       COUNT(*) AS count
  FROM tutorial.aapl_historical_stock_price
 GROUP BY year, month
-- You can also substitute column names with no's
SELECT year,
       month,
       COUNT(*) AS count
  FROM tutorial.aapl_historical_stock_price
 GROUP BY 1, 2

 -- HAVING: let’s say that you want to find every month during which AAPL stock worked its way over $400/share. The WHERE clause won’t work for this because it doesn’t allow you to filter on aggregate columns — that’s where the HAVING clause comes in
 SELECT year,
       month,
       MAX(high) AS month_high
  FROM tutorial.aapl_historical_stock_price
 GROUP BY year, month
HAVING MAX(high) > 400
 ORDER BY year, month

-- CASE: The CASE statement is SQL’s way of handling if/then logic. The CASE statement is followed by at least one pair of WHEN and THEN statements — SQL’s equivalent of IF/THEN. It must end with the END statement. The ELSE statement is optional, and provides a way to capture values not specified in the WHEN/THEN statements.

SELECT player_name,
       year,
       CASE WHEN year = 'SR' THEN 'yes'
            ELSE 'nope' END AS is_a_senior
  FROM benn.college_football_players
-- CASE checks for condtion in each row and if it is true it inserts yes in newly create is_a_senior column and if it is false it inserts nope
SELECT player_name,
       weight,
       CASE WHEN weight > 250 THEN 'over 250'
            WHEN weight > 200 THEN '201-250'
            WHEN weight > 175 THEN '176-200'
            ELSE '175 or under' END AS weight_group
  FROM benn.college_football_players
