package softuni.workshop.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.workshop.domain.dtos.binding.ProjectRootXmlDto;
import softuni.workshop.domain.dtos.view.ProjectExportDto;
import softuni.workshop.domain.entities.Company;
import softuni.workshop.domain.entities.Project;
import softuni.workshop.repository.CompanyRepository;
import softuni.workshop.repository.ProjectRepository;
import softuni.workshop.util.FileUtil;
import softuni.workshop.util.JsonParser;
import softuni.workshop.util.ValidatorUtil;
import softuni.workshop.util.XmlParser;

import javax.validation.ConstraintViolation;
import javax.xml.bind.JAXBException;

import java.util.List;
import java.util.stream.Collectors;

import static softuni.workshop.constant.FilesPaths.PROJECTS_JSON_PATH;
import static softuni.workshop.constant.FilesPaths.PROJECTS_XML_PATH;


@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    private final ModelMapper modelMapper;

    private final XmlParser xmlParser;

    private final JsonParser jsonParser;

    private final ValidatorUtil validatorUtil;

    private final FileUtil fileUtil;

    private final CompanyRepository companyRepository;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository, ModelMapper modelMapper, XmlParser xmlParser, JsonParser jsonParser, ValidatorUtil validatorUtil, FileUtil fileUtil, CompanyRepository companyRepository) {
        this.projectRepository = projectRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.jsonParser = jsonParser;
        this.validatorUtil = validatorUtil;
        this.fileUtil = fileUtil;
        this.companyRepository = companyRepository;
    }

    @Override
    public void importProjects(){
        StringBuilder importConstraintViolations = new StringBuilder();
        try {
            this.xmlParser.convertXmlToObject(ProjectRootXmlDto.class, PROJECTS_XML_PATH)
                    .getProjectImportDtos()
                    .stream()
                    .map(projectImportDto -> this.modelMapper.map(projectImportDto, Project.class))
                    .forEach(project -> {
                        if (!this.validatorUtil.isValid(project)) {
                            this.validatorUtil.validate(project)
                                    .stream()
                                    .map(ConstraintViolation::getMessage)
                                    .forEach(importConstraintViolations::append);
                        } else {
                            Company company = this.companyRepository.findByName(project.getCompany().getName());
                            project.setCompany(company);
                            this.projectRepository.saveAndFlush(project);
                        }
                    });
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        System.out.println(importConstraintViolations);
    }

    @Override
    public boolean areImported() {
        return this.projectRepository.count() > 0;
    }

    @Override
    public boolean areExported() {
        return this.readProjectsJsonFile().length() > 0;
    }

    @Override
    public String readProjectsXmlFile() {
        return this.fileUtil.readFile(PROJECTS_XML_PATH);
    }

    @Override
    public String exportFinishedProjects(){
        StringBuilder stringBuilder = new StringBuilder();

        this.projectRepository.findAllByIsFinishedTrue()
                .forEach(project ->
                        stringBuilder.append(String.format("Project name: %s", project.getName())).append(System.lineSeparator())
                                .append(String.format("    Description: %s", project.getDescription())).append(System.lineSeparator())
                                .append(String.format("    Payment: %.2f", project.getPayment())).append(System.lineSeparator())
                );

        return stringBuilder.toString().trim();
    }

    @Override
    public String readProjectsJsonFile() {
        return this.fileUtil.readFile(PROJECTS_JSON_PATH);
    }

    @Override
    public String exportProjectsWithNameEnding() {
        StringBuilder stringBuilder = new StringBuilder();

        this.projectRepository.findAllByNameEndingWith("re")
                .forEach(project ->
                        stringBuilder.append(String.format("Project name: %s", project.getName())).append(System.lineSeparator())
                                .append(String.format("\tDescription: %s", project.getDescription())).append(System.lineSeparator())
                );

        return stringBuilder.toString().trim();
    }

    @Override
    public void exportProjectsToJson() {
        List<ProjectExportDto> projectExportDtos = this.projectRepository.findAll()
                .stream()
                .map(project -> this.modelMapper.map(project, ProjectExportDto.class))
                .collect(Collectors.toList());

        this.fileUtil.writeToFile(
                this.jsonParser.convertEntityToJson(projectExportDtos),
                PROJECTS_JSON_PATH
        );
    }
}
