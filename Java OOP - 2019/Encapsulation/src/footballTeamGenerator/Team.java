package footballTeamGenerator;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private String name;
    private List<Player> players;

    public Team(String name) {
        this.setName(name);
        this.setPlayers();
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("A name should not be empty.");
        }
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    private void setPlayers() {
        this.players = new ArrayList<>();
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public void removePlayer(String name) {
        int playerIndex = -1;
        for (int i = this.players.size() - 1; i >= 0; i--) {
            if (this.players.get(i).getName().equals(name)) {
                playerIndex = i;
                break;
            }
        }
        if (playerIndex == -1) {
            throw new IllegalArgumentException("Player " + name + " is not in " + this.getName() + " team.");
        }
        this.players.remove(playerIndex);
    }

    public double getRating() {
        return this.calculateRating();
    }

    private double calculateRating() {
        return Math.round(this.players.stream().mapToDouble(Player::overallSkillLevel).average().orElse(0));
    }
}
