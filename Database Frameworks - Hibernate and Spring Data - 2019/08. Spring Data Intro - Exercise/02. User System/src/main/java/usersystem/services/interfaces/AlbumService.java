package usersystem.services.interfaces;

import usersystem.entities.Album;

import java.util.Set;

public interface AlbumService {

    void seedAlbumsTable(PictureService pictureService);

    long getAlbumsCount();

    void addNewAlbumToDatabase(Album album);

    Set<Album> getRandomAlbums();

    Album getRandomAlbum();
}
