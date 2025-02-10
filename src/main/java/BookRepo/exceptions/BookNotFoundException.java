
package BookRepo.exceptions;
public class BookNotFoundException extends BookRepoException {
    public BookNotFoundException(String id) {
        super("Book with ID #" + id + " not found.");
    }
}
