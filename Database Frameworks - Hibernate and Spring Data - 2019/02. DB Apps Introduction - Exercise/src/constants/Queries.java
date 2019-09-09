package constants;

public class Queries {
    public static final String SELECT_VILLAINS_WITH_MORE_THAN_15_MINIONS =
            "SELECT `v`.`name`, COUNT(*) AS `count` FROM `villains` AS `v` JOIN `minions_villains` AS `mv` ON `v`.`id` = `mv`.`villain_id` GROUP BY `v`.id HAVING `count` > 15 ORDER BY `count` DESC";

    public static final String SELECT_VILLAIN_BY_ID =
            "SELECT * FROM `villains` WHERE `id` = ?";

    public static final String SELECT_ALL_MINIONS_FOR_A_VILLAIN =
            "SELECT `m`.`name`, `m`.`age` FROM `minions` AS `m` JOIN minions_villains `mv` ON `m`.`id` = `mv`.`minion_id` WHERE `mv`.`villain_id` = ?";

    public static final String INSERT_NEW_TOWN =
            "INSERT INTO `towns`(`name`, `country`) VALUES (?, 'Unknown')";

    public static final String SELECT_TOWN_BY_NAME =
            "SELECT `id` FROM `towns` WHERE `name` = ?";

    public static final String INSERT_NEW_VILLAIN =
            "INSERT INTO `villains`(`name`, `evilness_factor`) VALUES (?, 'evil')";

    public static final String SELECT_VILLAIN_BY_NAME =
            "SELECT `id` FROM `villains` WHERE `name` = ?";

    public static final String INSERT_NEW_MINION =
            "INSERT INTO `minions`(`name`, `age`, `town_id`) VALUES(?, ?, ?)";

    public static final String SELECT_MINION_ID_BY_NAME =
            "SELECT `id` FROM `minions` WHERE `name` = ?";

    public static final String INSERT_MINION_TO_VILLAIN =
            "INSERT INTO `minions_villains`(`minion_id`, `villain_id`) VALUES (?, ?)";

    public static final String UPDATE_TOWN_NAMES_CASING_TO_UPPER =
            "UPDATE `towns` SET `name` = UPPER(`name`) WHERE `country` = ?";

    public static final String SELECT_AFFECTED_TOWN_NAMES =
            "SELECT `name` FROM `towns` WHERE `name` = UPPER(`name`) AND `country` = ?";

    public static final String SELECT_MINIONS_COUNT_FOR_VILLAIN =
            "SELECT COUNT(*) AS `count` FROM `minions_villains` WHERE `villain_id` = ? GROUP BY `villain_id`";

    public static final String DELETE_RELATION_BETWEEN_VILLAIN_AND_MINIONS =
            "DELETE FROM `minions_villains` WHERE `villain_id` = ?";

    public static final String DELETE_VILLAIN_BY_ID = "DELETE FROM `villains` WHERE `id` = ?";

    public static final String SELECT_ALL_MINION_NAMES = "SELECT `name` FROM `minions`";

    public static final String INCREASE_MINION_AGE_AND_MODIFY_NAME =
            "UPDATE `minions` SET `age` = `age` + 1, `name` = LOWER(`name`) WHERE `id` = ?";

    public static final String SELECT_ALL_MINIONS_NAMES_AND_AGE =
            "SELECT `name`, `age` FROM `minions`";

    public static final String INCREMENT_MINION_AGE_WITH_STORED_PROCEDURE =
            "CALL `usp_get_older`(?)";

    public static final String SELECT_MINION_NAME_AND_AGE_BY_ID =
            "SELECT `name`, `age` FROM `minions` WHERE `id` = ?";
}
