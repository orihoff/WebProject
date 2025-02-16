package BookRepo.dal;

import BookRepo.entity.Book;
import BookRepo.exceptions.BookAlreadyExistsException;
import BookRepo.exceptions.BookNotFoundException;

import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;

@Repository
public class BookFileDao implements BookDao {

    private final String filePath = "./books.dat";
    private int currentID;
    private List<Book> books;

    
    @PostConstruct
    private void initialize() {
        loadBooks();
    }
    
    
    @SuppressWarnings("unchecked")
    private void loadBooks() {
    	// try-with-resources: try what is written in () without the need to manually close it using a finally block
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            currentID = ois.readInt();
        	books = (List<Book>) ois.readObject();
        	System.out.println("hello");
        } catch (FileNotFoundException e) {
            currentID = 0;
        	books = new ArrayList<>();
        } catch (Exception e) {
            throw new RuntimeException("Error: Could not load books from file.", e);
        }
    }
    
    private void saveBooksToFile() throws Exception {
    	// try-with-resources: try what is written in () without the need to manually close it using a finally block
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
        	oos.writeInt(currentID);
        	oos.writeObject(books);
        } catch (Exception e) {
            throw new RuntimeException("Error: Could not save book to file.", e);
        }
    }
    
    @Override
    public List<Book> getAll() throws Exception {
        Collections.sort(books);
        return new ArrayList<>(books); // ArrayList class implements 'Serializable' interface by default
    }

    @Override
    public void save(Book book) throws Exception {
        if (books.contains(book)) {
            throw new BookAlreadyExistsException(book.getId());
        }
        currentID++;
        book.setId(String.valueOf(currentID));
        books.add(book);
        saveBooksToFile();
    }

    @Override
    public void update(Book book) throws Exception {
        Book existingBook = get(book.getId());
        if (existingBook == null) {
            throw new BookNotFoundException(book.getId());
        }
        books.remove(existingBook);
        books.add(book);	
        saveBooksToFile();
    }

    @Override
    public void delete(String id) throws Exception {
        Book book = get(id);
        if (book == null) {
            throw new BookNotFoundException(id);
        }
        books.remove(book);
        saveBooksToFile();
    }

    @Override
    public Book get(String id) throws Exception {
        for (Book book : books) {
            if (book.getId().equals(id)) {
                return book;
            }
        }
        throw new BookNotFoundException(id);
    }
}