-- Part I – Queries for SoftUni Database

/*1. Employees with Salary Above 35000
Create stored procedure usp_get_employees_salary_above_35000 that returns all employees’ first and last
names for whose salary is above 35000. The result should be sorted by first_name then by last_name
alphabetically, and id ascending.*/

DELIMITER $$
CREATE PROCEDURE `usp_get_employees_salary_above_35000`()
BEGIN
	SELECT `first_name`, `last_name`
    FROM `employees`
    WHERE `salary` > 35000
    ORDER BY `first_name`, `last_name`, `employee_id`;
END $$

/*2. Employees with Salary Above Number
Create stored procedure usp_get_employees_salary_above that accept a number as parameter and return all
employees’ first and last names whose salary is above or equal to the given number. The result should be sorted by
first_name then by last_name alphabetically and id ascending.*/

DELIMITER $$
CREATE PROCEDURE `usp_get_employees_salary_above`(`lower_bound_of_salary` DECIMAL(19, 4))
BEGIN
	SELECT `first_name`, `last_name`
    FROM `employees`
    WHERE `salary` > `lower_bound_of_salary`
    ORDER BY `first_name`, `last_name`, `employee_id`;
END $$

/*3. Town Names Starting With
Write a stored procedure usp_get_towns_starting_with that accept string as parameter and returns all town
names starting with that string. The result should be sorted by town_name alphabetically.*/

CREATE PROCEDURE `usp_get_towns_starting_with`(`start_string` VARCHAR(20))
BEGIN
	SELECT `name` AS `town_name`
    FROM `towns`
    WHERE LOWER(LEFT(`name`, LENGTH(`start_string`)) = LOWER(`start_string`))
    ORDER BY `town_name`;
END $$

/*4. Employees from Town
Write a stored procedure usp_get_employees_from_town that accepts town_name as parameter and return
the employees’ first and last name that live in the given town. The result should be sorted by first_name then by
last_name alphabetically and id ascending.*/

DELIMITER $$
CREATE PROCEDURE `usp_get_employees_from_town`(`town_name` VARCHAR(40))
BEGIN
	SELECT `e`.`first_name`, `e`.`last_name`
	FROM `towns` AS `t`
	JOIN `addresses` `a`
		ON `a`.`town_id` = `t`.`town_id`
	JOIN `employees` AS `e`
		ON `a`.`address_id` = `e`.`address_id`
	WHERE `t`.`name` = `town_name`
    ORDER BY `e`.`first_name`, `e`.`last_name`, `e`.`employee_id`;
END $$

/*5. Salary Level Function
Write a function ufn_get_salary_level that receives salary of an employee and returns the level of the salary.
- If salary is < 30000 return “Low”
- If salary is between 30000 and 50000 (inclusive) return “Average”
- If salary is > 50000 return “High”*/

DELIMITER $$
CREATE FUNCTION `ufn_get_salary_level`(`employee_salary` DECIMAL(19, 4))
RETURNS VARCHAR(7)
BEGIN
	DECLARE `salary_level` VARCHAR(7);
    IF `employee_salary` < 30000 
		THEN SET `salary_level` := 'Low';
	ELSEIF `employee_salary` BETWEEN 30000 AND 50000
		THEN SET `salary_level` := 'Average';
	ELSE SET `salary_level` = 'High';
    END IF;
    RETURN `salary_level`;
END $$

/*6. Employees by Salary Level
Write a stored procedure usp_get_employees_by_salary_level that receive as parameter level of salary
(low, average or high) and print the names of all employees that have given level of salary. The result should be
sorted by first_name then by last_name both in descending order.*/

DELIMITER $$
CREATE PROCEDURE `usp_get_employees_by_salary_level`(`salary_level` VARCHAR(7))
BEGIN
	SELECT `first_name`, `last_name`
    FROM `employees`
    WHERE LOWER(`ufn_get_salary_level`(`salary`)) = LOWER(`salary_level`)
    ORDER BY `first_name` DESC, `last_name` DESC;
END $$

CALL `usp_get_employees_by_salary_level`('HiGh');

/*7. Define Function
Define a function ufn_is_word_comprised(set_of_letters varchar(50), word varchar(50)) that
returns true or false depending on that if the word is a comprised of the given set of letters.*/

DELIMITER $$
CREATE FUNCTION `ufn_is_word_comprised`(`set_of_letters` VARCHAR(50), `word` VARCHAR(50))
RETURNS BIT
BEGIN
	DECLARE `result` BIT;
    DECLARE `current_occurences` INT;
    DECLARE `index` INT;
    SET `index` := 1;
    WHILE `index` <= LENGTH(`word`) DO
		SET `index` := `index` + 1;
        IF LOCATE(SUBSTRING(`word`, `index`, 1), `set_of_letters`) > 0 THEN 
			SET `current_occurences` = `current_occurences` + 1;
		END IF;
	END WHILE;
    IF `current_occurences` = LENGTH(`word`) THEN 
		SET `result` = 1;
	ELSE SET `result` = 0;
    END IF;
    RETURN `result`;
END $$















