/*1. Employee Address
Write a query that selects:
- employee_id, job_title, address_id, address_text
Return the first 5 rows sorted by address_id in ascending order.*/

SELECT 
	`emp`.`employee_id`,
    `emp`.`job_title`,
    `add`.`address_id`,
    `add`.`address_text`
FROM `employees` AS `emp`
JOIN `addresses` AS `add`
ON `emp`.`address_id` = `add`.`address_id`
ORDER BY `add`.`address_id`
LIMIT 5;

/*2. Addresses with Towns
Write a query that selects:
- first_name, last_name, town, address_text
Sort the result by first_name in ascending order then by last_name. Select first 5 employees.*/

SELECT 
	`e`.`first_name`,
    `e`.`last_name`,
    `t`.`name` AS 'town',
    `a`.`address_text`
FROM `employees` AS `e`
JOIN `addresses` AS `a`
	ON `e`.`address_id` = `a`.`address_id`
JOIN `towns` AS `t`
	ON `a`.`town_id` = `t`.`town_id`
ORDER BY `e`.`first_name`, `e`.`last_name`
LIMIT 5;

/*3. Sales Employee
Write a query that selects:
- employee_id, first_name, last_name, department_name
Sort the result by employee_id in descending order. Select only employees from the “Sales” department.*/

SELECT 
	`e`.`employee_id`,
    `e`.`first_name`,
    `e`.`last_name`,
    `d`.`name` AS 'department_name'
FROM `employees` AS `e`
JOIN `departments` AS `d`
ON `e`.`department_id` = `d`.`department_id`
WHERE `d`.`name` = 'Sales'
ORDER BY `e`.`employee_id` DESC;

/*4. Employee Departments
Write a query that selects:
- employee_id, first_name, salary, department_name
Filter only employees with salary higher than 15000. Return the first 5 rows sorted by department_id in
descending order.*/

SELECT 
	`e`.`employee_id`,
    `e`.`first_name`,
    `e`.`salary`,
    `d`.`name` AS 'department_name'
FROM `employees` AS `e`
JOIN `departments` AS `d`
ON `e`.`department_id` = `d`.`department_id`
WHERE `e`.`salary` > 15000
ORDER BY `d`.`department_id` DESC
LIMIT 5;

/*5. Employees Without Project
Write a query that selects:
- employee_id, first_name
Filter only employees without a project. Return the first 3 rows sorted by employee_id in descending order.*/

SELECT 
	`e`.`employee_id`,
    `e`.`first_name`
FROM `employees` AS `e`
LEFT JOIN `employees_projects` AS `emp_proj`
	ON `e`.`employee_id` = `emp_proj`.`employee_id`
LEFT JOIN `projects` AS `p`
	ON `p`.`project_id` = `emp_proj`.`project_id`
WHERE `emp_proj`.`employee_id` IS NULL
ORDER BY `e`.`employee_id` DESC
LIMIT 3;

/*6. Employees Hired After
Write a query that selects:
- first_name, last_name, hire_date, dept_name
Filter only employees hired after 1/1/1999 and from either the 'Sales' or the 'Finance' departments. Sort the
result by hire_date (ascending).*/

SELECT 
	`e`.`first_name`,
    `e`.`last_name`,
    `e`.`hire_date`,
    `d`.`name` AS 'dept_name'
FROM `employees` AS `e`
JOIN `departments` AS `d`
ON 
	`e`.`department_id` = `d`.`department_id`
	AND DATE(`e`.`hire_date`) > '1999/01/01'
    AND `d`.`name` IN ('Sales', 'Finance')
ORDER BY `e`.`hire_date` ASC;

/*7. Employees with Project
Write a query that selects:
- employee_id, first_name, project_name
Filter only employees with a project, which has started after 13.08.2002 and it is still ongoing (no end date). Return
the first 5 rows sorted by first_name then by project_name both in ascending order.*/

SELECT 
	`e`.`employee_id`,
    `e`.`first_name`,
    `p`.`name` AS 'project_name'
FROM `employees` AS `e`
LEFT JOIN `employees_projects` AS `e_p`
	ON `e`.`employee_id` = `e_p`.`employee_id`
LEFT JOIN `projects` AS `p`
	ON `e_p`.`project_id` = `p`.`project_id`
WHERE
	DATE(`p`.`start_date`) > '2002/08/13'
    AND `p`.`end_date` IS NULL
ORDER BY `e`.`first_name`, `p`.`name`
LIMIT 5;

/*8. Employee 24
Write a query that selects:
- employee_id, first_name, project_name
Filter all the projects of employees with id 24. If the project has started after 2005 inclusively the return value
should be NULL. Sort the result by project_name alphabetically.*/

SELECT 
	`e`.`employee_id`,
    `e`.`first_name`,
    IF
    (
		YEAR(`p`.`start_date`) >= 2005,
        NULL,
        `p`.`name`
	) AS `project_name`
FROM `employees` AS `e`
JOIN `employees_projects` AS `e_p`
	ON `e`.`employee_id` = `e_p`.`employee_id`
JOIN `projects` AS `p`
	ON `p`.`project_id` = `e_p`.`project_id`
WHERE `e`.`employee_id` = 24
ORDER BY `project_name`;

/*9. Employee Manager
Write a query that selects:
- employee_id, first_name, manager_id, manager_name
Filter all employees with a manager who has id equal to 3 or 7. Return all rows sorted by employee first_name
in ascending order.*/

SELECT 
	`emp`.`employee_id`,
    `emp`.`first_name`,
    `man`.`employee_id`,
    `man`.`first_name` AS 'manager_name'
FROM `employees` AS `emp`
JOIN `employees` AS `man`
ON `emp`.`manager_id` = `man`.`employee_id`
WHERE `man`.`employee_id` IN (3, 7)
ORDER BY `emp`.`first_name`;

/*10. Employee Summary
Write a query that selects:
- employee_id, employee_name, manager_name, department_name
Show the first 5 employees (only for employees who have a manager) with their managers and the departments
they are in (show the departments of the employees). Order by employee_id.*/

SELECT 
	`e`.`employee_id`,
    CONCAT(`e`.`first_name`, ' ', `e`.`last_name`) AS 'employee_name',
    CONCAT(`m`.`first_name`, ' ', `m`.`last_name`) AS 'manager_name',
    `d`.`name` AS 'department_name'
FROM `employees` AS `e`
JOIN `employees` AS `m`
	ON `e`.`manager_id` = `m`.`employee_id`
JOIN `departments` AS `d`
	ON `e`.`department_id` = `d`.`department_id`
ORDER BY `e`.`employee_id`
LIMIT 5;

/*11. Min Average Salary
Write a query that returns the value of the lowest average salary of all departments.*/

SELECT MIN(`average_department_salary`)
FROM
(
	SELECT AVG(`salary`) AS `average_department_salary`
    FROM `employees`
    GROUP BY `department_id`
) AS `average_departments_salaries`;