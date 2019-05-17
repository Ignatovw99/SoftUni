CREATE DATABASE `gamebar`;
USE `gamebar`;

-- 01. Create Tables
CREATE TABLE `employees`(
	`id` INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(45) NOT NULL,
    `last_name` VARCHAR(45) NOT NULL
);

CREATE TABLE `categories`(
	`id` INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(45) NOT NULL
);

CREATE TABLE `products`(
	`id` INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(45) NOT NULL,
    `category_id` INT(11) NOT NULL
);

-- 02. Insert Data in Tables
INSERT INTO `employees`(`first_name`, `last_name`)
VALUES
	('John', 'Terry'),
    ('Sam', 'Smith'),
    ('Tom', 'Edison');
    
-- 03. Alter Tables
ALTER TABLE `employees`
ADD COLUMN `middle_name` VARCHAR(45) DEFAULT NULL;

-- 04. Adding Constraints
ALTER TABLE `products`
ADD CONSTRAINT fk_products_categories
FOREIGN KEY(`category_id`)
REFERENCES `categories`(`id`);

-- 05. Modifying Columns
ALTER TABLE `employees`
MODIFY COLUMN `middle_name` VARCHAR(100);

-- 06. Drop Database
DROP DATABASE `gamebar`;