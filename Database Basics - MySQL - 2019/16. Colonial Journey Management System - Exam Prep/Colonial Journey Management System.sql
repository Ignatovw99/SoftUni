-- Colonial Journey Management System

CREATE DATABASE `colonial_journey_management_system_db`;
USE `colonial_journey_management_system_db`;

-- Section 01: Data Definition Language(DDL)

CREATE TABLE `planets`(
	`id` INT(11) PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(30) NOT NULL
);

CREATE TABLE `spaceports`(
	`id` INT(11) PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL,
    `planet_id` INT(11),
    CONSTRAINT `fk_spaceports_planets` FOREIGN KEY (`planet_id`) REFERENCES `planets`(`id`)
);

CREATE TABLE `spaceships`(
	`id` INT(11) PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL,
    `manufacturer` VARCHAR(30) NOT NULL,
    `light_speed_rate` INT(11) DEFAULT 0
);

CREATE TABLE `colonists`(
	`id` INT(11) PRIMARY KEY AUTO_INCREMENT,
    `first_name` VARCHAR(20) NOT NULL,
    `last_name` VARCHAR(20) NOT NULL,
    `ucn` CHAR(10) NOT NULL UNIQUE,
    `birth_date` DATE NOT NULL
);

CREATE TABLE `journeys`(
	`id` INT(11) PRIMARY KEY AUTO_INCREMENT,
    `journey_start` DATETIME NOT NULL,
    `journey_end` DATETIME NOT NULL,
    `purpose` ENUM('Medical', 'Technical', 'Educational', 'Military'),
    `destination_spaceport_id` INT(11),
    `spaceship_id` INT(11),
    CONSTRAINT `fk_journeys_spaceports` FOREIGN KEY (`destination_spaceport_id`) REFERENCES `spaceports`(`id`),
    CONSTRAINT `fk_journeys_spaceships` FOREIGN KEY (`spaceship_id`) REFERENCES `spaceships`(`id`)
);

CREATE TABLE `travel_cards`(
	`id` INT(11) PRIMARY KEY AUTO_INCREMENT,
    `card_number` CHAR(10) NOT NULL UNIQUE,
    `job_during_journey` ENUM('Pilot', 'Engineer', 'Trooper', 'Cleaner', 'Cook'),
    `colonist_id` INT(11),
    `journey_id` INT(11),
    CONSTRAINT `fk_travel_cards_colonists` FOREIGN KEY (`colonist_id`) REFERENCES `colonists`(`id`),
    CONSTRAINT `fk_travel_cards_journeys` FOREIGN KEY (`journey_id`) REFERENCES `journeys`(`id`)
);

-- Section 2 : Data Manipulation Language (DML)

-- 2. Data Insertion

INSERT INTO `travel_cards`(`card_number`, `job_during_journey`, `colonist_id`, `journey_id`)
	SELECT
    (
		CASE
			WHEN `c`.`birth_date` > '1980-01-01' THEN CONCAT(YEAR(`c`.`birth_date`), DAY(`c`.`birth_date`), SUBSTR(`c`.`ucn`, 1, 4))
            ELSE CONCAT(YEAR(`c`.`birth_date`), MONTH(`c`.`birth_date`), RIGHT(`c`.`ucn`, 4))
		END
    ) AS `card_number`,
    (
		CASE
			WHEN `c`.`id` % 2 = 0 THEN 'Pilot'
            WHEN `c`.`id` % 3 = 0 THEN 'Cook' 
			ELSE 'Engineer'
		END
	) AS `job_during_journey`,
    `c`.`id`,
    (
		LEFT(`c`.`ucn`, 1)
    ) AS `journey_id`
    FROM `colonists` AS `c`
    WHERE `c`.`id` BETWEEN 96 AND 100;
	
-- 03. Update Data

UPDATE `journeys`
SET `purpose` = 
(
	CASE
		WHEN `id` % 2 = 0 THEN 'Medical'
        WHEN `id` % 3 = 0 THEN 'Technical'
        WHEN `id` % 5 = 0 THEN 'Educational'
        WHEN `id` % 7 = 0 THEN 'Military'
  		ELSE `purpose`
	END
);

-- Data Deletion

DELETE FROM `colonists`
WHERE `id` NOT IN (
	SELECT `tc`.`colonist_id`
	FROM `travel_cards` AS `tc`
);


-- Section 3 : Querying

-- 5. Extract all military journeys

SELECT `id`, `journey_start`, `journey_end`
FROM `journeys`
WHERE `purpose` = 'Military'
ORDER BY `journey_start`;

-- 6. Extract the fastest spaceship

SELECT `ss`.`name`, `sp`.`name`
FROM `spaceships` AS `ss`
JOIN `journeys` AS `j`
	ON `ss`.`id` = `j`.`spaceship_id`
JOIN `spaceports` AS `sp`
	ON `j`.`destination_spaceport_id` = `sp`.`id`
ORDER BY `ss`.`light_speed_rate` DESC
LIMIT 1;

-- 7. Extract spaceships with pilots younger than 30 years

SELECT `s`.`name`, `s`.`manufacturer`
FROM `spaceships` AS `s`
JOIN `journeys` AS `j`
	ON `s`.`id` = `j`.`spaceship_id`
JOIN `travel_cards` AS `tc`
	ON `j`.`id` = `tc`.`journey_id`
JOIN `colonists` AS `c`
	ON `tc`.`colonist_id` = `c`.`id`
WHERE `tc`.`job_during_journey` = 'Pilot' AND TIMESTAMPDIFF(YEAR, `c`.`birth_date`, '2019-01-01') < 30 --NOW()
ORDER BY `s`.`name`;

-- 08. Extract all educational mission planets and spaceports

SELECT 
	`p`.`name` AS 'planet_name',
    `s`.`name` AS `spaceport_name`
FROM `planets` AS `p`
JOIN `spaceports` AS `s`
	ON `p`.`id` = `s`.`planet_id`
JOIN `journeys` AS `j`
	ON `s`.`id` = `j`.`destination_spaceport_id`
WHERE `j`.`purpose` = 'Educational'
ORDER BY `spaceport_name` DESC;

-- 09. Extract all planets and their journey count

SELECT 
	`p`.`name` AS `planet_name`,
    COUNT(`j`.`id`) AS `journeys_count`
FROM `planets` AS `p`
JOIN `spaceports` AS `s`
	ON `p`.`id` = `s`.`planet_id`
JOIN `journeys` AS `j`
	ON `s`.`id` = `j`.`destination_spaceport_id`
GROUP BY `planet_name`
ORDER BY `journeys_count` DESC, `planet_name`;


-- 10.Extract the less popular job

SELECT 
	`tc`.`job_during_journey` AS 'job_name'
FROM `travel_cards` AS `tc`
WHERE `tc`.`journey_id` = 
(
	SELECT `id`
    FROM `journeys`
    GROUP BY `id`
    ORDER BY MAX(TIMESTAMPDIFF(HOUR, `journey_start`, `journey_end`)) DESC
    LIMIT 1
)
GROUP BY `tc`.`job_during_journey`
ORDER BY COUNT(`tc`.`job_during_journey`)
LIMIT 1;

-- Section 4 : Programmability

-- 11. Get colonists count

DELIMITER $$
CREATE FUNCTION `udf_count_colonists_by_destination_planet`(`planet_name` VARCHAR(30))
RETURNS INT(11)
BEGIN
	DECLARE `count` INT(11);
    SET `count` := (SELECT COUNT(`tc`.`id`)
		FROM `planets` AS `p`
		JOIN `spaceports` AS `s`
			ON `p`.`id` = `s`.`planet_id`
		JOIN `journeys` AS `j`
			ON `s`.`id` = `j`.`destination_spaceport_id`
		JOIN `travel_cards` AS `tc`
			ON `j`.`id` = `tc`.`journey_id`
		WHERE `p`.`name` = `planet_name`);
    RETURN `count`;
END; $$

SELECT p.name, udf_count_colonists_by_destination_planet('Lescore') AS count
FROM planets AS p
WHERE p.name = 'Otroyphus';

-- 12. Modify spaceship

DELIMITER $$
CREATE PROCEDURE `udp_modify_spaceship_light_speed_rate`(`spaceship_name` VARCHAR(50), `light_speed_rate_increase` INT(11))
BEGIN
	START TRANSACTION;
	IF (SELECT COUNT(`name`) FROM `spaceships` WHERE `name` = `spaceship_name`) <> 1 THEN
		SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Spaceship you are trying to modify does not exist.';
		ROLLBACK;
	ELSE
		UPDATE `spaceships`
        SET `light_speed_rate` = `light_speed_rate` + `light_speed_rate_increase`
        WHERE `name` = `spaceship_name`;
	END IF;
    COMMIT;
END; $$