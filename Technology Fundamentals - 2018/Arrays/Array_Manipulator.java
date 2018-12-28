import java.util.Arrays;
import java.util.Scanner;

public class Array_Manipulator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] array = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        String command = scanner.nextLine();

        while (!command.equals("end")){
            String[] tokens = command.split("\\s+");
            String manipulationType = tokens[0];

            switch (manipulationType){
                case "exchange":
                    int index = Integer.parseInt(tokens[1]);
                    if (isIndexInsideArrayBoundaries(index, array)){
                        exchangeSubarrays(array, index);
                    } else {
                        System.out.println("Invalid index");
                    }
                    break;
                case "max":
                    String kindOfElement = tokens[1];
                    int indexOfMaxElement = getIndexOfMaxElement(array, kindOfElement);
                    if (indexOfMaxElement != -1){
                        System.out.println(indexOfMaxElement);
                    } else {
                        System.out.println("No matches");
                    }
                    break;
                case "min":
                    kindOfElement = tokens[1];
                    int indexOfMinElement = getIndexOfMinElement(array, kindOfElement);
                    if (indexOfMinElement != -1){
                        System.out.println(indexOfMinElement);
                    } else {
                        System.out.println("No matches");
                    }
                    break;
                case "first":
                    int countOfElements = Integer.parseInt(tokens[1]);
                    kindOfElement = tokens[2];
                    if (countOfElements <= array.length){
                        int[] firstElements = getFirstElements(array, countOfElements, kindOfElement);
                        printArray(firstElements);
                    } else {
                        System.out.println("Invalid count");
                    }
                    break;
                case "last":
                    countOfElements = Integer.parseInt(tokens[1]);
                    kindOfElement = tokens[2];
                    if (countOfElements <= array.length){
                        int[] lastElements = getLastElements(array, countOfElements, kindOfElement);
                        printArray(lastElements);
                    } else {
                        System.out.println("Invalid count");
                    }
                    break;
            }
            command = scanner.nextLine();
        }

        printArray(array);
    }

    private static int[] getLastElements(int[] array, int countOfElements, String kindOfElement) {
        int resultModulusDivision = kindOfElement.equals("even") ? 0 : 1;
        int[] elementsToGet = new int[countOfElements];
        int currentArrayCount = 0;

        for (int i = array.length - 1; i >= 0; i--){
            int currentElement = array[i];
            if (currentElement % 2 == resultModulusDivision){
                elementsToGet[currentArrayCount++] = currentElement;
            }
            if (currentArrayCount == elementsToGet.length){
                break;
            }
        }

        if (currentArrayCount < elementsToGet.length){
            return shrinkArray(elementsToGet, currentArrayCount);
        } else {
            elementsToGet = reverseArray(elementsToGet, currentArrayCount);
            return elementsToGet;
        }
    }

    private static int[] reverseArray(int[] elementsToGet, int countOfElements) {
        int[] copyArray = new int[elementsToGet.length];

        for (int i = 0; i < copyArray.length; i++) {
            copyArray[i] = elementsToGet[elementsToGet.length - 1 - i];
        }

        return copyArray;
    }

    private static void printArray(int[] array) {
        System.out.print("[");
        for (int i = 0; i < array.length; i++) {
            if (i == array.length - 1){
                System.out.print(array[i]);
            } else {
                System.out.print(array[i] + ", ");
            }
        }
        System.out.println("]");
    }

    private static int[] getFirstElements(int[] array, int countOfElements, String kindOfElement) {
        int resultModulusDivision = kindOfElement.equals("even") ? 0 : 1;
        int[] elementsToGet = new int[countOfElements];
        int currentArrayCount = 0;

        for (int i = 0; i < array.length; i++) {
            int currentElement = array[i];
            if (currentElement % 2 == resultModulusDivision){
                elementsToGet[currentArrayCount++] = currentElement;
            }
            if (currentArrayCount == elementsToGet.length){
                break;
            }
        }

        if (currentArrayCount < elementsToGet.length){
            return shrinkArray(elementsToGet, currentArrayCount);
        } else {
            return elementsToGet;
        }
    }

    private static int[] shrinkArray(int[] array, int length) {
        array = reverseArray(array, length);
        int[] arr = new int[length];
        for (int i = 0; i < arr.length; i++){
            arr[i] = array[array.length - 1 - i];
        }
        return arr;
    }

    private static int getIndexOfMinElement(int[] array, String kindOfElement) {
        int resultModulusDivision = kindOfElement.equals("even") ? 0 : 1;

        int maxElement = Integer.MAX_VALUE;
        int indexOfMaxElement = -1;

        for (int i = 0; i < array.length; i++) {
            int currentElement = array[i];
            if (currentElement % 2 == resultModulusDivision){
                if (currentElement <= maxElement){
                    maxElement = currentElement;
                    indexOfMaxElement = i;
                }
            }
        }
        return indexOfMaxElement;
    }

    private static int getIndexOfMaxElement(int[] array, String kindOfElement) {
        int resultModulusDivision = kindOfElement.equals("even") ? 0 : 1;

        int maxElement = Integer.MIN_VALUE;
        int indexOfMaxElement = -1;

        for (int i = 0; i < array.length; i++) {
            int currentElement = array[i];
            if (currentElement % 2 == resultModulusDivision){
                if (currentElement >= maxElement){
                    maxElement = currentElement;
                    indexOfMaxElement = i;
                }
            }
        }
        return indexOfMaxElement;
    }

    private static void exchangeSubarrays(int[] array, int index) {
        int[] copyArray = new int[array.length];
        for (int i = 0; i < copyArray.length; i++) {
            copyArray[i] = array[i];
        }

        for (int i = 0; i < array.length; i++) {
            int indexForExchange = (index + i + 1) % array.length;
            array[i] = copyArray[indexForExchange];
        }
    }

    private static boolean isIndexInsideArrayBoundaries(int index, int[] array) {
        return index >= 0 && index < array.length;
    }
}
