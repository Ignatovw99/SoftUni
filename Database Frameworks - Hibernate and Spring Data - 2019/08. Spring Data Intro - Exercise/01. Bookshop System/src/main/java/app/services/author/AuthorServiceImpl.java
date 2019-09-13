package app.services.author;

import app.constants.Global;
import app.constants.Messages;
import app.entities.Author;
import app.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Random;

@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {

    final private AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void registerAuthor(Author author) {
        this.authorRepository.saveAndFlush(author);
    }

    @Override
    public Long getAuthorsCount() {
        return this.authorRepository.count();
    }

    @Override
    public Author getRandomAuthor() {
        long authorsCount = this.authorRepository.count();
        if (authorsCount == 0L) {
            throw new IllegalArgumentException(Messages.NO_AUTHORS_IN_DATABASE);
        }

        Random random = new Random();
        long authorId = random.nextInt((int) authorsCount) + 1;
        return this.authorRepository.getOne(authorId);
    }

    @Override
    public Author getAuthorByName(String name) {
        String[] names = name.split(Global.SEPARATOR);
        String firstName = names[0];
        String lastName = names[1];
        return this.authorRepository.findAuthorByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public List<Author> getAllAuthorsInDatabase() {
        return this.authorRepository.findAll();
    }
}
