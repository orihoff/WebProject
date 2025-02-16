package BookRepo.service;

import BookRepo.dal.BookDao;
import BookRepo.entity.Book;
import BookRepo.exceptions.BookNotFoundException;
import BookRepo.exceptions.ExceedTitleLengthException;
import BookRepo.exceptions.InvalidBookYearException;
import BookRepo.exceptions.MissingRequiredBookFieldsException;
import BookRepo.exceptions.StorageLimitExceededException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;   
@Service
@PropertySource("classpath:params.properties")
public class BookService {

    @Value("${maxBooksAllowed}")
    private int maxNumofBooks;

    @Value("${maxWordsInBook}")
    private int maxCharactersinBookName;

    @Value("${whenWasTheFirstBookwriten}")
    private int MinYear;

    private final BookDao bookDao;

    @Autowired
    public BookService(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public List<Book> getAllBooks() throws Exception {
        return bookDao.getAll();
    }

    public void addBook(Book book) throws Exception {
    	if (book.getId() == null || book.getId().isEmpty()) {
            // Generate a unique ID (e.g., UUID)
            book.setId(UUID.randomUUID().toString());
        }
        if(bookDao.getAll().size() >= maxNumofBooks) {
            throw new StorageLimitExceededException();
        }
        validateBookFields(book.getTitle(), book.getAuthor(), book.getGenre(), book.getPublicationYear());
        bookDao.save(book);
    }

    public void updateBook(String id, String title, String author, String genre, int year) throws Exception {
        Book existingBook = bookDao.get(id);
        if(existingBook == null) {
            throw new BookNotFoundException(id);
        }
        validateBookFields(title, author, genre, year);
        existingBook.setTitle(title);
        existingBook.setAuthor(author);
        existingBook.setGenre(genre);
        existingBook.setPublicationYear(year);
        bookDao.update(existingBook);
    }

    public void deleteBook(String id) throws Exception {
        bookDao.delete(id);
    }

    public Book getBookById(String id) throws Exception {
        return bookDao.get(id);
    }

    public void validateBookFields(String title, String author, String genre, int year) throws Exception {
        if(title == null || title.isEmpty() || author == null || author.isEmpty() || genre == null || genre.isEmpty()) {
            throw new MissingRequiredBookFieldsException();
        }
        if(year < MinYear) {
            throw new InvalidBookYearException(MinYear);
        }
        if(title.length() > maxCharactersinBookName) {
            throw new ExceedTitleLengthException(maxCharactersinBookName);
        }
    }

}
