package customlist;

public class CommandParser {
    private SmartList<String> smartList;

    public CommandParser() {
        this.smartList = new SmartList<>();
    }

    public void execute(String command) {
        String[] tokens =command.split("\\s+");

        switch (tokens[0]) {
            case "Add":
                String element = tokens[1];
                this.smartList.add(element);
                break;
            case "Remove":
                int indexToRemove = Integer.parseInt(tokens[1]);
                this.smartList.remove(indexToRemove);
                break;
            case "Contains":
                String elementToCheck = tokens[1];
                System.out.println(this.smartList.contains(elementToCheck));
                break;
            case "Swap":
                int firstIndex = Integer.parseInt(tokens[1]);
                int secondIndex = Integer.parseInt(tokens[2]);
                this.smartList.swap(firstIndex, secondIndex);
                break;
            case "Greater":
                String comparatorString = tokens[1];
                int greaterElementsCount = this.smartList.countGreaterThan(comparatorString);
                System.out.println(greaterElementsCount);
                break;
            case "Max":
                String maxElement = this.smartList.getMax();
                System.out.println(maxElement);
                break;
            case "Min":
                String minElement = this.smartList.getMin();
                System.out.println(minElement);
                break;
            case "Print":
                for (String str : smartList) {
                    System.out.println(str);
                }
                break;
            case "Sort":
                Sorter.sort(smartList);
                break;
        }
    }
}
