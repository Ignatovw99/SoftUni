package softuni.exam.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.constant.FilePaths;
import softuni.exam.constant.Messages;
import softuni.exam.domain.dtos.PictureImportRootDto;
import softuni.exam.domain.entities.Picture;
import softuni.exam.repository.PictureRepository;
import softuni.exam.util.FileUtil;
import softuni.exam.util.ValidatorUtil;
import softuni.exam.util.XmlParser;

@Service
public class PictureServiceImpl implements PictureService {

    private final PictureRepository pictureRepository;

    private final FileUtil fileUtil;

    private final XmlParser xmlParser;

    private final ModelMapper modelMapper;

    private final ValidatorUtil validatorUtil;

    @Autowired
    public PictureServiceImpl(
            PictureRepository pictureRepository,
            FileUtil fileUtil,
            XmlParser xmlParser,
            ModelMapper modelMapper,
            ValidatorUtil validatorUtil) {
        this.pictureRepository = pictureRepository;
        this.fileUtil = fileUtil;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validatorUtil = validatorUtil;
    }


    @Override
    public String importPictures() {
        StringBuilder importResult = new StringBuilder();

        this.xmlParser.convertXmlToEntity(PictureImportRootDto.class, FilePaths.PICTURES_FILE_PATH)
                .getPictureImportXmlDtos()
                .stream()
                .map(pictureImportDto -> this.modelMapper.map(pictureImportDto, Picture.class))
                .forEach(picture -> {
                    if (!this.validatorUtil.isValid(picture)) {
                        importResult.append(String.format(Messages.INCORRECT_DATA, picture.getClass().getSimpleName().toLowerCase()))
                                .append(System.lineSeparator());
                        return;
                    }

                    this.pictureRepository.saveAndFlush(picture);
                    importResult.append(
                            String.format(Messages.SUCCESSFULLY_IMPORTED, picture.getClass().getSimpleName().toLowerCase(), picture.getUrl())
                    ).append(System.lineSeparator());
                });

        return importResult.toString().trim();
    }

    @Override
    public boolean areImported() {
        return this.pictureRepository.count() != 0;
    }

    @Override
    public String readPicturesXmlFile() {
        return this.fileUtil.readFile(FilePaths.PICTURES_FILE_PATH);
    }
}