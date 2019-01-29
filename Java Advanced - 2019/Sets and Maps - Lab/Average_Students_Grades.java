import java.awt.geom.Path2D;
import java.text.DecimalFormat;
import java.util.*;

public class Average_Students_Grades {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int studentsCount = Integer.parseInt(scanner.nextLine());

        Map<String, List<Double>> studentsByGrades = new TreeMap<>();

        for (int i = 0; i < studentsCount; i++) {
            String[] studentsInfo = scanner.nextLine().split("\\s+");

            String student = studentsInfo[0];
            double studentGrade = Double.parseDouble(studentsInfo[1]);

            if (studentsByGrades.containsKey(student)) {
                List<Double> currentGrades = studentsByGrades.get(student);
                currentGrades.add(studentGrade);
                studentsByGrades.put(student, currentGrades);
            } else {
                studentsByGrades.put(student, new ArrayList<>());
                studentsByGrades.get(student).add(studentGrade);
            }
        }

        for (Map.Entry<String, List<Double>> entry : studentsByGrades.entrySet()) {
            String student = entry.getKey();
            double gradeSum = entry.getValue().stream().mapToDouble(e -> e).sum();

            System.out.print(student + " -> ");

            for (Double currentGrade : entry.getValue()) {
                System.out.print(String.format("%.2f ", currentGrade));
            }
            System.out.println(String.format("(avg: %.2f)", gradeSum / studentsByGrades.get(student).size()));
        }
    }
}
