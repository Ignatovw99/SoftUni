import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SoftUni_Course_Planning {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> inputSchedule = Arrays.stream(scanner.nextLine().split(", "))
                .collect(Collectors.toList());

        List<String> lessons = new ArrayList<>();
        List<String> exercises = new ArrayList<>();
        classifySchedule(inputSchedule, lessons, exercises);

        String cmd = scanner.nextLine();

        while (!cmd.equalsIgnoreCase("course start")){
            String[] tokens = cmd.split("\\:");

            modifyCourseSchedule(lessons, exercises, tokens);

            cmd = scanner.nextLine();
        }

        printSchedule(lessons, exercises);
    }

    private static void printSchedule(List<String> lessons, List<String> exercises) {
        int position = 1;

        for (String lesson : lessons){
            System.out.println(String.format("%d.%s", position++, lesson));
            if (exercises.contains(lesson)){
                System.out.println(String.format("%d.%s-Exercise", position++, lesson));
            }
        }
    }

    private static void modifyCourseSchedule(List<String> lessons, List<String> exercises, String[] tokens) {
        String command = tokens[0];
        String lessonTitle = tokens[1];

        switch (command){
            case "Add":
                if (!lessons.contains(lessonTitle)){
                    lessons.add(lessonTitle);
                }
                break;
            case "Insert":
                if (!lessons.contains(lessonTitle)){
                    int index = Integer.parseInt(tokens[2]);
                    lessons.add(index, lessonTitle);
                }
                break;
            case "Remove":
                if (lessons.contains(lessonTitle)){
                    lessons.remove(lessonTitle);
                }
                break;
            case "Swap":
                String otherLessonTitle = tokens[2];
                if (lessons.contains(lessonTitle) && lessons.contains(otherLessonTitle)){
                    int firstLessonIndex = lessons.indexOf(lessonTitle);
                    int secondLessonIndex = lessons.indexOf(otherLessonTitle);

                    lessons.set(firstLessonIndex, otherLessonTitle);
                    lessons.set(secondLessonIndex, lessonTitle);
                }
                break;
            case "Exercise":
                if (lessons.contains(lessonTitle) && !exercises.contains(lessonTitle)){
                    exercises.add(lessonTitle);
                } else if (!lessons.contains(lessonTitle) && !exercises.contains(lessonTitle)){
                    exercises.add(lessonTitle);
                    lessons.add(lessonTitle);
                }
                break;
        }
    }

    private static void classifySchedule(List<String> inputSchedule, List<String> lessons, List<String> exercises) {
        IntStream.range(0, inputSchedule.size()).forEach(i -> {
            if (inputSchedule.get(i).contains("-Exercise")) {
                exercises.add(inputSchedule.get(i));
            } else {
                lessons.add(inputSchedule.get(i));
            }
        });
    }
}
