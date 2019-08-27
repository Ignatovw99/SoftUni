CREATE DATABASE IF NOT EXISTS `instagraph_db`;
USE `instagraph_db`;

-- Data Definition Language(DDL)

CREATE TABLE `pictures`(
	`id` INT(11) PRIMARY KEY AUTO_INCREMENT,
    `path` VARCHAR(255) NOT NULL,
    `size` DECIMAL(10, 2) NOT NULL
);

CREATE TABLE `users`(
	`id` INT(11) PRIMARY KEY AUTO_INCREMENT,
    `username` VARCHAR(30) NOT NULL UNIQUE,
    `password` VARCHAR(30) NOT NULL,
    `profile_picture_id` INT(11),
    CONSTRAINT `fk_users_pictures` FOREIGN KEY (`profile_picture_id`) REFERENCES `pictures`(`id`)
);

CREATE TABLE `posts`(
	`id` INT(11) PRIMARY KEY AUTO_INCREMENT,
    `caption` VARCHAR(255) NOT NULL,
    `user_id` INT(11) NOT NULL,
    `picture_id` INT(11) NOT NULL,
    CONSTRAINT `fk_posts_users` FOREIGN KEY (`user_id`) REFERENCES `users`(`id`),
	CONSTRAINT `fk_posts_pictures` FOREIGN KEY (`picture_id`) REFERENCES `pictures`(`id`)
);

CREATE TABLE `comments`(
	`id` INT(11) PRIMARY KEY AUTO_INCREMENT,
    `content` VARCHAR(255) NOT NULL,
    `user_id` INT(11) NOT NULL,
    `post_id` INT(11) NOT NULL,
    CONSTRAINT `fk_comments_users` FOREIGN KEY (`user_id`) REFERENCES `users`(`id`),
    CONSTRAINT `fk_comments_posts` FOREIGN KEY (`post_id`) REFERENCES `posts`(`id`)
);

CREATE TABLE `users_followers`(
	`user_id` INT(11),
    `follower_id` INT(11),
    CONSTRAINT `fk_users_followers_users` FOREIGN KEY (`user_id`) REFERENCES `users`(`id`),
    CONSTRAINT `fk_users_followers_followers` FOREIGN KEY (`follower_id`) REFERENCES `users`(`id`)
);

-- 02. Data Manipulation Language

INSERT INTO `comments`(`content`, `user_id`, `post_id`)
(
	SELECT 
		(
			SELECT CONCAT('Omg!', `u`.`username`, '!This is so cool!')
            FROM `users` AS `u`
            WHERE `p`.`user_id` = `u`.`id`
        ),
        CEILING(`p`.`id` * 3 / 2),
        `p`.`id`
    FROM `posts` AS `p`
    WHERE `p`.`id` BETWEEN 1 AND 10
);

UPDATE `users` AS `u`
JOIN
	(
		SELECT `uf`.`user_id` AS `id`, COUNT(`uf`.`follower_id`) AS `followers`
        FROM `users_followers` AS `uf`
        GROUP BY `uf`.`user_id`
    ) AS `c`
	ON `u`.`id` = `c`.`id`
SET `u`.`profile_picture_id` = IF (`c`.`followers` > 0, `c`.`followers`, 0)
WHERE `u`.`profile_picture_id` IS NULL;

DELETE `u` FROM `users` AS `u`
LEFT JOIN `users_followers` AS `uf`
	ON `u`.`id` = `uf`.`user_id`
WHERE `uf`.`user_id` IS NULL AND `uf`.`follower_id` IS NULL;

-- 03. Querring

SELECT `id`, `username`
FROM `users`
ORDER BY `id`;

SELECT `u`.`id`, `u`.`username`
FROM `users` AS `u`
JOIN `users_followers` AS `f`
	ON `u`.`id` = `f`.`user_id`
WHERE `user_id` = `follower_id`
ORDER BY `u`.`id`;

SELECT *
FROM `pictures`
WHERE `size` > 50000 AND (RIGHT(`path`, 4) = 'jpeg' OR RIGHT(`path`, 3) = 'png')
ORDER BY `size` DESC;

SELECT `c`.`id`, CONCAT(`u`.`username`, ' : ' , `c`.`content`) AS 'full_comment'
FROM `comments` AS `c`
JOIN `users` AS `u`
	ON `c`.`user_id` = `u`.`id`
ORDER BY `c`.`id` DESC;

SELECT DISTINCT `u1`.`id`, `u1`.`username`, CONCAT(`p`.`size`, 'KB') AS 'size'
FROM `users` AS `u1`
JOIN `users` AS `u2`
	ON `u1`.`profile_picture_id` = `u2`.`profile_picture_id`
JOIN `pictures` AS `p`
	ON `u1`.`profile_picture_id` = `p`.`id`
WHERE `u1`.`id` != `u2`.`id`
ORDER BY `u1`.`id`;

SELECT `p`.`id`, `p`.`caption`, COUNT(`c`.`id`) AS `comments`
FROM `posts` AS `p`
LEFT JOIN `comments` AS `c`
	ON `p`.`id` = `c`.`post_id`
GROUP BY `p`.`id`
ORDER BY `comments` DESC, `p`.`id` ASC
LIMIT 5;

SELECT 
	`u`.`id`,
	`u`.`username`,
    (
		SELECT COUNT(`p2`.`id`)
        FROM `users` AS `u2`
        JOIN `posts` AS `p2`
			ON `u2`.`id` = `p2`.`user_id`
		WHERE `u2`.`id` = `u`.`id`
		GROUP BY `u2`.`id`
    ) AS 'posts', 
    COUNT(`f`.`follower_id`) AS `followers`
FROM `users` AS `u`
JOIN `users_followers` AS `f`
	ON `u`.`id` = `f`.`user_id`
GROUP BY `u`.`id`
ORDER BY `followers` DESC LIMIT 1;

SELECT `u`.`id`, `u`.`username`, COUNT(`c`.`id`) AS `my_comments`
FROM `users` AS `u`
LEFT JOIN `posts` AS `p`
	ON `u`.`id` = `p`.`user_id`
LEFT JOIN `comments` AS `c`
	ON `p`.`id` = `c`.`post_id` AND `u`.`id` = `c`.`user_id`
GROUP BY `u`.`id`
ORDER BY `my_comments` DESC, `u`.`id` ASC;

SELECT
	`u`.`id`,
    `u`.`username`,
    (
		SELECT `p`.`caption`
        FROM `posts` AS `p`
        LEFT JOIN `comments` AS `c`
			ON `p`.`id` = `c`.`post_id`
		WHERE `p`.`user_id` = `u`.`id`
        GROUP BY `p`.`id`
        ORDER BY COUNT(`c`.`id`) DESC, `p`.`id` ASC
        LIMIT 1
    ) AS `post`
FROM `users` AS `u`
GROUP BY `u`.`id`
HAVING `post` IS NOT NULL
ORDER BY `u`.`id`;

SELECT `p`.`id`, `p`.`caption`, COUNT(DISTINCT `u`.`id`) AS `users`
FROM `posts` AS `p`
LEFT JOIN `comments` AS `c`
	ON `p`.`id` = `c`.`post_id`
LEFT JOIN `users` AS `u`
	ON `c`.`user_id` = `u`.`id`
GROUP BY `p`.`id`
ORDER BY `users` DESC, `p`.`id` ASC;

-- 04. Programmability

DELIMITER $$
CREATE PROCEDURE `udp_post`(`username` VARCHAR(30), `password` VARCHAR(30), `caption` VARCHAR(255), `path` VARCHAR(255))
BEGIN
	IF ((SELECT `u`.`password` FROM `users` AS `u` WHERE `u`.`username` = `username`) <> `password`) THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'Password is incorrect!';
	ELSEIF ((SELECT COUNT(*) FROM `pictures` AS `p` WHERE `p`.`path` = `path`) = 0) THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'The picture does not exist!';
	ELSE
		INSERT INTO `posts`(`caption`, `user_id`, `picture_id`)
        VALUES
			(
				`caption`,
                (
					SELECT `u`.`id`
                    FROM `users` AS `u`
                    WHERE `u`.`username` = `username`
                ),
                (
					SELECT `p`.`id`
                    FROM `pictures` AS `p`
                    WHERE `p`.`path` = `path`
                )
            );
	END IF;
END; $$

DELIMITER $$
CREATE PROCEDURE `udp_filter`(`hashtag` VARCHAR(200))
BEGIN
	SELECT `p`.`id`, `p`.`caption`, `u`.`username`
    FROM `posts` AS `p`
    JOIN `users` AS `u`
		ON `p`.`user_id` = `u`.`id`
	WHERE `p`.`catrion` LIKE CONCAT('%', `hashtag`, '%');
END; $$