import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

public class ReflectionMain {
    public static void main(String[] args) throws ClassNotFoundException {
        Class reflectionClass = Class.forName("Reflection");

        TreeSet<Field> fields = initialize(reflectionClass.getDeclaredFields());
        TreeSet<Method> getterMethods = new TreeSet<>(Comparator.comparing(Method::getName));
        TreeSet<Method> setterMethods = new TreeSet<>(Comparator.comparing(Method::getName));

        Method[] methods = reflectionClass.getDeclaredMethods();

        for (Method method : methods) {
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

        int mistakesCounter = 0;

        for (Field field : fields) {
            if (!Modifier.isPrivate(field.getModifiers())) {
                System.out.println(field.getName() + " must be private!");
                mistakesCounter += 1;
            }
        }
        for (Method getterMethod : getterMethods) {
            if (!Modifier.isPublic(getterMethod.getModifiers())) {
                System.out.println(getterMethod.getName() + " have to be public!");
                mistakesCounter += 1;
            }
        }
        for (Method setterMethod : setterMethods) {
            if (!Modifier.isPrivate(setterMethod.getModifiers())) {
                System.out.println(setterMethod.getName() + " have to be private!");
                mistakesCounter += 1;
            }
        }

        if (mistakesCounter > 3) {
            System.out.println("Your code is full of bugs. You don't understand encapsulation man!");
        }
    }

    private static TreeSet<Field> initialize(Field[] declaredFields) {
        TreeSet<Field> treeSet = new TreeSet<>(Comparator.comparing(Field::getName));
        treeSet.addAll(Arrays.asList(declaredFields));
        return treeSet;
    }
}
