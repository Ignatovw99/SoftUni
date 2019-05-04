import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.TreeSet;

public class ReflectionMain {
    public static void main(String[] args) throws ClassNotFoundException {
        Class reflectionClass = Class.forName("Reflection");

        Method[] declaredMethods = reflectionClass.getDeclaredMethods();

        TreeSet<Method> getterMethods = new TreeSet<>(Comparator.comparing(Method::getName));
        TreeSet<Method> setterMethods = new TreeSet<>(Comparator.comparing(Method::getName));

        for (Method method : declaredMethods) {
            if (method.getName().startsWith("get")) {
                if (method.getParameterCount() == 0) {
                    getterMethods.add(method);
                }
            } else if (method.getName().startsWith("set")) {
                if (method.getParameterCount() == 1) {
                    if (void.class.equals(method.getReturnType())) {
                        setterMethods.add(method);
                    }
                }
            }
        }

        getterMethods.forEach(method ->
                System.out.println(String.format("%s will return %s", method.getName(), method.getReturnType())));
        setterMethods.forEach(method ->
                System.out.println(String.format("%s and will set field of %s", method.getName(), method.getParameterTypes()[0])));
    }
}
