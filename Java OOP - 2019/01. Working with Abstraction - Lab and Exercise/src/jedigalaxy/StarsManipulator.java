package jedigalaxy;

public class StarsManipulator {
    private Galaxy galaxy;

    public StarsManipulator(Galaxy galaxy) {
        this.galaxy = galaxy;
    }

    public void destroyStars(int[] evilPosition) {
        int evilRow = evilPosition[0];
        int evilCol = evilPosition[1];

        while (evilRow >= 0 && evilCol >= 0) {
            if (this.isStarInsideGalaxy(evilRow, evilCol)) {
                this.galaxy.setStar(evilRow, evilCol, new Star(0));
            }
            evilRow--;
            evilCol--;
        }
    }

    private boolean isStarInsideGalaxy(int row, int col) {
        return row >= 0 && row < this.galaxy.getRows() &&
                col >= 0 && col < this.galaxy.getColumns(row);
    }

    public int sumOfStars(int[] playerPosition) {
        int playerRow = playerPosition[0];
        int playerCol = playerPosition[1];

        int sum = 0;

        while (playerRow >= 0 && playerCol < this.galaxy.getColumns(0)) {
            if (this.isStarInsideGalaxy(playerRow, playerCol)) {
                sum += this.galaxy.getStar(playerRow, playerCol).getValue();
            }
            playerRow--;
            playerCol++;
        }

        return sum;
    }
}
