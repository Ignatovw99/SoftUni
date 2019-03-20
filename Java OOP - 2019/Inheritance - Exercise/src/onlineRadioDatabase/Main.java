package onlineRadioDatabase;

import onlineRadioDatabase.songexceptions.InvalidSongException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        SongDatabase repository = new SongDatabase();

        int numberOfSongsToAdd = Integer.parseInt(reader.readLine());

        for (int i = 0; i < numberOfSongsToAdd; i++) {
            String[] songTokens = reader.readLine().split(";");
            String artistName = songTokens[0];
            String songName = songTokens[1];
            String length = songTokens[2];

            try {
                Song song = new Song(artistName, songName, length);
                repository.addSong(song);
                System.out.println("Song added.");
            } catch (InvalidSongException exception) {
                System.out.println(exception.getMessage());
            }
        }

        System.out.println(repository.toString());
    }
}
