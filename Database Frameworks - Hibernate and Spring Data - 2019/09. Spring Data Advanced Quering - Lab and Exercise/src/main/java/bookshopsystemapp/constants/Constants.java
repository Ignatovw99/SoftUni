package bookshopsystemapp.constants;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;

public final class Constants {

    public static final String SEPARATOR = "\\s+";

    public static final Integer BOOK_COPIES = 5000;

    public static final BigDecimal PRICE_LOWER_BOUND = BigDecimal.valueOf(5);

    public static final BigDecimal PRICE_UPPER_BOUND = BigDecimal.valueOf(40);

    public static final Integer FIRST = 1;

    public static final Integer LAST = 31;

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static final String BOOKS_COUNT_MESSAGE = "There are %d books with longer title than %d symbols.";

    public static final String NO_BOOK_WITH_SUCH_TITLE = "There is no book with such title.";

    public static final DateTimeFormatter DATE_TIME_FORMATTER_FOR_COPIES_INCREASE = DateTimeFormatter.ofPattern("dd MMM yyyy");

    public static final String INCREASED_COPIES_OF_BOOKS = "%d books are released after %s, so total of %d book copies were added.";

    public static final String TOTAL_BOOKS_AMOUNT_WRITTEN_BY_AUTHOR = "%s %s has written %d books.";

    public static final String DELETED_BOOKS_FROM_DATABAE = "%d books were deleted from database.";
}
