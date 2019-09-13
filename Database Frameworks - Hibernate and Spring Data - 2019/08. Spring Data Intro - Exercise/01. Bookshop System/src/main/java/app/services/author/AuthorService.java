package app.services.author;

import app.entities.Author;

import java.util.List;

public interface AuthorService {

    void registerAuthor(Author author);

    Long getAuthorsCount();

    Author getRandomAuthor();

    Author getAuthorByName(String name);

    List<Author> getAllAuthorsInDatabase();
}
