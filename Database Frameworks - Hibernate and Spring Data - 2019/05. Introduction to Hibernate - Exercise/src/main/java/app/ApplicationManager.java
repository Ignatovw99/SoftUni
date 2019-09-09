package app;

import constants.*;
import entities.Address;
import entities.Employee;
import entities.Project;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Comparator;
import java.util.List;

public class ApplicationManager {
    private final EntityManager entityManager;

    public ApplicationManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public String removeObjects() {
        this.entityManager.getTransaction()
                .begin();
        StringBuilder output = new StringBuilder();
        List<Town> towns = this.entityManager
                .createQuery(Queries.RETRIEVE_ALL_TOWNS, Town.class)
                .getResultList();

        towns.forEach(town -> {
            if (town.getName().length() > 5) {
                this.entityManager.detach(town);
            }
        });

        towns.forEach(town -> {
            if (this.entityManager.contains(town)) {
                output.append(town.getName())
                        .append(Globals.ARROW);
                town.setName(town.getName().toLowerCase());
                output.append(town.getName())
                        .append(System.lineSeparator());
            }
        });

        this.entityManager.getTransaction()
                .commit();
        return output.toString();
    }

    public String containsEmployee(String employeeFullName) {
        this.entityManager.getTransaction()
                .begin();

        String[] employeeNames = employeeFullName.split(Globals.TOKENS_SEPARATOR);
        if (employeeNames.length != 2) {
            this.entityManager.getTransaction()
                    .rollback();
            return Messages.EMPLOYEE_NAME_REQUIREMENTS;
        }
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> employeeRoot = criteriaQuery.from(Employee.class);

        criteriaQuery.select(employeeRoot)
                .where(criteriaBuilder.and(
                        criteriaBuilder.equal(employeeRoot.get(EntitiesFields.EMPLOYEE_FIRST_NAME), employeeNames[0]),
                        criteriaBuilder.equal(employeeRoot.get(EntitiesFields.EMPLOYEE_LAST_NAME), employeeNames[1])
                ));

        String output;
        try {
            this.entityManager.createQuery(criteriaQuery)
                    .getSingleResult();

            this.entityManager.getTransaction()
                    .commit();
            output = Messages.DATABASE_CONTAINS_EMPLOYEE;
        } catch (NoResultException ex) {
            this.entityManager.getTransaction()
                    .rollback();
            output = Messages.DATABASE_DOES_NOT_CONTAIN_EMPLOYEE;
        }

        return output;
    }

    public String employeesWithSalaryOver50000() {
        this.entityManager.getTransaction()
                .begin();

        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> employeeRoot = criteriaQuery.from(Employee.class);

        criteriaQuery.select(employeeRoot)
                .where(criteriaBuilder.greaterThan(employeeRoot.get(EntitiesFields.EMPLOYEE_SALARY), Constants.MINIMUM_SALARY));

        StringBuilder output = new StringBuilder();

        this.entityManager.createQuery(criteriaQuery)
                .getResultList()
                .forEach(employee -> output.append(employee.getFirstName()).append(System.lineSeparator()));

        this.entityManager.getTransaction()
                .commit();

        return output.toString();
    }

    public String employeesFromDepartment() {
        this.entityManager.getTransaction()
                .begin();

        StringBuilder output = new StringBuilder();

        this.entityManager
                .createQuery(Queries.RETRIEVE_EMPLOYEES_BY_DEPARTMENT, Employee.class)
                .setParameter(QueryParameters.DEPARTMENT_NAME, Constants.RESEARCH_AND_DEVELOPMENT_DEPARTMENT)
                .getResultList()
                .forEach(employee -> output.append(
                        String.format(Messages.ALL_EMPLOYEES_FROM_DEPARTMENT,
                                employee.getFirstName(), employee.getLastName(),
                                employee.getDepartment().getName(), employee.getSalary()))
                        .append(System.lineSeparator())
                );

        this.entityManager.getTransaction()
                .commit();
        return output.toString();
    }

    public String addNewAddressAndUpdateEmployee(String employeeLastName) {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> employeeCriteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> employeeRoot = employeeCriteriaQuery.from(Employee.class);

        employeeCriteriaQuery.select(employeeRoot)
                .where(criteriaBuilder.equal(employeeRoot.get(EntitiesFields.EMPLOYEE_LAST_NAME), employeeLastName));

        this.entityManager.getTransaction()
                .begin();

        String output;
        try {
            Employee employee = this.entityManager
                    .createQuery(employeeCriteriaQuery)
                    .getSingleResult();

            Town town = this.entityManager
                    .createQuery(Queries.RETRIEVE_TOWN_BY_NAME, Town.class)
                    .setParameter(QueryParameters.TOWN_NAME, Constants.SOFIA_TOWN)
                    .getSingleResult();

            Address address;
            try {
                address = this.entityManager
                        .createQuery(Queries.RETRIEVE_ADDRESS_BY_NAME, Address.class)
                        .setParameter(QueryParameters.ADDRESS_TEXT, Constants.VITOSHKA_ADDRESS)
                        .getSingleResult();
            } catch (NoResultException ex) {
                address = new Address();
                address.setText(Constants.VITOSHKA_ADDRESS);
                address.setTown(town);
                this.entityManager.persist(address);
            }

            employee.setAddress(address);

            output = String.format(Messages.SUCCESSFUL_ADDRESS_CHANGE,
                            employee.getFirstName(), employee.getLastName(),
                            employee.getAddress().getText(), employee.getAddress().getTown().getName()
            );
            this.entityManager.getTransaction()
                    .commit();
        } catch (NoResultException ex) {
            this.entityManager.getTransaction().rollback();
            output = Messages.ERROR_MESSAGE;
        }
        return output;
    }

    public String addressesWithEmployeeCount() {
        this.entityManager.getTransaction()
                .begin();
        StringBuilder output = new StringBuilder();
        this.entityManager
                .createQuery(Queries.RETRIEVE_ADDRESSES_ORDER_BY_EMPLOYEE_COUNT, Address.class)
                .setMaxResults(10)
                .getResultList()
                .forEach(address -> output.append(
                        String.format(Messages.ADDRESS_WITH_EMPLOYEE_COUNT,
                                address.getText(), address.getTown().getName(), address.getEmployees().size()))
                        .append(System.lineSeparator())
                );
        this.entityManager.getTransaction()
                .commit();
        return output.toString();
    }

    public String getEmployeeWithProject(int employeeId) {
        this.entityManager.getTransaction()
                .begin();
        StringBuilder output = new StringBuilder();
        try {
            Employee employee = this.entityManager
                    .createQuery(Queries.RETRIEVE_EMPLOYEE_BY_ID, Employee.class)
                    .setParameter(QueryParameters.EMPLOYEE_ID, employeeId)
                    .getSingleResult();

            output.append(String.format(Messages.EMPLOYEE_DATA,
                            employee.getFirstName(), employee.getLastName(), employee.getJobTitle()))
                    .append(System.lineSeparator());

            employee.getProjects()
                    .stream()
                    .sorted(Comparator.comparing(Project::getName))
                    .forEach(project -> output.append(Globals.TAB_KEY)
                            .append(project.getName())
                            .append(System.lineSeparator())
                    );
        } catch (NoResultException ex) {
            output.append(Messages.NO_EMPLOYEE_FOUND)
                    .append(System.lineSeparator());
        }
        this.entityManager.getTransaction()
                .commit();
        return output.toString();
    }

    public String findLatestTenProjects() {
        this.entityManager.getTransaction()
                .begin();
        StringBuilder output = new StringBuilder();
        this.entityManager
                .createQuery(Queries.RETRIEVE_PROJECTS_ORDER_DESC_BY_START_DATE, Project.class)
                .setMaxResults(10)
                .getResultList()
                .stream()
                .sorted(Comparator.comparing(Project::getName))
                .forEach(project ->output.append(
                        String.format(Messages.PROJECT_NAME_PATTERN +
                                        Globals.TAB_KEY + Messages.PROJECT_DESCRIPTION_PATTERN +
                                        Globals.TAB_KEY + Messages.PROJECT_START_DATE_PATTERN +
                                        Globals.TAB_KEY + Messages.PROJECT_END_DATE_PATTERN,
                                project.getName(), project.getDescription(), project.getStartDate(), project.getEndDate())
                        )
                        .append(System.lineSeparator())
                );
        this.entityManager.getTransaction()
                .commit();
        return output.toString();
    }

    public String increaseSalaries() {
        this.entityManager.getTransaction()
                .begin();
        StringBuilder output = new StringBuilder();
        this.entityManager
                .createQuery(Queries.RETRIEVE_EMPLOYEES_BY_MANY_DEPARTMENTS, Employee.class)
                .setParameter(QueryParameters.FIRST_DEPARTMENT, Constants.ENGINEERING_DEPARTMENT)
                .setParameter(QueryParameters.SECOND_DEPARTMENT, Constants.TOOL_DESIGN_DEPARTMENT)
                .setParameter(QueryParameters.THIRD_DEPARTMENT, Constants.MARKETING_DEPARTMENT)
                .setParameter(QueryParameters.FOURTH_DEPARTMENT, Constants.INFORMATION_SERVICES_DEPARTMENT)
                .getResultList()
                .forEach(employee -> {
                    employee.setSalary(employee.getSalary().multiply(Constants.INCREASE_SALARY_VALUE));
                    output.append(
                            String.format(Messages.INCREASE_SALARY_MESSAGE,
                                    employee.getFirstName(), employee.getLastName(), employee.getSalary())
                    ).append(System.lineSeparator());
                });
        this.entityManager.flush();
        this.entityManager.getTransaction()
                .commit();
        return output.toString();
    }

    public String removeTown(String townName) {
        this.entityManager.getTransaction()
                .begin();

        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Town> townCriteriaQuery = criteriaBuilder.createQuery(Town.class);
        Root<Town> townRoot = townCriteriaQuery.from(Town.class);

        townCriteriaQuery.select(townRoot)
                .where(criteriaBuilder.equal(townRoot.get(EntitiesFields.TOWN_NAME), townName));

        CriteriaQuery<Address> addressCriteriaQuery = criteriaBuilder.createQuery(Address.class);
        Root<Address> addressRoot = addressCriteriaQuery.from(Address.class);

        addressCriteriaQuery.select(addressRoot)
                .where(criteriaBuilder.equal(
                        addressRoot.get(EntitiesFields.ADDRESS_TOWN).get(EntitiesFields.TOWN_NAME), townName
                ));

        String output;
        try {
            Town town = this.entityManager
                    .createQuery(townCriteriaQuery)
                    .getSingleResult();

            List<Address> addresses = this.entityManager
                    .createQuery(addressCriteriaQuery)
                    .getResultList();

            addresses.forEach(address -> {
                address.getEmployees()
                        .forEach(employee -> employee.setAddress(null));
                this.entityManager.remove(address);
            });

            this.entityManager.remove(town);

            output = String.format(Messages.DELETED_ADDRESSES_IN_TOWN,
                    addresses.size(), addresses.size() <= 1 ? Constants.ADDRESS_SINGULAR_FORM : Constants.ADDRESS_PLURAL_FORM, town.getName()
            );
        } catch (NoResultException ex) {
            output = Messages.NO_TOWN_FOUND;
        }
        this.entityManager.getTransaction()
                .commit();
        return output;
    }

    public String findEmployeeByFirstName(String firstNamePattern) {
        this.entityManager.getTransaction()
                .begin();
        StringBuilder output = new StringBuilder();
        this.entityManager
                .createQuery(Queries.RETRIEVE_EMPLOYEE_BY_FIRST_NAME_PATTERN, Employee.class)
                .setParameter(QueryParameters.FIRST_NAME_PATTERN, firstNamePattern.toLowerCase().concat("%"))
                .getResultList()
                .forEach(employee -> output.append(
                        String.format(Messages.ALL_EMPLOYEES_FIRST_NAME_STARTING_WITH_SAME_PATTERN,
                                employee.getFirstName(), employee.getLastName(), employee.getJobTitle(), employee.getSalary()))
                        .append(System.lineSeparator())
                );
        if (output.toString().isEmpty()) {
            output.append(
                    String.format(Messages.NO_EMPLOYEES_FOUND_BY_PATTERN, firstNamePattern)
            );
        }
        this.entityManager.getTransaction()
                .commit();
        return output.toString();
    }

    public String employeesMaximumSalaries() {
        this.entityManager.getTransaction()
                .begin();
        StringBuilder output = new StringBuilder();
        this.entityManager
                .createQuery(Queries.RETRIEVE_MAX_SALARY_FOR_DEPARTMENT, Employee.class)
                .getResultList()
                .stream()
                .sorted(Comparator.comparingInt(employee -> employee.getDepartment().getId())) //Order ascending by id, because the data from DB is ordered by salary
                .forEach(employee -> output.append(
                        String.format(Messages.DEPARTMENT_WITH_MAX_SALARY,
                                employee.getDepartment().getName(), employee.getSalary()))
                        .append(System.lineSeparator())
                );
        this.entityManager.getTransaction()
                .commit();
        return output.toString();
    }
}
