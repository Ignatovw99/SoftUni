import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tracker {
    @Author(name = "Ivan")
    public static void printMethodsByAuthor(Class<?> clazz) {
        Map<String, List<String>> methodsByAuthor = new HashMap<>();
        Method[] methods = clazz.getDeclaredMethods();

        for (Method method : methods) {
            Author annotation = method.getAnnotation(Author.class);
            if (annotation != null) {
                methodsByAuthor.putIfAbsent(annotation.name(), new ArrayList<>());
                methodsByAuthor.get(annotation.name()).add(method.getName() + "()");
            }
        }

        methodsByAuthor.forEach((author, methodsWrittenByAuthor) -> {
            System.out.print(author + ": ");
            for (int i = 0; i < methodsWrittenByAuthor.size(); i++) {
                System.out.print(methodsWrittenByAuthor.get(i));
                if (i < methodsWrittenByAuthor.size() - 1) {
                    System.out.print(", ");
                } else {
                    System.out.println();
                }
            }
        });
    }

    @Author(name = "Ivan")
    public static void main(String[] args) {
        Tracker.printMethodsByAuthor(Tracker.class);
    }
}
