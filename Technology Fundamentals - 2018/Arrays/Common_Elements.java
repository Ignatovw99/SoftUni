import java.util.Scanner;

public class Common_Elements {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] firstArray = scanner.nextLine().split("\\s+");
        String[] secondArray = scanner.nextLine().split("\\s+");

        for (int i = 0; i < secondArray.length; i++) {
            String element = secondArray[i];
            for (int j = 0; j < firstArray.length; j++){
                if (element.equals(firstArray[j])){
                    System.out.print(element + " ");
                }
            }
        }
    }
}
