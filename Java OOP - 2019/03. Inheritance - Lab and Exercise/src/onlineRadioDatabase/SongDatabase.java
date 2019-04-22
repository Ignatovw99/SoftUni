package onlineRadioDatabase;

import java.util.ArrayList;
import java.util.List;

public class SongDatabase {
    private List<Song> songs;

    public SongDatabase() {
        this.songs = new ArrayList<>();
    }

    public void addSong(Song song) {
        this.songs.add(song);
    }

    public String getTotalLengthOfSongs() {
        int totalSeconds = 0;
        for (Song song : this.songs) {
            String[] tokens = song.getLength().split(":");
            int minutes = Integer.parseInt(tokens[0]);
            int seconds = Integer.parseInt(tokens[1]);
            totalSeconds += (minutes * 60 + seconds);
        }

        int hours = totalSeconds / 3600;
        int minutes = (totalSeconds % 3600) / 60;
        int seconds = (totalSeconds % 3600) % 60;

        return String.format("%dh %dm %ds", hours, minutes, seconds);
    }

    private int getSongsCount() {
        return this.songs.size();
    }

    @Override
    public String toString() {
        return String.format("Songs added: %d%n" +
                "Playlist length: %s", this.getSongsCount(), this.getTotalLengthOfSongs());
    }
}
