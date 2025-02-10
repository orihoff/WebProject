package BookRepo.dal;

import BookRepo.entity.Book;
import java.util.List;

public interface BookDao {
    List<Book> getAll() throws Exception;
    void save(Book book) throws Exception;
    void update(Book book) throws Exception;
    void delete(String id) throws Exception;
    Book get(String id) throws Exception;
}
