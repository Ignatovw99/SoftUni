CREATE DATABASE `buhtig`;
USE `buhtig`;
-- 01. Data Definition Langauge

CREATE TABLE `users`(
	`id` INT(11) PRIMARY KEY AUTO_INCREMENT,
    `username` VARCHAR(30) NOT NULL UNIQUE,
    `password` VARCHAR(30) NOT NULL,
    `email` VARCHAR(50) NOT NULL
);

CREATE TABLE `repositories`(
	`id` INT(11) PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL
);

CREATE TABLE `repositories_contributors`(
	`repository_id` INT(11) ,
    `contributor_id` INT(11),
    CONSTRAINT `fk_repositories_contributors_repositories` FOREIGN KEY(`repository_id`) REFERENCES `repositories`(`id`),
    CONSTRAINT `fk_repositories_contributors_contributors` FOREIGN KEY(`contributor_id`) REFERENCES `users`(`id`)
);

CREATE TABLE `issues`(
	`id` INT(11) PRIMARY KEY AUTO_INCREMENT,
    `title` VARCHAR(255) NOT NULL,
    `issue_status` VARCHAR(6) NOT NULL,
    `repository_id` INT(11) NOT NULL,
    `assignee_id` INT(11) NOT NULL,
    CONSTRAINT `fk_issues_repositories` FOREIGN KEY(`repository_id`) REFERENCES `repositories`(`id`),
    CONSTRAINT `fk_issues_users` FOREIGN KEY(`assignee_id`) REFERENCES `users`(`id`)
);

CREATE TABLE `commits`(
	`id` INT(11) PRIMARY KEY AUTO_INCREMENT,
    `message` VARCHAR(255) NOT NULL,
    `issue_id` INT(11),
    `repository_id` INT(11) NOT NULL,
    `contributor_id` INT(11) NOT NULL,
    CONSTRAINT `fk_commits_issues` FOREIGN KEY(`issue_id`) REFERENCES `issues`(`id`),
    CONSTRAINT `fk_commits_repositories` FOREIGN KEY(`repository_id`) REFERENCES `repositories`(`id`),
    CONSTRAINT `fk_commits_users` FOREIGN KEY(`contributor_id`) REFERENCES `users`(`id`)
);

CREATE TABLE `files`(
	`id` INT(11) PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(100) NOT NULL,
    `size` DECIMAL(10, 2) NOT NULL,
    `parent_id` INT(11),
    `commit_id` INT(11) NOT NULL,
    CONSTRAINT `fk_files_self_reference` FOREIGN KEY(`parent_id`) REFERENCES `files`(`id`),
    CONSTRAINT `fk_files_commits` FOREIGN KEY(`commit_id`) REFERENCES `commits`(`id`)
);

-- 02. Data Manipulation Language

INSERT INTO `issues`
	(`title`, `issue_status`, `repository_id`, `assignee_id`)
(    
	SELECT 
		CONCAT('Critical Problem With ', `file`.`name`, '!'),
        'open',
        CEILING(`file`.`id` * 2 / 3),
        (
			SELECT `u`.`id`
            FROM `files` AS `f`
            JOIN `commits` AS `c`
				ON `f`.`commit_id` = `c`.`id`
			JOIN `users` AS `u`
				ON `c`.`contributor_id` = `u`.`id`
			WHERE `file`.`id` = `f`.`id`
        )
    FROM `files` AS `file`
    WHERE `file`.`id` BETWEEN 46 AND 50
);

UPDATE `repositories_contributors` AS `rc`
	JOIN
    (
		SELECT `r`.`id` AS `id`
        FROM `repositories` AS `r`
        LEFT JOIN `repositories_contributors` AS `rc2`
			ON `r`.`id` = `rc2`.`repository_id`
		WHERE `rc2`.`repository_id` IS NULL
        ORDER BY `r`.`id`
        LIMIT 1
    ) AS `repo_min`
SET `rc`.`repository_id` = `repo_min`.`id`
WHERE `rc`.`repository_id` = `rc`.`contributor_id`;
        
DELETE `r` FROM `repositories` AS `r`
	LEFT JOIN `issues` AS `i`
		ON `r`.`id` = `i`.`repository_id`
	WHERE `i`.`id` IS NULL;
    
-- 03. Quering

SELECT `id`, `username`
FROM `users`
ORDER BY `id`;

SELECT `rc`.`repository_id`, `rc`.`contributor_id`
FROM `users` AS `u`
JOIN `repositories_contributors` AS `rc`
	ON `u`.`id` = `rc`.`contributor_id`
JOIN `repositories` AS `r`
	ON `rc`.`repository_id` = `r`.`id`
WHERE `u`.`id` = `r`.`id`
ORDER BY `r`.`id`;

SELECT `id`, `name`, `size`
FROM `files`
WHERE `size` > 1000 AND `name` LIKE '%.html'
ORDER BY `size` DESC;

SELECT `i`.`id`, CONCAT(`u`.`username`, ' : ', `i`.`title`)
FROM `issues` AS `i`
JOIN `users` AS `u`
	ON `i`.`assignee_id` = `u`.`id`
ORDER BY `i`.`id` DESC;

SELECT `f`.`id`, `f`.`name`, CONCAT(`f`.`size`, 'KB') AS 'size'
FROM `files` AS `f`
LEFT JOIN `files` AS `p`
	ON `f`.`id` = `p`.`parent_id`
WHERE `p`.`id` IS NULL
ORDER BY `f`.`id` ASC;

SELECT 
	`r`.`id`,
    `r`.`name`,
    COUNT(`i`.`id`) AS `count`
FROM `repositories` AS `r`
JOIN `issues` AS `i` 
	ON `r`.`id` = `i`.`repository_id`
GROUP BY `r`.`id`
ORDER BY `count` DESC, `r`.`id`
LIMIT 5;

SELECT 
	`r`.`id`,
    `r`.`name`,
    (
		SELECT COUNT(`c`.`id`)
        FROM `commits` AS `c`
        JOIN `repositories` AS `r1`
			ON `c`.`repository_id` = `r1`.`id`
		WHERE `r1`.`id` = `r`.`id`
    ) AS `commits`,
    COUNT(`rc`.`contributor_id`) AS `contributors`
FROM `repositories` AS `r`
JOIN `repositories_contributors` AS `rc`
	ON `r`.`id` = `rc`.`repository_id`
GROUP BY `r`.`id`
ORDER BY `contributors` DESC, `r`.`id` ASC
LIMIT 1;

SELECT 
	`id`,
    `username`,
    (
		SELECT COUNT(`c`.`id`)
		FROM `users` AS `u1`
		JOIN `commits` AS `c`
			ON `u1`.`id` = `c`.`contributor_id`
		JOIN `issues` AS `i`
			ON `u1`.`id` = `i`.`assignee_id` AND `c`.`issue_id` = `i`.`id`
		WHERE `u1`.`id` = `u`.`id`
    ) AS `commits`
FROM `users` AS `u`
ORDER BY `commits` DESC, `u`.`id`;

SELECT 
    SUBSTRING_INDEX(`f`.`name`, '.', 1) AS 'file',
    COUNT(nullif(LOCATE(`f`.`name`, `c`.`message`), 0)) AS 'recursive_count'
FROM
    `files` AS `f`
        JOIN
    `files` AS `p` ON `f`.`parent_id` = `p`.`id`
        JOIN
    `commits` AS `c`
WHERE
    `f`.`id` <> `p`.`id`
    AND `f`.`parent_id` = `p`.`id`
    AND `p`.`parent_id` = `f`.`id`
GROUP BY `f`.`name`
ORDER BY `f`.`name`;

SELECT 
	`r`.`id`,
    `r`.`name`,
    CASE
		WHEN COUNT(DISTINCT `u`.`id`) > 0 THEN COUNT(DISTINCT `u`.`id`)
        ELSE 0
	END AS 'users'
FROM `repositories` AS `r`
LEFT JOIN `commits` AS `c`
	ON `r`.`id` = `c`.`repository_id`
LEFT JOIN `users` AS `u`
	ON `c`.`contributor_id` = `u`.`id`
GROUP BY `r`.`id`
ORDER BY `users` DESC, `r`.`id` ASC;

-- 04. Programmability

DELIMITER $$ -- Add a transaction
CREATE PROCEDURE `udp_commit`(`username` VARCHAR(30), `password` VARCHAR(30), `message` VARCHAR(255), `issue_id` INT(11))
BEGIN
	IF ((SELECT COUNT(*) FROM `users` AS `u` WHERE `u`.`username` = `username`) = 0) THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'No such user!';
	ELSEIF ((SELECT `u`.`password` FROM `users` AS `u` WHERE `u`.`username` = `username`) <> `password`) THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'Password is incorect!';
	ELSEIF ((SELECT COUNT(*) FROM `issues` AS `i` WHERE `i`.`id` = `issue_id`) = 0) THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'The issue does not exist!';
	ELSE 
		INSERT INTO `commits`(`message`, `issue_id`, `repository_id`, `contributor_id`)
        VALUES
			(
				`message`,
                `issue_id`,
                (
					SELECT `r`.`id`
                    FROM `repositories` AS `r`
                    JOIN `issues` AS `i`
						ON `r`.`id` = `i`.`repository_id`
					WHERE `i`.`id` = `issue_id`
                ),
                (
					SELECT `u`.`id`
                    FROM `users` AS `u`
                    WHERE `u`.`username` = `username`
                )
            );
		
        UPDATE `issues` AS `i`
        SET `i`.`issue_status` = 'closed'
        WHERE `i`.`id` = `issue_id`;
	END IF;
END; $$

DELIMITER $$
CREATE PROCEDURE `udp_findbyextension`(`extension` VARCHAR(100))
BEGIN
	SELECT 
		`id`,
        `name` AS 'caption',
		CONCAT(`size`, 'KB') AS 'user' 
    FROM `files`
    WHERE SUBSTRING_INDEX(`name`, '.', -1) = `extension`
    ORDER BY `id`;
END; $$


call `udp_findbyextension`('html');