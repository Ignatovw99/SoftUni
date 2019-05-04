import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.function.Consumer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		Field[] fields = RichSoilLand.class.getDeclaredFields();
		String input = bufferedReader.readLine();

		Consumer<Field> printer = field -> System.out.println(String.format("%s %s %s",
				Modifier.toString(field.getModifiers()),
				field.getType().getSimpleName(),
				field.getName()));

		while (!"HARVEST".equals(input)) {
			if (input.equals("all")) {
				Arrays.stream(fields)
						.forEach(printer);
			} else {
				String inputModifier = input;
				Arrays.stream(fields)
						.filter(f -> Modifier.toString(f.getModifiers()).equalsIgnoreCase(inputModifier))
						.forEach(printer);
			}
			input = bufferedReader.readLine();
		}
	}
}
