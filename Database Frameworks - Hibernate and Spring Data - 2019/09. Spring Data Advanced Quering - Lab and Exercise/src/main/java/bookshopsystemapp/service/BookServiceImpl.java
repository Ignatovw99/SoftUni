package bookshopsystemapp.service;

import bookshopsystemapp.domain.entities.*;
import bookshopsystemapp.dto.book.ReducedBook;
import bookshopsystemapp.repository.AuthorRepository;
import bookshopsystemapp.repository.BookRepository;
import bookshopsystemapp.repository.CategoryRepository;
import bookshopsystemapp.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import static bookshopsystemapp.constants.Constants.*;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    private final static String BOOKS_FILE_PATH = "C:\\Users\\Lyuboslav\\Downloads\\Databases\\src\\main\\resources\\files\\books.txt";

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;
    private final FileUtil fileUtil;

    @Autowired
    public BookServiceImpl(
            BookRepository bookRepository,
            AuthorRepository authorRepository,
            CategoryRepository categoryRepository,
            FileUtil fileUtil) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
        this.fileUtil = fileUtil;
    }

    @Override
    public void seedBooks() throws IOException {
        if (this.bookRepository.count() != 0) {
            return;
        }

        String[] booksFileContent = this.fileUtil.getFileContent(BOOKS_FILE_PATH);
        for (String line : booksFileContent) {
            String[] lineParams = line.split("\\s+");

            Book book = new Book();
            book.setAuthor(this.getRandomAuthor());

            EditionType editionType = EditionType.values()[Integer.parseInt(lineParams[0])];
            book.setEditionType(editionType);

            LocalDate releaseDate = LocalDate.parse(lineParams[1], DateTimeFormatter.ofPattern("d/M/yyyy"));
            book.setReleaseDate(releaseDate);

            int copies = Integer.parseInt(lineParams[2]);
            book.setCopies(copies);

            BigDecimal price = new BigDecimal(lineParams[3]);
            book.setPrice(price);

            AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(lineParams[4])];
            book.setAgeRestriction(ageRestriction);

            StringBuilder title = new StringBuilder();
            for (int i = 5; i < lineParams.length; i++) {
                title.append(lineParams[i]).append(" ");
            }

            book.setTitle(title.toString().trim());

            Set<Category> categories = this.getRandomCategories();
            book.setCategories(categories);

            this.bookRepository.saveAndFlush(book);
        }
    }

    @Override
    public List<String> getAllBooksTitlesAfter() {
        List<Book> books = this.bookRepository.findAllByReleaseDateAfter(LocalDate.parse("2000-12-31"));

        return books
                .stream()
                .map(Book::getTitle)
                .collect(Collectors.toList());
    }

    @Override
    public Set<String> getAllAuthorsWithBookBefore() {
        List<Book> books = this.bookRepository.findAllByReleaseDateBefore(LocalDate.parse("1990-01-01"));

        return books.stream().map(b -> String.format("%s %s", b.getAuthor().getFirstName(), b.getAuthor().getLastName())).collect(Collectors.toSet());
    }

    private Author getRandomAuthor() {
        Random random = new Random();

        int randomId = random.nextInt((int) (this.authorRepository.count() - 1)) + 1;

        return this.authorRepository.findById(randomId).orElse(null);
    }

    private Set<Category> getRandomCategories() {
        Set<Category> categories = new LinkedHashSet<>();

        Random random = new Random();
        int length = random.nextInt(5);

        for (int i = 0; i < length; i++) {
            Category category = this.getRandomCategory();

            categories.add(category);
        }

        return categories;
    }

    private Category getRandomCategory() {
        Random random = new Random();

        int randomId = random.nextInt((int) (this.categoryRepository.count() - 1)) + 1;

        return this.categoryRepository.findById(randomId).orElse(null);
    }

    @Override
    public List<String> getAllBookTitlesByAgeRestriction(String inputAgeRestriction) {
        boolean isAgeRestrictionCorrect = Arrays.stream(AgeRestriction.values())
                .map(Enum::toString)
                .collect(Collectors.toCollection(ArrayList::new))
                .contains(inputAgeRestriction.toUpperCase());
        if (!isAgeRestrictionCorrect) {
            return new ArrayList<>();
        }
        AgeRestriction ageRestriction = AgeRestriction.valueOf(inputAgeRestriction.toUpperCase());
        return this.bookRepository.findAllByAgeRestriction(ageRestriction)
                .stream()
                .map(Book::getTitle)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getAllGoldenBooksTitles() {
        return this.bookRepository.findAllByCopiesLessThan(BOOK_COPIES)
                .stream()
                .map(Book::getTitle)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getBooksByPriceBoundary() {
        return this.bookRepository.findAllByPriceLessThanOrPriceGreaterThan(PRICE_LOWER_BOUND, PRICE_UPPER_BOUND)
                .stream()
                .map(book -> String.format("%s - $%.2f", book.getTitle(), book.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getAllBooksTitlesNotReleasedInGivenYear(int year) {
        LocalDate firstDayOfYear = LocalDate.of(year, Month.JANUARY, FIRST);
        LocalDate lastDayOfYear = LocalDate.of(year, Month.DECEMBER, LAST);
        return this.bookRepository.findAllByReleaseDateBeforeOrReleaseDateAfter(firstDayOfYear, lastDayOfYear)
                .stream()
                .map(Book::getTitle)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getAllBookTitleEditionTypeAndPriceForBooksReleasedBefore(String inputDate) {
        LocalDate date = LocalDate.parse(inputDate, DATE_TIME_FORMATTER);
        return this.bookRepository.findAllByReleaseDateBefore(date)
                .stream()
                .map(book -> String.format("%s %s %.2f", book.getTitle(), book.getEditionType(), book.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public String getBooksWithTitlesContain(String text) {
        List<String> booksTitles = this.bookRepository.findAllByTitleIgnoreCaseContains(text)
                .stream()
                .map(Book::getTitle)
                .collect(Collectors.toList());
        return String.join(System.lineSeparator(), booksTitles);
    }

    @Override
    public String getBooksWithAuthorLastNameStartsWith(String pattern) {
        List<String> booksTitlesAndAuthors = this.bookRepository.findAllByAuthorLastNameIgnoreCaseStartsWith(pattern)
                .stream()
                .map(book -> String.format("%s (%s %s)",
                        book.getTitle(), book.getAuthor().getFirstName(), book.getAuthor().getLastName()))
                .collect(Collectors.toList());
        return String.join(System.lineSeparator(), booksTitlesAndAuthors);
    }

    @Override
    public String getCountOfBookTitlesLongerThanGivenLength(Integer length) {
        int booksCount = this.bookRepository.findBooksCountOfBooksTitleLengthGreaterThan(length);
        return String.format(BOOKS_COUNT_MESSAGE, booksCount, length);
    }

    @Override
    public String getReducedBookDataByTitle(String title) {
        ReducedBook reducedBook = this.bookRepository.findByTitle(title);
        return reducedBook == null ?
                NO_BOOK_WITH_SUCH_TITLE :
                reducedBook.toString();
    }

    @Override
    public String increaseBookCopiesAfterGivenReleaseDate(String inputReleaseDate, int numberOfCopies) {
        LocalDate releaseDate = LocalDate.parse(inputReleaseDate, DATE_TIME_FORMATTER_FOR_COPIES_INCREASE);
        List<Book> books = this.bookRepository.findAllByReleaseDateAfter(releaseDate);
        books.forEach(book -> {
//          Update book copies with plain code
//            book.setCopies(book.getCopies() + numberOfCopies);
//            this.bookRepository.saveAndFlush(book);

//          Update book copies by query
            this.bookRepository.increaseCopies(book.getId(), numberOfCopies);
        });
        return String.format(INCREASED_COPIES_OF_BOOKS, books.size(), inputReleaseDate, books.size() * numberOfCopies);
    }

    @Override
    public String removeBookWithCopiesLessThan(Integer inputCopies) {
        List<Book> booksToDelete = this.bookRepository.findAllByCopiesLessThan(inputCopies);

        for (int i = booksToDelete.size() - 1; i >= 0 ; i--) {
            Book currentBook = booksToDelete.get(i);
//          Delete by code
//            currentBook.setCategories(new HashSet<>());
//            currentBook.setAuthor(null);
//            this.bookRepository.delete(currentBook);

            //Delete by query
            this.bookRepository.deleteBook(currentBook.getId());
        }
//        this.bookRepository.flush();
        return String.format(DELETED_BOOKS_FROM_DATABAE, booksToDelete.size());
    }
}
