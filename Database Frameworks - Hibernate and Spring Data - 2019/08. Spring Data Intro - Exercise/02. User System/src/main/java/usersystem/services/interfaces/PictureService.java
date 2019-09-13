package usersystem.services.interfaces;

import usersystem.entities.Picture;

import java.util.Set;

public interface PictureService {

    Long getPicturesCount();

    void seedPicturesTable();

    void addNewPictureToDatabase(Picture picture);

    Set<Picture> getRandomPictures();
}
