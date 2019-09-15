package app.terminal;

import app.domain.dto.PersonJsonDto;
import app.service.PersonService;
import app.utils.FileUtils;
import app.utils.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class Terminal implements CommandLineRunner {

    private static final String JSON_FILES_PATH = System.getProperty("user.dir") + "/src/main/resources/json/";
    private static final String EXPORT_JSON_FILE = "export.json";
    private static final String IMPORT_JSON_FILE = "import.json";

    private PersonService personService;

    private JsonParser jsonParser;

    @Autowired
    public Terminal(PersonService personService, JsonParser jsonParser) {
        this.personService = personService;
        this.jsonParser = jsonParser;
    }

    @Override
    public void run(String... strings) throws Exception {
        this.exportJavaClassToJsonFile();
        this.importJsonFileToJavaClass();
    }

    private void exportJavaClassToJsonFile() {
        PersonJsonDto personJsonDto = this.personService.getById(1L);
        if (personJsonDto != null) {
            FileUtils.writeToFile(this.jsonParser.toJson(personJsonDto), JSON_FILES_PATH + EXPORT_JSON_FILE);
        }
    }

    private void importJsonFileToJavaClass() {
        PersonJsonDto[] personJsonDtos = this.jsonParser.fromJson(
                FileUtils.readFile(JSON_FILES_PATH + IMPORT_JSON_FILE),
                PersonJsonDto[].class
        );

        Arrays.stream(personJsonDtos)
                .forEach(personJsonDto -> this.personService.save(personJsonDto));
    }
}
