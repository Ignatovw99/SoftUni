CREATE DATABASE IF NOT EXISTS `colonial_blog_db`;

USE `colonial_blog_db`;

CREATE TABLE `users`(
	`id` INT(11) PRIMARY KEY AUTO_INCREMENT,
    `username` VARCHAR(30) NOT NULL UNIQUE,
    `password` VARCHAR(30) NOT NULL,
    `email` VARCHAR(50) NOT NULL
);

CREATE TABLE `categories`(
	`id` INT(11) PRIMARY KEY AUTO_INCREMENT,
    `category` VARCHAR(30) NOT NULL
);

CREATE TABLE `articles`(
	`id` INT(11) PRIMARY KEY AUTO_INCREMENT,
    `title` VARCHAR(50) NOT NULL,
    `content` TEXT NOT NULL,
    `category_id` INT(11),
    CONSTRAINT `fk_articles_categories` FOREIGN KEY (`category_id`) REFERENCES `categories`(`id`)
);

CREATE TABLE `users_articles`(
	`user_id` INT(11),
    `article_id` INT(11),
    CONSTRAINT `fk_users_articles_users` FOREIGN KEY (`user_id`) REFERENCES `users`(`id`),
    CONSTRAINT `fk_users_articles_articles` FOREIGN KEY (`article_id`) REFERENCES `articles`(`id`)
);

CREATE TABLE `comments`(
	`id` INT(11) PRIMARY KEY AUTO_INCREMENT,
    `comment` VARCHAR(255) NOT NULL,
    `article_id` INT(11) NOT NULL,
    `user_id` INT(11) NOT NULL,
    CONSTRAINT `fk_comments_articles` FOREIGN KEY (`article_id`) REFERENCES `articles`(`id`),
    CONSTRAINT `fk_comments_users` FOREIGN KEY (`user_id`) REFERENCES `users`(`id`)
);

CREATE TABLE `likes`(
	`id` INT(11) PRIMARY KEY AUTO_INCREMENT,
    `article_id` INT(11),
    `comment_id` INT(11),
    `user_id` INT(11) NOT NULL,
    CONSTRAINT `fk_likes_articles` FOREIGN KEY (`article_id`) REFERENCES `articles`(`id`),
    CONSTRAINT `fk_likes_comments` FOREIGN KEY (`comment_id`) REFERENCES `comments`(`id`),
    CONSTRAINT `fk_likes_users` FOREIGN KEY (`user_id`) REFERENCES `users`(`id`)
);

-- Data Manipulation Language

INSERT INTO `likes`(`article_id`, `comment_id`, `user_id`)
	SELECT 
		(
			CASE
				WHEN `u`.`id` % 2 = 0 THEN LENGTH(`u`.`username`)
                ELSE NULL
            END
        ) AS `article_id`,
        (
			CASE
				WHEN `u`.`id` % 2 <> 0 THEN LENGTH(`u`.`email`)
                ELSE NULL
            END
        ) AS `comment_id`,
        `u`.`id` AS `user_id`
    FROM `users` AS `u` 
    WHERE `u`.`id` BETWEEN 16 AND 20;

UPDATE `comments`
SET `comment` =
	(
		CASE 
			WHEN `id` % 2 = 0 THEN 'Very good article.'
			WHEN `id` % 3 = 0 THEN 'This is interesting.'
			WHEN `id` % 5 = 0 THEN 'I definitely will read the article again.'
			WHEN `id` % 7 = 0 THEN 'The universe is such an amazing thing.'
            ELSE `comment`
        END
    )
WHERE `id` BETWEEN 1 AND 15;

DELETE `a` FROM `articles` AS `a`
WHERE `a`.`category_id` IS NULL;
-- Quering

SELECT `biggest_articles`.`title`, `biggest_articles`.`summary`
FROM (SELECT `id`, `title`, CONCAT(LEFT(`content`, 20), '...') AS `summary`
FROM `articles`
ORDER BY LENGTH(`content`) DESC LIMIT 3) AS `biggest_articles`
ORDER BY `biggest_articles`.`id`;

SELECT `a`.`id`, `a`.`title`
FROM `articles` AS `a`
JOIN `users_articles` AS `ua`
	ON `a`.`id` = `ua`.`article_id`
WHERE `a`.`id` = `ua`.`user_id`
ORDER BY `a`.`id`;

SELECT 
	`c`.`category`,
	COUNT(`a`.`id`) AS `articles`,
    (
		SELECT COUNT(`l`.`id`)
        FROM `categories` AS `ca`
        JOIN `articles` AS `ar`
        ON `ca`.`id` = `ar`.`category_id`
        JOIN `likes` AS `l`
        ON `ar`.`id` = `l`.`article_id`
        WHERE `ca`.`id` = `c`.`id`
        GROUP BY `ca`.`id`
    ) AS `likes`
FROM `categories` AS `c`
JOIN `articles` AS `a`
	ON `c`.`id` = `a`.`category_id`
GROUP BY `c`.`id`
ORDER BY `likes`DESC, `articles` DESC, `c`.`id`;


	SELECT `a`.`title`, COUNT(`c`.`id`) as `comments`
    FROM `articles` as `a`
    JOIN `comments` AS `c` ON `a`.`id` = `c`.`article_id`
    JOIN `categories` AS  `ca` ON `a`.`category_id` = `ca`.`id`
    WHERE `ca`.`category` = 'Social'
    GROUP BY `a`.`id`
    ORDER BY `comments` DESC
    LIMIT 1;

SELECT CONCAT(LEFT(`c`.`comment`, 20), '...') AS 'summary'
FROM `comments` AS `c`
LEFT JOIN `likes` AS `l`
ON `c`.`id` = `l`.`comment_id`
WHERE `l`.`id` IS NULL
ORDER BY `c`.`id`
 DESC;

DELIMITER $$
CREATE FUNCTION `udf_users_articles_count`(`username` VARCHAR(30))
RETURNS INT(11)
BEGIN
	DECLARE `count` INT(11);
    SET `count` := 
    (
		SELECT COUNT(`ua`.`article_id`)
        FROM `users` AS `u`
        JOIN `users_articles` `ua`
        ON `u`.`id` = `ua`.`user_id`
        WHERE `u`.`username` = `username`
        GROUP BY `u`.`id`
    );
    RETURN `count`;
END; $$

DELIMITER $$
CREATE PROCEDURE `udp_like_article`(`username` VARCHAR(30), `title` VARCHAR(30))
BEGIN
	START TRANSACTION;
    IF ((SELECT COUNT(*) FROM `users` AS `u` WHERE `u`.`username` = `username`) <> 1) THEN
		SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Non-existent user.';
        ROLLBACK;
	ELSEIF ((SELECT COUNT(*) FROM `articles` AS `a` WHERE `a`.`title` = `title`) <> 1) THEN
		SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Non-existent article.';
        ROLLBACK;
	ELSE
		INSERT INTO `likes`(`article_id`, `user_id`)
        VALUES
        (
			(SELECT `a`.`id` FROM `articles` AS `a` WHERE `a`.`title` = `title`),
            (SELECT `u`.`id` FROM `users` AS `u` WHERE `u`.`username` = `username`)
        );
    END IF;
END; $$