CREATE DATABASE `people`;
USE `people`;

/*6. Create Table People*/
CREATE TABLE `people`(
	`id` INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(200) NOT NULL,
    `picture` BLOB(2000),
    `height` DOUBLE(5, 2),
    `weight` DOUBLE(5, 2),
    `gender` ENUM('m', 'f') NOT NULL,
    `birthdate` DATE NOT NULL,
    `biography` LONGTEXT
);

INSERT INTO `people`(`id`, `name`, `gender`, `birthdate`)
VALUES
	(1, 'John', 'm', '1977-04-15'),
    (2, 'Maria', 'f', '1979-09-26'),
    (3, 'Sam', 'm', '1977-04-15'),
    (4, 'Frank', 'm', '1975-04-15'),
    (5, 'Anthony', 'm', '1972-04-19');
    
/*7. Create Table Users*/
CREATE TABLE `users`(
	`id` INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(30) UNIQUE NOT NULL,
    `password` VARCHAR(26) NOT NULL,
    `profile_picture` BLOB(900),
    `last_login_time` TIMESTAMP,
    `is_deleted` BIT
);

INSERT INTO `users`(`id`, `username`, `password`, `last_login_time`, `is_deleted`)
VALUES
	(1, 'John', '123', NOW() , 1),
    (2, 'Maria', '432', NOW(), 0),
    (3, 'Sam', '421', NOW(), 1),
    (4, 'Frank', '1432', NOW(), 0),
    (5, 'Anthony', '543', NULL, 0);

/*8. Change Primary Key
Using SQL queries modify table users from the previous task.
First remove current primary key then create new primary key that would be combination of fields id and username.
The initial primary key name on id is pk_users.*/
ALTER TABLE `users`
DROP PRIMARY KEY,
ADD CONSTRAINT `pk_users` PRIMARY KEY (`id`, `username`);

/*9. Set Default Value of a Field
Using SQL queries modify table users. Make the default value of last_login_time field to be the current time.*/
ALTER TABLE `users`
MODIFY COLUMN `last_login_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP;  -- or NOW() is possible;

/*10. Set Unique Field
Using SQL queries modify table users.
Remove username field from the primary key so only the field id would be primary key.
Now add unique constraint to the username field.
The initial primary key name on (id, username) is pk_users.*/
ALTER TABLE `users`
DROP PRIMARY KEY,
ADD CONSTRAINT `pk_id` PRIMARY KEY (`id`),
ADD CONSTRAINT `uq_username` UNIQUE (`username`);

DROP DATABASE `people`;