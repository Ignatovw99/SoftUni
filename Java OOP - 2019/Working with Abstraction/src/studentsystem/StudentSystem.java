package studentsystem;

public class StudentSystem {
    private StudentRepository repository;

    public StudentSystem()
    {
        this.repository = new StudentRepository();
    }

    public void executeCommandWithArguments(String[]args) {
        if (args[0].equals("Create")) {
            this.createStudent(args);
        } else if (args[0].equals("Show")) {
            this.showStudent(args);
        }
    }

    private void showStudent(String[] args) {
        String name = args[1];

        if (!repository.exists(name)) { return; }

        Student student = repository.getByName(name);
        System.out.println(student);
    }

    private void createStudent(String[] args) {
        String name = args[1];
        if (repository.exists(name)) { return; }

        int age = Integer.parseInt(args[2]);
        double grade =Double.parseDouble(args[3]);

        Student student = new Student(name, age, grade);
        repository.save(name,student);
    }
}
