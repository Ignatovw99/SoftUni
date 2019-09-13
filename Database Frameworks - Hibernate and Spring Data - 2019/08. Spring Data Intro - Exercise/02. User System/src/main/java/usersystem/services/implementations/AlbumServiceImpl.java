package usersystem.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import usersystem.entities.Album;
import usersystem.repositories.AlbumRepository;
import usersystem.services.interfaces.AlbumService;
import usersystem.services.interfaces.PictureService;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static usersystem.constants.AlbumValues.*;
import static usersystem.constants.Constants.EMPTY_ALBUMS_TABLE;

@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {

    private AlbumRepository albumRepository;

    @Autowired
    public AlbumServiceImpl(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Override
    public void seedAlbumsTable(PictureService pictureService) {
        for (int i = 0; i < ALBUMS_COUNT; i++) {
            Album album = new Album();
            album.setName(NAME + i);
            album.setBackgroundColor(BACKGROUND_COLOR + i);
            album.setPublic(IS_PUBLIC_VALUES[i % IS_PUBLIC_VALUES.length]);
            album.setPictures(pictureService.getRandomPictures());
            album.setUser(null);
            this.addNewAlbumToDatabase(album);
        }
    }

    @Override
    public long getAlbumsCount() {
        return this.albumRepository.count();
    }

    @Override
    public void addNewAlbumToDatabase(Album album) {
        this.albumRepository.saveAndFlush(album);
    }

    @Override
    public Set<Album> getRandomAlbums() {
        Set<Album> albums = new HashSet<>();

        for (int i = 0; i < ALBUMS_COUNT_PER_USER; i++) {
            albums.add(this.getRandomAlbum());
        }

        return albums;
    }

    @Override
    public Album getRandomAlbum() {
        Random random = new Random();
        long albumsCount = this.albumRepository.count();

        if (albumsCount == 0L) {
            throw new IllegalArgumentException(EMPTY_ALBUMS_TABLE);
        }

        return this.albumRepository.findAll()
                .get(random.nextInt((int) albumsCount));
    }
}
