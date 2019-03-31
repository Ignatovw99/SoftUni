import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException, NoSuchMethodException,
			IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String input = reader.readLine();

		Constructor<BlackBoxInt> constructor = BlackBoxInt.class.getDeclaredConstructor();
		constructor.setAccessible(true);
		BlackBoxInt blackBoxInt = constructor.newInstance();

		Method[] methods = blackBoxInt.getClass().getDeclaredMethods();
		Field innerValue = blackBoxInt.getClass().getDeclaredField("innerValue");
		innerValue.setAccessible(true);

		while (!"END".equals(input)) {
			String[] tokens = input.split("_");
			String command = tokens[0];
			int value = Integer.parseInt(tokens[1]);

			Method method = Arrays.stream(methods)
					.filter(m -> m.getName().equals(command))
					.findFirst().orElse(null);

			if (method != null) {
				method.setAccessible(true);
				method.invoke(blackBoxInt, value);
				System.out.println(innerValue.get(blackBoxInt));
			}
			input = reader.readLine();
		}
	}
}
