package app.ccb.services;

import app.ccb.constants.Constants;
import app.ccb.constants.FilePaths;
import app.ccb.domain.dtos.BranchImportDto;
import app.ccb.domain.entities.Branch;
import app.ccb.repositories.BranchRepository;
import app.ccb.util.FileUtil;
import app.ccb.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;

    private final FileUtil fileUtil;

    private final Gson gson;

    private final ModelMapper modelMapper;

    private final ValidationUtil validationUtil;

    @Autowired
    public BranchServiceImpl(BranchRepository branchRepository, FileUtil fileUtil, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.branchRepository = branchRepository;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }


    @Override
    public Boolean branchesAreImported() {
        return this.branchRepository.count() != 0;
    }

    @Override
    public String readBranchesJsonFile() {
        return this.fileUtil.readFile(FilePaths.BRANCHES_JSON_FILE_PATH);
    }

    @Override
    public String importBranches(String branchesJson) {
        StringBuilder importResult = new StringBuilder();

        Arrays.stream(this.gson.fromJson(branchesJson, BranchImportDto[].class))
                .map(branchImportDto -> this.modelMapper.map(branchImportDto, Branch.class))
                .forEach(branch -> {
                    if (!this.validationUtil.isValid(branch)) {
                        importResult.append(Constants.ERROR_MESSAGE)
                                .append(System.lineSeparator());
                        return;
                    }

                    importResult.append(String.format(Constants.SUCCESSFULLY_IMPORTED_MESSAGE,
                            String.format("Branch - %s", branch.getName()))
                    )
                            .append(System.lineSeparator());
                    this.branchRepository.saveAndFlush(branch);
                });

        return importResult.toString().trim();
    }
}
