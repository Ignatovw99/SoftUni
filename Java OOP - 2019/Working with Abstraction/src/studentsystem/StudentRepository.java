package studentsystem;

import java.util.HashMap;
import java.util.Map;

public class StudentRepository {
    private Map<String, Student> storage;

    public StudentRepository() {
        this.storage = new HashMap<>();
    }

    public boolean exists(String name) {
        return this.storage.containsKey(name);
    }

    public Student getByName(String name) {
        return this.storage.get(name);
    }

    public void save(String name, Student student) {
        this.storage.put(name, student);
    }
}
