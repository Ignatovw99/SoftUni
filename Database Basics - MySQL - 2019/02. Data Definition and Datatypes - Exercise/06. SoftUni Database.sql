/*14. Create SoftUni Database*/
CREATE DATABASE `soft_uni`;
USE `soft_uni`;

CREATE TABLE `towns`(
	`id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL,
    CONSTRAINT `pk_towns` PRIMARY KEY (`id`)
);

CREATE TABLE `addresses`(
	`id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
    `address_text` VARCHAR(50) NOT NULL,
    `town_id` INT(11) UNSIGNED NOT NULL,
    CONSTRAINT `pk_addresses` PRIMARY KEY (`id`),
    CONSTRAINT `fk_addresses_towns` FOREIGN KEY (`town_id`) REFERENCES `towns` (`id`)
);

CREATE TABLE `departments`(
	`id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL,
    CONSTRAINT `pk_departmets` PRIMARY KEY (`id`)
);

CREATE TABLE `employees`(
	`id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(20) NOT NULL,
    `middle_name` VARCHAR(20),
    `last_name` VARCHAR(20) NOT NULL,
    `job_title` VARCHAR(45),
    `department_id` INT(11) UNSIGNED NOT NULL,
    `hire_date` DATE,
    `salary` DECIMAL(10, 2),
    `address_id` INT(11) UNSIGNED,
    CONSTRAINT `pk_employees` PRIMARY KEY (`id`),
    CONSTRAINT `fk_employees_departments` FOREIGN KEY (`department_id`) REFERENCES `departments` (`id`),
    CONSTRAINT `fk_employees_addresses` FOREIGN KEY (`address_id`) REFERENCES `addresses` (`id`)
);

/*15. Backup Database
By using mysqldump command from MySql command line make a backup of the database soft_uni, 
from the previous tasks, into a file named “softuni-backup.sql”. Drop your database from 
MySQL Workbench. Then restore the database from the created backup file by using mysql command line.

First set path of MySQL Server bin file in cmd. Than execute in command line:

mysqldump -u root -p soft_uni > C:\Program files\soft_uni_backup.sql (File path)

DROP DATABASE `soft_uni`;

Add following lines on the top of soft_uni_backup.sql file
CREATE DATABASE `soft_uni`;
USE `soft_uni`;

And than execute in cmd:

mysql -u root -p < C:\Program files\soft_uni_backup.sql (File path)
*/

/*16. Basic Insert*/
INSERT INTO `towns`(`name`)
VALUES ('Sofia'), ('Plovdiv'), ('Varna'), ('Burgas');

INSERT INTO `departments`(`name`)
VALUES ('Engineering'), ('Sales'), ('Marketing'), ('Software Development'), ('Quality Assurance');

INSERT INTO `employees`
					(`first_name`, `middle_name`, `last_name`, `job_title`, `department_id`, `hire_date`, `salary`)
VALUES  ('Ivan', 'Ivanov', 'Ivanov', '.NET Developer', 4, '2013-02-01', 3500.00),
		('Petar', 'Petrov', 'Petrov', 'Senior Engineer', 1, '2004-03-02', 4000.00),
		('Maria', 'Petrova', 'Ivanova', 'Intern', 5, '2016-08-28', 525.25),
		('Georgi', 'Terziev', 'Ivanov', 'CEO', 2, '2007-12-09', 3000.00),
		('Peter', 'Pan', 'Pan', 'Intern', 3, '2016-08-28', 599.88);

/*17. Basic Select All Fields
Use the soft_uni database and first select all records from the towns,
then from departments and finally from employees table.*/
SELECT * FROM `towns`;
SELECT * FROM `departments`;
SELECT * FROM `soft_uni`.`employees`;

/*18. Basic Select All Fields and Order Them*/
SELECT * FROM `towns` ORDER BY `name`;
SELECT * FROM `departments` ORDER BY `name`;
SELECT * FROM `soft_uni`.`employees` ORDER BY `salary` DESC;

/*19. Basic Select Some Fields*/
SELECT `name` 
FROM `towns`
ORDER BY `name`;

SELECT `name`
FROM `departments`
ORDER BY `name`;

SELECT `first_name`, `last_name`, `job_title`, `salary`
FROM `soft_uni`.`employees`
ORDER BY `salary` DESC;

/*20. Increase Employees Salary
Use softuni database and increase the salary of all employees by 10%. 
Select only salary column from the employees table.*/
UPDATE `employees`
SET `salary` = `salary` * 1.1;

SELECT `salary` FROM `employees`;

DROP DATABASE `soft_uni`;









