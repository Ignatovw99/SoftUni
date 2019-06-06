USE `gringotts`;

/*1. Records’ Count
Import the database and send the total count of records to Mr. Bodrog. Make sure nothing got lost.*/

SELECT COUNT(*) AS 'Count'
FROM `wizzard_deposits`;

/*2. Longest Magic Wand
Select the size of the longest magic wand. Rename the new column appropriately.*/

SELECT MAX(`magic_wand_size`) AS 'longest_magic_wand'
FROM `wizzard_deposits`;

/*3. Longest Magic Wand per Deposit Groups
For wizards in each deposit group show the longest magic wand.
Sort result by longest magic wand for each deposit group in increasing order,
then by deposit_group alphabetically. Rename the new column appropriately.*/

SELECT `deposit_group`, MAX(`magic_wand_size`) AS 'longest_magic_wand'
FROM `wizzard_deposits`
GROUP BY `deposit_group`
ORDER BY `longest_magic_wand`, `deposit_group`;

/*4. Smallest Deposit Group per Magic Wand Size*
Select the deposit group with the lowest average wand size.*/

SELECT `deposit_group`
FROM `wizzard_deposits`
GROUP BY `deposit_group`
ORDER BY AVG(`magic_wand_size`)
LIMIT 1;

/*5. Deposits Sum
Select all deposit groups and its total deposit sum. Sort result by total_sum in increasing order.*/

SELECT `deposit_group`, SUM(`deposit_amount`) AS 'total_sum'
FROM `wizzard_deposits`
GROUP BY `deposit_group`
ORDER BY `total_sum`;

/*6. Deposits Sum for Ollivander family
Select all deposit groups and its total deposit sum but only for the wizards
who has their magic wand crafted by Ollivander family. Sort result by deposit_group alphabetically.*/

SELECT `deposit_group`, SUM(`deposit_amount`) AS 'total_sum'
FROM `wizzard_deposits`
WHERE `magic_wand_creator` = 'Ollivander family'
GROUP BY `deposit_group`
ORDER BY `deposit_group`;

/*7. Deposits Filter
Select all deposit groups and its total deposit sum but only for the wizards
who has their magic wand crafted by Ollivander family.
After this, filter total deposit sums lower than 150000.
Order by total deposit sum in descending order.*/

SELECT `deposit_group`, SUM(`deposit_amount`) AS 'total_sum'
FROM `wizzard_deposits`
WHERE `magic_wand_creator` = 'Ollivander family'
GROUP BY `deposit_group`
HAVING `total_sum` < 150000
ORDER BY `total_sum` DESC;

/*8. Deposit charge
Create a query that selects:
• Deposit group
• Magic wand creator
• Minimum deposit charge for each group
Group by deposit_group and magic_wand_creator.
Select the data in ascending order by magic_wand_creator and deposit_group.*/

SELECT 
	`deposit_group`,
    `magic_wand_creator`,
    MIN(`deposit_charge`) AS 'min_deposit_charge'
FROM `wizzard_deposits`
GROUP BY `deposit_group`, `magic_wand_creator`
ORDER BY `magic_wand_creator`, `deposit_group`;

/*Write down a query that creates 7 different groups based on their age.
Age groups should be as follows:
• [0-10], [11-20], [21-30], [31-40], [41-50], [51-60], [61+]
The query should return:
• Age groups and count of wizards in it
Sort result by increasing size of age groups.*/

SELECT
	CASE
		WHEN `age` BETWEEN 0 AND 10 THEN '[0-10]'
        WHEN `age` BETWEEN 11 AND 20 THEN '[11-20]'
		WHEN `age` BETWEEN 21 AND 30 THEN '[21-30]'
        WHEN `age` BETWEEN 31 AND 40 THEN '[31-40]'
        WHEN `age` BETWEEN 41 AND 50 THEN '[41-50]'
        WHEN `age` BETWEEN 51 AND 60 THEN '[51-60]'
        WHEN `age` > 60 THEN '[61+]'
	END AS 'age_group',
    COUNT(*) AS 'wizard_count'
FROM `wizzard_deposits`
GROUP BY `age_group`
ORDER BY `wizard_count`;

/*10. First Letter
Write a query that returns all unique wizard first letters of their first names
only if they have deposit of type Troll Chest. Order them alphabetically.
Use GROUP BY for uniqueness.*/

SELECT LEFT(`first_name`, 1) AS 'first_letter'
FROM `wizzard_deposits`
WHERE `deposit_group` = 'Troll Chest'
GROUP BY `first_letter`
ORDER BY `first_letter`;

/*11. Average Interest
Mr. Bodrog is highly interested in profitability. He wants to know the average interest of all deposits groups split by
whether the deposit has expired or not. But that’s not all. He wants you to select deposits with start date after
01/01/1985. Order the data descending by Deposit Group and ascending by Expiration Flag.*/

SELECT
	`deposit_group`,
    `is_deposit_expired`,
    AVG(`deposit_interest`) AS 'Average Interest'
FROM `wizzard_deposits`
WHERE `deposit_start_date` > '1985/01/01'
GROUP BY `deposit_group`, `is_deposit_expired`
ORDER BY `deposit_group` DESC, `is_deposit_expired`;

/*12. Rich Wizard, Poor Wizard*
Give Mr. Bodrog some data to play his favorite game Rich Wizard, Poor Wizard. The rules are simple: You compare
the deposits of every wizard with the wizard after him. If a wizard is the last one in the database, simply ignore it.
At the end you have to sum the difference between the deposits.*/

SELECT SUM(`all_differences`.`two_deposits_diff`) AS 'sum_difference'
FROM (
	SELECT `deposit_amount` - 
		(
			SELECT `deposit_amount`
			FROM `wizzard_deposits` AS `wd2`
			WHERE `wd2`.`id` = `wd1`.`id` + 1
		) AS `two_deposits_diff`
	FROM `wizzard_deposits` AS `wd1`) AS `all_differences`;