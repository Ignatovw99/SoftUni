package bookshopsystemapp.service;

import bookshopsystemapp.domain.entities.Author;
import bookshopsystemapp.repository.AuthorRepository;
import bookshopsystemapp.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static bookshopsystemapp.constants.Constants.TOTAL_BOOKS_AMOUNT_WRITTEN_BY_AUTHOR;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final static String AUTHORS_FILE_PATH = "C:\\Users\\Lyuboslav\\Downloads\\Databases\\src\\main\\resources\\files\\authors.txt";

    private final AuthorRepository authorRepository;
    private final FileUtil fileUtil;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, FileUtil fileUtil) {
        this.authorRepository = authorRepository;
        this.fileUtil = fileUtil;
    }

    @Override
    public void seedAuthors() throws IOException {
        if (this.authorRepository.count() != 0) {
            return;
        }

        String[] authorFileContent = this.fileUtil.getFileContent(AUTHORS_FILE_PATH);
        for (String line : authorFileContent) {
            String[] names = line.split("\\s+");

            Author author = new Author();
            author.setFirstName(names[0]);
            author.setLastName(names[1]);

            this.authorRepository.saveAndFlush(author);
        }
    }

    @Override
    public String getAllAuthorsWithFirstNameEndingWith(String namePattern) {
        List<String> authorsNames = this.authorRepository.findAllByFirstNameEndingWith(namePattern)
                .stream()
                .map(author -> String.format("%s %s", author.getFirstName(), author.getLastName()))
                .collect(Collectors.toList());
        return String.join(System.lineSeparator(), authorsNames);
    }

    @Override
    public List<String> getAllAuthorsWithTotalBookCopies() {
        return this.authorRepository.findAllWithTotalBookCopies()
                .stream()
                .map(record -> String.format("%s %s - %s", record[0], record[1], record[2]))
                .collect(Collectors.toList());
    }

    @Override
    public String getTotalAmountOfBooksWrittenBy(String firstName, String lastName) {
        int amountOfBooks = this.authorRepository.findTotalAmountOfBooksWrittenBy(firstName, lastName);
        return String.format(TOTAL_BOOKS_AMOUNT_WRITTEN_BY_AUTHOR, firstName, lastName, amountOfBooks);
    }
}
