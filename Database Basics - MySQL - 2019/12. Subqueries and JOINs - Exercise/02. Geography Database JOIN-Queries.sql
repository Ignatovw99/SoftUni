/*12. Highest Peaks in Bulgaria
Write a query that selects:
- country_code, mountain_range, peak_name, elevation
Filter all peaks in Bulgaria with elevation over 2835. Return all rows sorted by elevation in descending order.*/

SELECT 
	`mc`.`country_code`,
    `m`.`mountain_range`,
    `p`.`peak_name`,
    `p`.`elevation`
FROM `mountains_countries` AS `mc`
JOIN `mountains` AS `m`
	ON `mc`.`mountain_id` = `m`.`id`
JOIN `peaks` AS `p`
	ON `m`.`id` = `p`.`mountain_id`
WHERE `mc`.`country_code` = 'BG' AND `p`.`elevation` > 2835
ORDER BY `p`.`elevation` DESC;

/*13. Count Mountain Ranges
Write a query that selects:
- country_code, mountain_range
Filter the count of the mountain ranges in the United States, Russia and Bulgaria. Sort result by mountain_range
count in decreasing order.*/

SELECT 
	`mc`.`country_code`,
    COUNT(`m`.`id`) AS `mountain_range`
FROM `mountains_countries` AS `mc`
JOIN `mountains` AS `m`
ON `mc`.`mountain_id` = `m`.`id`
WHERE `mc`.`country_code` IN ('US', 'RU', 'BG')
GROUP BY `mc`.`country_code`
ORDER BY `mountain_range` DESC;

/*14. Countries with Rivers
Write a query that selects:
- country_name, river_name
Find the first 5 countries with or without rivers in Africa. Sort them by country_name in ascending order.*/

SELECT 
	`c`.`country_name`,
    `r`.`river_name`
FROM `continents`
INNER JOIN `countries` AS `c`
	ON `continents`.`continent_code` = `c`.`continent_code`
LEFT JOIN `countries_rivers` AS `cr`
	ON `c`.`country_code` = `cr`.`country_code`
LEFT JOIN `rivers` AS `r`
	ON `cr`.`river_id` = `r`.`id`
WHERE `continents`.`continent_name` = 'Africa'
ORDER BY `c`.`country_name`
LIMIT 5;

/*15. *Continents and Currencies
Write a query that selects:
- continent_code, currency_code, currency_usage
Find all continents and their most used currency. Filter any currency that is used in only one country. Sort the result
by continent_code and currency_code.*/
//TODO + University Database + Salary Challenge
SELECT `1`.`continent_code`, `1`.`currency_code`, `1`.`count2`
FROM
(
	SELECT `c2`.`continent_code`, `c2`.`currency_code`, COUNT(`c2`.`currency_code`) AS `count2`
	FROM `countries` AS `c2`
	GROUP BY `c2`.`continent_code`, `c2`.`currency_code`
	HAVING `count2` > 1
) as `1`
INNER JOIN
(
	SELECT `table`.`continent_code`, `table`.`currency_code` , MAX(`table`.`count`) AS `coun`
	FROM
	(
		SELECT `c`.`continent_code`, `c`.`currency_code`, COUNT(`c`.`currency_code`) AS `count`
		FROM `countries` AS `c`
		GROUP BY `c`.`continent_code`, `c`.`currency_code`
		HAVING `count` > 1
	) AS `table`
	GROUP BY `table`.`continent_code`
) as `2`
ON `1`.`count2` = `2`.`coun` AND `1`.`continent_code` = `2`.`continent_code`
ORDER BY `1`.`continent_code`, `1`.`currency_code`;


/*16. Countries without any Mountains
Find the count of all countries which don’t have a mountain.*/

SELECT COUNT(*)
FROM `countries` AS `c`
LEFT JOIN `mountains_countries` AS `mc`
ON `c`.`country_code` = `mc`.`country_code`
LEFT JOIN `mountains` AS `m`
ON `mc`.`mountain_id` = `m`.`id`
WHERE `m`.`id` IS NULL;

/*17. Highest Peak and Longest River by Country
For each country, find the elevation of the highest peak and the length of the longest river, sorted by the highest
peak_elevation (from highest to lowest), then by the longest river_length (from longest to smallest), then
by country_name (alphabetically). Display NULL when no data is available in some of the columns. Limit only the
first 5 rows.*/

SELECT 
	`c`.`country_name`,
    MAX(`p`.`elevation`) AS `highest_peak_elevation`,
    MAX(`r`.`length`) AS `longest_river_length`
FROM `countries` AS `c`
LEFT JOIN `mountains_countries` AS `mc`
	ON `c`.`country_code` = `mc`.`country_code`
LEFT JOIN `peaks` AS `p`
	ON `mc`.`mountain_id` = `p`.`mountain_id`
LEFT JOIN `countries_rivers` AS `cr`
	ON `c`.`country_code` = `cr`.`country_code`
LEFT JOIN `rivers` AS `r`
	ON `cr`.`river_id` = `r`.`id`
GROUP BY `c`.`country_name`
ORDER BY `highest_peak_elevation` DESC, `longest_river_length` DESC
LIMIT 5;




