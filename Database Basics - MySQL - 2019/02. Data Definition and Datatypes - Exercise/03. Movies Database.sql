/*11. Movies Database*/
CREATE DATABASE `movies`;
USE `movies`;

CREATE TABLE `directors`(
	`id` INT(11) UNIQUE NOT NULL AUTO_INCREMENT,
    `director_name` VARCHAR(30) NOT NULL,
    `notes` LONGTEXT,
    PRIMARY KEY(`id`)
);

CREATE TABLE `genres`(
	`id` INT(11) UNIQUE NOT NULL AUTO_INCREMENT,
    `genre_name` VARCHAR(30) NOT NULL,
    `notes` TEXT,
    CONSTRAINT pk_genres PRIMARY KEY (`id`)
);

CREATE TABLE `categories`(
	`id` INT(11) UNIQUE NOT NULL AUTO_INCREMENT,
    `category_name` VARCHAR(30) NOT NULL,
    `notes` TEXT,
    PRIMARY KEY(`id`)
);

CREATE TABLE `movies`(
	`id` INT(11) UNIQUE NOT NULL AUTO_INCREMENT,
    `title` VARCHAR(30) NOT NULL,
    `director_id` INT(11) NOT NULL,
    `copyright_year` YEAR NOT NULL,
    `length` INT NOT NULL,
    `genre_id` INT(11) NOT NULL,
    `category_id` INT(11) NOT NULL,
    `rating` INT NOT NULL DEFAULT 0,
    `notes` TEXT,
    PRIMARY KEY (`id`)
    -- CONSTRAINT fk_movies_directors FOREIGN KEY (`director_id`) REFERENCES `directors`(`id`),
    -- CONSTRAINT fk_movies_genres FOREIGN KEY (`genre_id`) REFERENCES `genres`(`id`),
    -- CONSTRAINT fk_movies_categories FOREIGN KEY (`category_id`) REFERENCES `categories`(`id`)
);

INSERT INTO `directors`
	(`id`, `director_name`, `notes`)
VALUES
	(1,'dasdasd','fasdfasdfasdfa'),
	(2,'dasdasd','fasdfasdfasdfa'),
	(3,'dasdasd','fasdfasdfasdfa'),
	(4,'dasdasd','fasdfasdfasdfa'),
	(5,'dasdasd','fasdfasdfasdfa');

INSERT INTO `categories`
	(`id`, `category_name`)
VALUES
	(1,'wi-fi'),
	(2,'wi-fi'),
	(3,'wi-fi'),
	(4,'wi-fi'),
	(5,'wi-fi');

INSERT INTO `genres`
	( `id`, `genre_name`, `notes`)
VALUES
	(2,'dasdad','kaman'),
	(1,'dasdad','kaman'),
	(3,'dasdad','kaman'),
	(4,'dasdad','kaman'),
	(5,'dasdad','kaman');
    
INSERT INTO `movies`
	(`id`, `title`, `director_id`, `copyright_year`, `length`, `genre_id`, `category_id`)
VALUES
	(11,"kamen",2,'2016',23,1,2),
	(10,"kamen",2,'2016',23,1,2),
	(13,"kamen",2,'2016',23,1,2),
	(14,"kamen",2,'2016',23,1,2),
	(15,"kamen",1,'2016',23,1,2);

