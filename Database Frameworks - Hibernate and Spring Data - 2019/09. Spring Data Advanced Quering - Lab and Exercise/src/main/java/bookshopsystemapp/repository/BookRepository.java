package bookshopsystemapp.repository;

import bookshopsystemapp.domain.entities.AgeRestriction;
import bookshopsystemapp.domain.entities.Book;
import bookshopsystemapp.dto.book.ReducedBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findAllByReleaseDateAfter(LocalDate date);

    List<Book> findAllByReleaseDateBefore(LocalDate date);

    List<Book> findAllByAgeRestriction(AgeRestriction ageRestriction);

    List<Book> findAllByCopiesLessThan(Integer copies);

    List<Book> findAllByPriceLessThanOrPriceGreaterThan(BigDecimal lowerBound, BigDecimal upperBound);

    List<Book> findAllByReleaseDateBeforeOrReleaseDateAfter(LocalDate firstDayOfYear, LocalDate lastDayOfYear);

    List<Book> findAllByTitleIgnoreCaseContains(String text);

    List<Book> findAllByAuthorLastNameIgnoreCaseStartsWith(String authorLastNamePattern);

    @Query("SELECT COUNT(b) FROM Book b WHERE LENGTH(b.title) > :length")
    Integer findBooksCountOfBooksTitleLengthGreaterThan(@Param("length") Integer length);

    /*@Query("SELECT new bookshopsystemapp.dto.book.ReducedBook(b.title, b.editionType, b.ageRestriction, b.price) " +
            "FROM Book b " +
            "WHERE b.title = :title ")*/
    ReducedBook findByTitle(/*@Param("title")*/ String title);  //Two ways to retrieve data from DB

    @Query("UPDATE Book b SET b.copies = b.copies + :copies WHERE b.id = :id")
    @Modifying
    void increaseCopies(@Param("id") Integer id, @Param("copies") Integer copies);

    @Query("DELETE FROM Book b WHERE b.id = :id")
    @Modifying
    void deleteBook(@Param("id") Integer id);
}
