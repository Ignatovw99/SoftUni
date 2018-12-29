import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Quests_Journal {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        List<String> questsInJournal = Arrays.stream(scanner.nextLine().split(", "))
                .collect(Collectors.toList());

        String command = scanner.nextLine();

        while (!command.equals("Retire!")){
            String[] tokens = command.split(" - ");
            String action = tokens[0];
            String quest = tokens[1];

            switch (action){
                case "Start":
                    if (!questsInJournal.contains(quest)){
                        questsInJournal.add(quest);
                    }
                    break;
                case "Complete":
                    if (questsInJournal.contains(quest)){
                        questsInJournal.remove(quest);
                    }
                    break;
                case "Side Quest":
                    String[] sideQuestToken = quest.split(":");
                    quest = sideQuestToken[0];
                    String sideQuest = sideQuestToken[1];
                    if (questsInJournal.contains(quest) && !questsInJournal.contains(sideQuest)){
                        questsInJournal.add(questsInJournal.indexOf(quest) + 1, sideQuest);
                    }
                    break;
                case "Renew":
                    if (questsInJournal.contains(quest)){
                        questsInJournal.remove(quest);
                        questsInJournal.add(quest);
                    }
                    break;
            }
            command = scanner.nextLine();
        }
        System.out.println(String.join(", ", questsInJournal));
    }
}
