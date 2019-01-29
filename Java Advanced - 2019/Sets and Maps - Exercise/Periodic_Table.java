import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Periodic_Table {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        Set<String> chemicalCompounds = new TreeSet<>();

        for (int i = 0; i < n; i++) {
            String[] compounds = scanner.nextLine().split("\\s+");
            for (int j = 0; j < compounds.length; j++) {
                chemicalCompounds.add(compounds[j]);
            }
        }

        for (String chemicalCompound : chemicalCompounds) {
            System.out.print(chemicalCompound + " ");
        }
    }
}
