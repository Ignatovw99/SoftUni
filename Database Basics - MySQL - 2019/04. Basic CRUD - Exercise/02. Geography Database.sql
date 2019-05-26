# Part II – Queries for Geography Database

USE `geography`;

/*21. All Mountain Peaks
Display all mountain peaks in alphabetical order.*/

SELECT `peak_name`
FROM `peaks`
ORDER BY `peak_name`;

/*22. Biggest Countries by Population
Find the 30 biggest countries by population from Europe.
Display the country name and population.
Sort the results by population (from biggest to smallest),
then by country alphabetically.*/

SELECT `country_name`, `population`
FROM `countries`
WHERE `continent_code` = 'EU'
ORDER BY `population` DESC, `country_name`
LIMIT 30;

/*23. Countries and Currency (Euro / Not Euro)
Find all countries along with information about their currency.
Display the country name, country code and information about its currency: 
either "Euro" or "Not Euro". Sort the results by country name alphabetically.*/

SELECT 
	`country_name`, 
    `country_code`,
    IF(`currency_code` = 'EUR', 'Euro', 'Not Euro') AS `currency`
FROM `countries`
ORDER BY `country_name`;