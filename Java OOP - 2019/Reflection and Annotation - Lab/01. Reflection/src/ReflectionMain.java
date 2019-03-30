import java.lang.reflect.InvocationTargetException;

public class ReflectionMain {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class clazz = Class.forName("Reflection");
        System.out.println(clazz);
        System.out.println(clazz.getSuperclass());
        Class[] interfaces = clazz.getInterfaces();
        for (Class anInterface : interfaces) {
            System.out.println(anInterface);
        }

        Reflection reflection = (Reflection) clazz.getConstructors()[0].newInstance();
        System.out.println(reflection);
    }
}
