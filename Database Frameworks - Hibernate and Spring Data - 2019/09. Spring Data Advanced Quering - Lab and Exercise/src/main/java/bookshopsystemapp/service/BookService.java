package bookshopsystemapp.service;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface BookService {

    void seedBooks() throws IOException;

    List<String> getAllBooksTitlesAfter();

    Set<String> getAllAuthorsWithBookBefore();

    List<String> getAllBookTitlesByAgeRestriction(String inputAgeRestriction);

    List<String> getAllGoldenBooksTitles();

    List<String> getBooksByPriceBoundary();

    List<String> getAllBooksTitlesNotReleasedInGivenYear(int year);

    List<String> getAllBookTitleEditionTypeAndPriceForBooksReleasedBefore(String inputDate);

    String getBooksWithTitlesContain(String text);

    String getBooksWithAuthorLastNameStartsWith(String pattern);

    String getCountOfBookTitlesLongerThanGivenLength(Integer length);

    String getReducedBookDataByTitle(String title);

    String increaseBookCopiesAfterGivenReleaseDate(String releaseDate, int numberOfCopies);

    String removeBookWithCopiesLessThan(Integer inputCopies);
}
