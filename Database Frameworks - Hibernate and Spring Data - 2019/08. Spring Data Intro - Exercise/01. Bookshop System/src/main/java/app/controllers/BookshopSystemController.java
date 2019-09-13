package app.controllers;

import app.entities.*;
import app.services.author.AuthorService;
import app.services.book.BookService;
import app.services.category.CategoryService;
import app.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;

import static app.constants.Global.*;

@Controller
public class BookshopSystemController implements CommandLineRunner {

    private final AuthorService authorService;

    private final CategoryService categoryService;

    private final BookService bookService;

    private final FileUtil fileUtil;

    @Autowired
    public BookshopSystemController(AuthorService authorService, CategoryService categoryService, BookService bookService, FileUtil fileUtil) {
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.bookService = bookService;
        this.fileUtil = fileUtil;
    }

    @Override
    public void run(String... args) {
        if (this.bookService.getBooksCount() == 0L || this.authorService.getAuthorsCount() == 0L) {
            seedDatabase();
        }

        //printAllBookTitlesAfter();

        //printAuthorsWithBookWithReleaseDateBefore();

        printAllAuthorsSortedByTheirBookCount();

        //printAllFromAuthor();
    }

    private void seedDatabase() {
        try {
            Arrays.stream(this.fileUtil.getFileContent(AUTHORS_FILE))
                    .map(line -> line.split(SEPARATOR))
                    .forEach(authorData -> {
                        Author author = new Author();
                        author.setFirstName(authorData[0]);
                        author.setLastName(authorData[1]);
                        author.setBooks(new HashSet<>());
                        this.authorService.registerAuthor(author);
                    });

            Arrays.stream(this.fileUtil.getFileContent(CATEGORIES_FILE))
                    .map(line -> line.split(SEPARATOR))
                    .forEach(categoryData -> {
                        Category category = new Category();
                        category.setName(categoryData[0]);
                        category.setBooks(new HashSet<>());
                        this.categoryService.addNewCategory(category);
                    });

            Arrays.stream(this.fileUtil.getFileContent(BOOKS_FILE))
                    .map(line -> line.split(SEPARATOR))
                    .forEach(bookData -> {
                        Book book = new Book();
                        book.setAuthor(this.authorService.getRandomAuthor());
                        EditionType editionType = EditionType.values()[Integer.parseInt(bookData[0])];
                        book.setEditionType(editionType);
                        LocalDate releaseDate = LocalDate.parse(bookData[1], DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN));
                        book.setReleaseDate(releaseDate);
                        book.setCopies(Integer.parseInt(bookData[2]));
                        book.setPrice(new BigDecimal(bookData[3]));
                        AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(bookData[4])];
                        book.setAgeRestriction(ageRestriction);
                        String title = String.join(DELIMITER, Arrays.copyOfRange(bookData, 5, bookData.length));
                        book.setTitle(title);
                        book.setCategories(this.categoryService.getRandomCategories());
                        this.bookService.addNewBook(book);
                    });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printAllBookTitlesAfter() {
        this.bookService
                .getAllBooksTitlesAfter(LocalDate.parse(LAST_DAY_OF_YEAR_2000, DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN)))
                .forEach(System.out::println);
    }

    private void printAuthorsWithBookWithReleaseDateBefore() {
        this.bookService
                .getAllAuthorsWithBookBefore(LocalDate.parse(FIRST_DAY_OF_YEAR_1990, DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN)))
                .forEach(System.out::println);
    }

    private void printAllAuthorsSortedByTheirBookCount() {
        StringBuilder result = new StringBuilder();
        this.authorService.getAllAuthorsInDatabase()
                .stream()
                .sorted((firstAuthor, secondAuthor) ->
                        Integer.compare(
                                this.bookService.getAllBooksFromAuthor(secondAuthor).size(),
                                this.bookService.getAllBooksFromAuthor(firstAuthor).size())
                )
                .forEach(author ->
                        result.append(String.format("%s %s %d",
                                author.getFirstName(),
                                author.getLastName(),
                                this.bookService.getAllBooksFromAuthor(author).size()))
                                .append(System.lineSeparator())
                );
        System.out.println(result.toString());
    }

    private void printAllFromAuthor() {
        Author author = this.authorService.getAuthorByName(AUTHOR_NAME);
        this.bookService.getAllBooksFromAuthorWithReleaseDateBeforeSomeYear(author)
                .forEach(book -> System.out.println(book.getTitle()));
    }
}
