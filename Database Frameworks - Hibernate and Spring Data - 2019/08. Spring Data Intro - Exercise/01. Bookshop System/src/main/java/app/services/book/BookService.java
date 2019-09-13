package app.services.book;

import app.entities.Author;
import app.entities.Book;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface BookService {

    void addNewBook(Book book);

    Long getBooksCount();

    List<String> getAllBooksTitlesAfter(LocalDate date);

    Set<String> getAllAuthorsWithBookBefore(LocalDate date);

    List<Book> getAllBooksFromAuthorWithReleaseDateBeforeSomeYear(Author author);

    List<Book> getAllBooksFromAuthor(Author author);
}
