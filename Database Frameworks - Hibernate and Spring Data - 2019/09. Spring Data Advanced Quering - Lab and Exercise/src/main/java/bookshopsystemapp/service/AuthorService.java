package bookshopsystemapp.service;

import java.io.IOException;
import java.util.List;

public interface AuthorService {

    void seedAuthors() throws IOException;

    String getAllAuthorsWithFirstNameEndingWith(String namePattern);

    List<String> getAllAuthorsWithTotalBookCopies();

    String getTotalAmountOfBooksWrittenBy(String firstName, String lastName);
}
