CREATE DATABASE `car_rental`;
USE `car_rental`;

CREATE TABLE `categories`(
	`id` INT(11) UNSIGNED UNIQUE NOT NULL AUTO_INCREMENT,
    `category` VARCHAR(45) NOT NULL,
    `daily_rate` DOUBLE(5, 2) NOT NULL,
    `weekly_rate` DOUBLE(5, 2) NOT NULL,
    `monthly_rate` DOUBLE(5, 2) NOT NULL,
    `weekend_rate` DOUBLE(5, 2) NOT NULL,
    CONSTRAINT `pk_categories` PRIMARY KEY(`id`)
);

CREATE TABLE `cars`(
	`id` INT(11) UNSIGNED UNIQUE NOT NULL AUTO_INCREMENT,
    `plate_number` VARCHAR(8) NOT NULL UNIQUE,
    `make` VARCHAR(20) NOT NULL,
    `model` VARCHAR(20) NOT NULL,
	`car_year` YEAR NOT NULL,
    `category_id` INT(11) UNSIGNED NOT NULL,
    `doors` TINYINT,
    `picture` BLOB(2000),
    `car_condition` TEXT,
    `available` BOOLEAN NOT NULL DEFAULT TRUE,
    CONSTRAINT `pk_cars` PRIMARY KEY(`id`)
);

CREATE TABLE `employees`(
	`id` INT(11) UNSIGNED UNIQUE NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(45) NOT NULL,
    `last_name` VARCHAR(45) NOT NULL,
    `title` VARCHAR(50) NOT NULL,
    `notes` TEXT,
    CONSTRAINT `pk_employees` PRIMARY KEY(`id`)
);

CREATE TABLE `customers`(
	`id` INT(11) UNSIGNED UNIQUE NOT NULL AUTO_INCREMENT,
    `driver_licence_number` VARCHAR(50) NOT NULL,
    `full_name` VARCHAR(80) NOT NULL,
    `address` VARCHAR(50) NOT NULL,
    `city` VARCHAR(50) NOT NULL,
    `zip_code` INT(11),
	`notes` TEXT,
    CONSTRAINT `pk_customers` PRIMARY KEY(`id`)
);

CREATE TABLE `rental_orders`(
	`id` INT(11) UNSIGNED UNIQUE NOT NULL AUTO_INCREMENT,
    `employee_id` INT(11) NOT NULL,
    `customer_id` INT(11) NOT NULL,
    `car_id` INT(11) NOT NULL,
    `car_condition` TEXT,
    `tank_level` DOUBLE(5, 2),
    `kilometrage_start` DOUBLE(5, 2),
    `kilometrage_end` DOUBLE(5, 2),
    `total_kilometrage` DOUBLE(5, 2),
    `start_date` DATE,
    `end_date` DATE,
    `total_days` INT UNSIGNED,
    `rate_applied` DOUBLE(5, 2),
    `tax_rate` DOUBLE(5, 2),
    `order_status` VARCHAR(45),
    `notes` TEXT,
    CONSTRAINT `pk_rental_orders` PRIMARY KEY(`id`)
);

INSERT INTO `categories` (`category`, `daily_rate`, `weekly_rate`, `monthly_rate`, `weekend_rate`)
VALUES 
		('First Category', 11.32, 23.1, 42.2, 23.4),
		('Second Category', 71.2, 24.2, 63.2, 45.2),
		('Third Category', 71.35, 42.3, 23.3, 47.3);
        
INSERT INTO `cars` (`plate_number`, `make`, `model`, `car_year`, `category_id`, `doors`, `car_condition`)
VALUES 
		('Plate 1', 'BMW', 'M Power', '1910', 1, 2, 'Unknown'),
		('Plate 2', 'Mercedes', 'AMG', '1920', 2, 4, 'Bad'),
		('Plate 3', 'Porsche', '911', '1940', 3, 5, 'Very Good');
        
INSERT INTO `employees` (`first_name`, `last_name`, `title`, `notes`)
VALUES 
		('John', 'Goshev', 'Boss', 'I am the best!'),
		('Voivoda', 'Peshev', 'Supervisor', ''),
		('Gergi', 'Ivan', 'Worker', 'Can do any work');
        
INSERT INTO `customers` (`driver_licence_number`, `full_name`, `address`, `city`, `zip_code`, `notes`)
VALUES 
		('24124SFS', 'Gergi Petrov', 'A casstle', 'Paris', 1000, 'None'),
		('2342RFDS', 'Pesho Peshev', 'A boat', 'London', 2000, 'None'),
		('454FWER4', 'Jogn Smith', 'Under the bridge', 'Somowhere', 1000, '');
        
INSERT INTO `rental_orders` (`employee_id`, `customer_id`, `car_id`, `car_condition`, `start_date`)
VALUES 
		(2, 2, 3, 'Excellent', NOW()),
		(1, 3, 1, 'Very Bad', NOW()),
		(3, 1, 2, 'OK', NOW());