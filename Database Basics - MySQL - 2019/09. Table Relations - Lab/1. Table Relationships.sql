CREATE DATABASE `table_relations`;
USE `table_relations`;

-- 1.One-To-One Relationship
CREATE TABLE `passports`(
	`passport_id` INT(11) PRIMARY KEY NOT NULL UNIQUE,
    `passport_number` VARCHAR(8) NOT NULL UNIQUE
);

INSERT INTO `passports`(`passport_id`, `passport_number`)
VALUES
	(101, 'N34FG21B'),
	(102, 'K65LO4R7'),
	(103, 'ZE657QP2');
    
CREATE TABLE `persons`(
	`person_id` INT(11) PRIMARY KEY NOT NULL UNIQUE AUTO_INCREMENT,
    `first_name` VARCHAR(45) NOT NULL,
    `salary` DECIMAL(7, 2) NOT NULL,
    `passport_id` INT(11) UNIQUE
);

INSERT INTO `persons`(`person_id`, `first_name`, `salary`, `passport_id`)
VALUES
	(1, 'Roberto', 43300.00, 102),
	(2, 'Tom', 56100.00, 103),
	(3, 'Yana', 60200.00, 101);
    
ALTER TABLE `persons`
ADD CONSTRAINT `fk_persons_passports` FOREIGN KEY (`passport_id`) REFERENCES `passports`(`passport_id`);

-- 2. One-To-Many Relationship
CREATE TABLE `manufacturers`(
	`manufacturer_id` INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(45) NOT NULL,
    `established_on` DATE
);

INSERT INTO `manufacturers`(`manufacturer_id`, `name`, `established_on`)
VALUES
	(1, 'BMW', '1916/03/01'),
	(2, 'Tesla', '2003/01/01'),
	(3, 'Lada', '1966/05/01');

CREATE TABLE `models`(
	`model_id` INT(11) PRIMARY KEY NOT NULL,
    `name` VARCHAR(45) NOT NULL,
    `manufacturer_id` INT(11) NOT NULL,
    CONSTRAINT `fk_models_manufacturers` FOREIGN KEY (`manufacturer_id`) REFERENCES `manufacturers`(`manufacturer_id`)
);

INSERT INTO `models`(`model_id`, `name`, `manufacturer_id`)
VALUES
	(101, 'X1', 1),
	(102, 'i6', 1),
	(103, 'Model S', 2),
	(104, 'Model X', 2),
	(105, 'Model 3', 2),
	(106, 'Nova', 3);

-- 3. Many-To-Many Relationship
CREATE TABLE `students`(
	`student_id` INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(45) NOT NULL
);

INSERT INTO `students`(`student_id`, `name`)
VALUES
	(1, 'Mila'),
	(2, 'Toni'),
	(3, 'Ron');

CREATE TABLE `exams`(
	`exam_id` INT(11) PRIMARY KEY NOT NULL,
    `name` VARCHAR(45) NOT NULL
);

INSERT INTO `exams`(`exam_id`, `name`)
VALUES
	(101, 'Spring MVC'),
	(102, 'Neo4j'),
	(103, 'Oracle 11g');

CREATE TABLE `students_exams`(
	`student_id` INT(11) NOT NULL,
    `exam_id` INT(11) NOT NULL,
    CONSTRAINT `pk_students_exams` PRIMARY KEY (`student_id`, `exam_id`),
    CONSTRAINT `fk_students_exams_students` FOREIGN KEY (`student_id`) REFERENCES `students`(`student_id`),
    CONSTRAINT `fk_students_exams_exams` FOREIGN KEY (`exam_id`) REFERENCES `exams`(`exam_id`)
);

INSERT INTO `students_exams`(`student_id`, `exam_id`)
VALUES
	(1, 101),
	(1, 102),
	(2, 101),
	(3, 103),
	(2, 102),
    (2, 103);
    
-- 4. Self-Referencing
CREATE TABLE `teachers`(
	`teacher_id` INT(11) PRIMARY KEY NOT NULL,
    `name` VARCHAR(45) NOT NULL,
    `manager_id` INT(11)
);

INSERT INTO `teachers`(`teacher_id`, `name`, `manager_id`)
VALUES
	(101, 'John', NULL),
	(102, 'Maya', 106),
	(103, 'Silvia', 106),
	(104, 'Ted', 105),
	(105, 'Mark', 101),
	(106, 'Greta', 101);
    
ALTER TABLE `teachers`
ADD CONSTRAINT `fk_teachers_teachers` FOREIGN KEY (`manager_id`) REFERENCES `teachers`(`teacher_id`);