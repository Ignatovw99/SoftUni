package app.repositories;

import app.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    Author findAuthorByFirstNameAndLastName(String firstName, String lastName);
}
