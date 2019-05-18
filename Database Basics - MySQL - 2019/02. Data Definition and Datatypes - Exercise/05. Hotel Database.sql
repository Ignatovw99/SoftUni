/*13. Hotel Database*/
CREATE DATABASE `hotel`;
USE `hotel`;

CREATE TABLE `employees`(
	`id` INT(11) UNSIGNED NOT NULL UNIQUE AUTO_INCREMENT,
    `first_name` VARCHAR(45) NOT NULL,
    `last_name` VARCHAR(45) NOT NULL,
    `title` VARCHAR(50) NOT NULL,
    `notes` VARCHAR(100),
    CONSTRAINT `pk_employees` PRIMARY KEY(`id`)
);

INSERT INTO `employees`(`first_name`, `last_name`, `title`)
VALUES
		('John', 'Smith', 'Manager'),
        ('Mark', 'Terry', 'CEO'),
        ('Alex', 'Ivanov', 'Software Developer');
        
CREATE TABLE `customers`(
	`account_number` INT(11) UNSIGNED NOT NULL UNIQUE AUTO_INCREMENT,
    `first_name` VARCHAR(50) NOT NULL,
    `last_name` VARCHAR(50) NOT NULL,
    `phone_number` VARCHAR(10) NOT NULL,
    `emergency_name` VARCHAR(50),
	`emergency_number` VARCHAR(10),
	`notes` VARCHAR(100),
    CONSTRAINT `pk_customers` PRIMARY KEY(`account_number`)
);

INSERT INTO `customers`(`first_name`, `last_name`, `phone_number`)
VALUES 
		('Adam', 'Hans', '02322412'),
		('Sam', 'Runner', '02322435'),
		('Frank', 'Lampard', '02214324');
        
CREATE TABLE `room_status`(
	`room_status` INT(11) UNSIGNED NOT NULL UNIQUE AUTO_INCREMENT,
    `notes` VARCHAR(50) NOT NULL,
    CONSTRAINT `pk_room_status` PRIMARY KEY(`room_status`)
);

INSERT INTO `room_status`(`notes`)
VALUES
		('Free'),
        ('Occupied'),
        ('For clean');
        
CREATE TABLE `room_types`(
	`room_type` INT(11) UNSIGNED NOT NULL UNIQUE AUTO_INCREMENT,
    `notes` VARCHAR(45) NOT NULL,
    CONSTRAINT `pk_room_types` PRIMARY KEY(`room_type`)
);

INSERT INTO `room_types`(`notes`)
VALUES
		('Suite'),
        ('VIP Appertment'),
        ('Double Bedroom');
        
CREATE TABLE `bed_types`(
	`bed_type` INT(11) UNSIGNED NOT NULL UNIQUE AUTO_INCREMENT,
	`notes` VARCHAR(45) NOT NULL,
    CONSTRAINT `pk_bed_types` PRIMARY KEY(`bed_type`)
);

INSERT INTO `bed_types`(`notes`)
VALUES
		('Single Bed'),
        ('Double Bed'),
        ('Sofa');
        
CREATE TABLE `rooms`(
	`room_number` INT(5) UNSIGNED NOT NULL UNIQUE,
    `room_type` INT(11) UNSIGNED NOT NULL,
    `bed_type` INT(11) UNSIGNED NOT NULL,
    `rate` DOUBLE(3, 2) DEFAULT 0.0,
    `room_status` INT(11) UNSIGNED NOT NULL,
    `notes` VARCHAR(45),
    CONSTRAINT `pk_rooms` PRIMARY KEY(`room_number`)
);

INSERT INTO `rooms`(`room_number`, `room_type`, `bed_type`, `room_status`)
VALUES
		(103, 1, 2, 3),
        (210, 2, 1, 2),
        (306, 3, 3, 1);
        
CREATE TABLE `payments`(
	`id` INT(11) UNSIGNED NOT NULL UNIQUE AUTO_INCREMENT,
	`employee_id` INT(11) UNSIGNED NOT NULL,
    `payment_date` DATE NOT NULL,
    `account_number` INT(11) UNSIGNED NOT NULL,
    `first_date_occupied` DATE NOT NULL,
    `last_date_occupied` DATE NOT NULL,
    `total_days` INT UNSIGNED,
    `amount_charged` DOUBLE(5, 2) DEFAULT 0.0,
    `tax_rate` DOUBLE(5, 2) DEFAULT 0.0,
    `payment_total` DOUBLE(5, 2) NOT NULL,
    `notes` VARCHAR(50),
    CONSTRAINT `pk_payments` PRIMARY KEY(`id`)
);

INSERT INTO `payments`(`employee_id`, `payment_date`, `account_number`, `first_date_occupied`, `last_date_occupied`, `payment_total`)
VALUES
		(1, '2019-05-01', 1, '2019-05-03', '2019-05-07', 154.65),
		(2, '2019-05-03', 2, '2019-05-08', '2019-05-14', 244.65),
		(3, '2019-05-02', 3, '2019-05-10', '2019-05-18', 644.63);

CREATE TABLE `occupancies`(
	`id` INT(11) UNSIGNED NOT NULL UNIQUE AUTO_INCREMENT,
    `employee_id` INT(11) UNSIGNED NOT NULL,
    `date_occupied` DATE NOT NULL,
    `account_number` INT(11) UNSIGNED NOT NULL,
    `room_number` INT(11) NOT NULL,
    `rate_applied` DOUBLE(3, 2) DEFAULT 0.0,
    `phone_charge` DOUBLE(3, 2) DEFAULT 0.0,
    `notes` VARCHAR(50),
    CONSTRAINT `pk_occupancies` PRIMARY KEY(`id`)
);

INSERT INTO `occupancies`(`employee_id`, `date_occupied`, `account_number`, `room_number`)
VALUES 
		(1, '2019-05-12', 1, 103),
		(2, '2019-05-14', 2, 210),
		(3, '2019-05-17', 3, 306);

		
/*21. Decrease Tax Rate
Use hotel database and decrease tax rate by 3% to all payments.
Select only tax_rate column from the payments table.*/

UPDATE `payments`
SET `tax_rate` = `tax_rate` * 0.97;

SELECT `tax_rate`
FROM `payments`;

/*22. Delete All Records
Use Hotel database and delete all records from the occupancies table. Use SQL query.*/
TRUNCATE TABLE `occurancies`;
