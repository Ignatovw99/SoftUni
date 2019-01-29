import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

public class Academy_Graduation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int studentsNumber = Integer.parseInt(scanner.nextLine());
        Map<String, Double[]> studentsByGrades = new TreeMap<>();

        for (int i = 0; i < studentsNumber; i++) {
            String name = scanner.nextLine();
            String[] gradesStrings = scanner.nextLine().split("\\s+");
            Double[] studentGrades = new Double[gradesStrings.length];

            for (int j = 0; j < gradesStrings.length; j++) {
                studentGrades[j] = Double.parseDouble(gradesStrings[j]);
            }

            studentsByGrades.put(name, studentGrades);
        }

        for (Map.Entry<String, Double[]> entry : studentsByGrades.entrySet()) {
            String student = entry.getKey();
            Double[] grades = entry.getValue();
            double average = 0.0;

            for (int i = 0; i < grades.length; i++) {
                average += grades[i];
            }

            average /= grades.length;
            System.out.println(student + " is graduated with " + average);
        }
    }
}
