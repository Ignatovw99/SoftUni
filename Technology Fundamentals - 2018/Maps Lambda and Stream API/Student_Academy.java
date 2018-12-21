import java.util.*;

public class Student_Academy {
    public static void main(String[] args){
        Scanner  scanner = new Scanner(System.in);
        Map<String, List<Double>> studentsByGrade = new LinkedHashMap<>();

        int studentsCount = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < studentsCount; i++){
            String student = scanner.nextLine();
            double grade = Double.parseDouble(scanner.nextLine());

            if (!studentsByGrade.containsKey(student)){
                studentsByGrade.put(student, new ArrayList<>());
            }
            studentsByGrade.get(student).add(grade);
        }

        Map<String, Double> studentsByAverageGrade = new LinkedHashMap<>();

        studentsByGrade
                .forEach((student, grades) ->{
                    double averageGrade = grades.stream().mapToDouble(g -> g).average().getAsDouble();
                    if (averageGrade >= 4.5){
                        studentsByAverageGrade.put(student, averageGrade);
                    }
                });

        studentsByAverageGrade
                .entrySet()
                .stream()
                .sorted((student1, student2) -> {
                    return student2.getValue().compareTo(student1.getValue());
                })
                .forEach(entry -> System.out.println(String.format("%s -> %.2f", entry.getKey(), entry.getValue())));
    }
}
