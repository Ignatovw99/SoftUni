package softuni.workshop.service;

public interface ProjectService {

    void importProjects();

    boolean areImported();

    boolean areExported();

    String readProjectsXmlFile();

    String exportFinishedProjects();

    String readProjectsJsonFile();

    String exportProjectsWithNameEnding();

    void exportProjectsToJson();
}
