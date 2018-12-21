package technologyFundamentalsFinalExam;

import java.util.*;

public class Concert {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Map<String, List<String>> bandsByMembers = new HashMap<>();
        Map<String, Integer> bandsByTimeOnStage = new HashMap<>();

        String cmd = sc.nextLine();

        while (!cmd.equalsIgnoreCase("start of concert")){
            String[] tokens = Arrays.stream(cmd.split("; "))
                    .toArray(x -> new String[x]);

            String action = tokens[0];
            String bandName = tokens[1];

            if (action.equals("Add")){
                String[] members = Arrays.stream(tokens[2].split(", ")).toArray(String[]::new);

                if (!bandsByMembers.containsKey(bandName)){
                    bandsByMembers.put(bandName, new ArrayList<>());
                }

                List<String> membersOfBand = bandsByMembers.get(bandName);

                for (int i = 0; i < members.length; i++) {
                    if (!membersOfBand.contains(members[i])){
                        bandsByMembers.get(bandName).add(members[i]);
                    }
                }
            } else if (action.equals("Play")){
                int timeOnstage = Integer.parseInt(tokens[2]);

                if (!bandsByTimeOnStage.containsKey(bandName)){
                    bandsByTimeOnStage.put(bandName, 0);
                }

                int bandTime = bandsByTimeOnStage.get(bandName);
                bandTime += timeOnstage;
                bandsByTimeOnStage.put(bandName, bandTime);
            }

            cmd = sc.nextLine();
        }

        int totalTime = 0;

        for (Map.Entry<String, Integer> bandTime: bandsByTimeOnStage.entrySet()) {
            totalTime += bandTime.getValue();
        }

        System.out.println("Total time: " + totalTime);

        bandsByTimeOnStage.entrySet().stream()
                .sorted(
                        (t1, t2) ->{
                        int firstTime = t1.getValue();
                        int secondTime = t2.getValue();
                        int timeCompare = Integer.compare(secondTime, firstTime);
                        if (timeCompare == 0){
                            String firstBand = t1.getKey();
                            String secondBand = t2.getKey();
                            return firstBand.compareTo(secondBand);
                        }
                        return Integer.compare(secondTime, firstTime);
                        })
                .forEach(pair ->{
                    String band = pair.getKey();
                    int time = pair.getValue();
                    System.out.println(band + " -> " + time);
                });

        String bandToPrint = sc.nextLine();
        System.out.println(bandToPrint);
        for (String member : bandsByMembers.get(bandToPrint)) {
            System.out.println("=> " + member);
        }
    }
}
