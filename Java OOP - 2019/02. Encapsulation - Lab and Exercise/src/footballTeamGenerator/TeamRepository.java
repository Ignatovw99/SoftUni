package footballTeamGenerator;

import java.util.HashMap;

public class TeamRepository {
    private HashMap<String, Team> data;

    public TeamRepository() {
        this.data = new HashMap<>();
    }

    public void add(Team team) {
        this.data.putIfAbsent(team.getName(), team);
    }

    public Team getByName(String teamName) {
        if (!this.contains(teamName)) {
            throw new IllegalArgumentException("Team " + teamName + " does not exist.");
        }
        return this.data.get(teamName);
    }

    private boolean contains(String teamName) {
        return this.data.containsKey(teamName);
    }
}
