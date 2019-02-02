import java.io.*;
import java.util.ArrayList;

public class Serialize_Array_List {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        ArrayList<Double> list = new ArrayList<>() {{
            add(43.2);
            add(43.2);
            add(4.1);
        }};

        FileOutputStream outputStream = new FileOutputStream("list.ser");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(list);
        objectOutputStream.close();

        FileInputStream inputStream = new FileInputStream("list.ser");
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

        ArrayList<Double> deserializedList = (ArrayList<Double>) objectInputStream.readObject();

        for (Double aDouble : deserializedList) {
            System.out.println(aDouble);
        }
    }
}
