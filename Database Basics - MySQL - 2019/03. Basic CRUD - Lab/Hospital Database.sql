CREATE DATABASE IF NOT EXISTS `hospital`; 
USE `hospital`;

CREATE TABLE departments (
	id INT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(50)
);

INSERT INTO departments(name) VALUES('Therapy'), ('Support'), ('Management'), ('Other');

CREATE TABLE employees (
	id INT PRIMARY KEY AUTO_INCREMENT,
	first_name VARCHAR(50) NOT NULL,
	last_name VARCHAR(50) NOT NULL,
	job_title VARCHAR(50) NOT NULL,
	department_id INT NOT NULL,
	salary DOUBLE NOT NULL,
	CONSTRAINT `fk_department_id` FOREIGN KEY (`department_id`) REFERENCES `departments` (`id`)
);

INSERT INTO `employees` (`first_name`,`last_name`, `job_title`,`department_id`,`salary`) VALUES
	('John', 'Smith', 'Therapist',1, 900.00),
	('John', 'Johnson', 'Acupuncturist',1, 880.00),
	('Smith', 'Johnson', 'Technician',2, 1100.00),
	('Peter', 'Petrov', 'Supervisor',3, 1100.00),
	('Peter', 'Ivanov', 'Dentist',4, 1500.23),
	('Ivan' ,'Petrov', 'Therapist',1, 990.00),
	('Jack', 'Jackson', 'Epidemiologist',4, 1800.00),
	('Pedro', 'Petrov', 'Medical Director',3, 2100.00),
	('Nikolay', 'Ivanov', 'Nutrition Technician',4, 1600.00);
	

	
CREATE TABLE rooms (
	id INT PRIMARY KEY AUTO_INCREMENT,
	occupation VARCHAR(30)
);

INSERT INTO rooms(`occupation`) VALUES('free'), ('occupied'),('free'),('free'),('occupied');

CREATE TABLE patients (
	id INT PRIMARY KEY AUTO_INCREMENT,
	first_name VARCHAR(50),
	last_name VARCHAR(50),
	room_id INT NOT NULL
);

INSERT INTO patients(`first_name`,`last_name`,`room_id`) 
	VALUES ('Pesho','Petrov',1),('Gosho','Georgiev',3),('Mariya','Marieva', 2), ('Katya','Katerinova', 2), ('Nikolay','Nikolaev',3);
    
    
/*1. Select Employee Information
Write a query to select all employees and retrieve information about their id, 
first_name, last_name and job_title ordered by id.*/

SELECT `id`, `first_name`, `last_name`, `job_title`
FROM `employees`
ORDER BY `id`;


/*2. Select Employees with Filter
Write a query to select all employees (id, first_name, last_name, job_title, salary)
whose salaries are higher than 1000.00, ordered by id.
Concatenate fields first_name and last_name into ‘full_name’.*/

SELECT `id`, CONCAT(`first_name`, ' ', `last_name`) AS `full_name`, `job_title`, `salary`
FROM `employees` AS emp
WHERE emp.`salary` > 1000.00
ORDER BY `id` ASC;


/*3. Update Employees Salary
Update all employees salaries whose job_title is “Therapist” by 10%. 
Retrieve information about all salaries ordered ascending.*/

UPDATE `employees`
SET `salary` = `salary` + (0.1 * `salary`)
WHERE `job_title` = 'Therapist';

SELECT `salary`
FROM `employees`
ORDER BY `salary` ASC;


/*4. Top Paid Employee
Write a query to create a view that selects all information 
about the top paid employee from the “employees” table
in the hospital database.*/

CREATE VIEW `v_top_paid_employee` AS
	SELECT * FROM `employees`
	ORDER BY `salary` DESC
    LIMIT 1;

SELECT * FROM `v_top_paid_employee`;


/*5. Select Employees by Multiple Filters
Write a query to retrieve information about employees,
who are in department 4 and have salary higher or equal to
1600. Order the information by id.*/

SELECT * 
FROM `employees` AS emp
WHERE `emp`.`department_id` = 4 AND `emp`.`salary` >= 1600
ORDER BY `id`;
    

/*6. Delete from Table
Write a query to delete all employees from the “employees” table
who are in department 2 or 1. Order the information by id.*/
    
DELETE FROM `employees`
WHERE `department_id` = 1 OR `department_id` = 2; 

SELECT *
FROM `employees`
ORDER BY `id`;