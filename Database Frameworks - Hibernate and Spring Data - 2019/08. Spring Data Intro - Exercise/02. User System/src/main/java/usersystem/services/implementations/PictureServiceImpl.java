package usersystem.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import usersystem.entities.Picture;
import usersystem.repositories.PictureRepository;
import usersystem.services.interfaces.PictureService;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static usersystem.constants.Constants.EMPTY_PICTURES_TABLE;
import static usersystem.constants.PictureValues.*;

@Service
@Transactional
public class PictureServiceImpl implements PictureService {

    private PictureRepository pictureRepository;

    @Autowired
    public PictureServiceImpl(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    @Override
    public Long getPicturesCount() {
        return this.pictureRepository.count();
    }

    @Override
    public void seedPicturesTable() {
        for (int i = 0; i < PICTURES_COUNT; i++) {
            Picture picture = new Picture();
            picture.setCaption(CAPTION + i);
            picture.setImage(new byte[1024]);
            picture.setPathOnFileSystem(PATH + i);
            picture.setAlbums(new HashSet<>());
            this.addNewPictureToDatabase(picture);
        }
    }

    @Override
    public void addNewPictureToDatabase(Picture picture) {
        this.pictureRepository.saveAndFlush(picture);
    }

    @Override
    public Set<Picture> getRandomPictures() {
        Set<Picture> pictures = new HashSet<>();

        for (int i = 0; i < PICTURES_COUNT_PER_ALBUM; i++) {
            pictures.add(this.getRandomPicture());
        }

        return pictures;
    }

    private Picture getRandomPicture() {
        Random random = new Random();

        long picturesCount = this.pictureRepository.count();
        if (picturesCount == 0L) {
            throw new IllegalArgumentException(EMPTY_PICTURES_TABLE);
        }

        long pictureId = random.nextInt((int) picturesCount) + 1;
        return this.pictureRepository.findById(pictureId)
                .orElse(null);
    }
}
