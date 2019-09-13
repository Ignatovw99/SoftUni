package bookshopsystemapp.controller;

import bookshopsystemapp.service.AuthorService;
import bookshopsystemapp.service.BookService;
import bookshopsystemapp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.io.BufferedReader;

import static bookshopsystemapp.constants.Constants.SEPARATOR;
import static bookshopsystemapp.constants.Messages.*;

@Controller
public class BookshopController implements CommandLineRunner {

    private final BufferedReader reader;

    private final AuthorService authorService;

    private final CategoryService categoryService;

    private final BookService bookService;

    @Autowired
    public BookshopController(BufferedReader reader, AuthorService authorService, CategoryService categoryService, BookService bookService) {
        this.reader = reader;
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... strings) throws Exception {
        this.authorService.seedAuthors();
        this.categoryService.seedCategories();
        this.bookService.seedBooks();

        System.out.print(EXERCISE_CHOICE);
        int numberOfExercise = Integer.parseInt(this.reader.readLine());

        switch (numberOfExercise) {
            case 1:
                System.out.print(ENTER_AGE_RESTRICTION);
                String inputAgeRestriction = this.reader.readLine();
                this.printBooksTitlesByAgeRestriction(inputAgeRestriction);
                break;
            case 2:
                this.printGoldenBooksTitles();
                break;
            case 3:
                this.printBooksByPrice();
                break;
            case 4:
                System.out.print(ENTER_YEAR);
                int year = Integer.parseInt(this.reader.readLine());
                this.printAllBooksNotReleasedInGivenYear(year);
                break;
            case 5:
                System.out.print(ENTER_DATE);
                String dateAsString = this.reader.readLine();
                this.printBooksInfoReleasedBefore(dateAsString);
                break;
            case 6:
                System.out.print(ENTER_FIRST_NAME_PATTERN);
                String namePattern = this.reader.readLine();
                this.printAllAuthorsWithFirstNameEndingWith(namePattern);
                break;
            case 7:
                System.out.print(ENTER_BOOK_TITLE_PATTERN);
                String text = this.reader.readLine();
                this.printBooksWithTitlesContain(text);
                break;
            case 8:
                System.out.print(ENTER_AUTHOR_LAST_NAME_PATTERN);
                String lastNamePattern = this.reader.readLine();
                this.printBooksWithAuthorLastNameStartsWith(lastNamePattern);
                break;
            case 9:
                System.out.print(ENTER_MIN_TITLE_LENGTH);
                int minTitleLength = Integer.parseInt(this.reader.readLine());
                this.printBooksCount(minTitleLength);
                break;
            case 10:
                printAuthorsWithTotalBookCopies();
                break;
            case 11:
                System.out.print(ENTER_BOOK_TITLE);
                String bookTitle = this.reader.readLine();
                this.printReducedBookData(bookTitle);
                break;
            case 12:
                System.out.println(INCREASE_BOOKS_COPIES);
                System.out.print(ENTER_DATE_FOR_COPIES_INCREASE);
                String date = this.reader.readLine();
                System.out.print(ENTER_COPIES_VALUE);
                int copies = Integer.parseInt(this.reader.readLine());
                this.increaseBookCopiesAfterGivenReleaseDate(date, copies);
                break;
            case 13:
                System.out.print(ENTER_COPIES_VALUE_TO_REMOVE_BOOKS);
                int copiesValue = Integer.parseInt(this.reader.readLine());
                this.deleteBooksWithCopiesLessThan(copiesValue);
                break;
            case 14:
                System.out.print(ENTER_AUTHOR_FIRST_AND_LAST_NAMES);
                String[] authorNames = this.reader.readLine().split(SEPARATOR);
                this.printAmountOfBooksWrittenByAuthor(authorNames[0], authorNames[1]);
                break;
                default:
                    System.out.println(INVALID_EXERCISE);
                    break;
        }
    }

    public void printBooksTitlesByAgeRestriction(String inputAgeRestriction) {
        this.bookService.getAllBookTitlesByAgeRestriction(inputAgeRestriction)
                .forEach(System.out::println);
    }

    public void printGoldenBooksTitles() {
        this.bookService.getAllGoldenBooksTitles()
                .forEach(System.out::println);
    }

    public void printBooksByPrice() {
        this.bookService.getBooksByPriceBoundary()
                .forEach(System.out::println);
    }

    public void printAllBooksNotReleasedInGivenYear(int year) {
        this.bookService.getAllBooksTitlesNotReleasedInGivenYear(year)
                .forEach(System.out::println);
    }

    public void printBooksInfoReleasedBefore(String inputDate) {
        this.bookService.getAllBookTitleEditionTypeAndPriceForBooksReleasedBefore(inputDate)
                .forEach(System.out::println);
    }

    public void printAllAuthorsWithFirstNameEndingWith(String namePattern) {
        System.out.println(this.authorService.getAllAuthorsWithFirstNameEndingWith(namePattern));
    }

    public void printBooksWithTitlesContain(String text) {
        System.out.println(this.bookService.getBooksWithTitlesContain(text));
    }

    public void printBooksWithAuthorLastNameStartsWith(String text) {
        System.out.println(this.bookService.getBooksWithAuthorLastNameStartsWith(text));
    }

    public void printBooksCount(Integer length) {
        System.out.println(this.bookService.getCountOfBookTitlesLongerThanGivenLength(length));
    }

    public void printAuthorsWithTotalBookCopies() {
        this.authorService.getAllAuthorsWithTotalBookCopies()
                .forEach(System.out::println);
    }

    public void printReducedBookData(String title) {
        System.out.println(this.bookService.getReducedBookDataByTitle(title));
    }

    public void increaseBookCopiesAfterGivenReleaseDate(String inputReleaseDate, int copies) {
        System.out.println(this.bookService.increaseBookCopiesAfterGivenReleaseDate(inputReleaseDate, copies));
    }

    public void deleteBooksWithCopiesLessThan(int copies) {
        System.out.println(this.bookService.removeBookWithCopiesLessThan(copies));
    }

    public void printAmountOfBooksWrittenByAuthor(String firstName, String lastName) {
        System.out.println(this.authorService.getTotalAmountOfBooksWrittenBy(firstName, lastName));
    }
}
