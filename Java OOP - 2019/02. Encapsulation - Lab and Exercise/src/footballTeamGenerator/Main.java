package footballTeamGenerator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        TeamRepository repository = new TeamRepository();

        String input = reader.readLine();

        while (!"END".equals(input)) {
            String[] tokens = input.split(";");
            String command = tokens[0];
            String teamName = tokens[1];

            try {
                switch (command) {
                    case "Team":
                        Team team = new Team(teamName);
                        repository.add(team);
                        break;
                    case "Add":
                        team = repository.getByName(teamName);

                        String playerName = tokens[2];
                        int endurance = Integer.parseInt(tokens[3]);
                        int sprint = Integer.parseInt(tokens[4]);
                        int dribble = Integer.parseInt(tokens[5]);
                        int passing = Integer.parseInt(tokens[6]);
                        int shooting = Integer.parseInt(tokens[7]);

                        Player player = new Player(playerName, endurance, sprint, dribble, passing, shooting);
                        team.addPlayer(player);
                        break;
                    case "Remove":
                        playerName = tokens[2];
                        team = repository.getByName(teamName);
                        team.removePlayer(playerName);
                        break;
                    case "Rating":
                        team = repository.getByName(teamName);
                        int teamRating = (int)team.getRating();
                        System.out.println(team.getName() + " - " + teamRating);
                        break;
                }
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }

            input = reader.readLine();
        }
    }
}
