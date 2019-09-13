package bookshopsystemapp.repository;

import bookshopsystemapp.domain.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

    List<Author> findAllByFirstNameEndingWith(String namePattern);

    @Query("SELECT a.firstName, a.lastName, SUM(b.copies) " +
            "FROM Author a " +
            "JOIN a.books b " +
            "GROUP BY a.id " +
            "ORDER BY SUM(b.copies) DESC")
    List<Object[]> findAllWithTotalBookCopies();

    @Query(value = "CALL usp_author_total_amount_of_books(:first_name, :last_name)", nativeQuery = true)
    Integer findTotalAmountOfBooksWrittenBy(@Param("first_name") String firstName, @Param("last_name") String lastName);
}
