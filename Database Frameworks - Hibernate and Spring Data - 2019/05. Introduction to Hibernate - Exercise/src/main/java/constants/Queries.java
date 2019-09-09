package constants;

public class Queries {
    public static final String RETRIEVE_ALL_TOWNS = "FROM Town";

    public static final String RETRIEVE_EMPLOYEES_BY_DEPARTMENT =
            "FROM Employee WHERE department.name = :departmentName ORDER BY salary, id";

    public static final String RETRIEVE_TOWN_BY_NAME = "FROM Town WHERE name = :townName";

    public static final String RETRIEVE_ADDRESSES_ORDER_BY_EMPLOYEE_COUNT = "FROM Address ORDER BY employees.size DESC, town.id";

    public static final String RETRIEVE_ADDRESS_BY_NAME = "FROM Address WHERE text = :addressText";

    public static final String RETRIEVE_EMPLOYEE_BY_ID = "FROM Employee WHERE id = :employeeId";

    public static final String RETRIEVE_PROJECTS_ORDER_DESC_BY_START_DATE = "FROM Project ORDER BY startDate DESC";

    public static final String RETRIEVE_EMPLOYEES_BY_MANY_DEPARTMENTS =
            "FROM Employee WHERE department.name IN(:firstDepartment, :secondDepartment, :thirdDepartment, :fourthDepartment)";

    public static final String RETRIEVE_EMPLOYEE_BY_FIRST_NAME_PATTERN = "FROM Employee WHERE LOWER(firstName) LIKE :pattern";

    public static final String RETRIEVE_MAX_SALARY_FOR_DEPARTMENT =
            "FROM Employee WHERE salary NOT BETWEEN 30000 AND 70000 GROUP BY department ORDER BY salary DESC";
}
