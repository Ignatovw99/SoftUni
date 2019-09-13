package app.services.book;

import app.entities.Author;
import app.entities.Book;
import app.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void addNewBook(Book book) {
        this.bookRepository.saveAndFlush(book);
    }

    @Override
    public Long getBooksCount() {
        return this.bookRepository.count();
    }

    @Override
    public List<String> getAllBooksTitlesAfter(LocalDate date) {
        return this.bookRepository.findAllByReleaseDateAfter(date)
                .stream()
                .map(Book::getTitle)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public Set<String> getAllAuthorsWithBookBefore(LocalDate date) {
        return this.bookRepository.findAllByReleaseDateBefore(date)
                .stream()
                .map(book -> String.format("%s %s", book.getAuthor().getFirstName(), book.getAuthor().getLastName()))
                .collect(Collectors.toCollection(HashSet::new));
    }

    @Override
    public List<Book> getAllBooksFromAuthorWithReleaseDateBeforeSomeYear(Author author) {
        return this.bookRepository.findAllByAuthorOrderByReleaseDateDescTitleAsc(author);
    }

    @Override
    public List<Book> getAllBooksFromAuthor(Author author) {
        return this.bookRepository.findAllByAuthor(author);
    }
}
