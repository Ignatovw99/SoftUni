-- Part II – Queries for Geography Database

/*10. Countries Holding ‘A’ 3 or More Times
Find all countries that hold the letter 'A' in their name at least 3 times (case insensitively),
sorted by ISO code. Display the country name and the ISO code.*/

SELECT `country_name`, `iso_code`
FROM `countries`
WHERE LOWER(`country_name`) REGEXP '^[^a]*a[^a]*a[^a]*a.*$'
ORDER BY `iso_code`;

/*11. Mix of Peak and River Names
Combine all peak names with all river names, so that the last letter of each peak name is the same as the first letter
of its corresponding river name. Display the peak name, the river name, and the obtained mix(converted to lower
case). Sort the results by the obtained mix alphabetically.*/

SELECT
	`peak_name`,
    `river_name`,
    LOWER(CONCAT(`peak_name`, SUBSTRING(`river_name`, 2))) AS 'mix'
FROM 
	`peaks`,
    `rivers`
WHERE LOWER(RIGHT(`peak_name`, 1)) = LOWER(LEFT(`river_name`, 1))
ORDER BY `mix`;