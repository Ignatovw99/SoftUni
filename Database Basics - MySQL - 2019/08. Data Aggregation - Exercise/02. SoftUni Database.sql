/*13. Employees Minimum Salaries
That’s it! You no longer work for Mr. Bodrog. You have decided to find a proper job as an analyst in SoftUni.
It’s not a surprise that you will use the soft_uni database.
Select the minimum salary from the employees for departments with ID (2,5,7) but only for those who are hired
after 01/01/2000. Sort result by department_id in ascending order.*/

SELECT `department_id`, MIN(`salary`) AS 'minimum_salary'
FROM `employees`
WHERE `department_id` IN (2, 5, 7) AND `hire_date` > '2000/01/01'
GROUP BY `department_id`;

/*14. Employees Average Salaries
Select all high paid employees who earn more than 30000 into a new table. Then delete all high paid employees
who have manager_id = 42 from the new table; Then increase the salaries of all high paid employees with
department_id =1 with 5000 in the new table. Finally, select the average salaries in each department from the new
table. Sort result by department_id in increasing order.*/

SELECT
	`department_id`,
    CASE
		WHEN `department_id` = 1 THEN AVG(`salary`) + 5000
        ELSE AVG(`salary`)
	END
FROM `employees`
WHERE `salary` > 30000 AND `manager_id` != 42
GROUP BY `department_id`
ORDER BY `department_id`;

/*15. Employees Maximum Salaries
Find the max salary for each department.
Filter those which have max salaries not in the range 30000 and 70000.
Sort result by department_id in increasing order.*/

SELECT `department_id`, MAX(`salary`) AS 'max_salary'
FROM `employees`
GROUP BY `department_id`
HAVING `max_salary` NOT BETWEEN 30000 AND 70000
ORDER BY `department_id`;

/*16. Employees Count Salaries
Count the salaries of all employees who don’t have a manager.*/

SELECT COUNT(*)
FROM `employees`
WHERE `manager_id` IS NULL;

/*17. 3rd Highest Salary*
Find the third highest salary in each department if there is such. Sort result by department_id in increasing order.*/

SELECT 
	`department_id`,
    (
		SELECT DISTINCT `emp2`.`salary`
        FROM `employees` AS `emp2`
        WHERE `emp1`.`department_id` = `emp2`.`department_id`
        ORDER BY `emp2`.`salary` DESC
        LIMIT 2, 1
    ) AS 'third_highest_salary'
FROM `employees` AS `emp1`
GROUP BY `department_id`
HAVING `third_highest_salary` IS NOT NULL
ORDER BY `department_id`;

/*18. Salary Challenge**
Write a query that returns:
• first_name, last_name, department_id
for all employees who have salary higher than the average salary of their respective departments. Select only the
first 10 rows. Order by department_id.*/

SELECT `first_name`, `last_name`, `department_id`
FROM `employees` AS `emp1`
WHERE `emp1`.`salary` >
		(
			SELECT AVG(`salary`)
            FROM `employees` AS `emp2`
            WHERE `emp2`.`department_id` = `emp1`.`department_id`
            GROUP BY `department_id`
		)
ORDER BY `emp1`.`department_id`
LIMIT 10;

/*19. Departments Total Salaries
Create a query which shows the total sum of salaries for each department. Order by department_id.*/

SELECT `department_id`, SUM(`salary`) AS 'total_salary'
FROM `employees`
GROUP BY `department_id`;