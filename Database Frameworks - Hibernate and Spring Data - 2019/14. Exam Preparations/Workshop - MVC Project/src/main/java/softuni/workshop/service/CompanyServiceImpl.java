package softuni.workshop.service;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.workshop.domain.dtos.binding.CompanyImportDto;
import softuni.workshop.domain.dtos.binding.CompanyRootXmlDto;
import softuni.workshop.domain.dtos.view.CompanyExportDto;
import softuni.workshop.domain.entities.Company;
import softuni.workshop.repository.CompanyRepository;
import softuni.workshop.util.FileUtil;
import softuni.workshop.util.JsonParser;
import softuni.workshop.util.ValidatorUtil;
import softuni.workshop.util.XmlParser;

import javax.validation.ConstraintViolation;
import javax.xml.bind.JAXBException;

import java.util.List;
import java.util.stream.Collectors;

import static softuni.workshop.constant.FilesPaths.*;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    private final ModelMapper modelMapper;

    private final XmlParser xmlParser;

    private final JsonParser jsonParser;

    private final ValidatorUtil validatorUtil;

    private final FileUtil fileUtil;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository, ModelMapper modelMapper, XmlParser xmlParser, JsonParser jsonParser, ValidatorUtil validatorUtil, FileUtil fileUtil) {
        this.companyRepository = companyRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.jsonParser = jsonParser;
        this.validatorUtil = validatorUtil;
        this.fileUtil = fileUtil;
    }

    @Override
    public void importCompanies() {
        try {
            StringBuilder importConstraintViolations = new StringBuilder();
            this.xmlParser.convertXmlToObject(CompanyRootXmlDto.class, COMPANIES_XML_PATH)
                    .getCompanyImportDtos()
                    .stream()
                    .map(companyImportDto -> this.modelMapper.map(companyImportDto, Company.class))
                    .forEach(company -> {
                        if (!this.validatorUtil.isValid(company)) {
                            this.validatorUtil.validate(company)
                                    .stream()
                                    .map(ConstraintViolation::getMessage)
                                    .forEach(importConstraintViolations::append);
                        } else {
                            this.companyRepository.saveAndFlush(company);
                        }
                    });
            System.out.println(importConstraintViolations.toString());
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean areImported() {
        return this.companyRepository.count() > 0L;
    }

    @Override
    public boolean areExported() {
        return this.readCompaniesJsonFile().length() > 0;
    }

    @Override
    public String readCompaniesXmlFile() {
        return this.fileUtil.readFile(COMPANIES_XML_PATH);
    }

    @Override
    public String readCompaniesJsonFile() {
        return this.fileUtil.readFile(COMPANIES_JSON_PATH);
    }

    @Override
    public void exportAllCompaniesToXmlFile() {
        CompanyRootXmlDto companyRootXmlDto = new CompanyRootXmlDto();

        List<CompanyImportDto> companyImportDtos = this.companyRepository.findAll()
                .stream()
                .map(company -> this.modelMapper.map(company, CompanyImportDto.class))
                .collect(Collectors.toList());
        companyRootXmlDto.setCompanyImportDtos(companyImportDtos);

        try {
            this.fileUtil.writeToFile(
                    this.xmlParser.convertObjectToXml(companyRootXmlDto),
                    EXPORTED_COMPANIES_XML_PATH
            );
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void exportCompaniesToJson() {
        List<CompanyExportDto> companyExportDtos = this.companyRepository.findAll()
                .stream()
                .map(company -> this.modelMapper.map(company, CompanyExportDto.class))
                .collect(Collectors.toList());

        this.fileUtil.writeToFile(
                this.jsonParser.convertEntityToJson(companyExportDtos),
                COMPANIES_JSON_PATH
        );
    }
}
