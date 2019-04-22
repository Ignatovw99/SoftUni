package jedigalaxy;

public class Galaxy {
    private Star[][] stars;

    public Galaxy(int rows, int cols) {
        this.stars = new Star[rows][cols];
        this.fillStars();
    }

    private void fillStars() {
        int starValue = 0;

        for (int row = 0; row < this.stars.length; row++) {
            for (int col = 0; col < this.stars[0].length; col++) {
                this.stars[row][col] = new Star(starValue++);
            }
        }
    }

    public int getRows() {
        return this.stars.length;
    }

    public int getColumns(int row) {
        return this.stars[row].length;
    }

    public Star getStar(int row, int col) {
        if (this.isStarInsideGalaxy(row, col)) {
            return this.stars[row][col];
        }
        return null;
    }

    public void setStar(int row, int col, Star star) {
        if (this.isStarInsideGalaxy(row, col)) {
            this.stars[row][col] = star;
        }
    }

    private boolean isStarInsideGalaxy(int row, int col) {
        return row >= 0 && row < this.stars.length &&
                col >= 0 && col < this.stars[row].length;
    }
}
