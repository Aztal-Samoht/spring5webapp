package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository anAuthorRepository;
    private final BookRepository aBookRepository;
    private final PublisherRepository aPublisherRepository;

    public BootStrapData(AuthorRepository anAuthorRepository, BookRepository aBookRepository, PublisherRepository aPublisherRepository) {
        this.anAuthorRepository = anAuthorRepository;
        this.aBookRepository = aBookRepository;
        this.aPublisherRepository = aPublisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Started in Bootstrap");

        Publisher tor = new Publisher("tor", "123 somewhere rd", "SLC", "UT");

        aPublisherRepository.save(tor);

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "1232123123");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        ddd.setPublisher(tor);
        tor.getBooks().add(ddd);

        anAuthorRepository.save(eric);
        aBookRepository.save(ddd);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "012341234");
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        noEJB.setPublisher(tor);
        tor.getBooks().add(noEJB);

        anAuthorRepository.save(rod);
        aBookRepository.save(noEJB);
        aPublisherRepository.save(tor);


        System.out.println("number of books: " + aBookRepository.count());
        System.out.println("number of publishers: " + aPublisherRepository.count());
        System.out.println("number of books from tor: " + tor.getBooks().size());

    }
}
