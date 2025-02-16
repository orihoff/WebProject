package BookRepo.dal;

import BookRepo.entity.Book;
import BookRepo.exceptions.BookAlreadyExistsException;
import BookRepo.exceptions.BookNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class BookFileDao implements BookDao {

    // נוכל להחליף ע"י פרופרטי חיצוני. ברירת מחדל: "./books.dat"
    @Value("${bookFilePath:./books.dat}")
    private String filePath;

    private int currentID;
    private List<Book> books;

    @PostConstruct
    private void initialize() {
        loadBooks();
    }

    @SuppressWarnings("unchecked")
    private synchronized void loadBooks() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            currentID = ois.readInt();
            books = (List<Book>) ois.readObject();
            System.out.println("Books loaded from file");
        } catch (FileNotFoundException e) {
            currentID = 0;
            books = new ArrayList<>();
        } catch (Exception e) {
            throw new RuntimeException("Error: Could not load books from file.", e);
        }
    }

    private synchronized void saveBooksToFile() throws Exception {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeInt(currentID);
            oos.writeObject(books);
        } catch (Exception e) {
            throw new RuntimeException("Error: Could not save book to file.", e);
        }
    }

    @Override
    public synchronized List<Book> getAll() throws Exception {
        // נחזיר עותק ממויין של הרשימה
        List<Book> sortedBooks = new ArrayList<>(books);
        Collections.sort(sortedBooks);
        return sortedBooks;
    }

    @Override
    public synchronized void save(Book book) throws Exception {
        // אם הספר מגיע בלי ID - נייצר ID רץ
        if (book.getId() == null || book.getId().isEmpty()) {
            currentID++;
            book.setId(String.valueOf(currentID));
        }
        // בדיקה אם כבר קיים ספר עם אותו ID
        if (books.contains(book)) {
            throw new BookAlreadyExistsException(book.getId());
        }
        books.add(book);
        saveBooksToFile();
    }

    @Override
    public synchronized void update(Book book) throws Exception {
        Book existingBook = get(book.getId());
        if (existingBook == null) {
            throw new BookNotFoundException(book.getId());
        }
        books.remove(existingBook);
        books.add(book);
        saveBooksToFile();
    }

    @Override
    public synchronized void delete(String id) throws Exception {
        Book book = get(id);
        if (book == null) {
            throw new BookNotFoundException(id);
        }
        books.remove(book);
        saveBooksToFile();
    }

    @Override
    public synchronized Book get(String id) throws Exception {
        for (Book book : books) {
            if (book.getId().equals(id)) {
                return book;
            }
        }
        throw new BookNotFoundException(id);
    }
}
