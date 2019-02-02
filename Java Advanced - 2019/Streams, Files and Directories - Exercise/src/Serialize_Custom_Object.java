import java.io.*;

public class Serialize_Custom_Object {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //Serialize object
        Course course = new Course();
        course.name = "Java Advanced";
        course.studentsNumber = 54;

        FileOutputStream outputStream = new FileOutputStream("course.ser");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

        objectOutputStream.writeObject(course);
        objectOutputStream.close();

        //Deserialize object
        FileInputStream inputStream = new FileInputStream("course.ser");
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

        Course deserializedObject = (Course) objectInputStream.readObject();

        System.out.println(deserializedObject.name + " " + deserializedObject.studentsNumber);
        objectInputStream.close();
        inputStream.close();
    }
}

class Course implements Serializable {
    String name;
    int studentsNumber;
}
