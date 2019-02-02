import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Serialize_Custom_Object {
    public static void main(String[] args) throws IOException {
        String path = "D:\\Software University\\Java Development\\Java Advanced" +
                "\\Streams, Files and Directories" +
                "\\04. Java-Advanced-Files-and-Streams-Lab-Resources" +
                "\\SerializeObject.txt";

        Cube cube = new Cube();
        cube.color = "green";
        cube.depth = 3;
        cube.heigth = 5.2;
        cube.width = 6.4;

        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(path));
            objectOutputStream.writeObject(cube);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (objectOutputStream != null) {
                objectOutputStream.close();
            }
        }
    }
}

class Cube implements Serializable {
    public String color;
    public double width;
    public double heigth;
    public double depth;
}
