/*0. Create Database
Firstly, just create new database named minions.*/
CREATE DATABASE `minions`;
USE `minions`;

/*1. Create Tables
In the newly created database Minions add table minions (id, name, age). 
Then add new table towns (id, name). 
Set id columns of both tables to be primary key as constraint.*/
CREATE TABLE `minions`(
	`id` INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL,
    `age` TINYINT UNSIGNED DEFAULT NULL
);

CREATE TABLE `towns`(
	`id` INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL
);

/*02. Alter Minions Table
Change the structure of the Minions table to have new column town_id,
that would be of the same type as the id column of towns table.
Add new constraint that makes town_id foreign key and references to id column of towns table.*/
ALTER TABLE `minions`
ADD COLUMN `town_id` INT(11) DEFAULT NULL,
ADD CONSTRAINT `fk_minions_towns`
FOREIGN KEY (`town_id`)
REFERENCES `towns`(`id`);

/*03. Insert Records in Both Tables*/
INSERT INTO `towns`(`id`, `name`)
VALUES
	(1, 'Sofia'),
    (2, 'London'),
    (3, 'Berlin');

INSERT INTO `minions`(`id`, `name`, `age`, `town_id`)
VALUES
	(1, 'Kevin', 22, 1),
    (2, 'Bob', 15, 3),
    (3, 'Steward', NULL, 2);

/*04. Truncate Table Minions
Delate all the data from minions table.*/
TRUNCATE TABLE `minions`;

/*05. Drop all Tables
Delete all tables from the minions database*/
DROP TABLE `minions`;
DROP TABLE `towns`;

DROP DATABASE `minions`;