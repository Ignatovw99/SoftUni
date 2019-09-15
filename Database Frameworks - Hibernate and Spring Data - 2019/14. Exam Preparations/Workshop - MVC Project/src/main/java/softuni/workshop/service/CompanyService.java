package softuni.workshop.service;

public interface CompanyService {

    void importCompanies();

    boolean areImported();

    boolean areExported();

    String readCompaniesXmlFile();

    void exportAllCompaniesToXmlFile();

    String readCompaniesJsonFile();

    void exportCompaniesToJson();
}
