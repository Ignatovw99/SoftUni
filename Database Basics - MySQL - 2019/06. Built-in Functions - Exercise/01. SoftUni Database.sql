-- Part I – Queries for SoftUni Database

/*1. Find Names of All Employees by First Name
Write a SQL query to find first and last names of all employees,
whose first name starts with “Sa” (case insensitively).
Order the information by id.*/

SELECT `first_name`, `last_name`
FROM `employees`
WHERE LOWER(LEFT(`first_name`, 2)) = 'sa'
ORDER BY `employee_id`;

/*2. Find Names of All employees by Last Name
Write a SQL query to find first and last names of all employees,
whose last name contains “ei” (case insensitively).
Order the information by id.*/

SELECT `first_name`, `last_name`
FROM `employees`
WHERE LOWER(`last_name`) LIKE '%ei%'
ORDER BY `employee_id`;

/*3. Find First Names of All Employees
Write a SQL query to find the first names of all employees in the departments with ID 3 or 10
and whose hire year is between 1995 and 2005 inclusively. Order the information by id.*/

SELECT `first_name`
FROM `employees`
WHERE 
	`department_id` IN (3, 10) 
	AND EXTRACT(YEAR FROM `hire_date`) BETWEEN '1995' AND '2005'
ORDER BY `employee_id`;

/*04. Find All Employees Except Engineers 
Write a SQL query to find the first and last names of all employees,
whose job titles does not contain “engineer”. Order the information by id.*/

SELECT `first_name`, `last_name`
FROM `employees`
WHERE `job_title` NOT LIKE '%engineer%'
ORDER BY `employee_id`;

/*05. Find Towns with Name Length
Write a SQL query to find town names that are 5 or 6 symbols long
and order them alphabetically by town name.*/

SELECT `name`
FROM `towns`
WHERE LENGTH(`name`) IN(5, 6)
ORDER BY `name`;

/*6. Find Towns Starting With
Write a SQL query to find all towns that start with letters M, K, B or E (case insensitively).
Order them alphabetically by town name.*/

SELECT *
FROM `towns`
WHERE LOWER(LEFT(`name`, 1)) IN ('m', 'k', 'b', 'e')
ORDER BY `name`;

/*7. Find Towns Not Starting With
Write a SQL query to find all towns that do not start with letters R, B or D (case insensitively).
Order them alphabetically by name.*/

SELECT *
FROM `towns`
WHERE LOWER(`name`) REGEXP '^[^rbd]+'
ORDER BY `name`;

/*8. Create View Employees Hired After 2000 Year
Write a SQL query to create view v_employees_hired_after_2000
with the first and the last name of all employees hired after 2000 year.*/

CREATE VIEW `v_employees_hired_after_2000` AS
	SELECT `first_name`, `last_name`
    FROM `employees`
    WHERE EXTRACT(YEAR FROM `hire_date`) > 2000;
    
SELECT * FROM `v_employees_hired_after_2000`;

/*9. Length of Last Name
Write a SQL query to find the names of all employees whose last name is exactly 5 characters long.*/

SELECT `first_name`, `last_name`
FROM `employees`
WHERE LENGTH(`last_name`) = 5;