package BookRepo.exceptions;

public class BookAlreadyExistsException extends BookRepoException {
    public BookAlreadyExistsException(String id) {
        super("Book with ID #" + id + " already exists in the system.");
    }
}
