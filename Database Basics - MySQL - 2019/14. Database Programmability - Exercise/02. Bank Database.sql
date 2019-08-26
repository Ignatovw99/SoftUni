-- PART II – Queries for Bank Database
USE `bank`;


-- 09. Find Full Name
-- You are given a database schema with tables:
--  account_holders(id (PK), first_name, last_name, ssn) 
--  accounts(id (PK), account_holder_id (FK), balance).
-- Write a stored procedure usp_get_holders_full_name that selects the
-- full names of all people. The result should be sorted by full_name 
-- alphabetically and id ascending.

DELIMITER $$
CREATE PROCEDURE usp_get_holders_full_name()
BEGIN
    SELECT 
        CONCAT_WS(' ', h.first_name, h.last_name) AS 'full_name'
    FROM
        `account_holders` AS h
            JOIN
        (SELECT DISTINCT
            a.account_holder_id
        FROM
            `accounts` AS a) as a ON h.id = a.account_holder_id
    ORDER BY `full_name`;
END $$
DELIMITER ;

CALL usp_get_holders_full_name();

DROP PROCEDURE IF EXISTS usp_get_holders_full_name;


-- 10. People with Balance Higher Than
-- Your task is to create a stored procedure usp_get_holders_with_balance_higher_than
-- that accepts a number as a parameter and returns all people who have more money in
-- total of all their accounts than the supplied number. 
-- The result should be sorted by first_name then by last_name alphabetically and 
-- account id ascending.

DELIMITER $$
CREATE PROCEDURE usp_get_holders_with_balance_higher_than(balance DECIMAL(19, 4))
BEGIN
    SELECT 
         h.first_name, h.last_name
    FROM
        `account_holders` AS h
            JOIN
        (SELECT 
            a.id, a.account_holder_id, SUM(a.balance) AS 'total_balance'
        FROM
            `accounts` AS a
        GROUP BY (a.account_holder_id)
        HAVING `total_balance` > balance) as a ON h.id = a.account_holder_id
    ORDER BY a.id;
END $$
DELIMITER ;

CALL usp_get_holders_with_balance_higher_than(7000);

DROP PROCEDURE IF EXISTS usp_get_holders_with_balance_higher_than;


-- 11. Future Value Function
-- Your task is to create a function ufn_calculate_future_value that accepts 
-- as parameters – sum, yearly interest rate and number of years. It should 
-- calculate and return the future value of the initial sum. Using the following formula:
-- FV=I*((1+R)^T)
--  I – Initial sum
--  R – Yearly interest rate
--  T – Number of years

DELIMITER $$
CREATE FUNCTION ufn_calculate_future_value(
    initial_sum DECIMAL(19, 4), interest_rate DECIMAL(19, 4), years INT)
RETURNS DECIMAL(19, 4)
-- RETURNS DOUBLE(19, 2) -- Judge
BEGIN
    RETURN initial_sum * POW((1 + interest_rate), years);
END $$
DELIMITER ;

SELECT ufn_calculate_future_value(1000, 0.1, 5); -- Expected result: 1610.51

DROP FUNCTION IF EXISTS ufn_calculate_future_value;


-- 12. Calculating Interest
-- Your task is to create a stored procedure usp_calculate_future_value_for_account
-- that uses the function from the previous problem to give an interest to a person's
-- account for 5 years, along with information about his/her account id, first name,
-- last name and current balance as it is shown in the example below. It should take
-- the account_id and the interest_rate as parameters. Interest rate should have
-- precision up to 0.0001, same as the calculated balance after 5 years. Be extremely
-- careful to achieve the desired precision!
/*
Here is the result for account_id = 1 and interest_rate = 0.1.
account_id  fist_name   last_name   current_balance balance_in_5_years
1           Susan       Cane        123.1200        198.2860
*/

DELIMITER $$
CREATE PROCEDURE usp_calculate_future_value_for_account(
    account_id INT, interest_rate DECIMAL(19, 4))
BEGIN
    SELECT 
         a.id AS 'account_id', h.first_name, h.last_name, a.balance AS 'current_balance',
         ufn_calculate_future_value(a.balance, interest_rate, 5) AS 'balance_in_5_years'
    FROM
        `account_holders` AS h
            JOIN
        `accounts` AS a ON h.id=a.account_holder_id
    WHERE a.id = account_id;
END $$
DELIMITER ;

CALL usp_calculate_future_value_for_account(1, 0.1);

DROP PROCEDURE IF EXISTS usp_calculate_future_value_for_account;


-- 13. Deposit Money
-- Add stored procedure usp_deposit_money(account_id, money_amount) that operate in
-- transactions. Make sure to guarantee valid positive money_amount with precision
-- up to fourth sign after decimal point. The procedure should produce exact results
-- working with the specified precision.
/*
Here is the result for account_id = 1 and money_amount = 10.
account_id  account_holder_id   balance
1           1                   133.1200
*/

DELIMITER $$
CREATE PROCEDURE usp_deposit_money(
    account_id INT, money_amount DECIMAL(19, 4))
BEGIN
    IF money_amount > 0 THEN
        START TRANSACTION;
        
        UPDATE `accounts` AS a 
        SET 
            a.balance = a.balance + money_amount
        WHERE
            a.id = account_id;
        
        IF (SELECT a.balance 
            FROM `accounts` AS a 
            WHERE a.id = account_id) < 0
            THEN ROLLBACK;
        ELSE
            COMMIT;
        END IF;
    END IF;
END $$
DELIMITER ;

CALL usp_deposit_money(1, 10);

SELECT 
    a.id AS 'account_id', a.account_holder_id, a.balance
FROM
    `accounts` AS a
WHERE
    a.id = 1;
            
DROP PROCEDURE IF EXISTS usp_deposit_money;


-- 14. Withdraw Money
-- Add stored procedures usp_withdraw_money(account_id, money_amount)
-- that operate in transactions. 
-- Make sure to guarantee withdraw is done only when balance is enough
-- and money_amount is valid positive number. Work with precision up
-- to fourth sign after decimal point. The procedure should produce exact
-- results working with the specified precision.
/*
Here is the result for account_id = 1 and money_amount = 10.
account_id  account_holder_id   balance
1           1                   113.1200
*/

DELIMITER $$
CREATE PROCEDURE usp_withdraw_money(
    account_id INT, money_amount DECIMAL(19, 4))
BEGIN
    IF money_amount > 0 THEN
        START TRANSACTION;
        
        UPDATE `accounts` AS a 
        SET 
            a.balance = a.balance - money_amount
        WHERE
            a.id = account_id;
        
        IF (SELECT a.balance 
            FROM `accounts` AS a 
            WHERE a.id = account_id) < 0
            THEN ROLLBACK;
        ELSE
            COMMIT;
        END IF;
    END IF;
END $$
DELIMITER ;

CALL usp_withdraw_money(1, 10);

SELECT 
    a.id AS 'account_id', a.account_holder_id, a.balance
FROM
    `accounts` AS a
WHERE
    a.id = 1;

DROP PROCEDURE IF EXISTS usp_withdraw_money;


-- 15. Money Transfer
-- Write stored procedure usp_transfer_money(from_account_id, to_account_id, amount)
-- that transfers money from one account to another. Consider cases when one of the
-- account_ids is not valid, the amount of money is negative number, outgoing balance
-- is enough or transferring from/to one and the same account. Make sure that the whole
-- procedure passes without errors and if error occurs make no change in the database. 
-- Make sure to guarantee exact results working with precision up to fourth sign after
-- decimal point.
/*
Here is the result for from_account_id = 1, to_account_id = 2 and money_amount = 10.
account_id  account_holder_id   balance
1           1                   113.1200
2           3                   4364.2300
*/

DELIMITER $$
CREATE PROCEDURE usp_transfer_money(
    from_account_id INT, to_account_id INT, money_amount DECIMAL(19, 4))
BEGIN
    IF money_amount > 0 
        AND from_account_id <> to_account_id 
        AND (SELECT a.id 
            FROM `accounts` AS a 
            WHERE a.id = to_account_id) IS NOT NULL
        AND (SELECT a.id 
            FROM `accounts` AS a 
            WHERE a.id = from_account_id) IS NOT NULL
        AND (SELECT a.balance 
            FROM `accounts` AS a 
            WHERE a.id = from_account_id) >= money_amount
    THEN
        START TRANSACTION;
        
        UPDATE `accounts` AS a 
        SET 
            a.balance = a.balance + money_amount
        WHERE
            a.id = to_account_id;
            
        UPDATE `accounts` AS a 
        SET 
            a.balance = a.balance - money_amount
        WHERE
            a.id = from_account_id;
        
        IF (SELECT a.balance 
            FROM `accounts` AS a 
            WHERE a.id = from_account_id) < 0
            THEN ROLLBACK;
        ELSE
            COMMIT;
        END IF;
    END IF;
END $$
DELIMITER ;

CALL usp_transfer_money(1, 2, 10);
CALL usp_transfer_money(2, 1, 10);

SELECT 
    a.id AS 'account_id', a.account_holder_id, a.balance
FROM
    `accounts` AS a
WHERE
    a.id IN (1 , 2);
            
DROP PROCEDURE IF EXISTS usp_transfer_money;


-- 16. Log Accounts Trigger
-- Create another table – logs(log_id, account_id, old_sum, new_sum). 
-- Add a trigger to the accounts table that enters a new entry into the logs
-- table every time the sum on an account changes.
/*
The following data in logs table is inserted after updating balance of
account with account_id = 1 with 10.
 
log_id  account_id  old_sum new_sum
1       1           123.12  113.12
2       1           145.43  155.43
*/

CREATE TABLE `logs` (
    log_id INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    account_id INT(11) NOT NULL,
    old_sum DECIMAL(19, 4) NOT NULL,
    new_sum DECIMAL(19, 4) NOT NULL
);

DELIMITER $$
CREATE TRIGGER `tr_balance_updated`
AFTER UPDATE ON `accounts`
FOR EACH ROW
BEGIN
    IF OLD.balance <> NEW.balance THEN
        INSERT INTO `logs` 
            (`account_id`, `old_sum`, `new_sum`)
        VALUES (OLD.id, OLD.balance, NEW.balance);
    END IF;
END $$
DELIMITER ;

CALL usp_transfer_money(1, 2, 10);
CALL usp_transfer_money(2, 1, 10);

SELECT * FROM `logs`;

DROP TRIGGER IF EXISTS `bank`.tr_balance_updated;
DROP TABLE IF EXISTS `logs`;


-- 17.	Emails Trigger
-- Create another table – notification_emails(id, recipient, subject, body).
-- Add a trigger to logs table to create new email whenever new record is inserted
-- in logs table. The following data is required to be filled for each email:
-- * recipient – account_id
-- * subject – “Balance change for account: {account_id}”
-- * body - “On {date (current date)} your balance was changed from {old} to {new}.”
/*	
id  recipient   subject                         body
1   1           Balance change for account: 1   On Sep 15 2016 at 11:44:06 AM your balance was changed from 133 to 143.
…   …           …                               …
*/ 

CREATE TABLE `notification_emails` (
    `id` INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `recipient` INT(11) NOT NULL,
    `subject` VARCHAR(50) NOT NULL,
    `body` VARCHAR(255) NOT NULL
);

DELIMITER $$
CREATE TRIGGER `tr_notification_emails`
AFTER INSERT ON `logs`
FOR EACH ROW
BEGIN
    INSERT INTO `notification_emails` 
        (`recipient`, `subject`, `body`)
    VALUES (
        NEW.account_id, 
        CONCAT('Balance change for account: ', NEW.account_id), 
        CONCAT('On ', DATE_FORMAT(NOW(), '%b %d %Y at %r'), ' your balance was changed from ', ROUND(NEW.old_sum, 2), ' to ', ROUND(NEW.new_sum, 2), '.'));
END $$
DELIMITER ;

SELECT * FROM `notification_emails`;

DROP TRIGGER IF EXISTS `bank`.tr_notification_emails;
DROP TABLE IF EXISTS `notification_emails`;