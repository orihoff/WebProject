package BookRepo.controller;

import BookRepo.entity.Book;
import BookRepo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // ���� ����� ����� � ������� �� ��� "home.jsp"
    @GetMapping("/")
    public String listBooks(Model model) throws Exception {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "home";
    }

    // ���� ����� ����� ��� ���
    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("book", new Book());

        System.out.println("new book");
        
        return "book-form";
    }

    // ����� ��� ���
    @PostMapping("/save")
    public String saveBook(@ModelAttribute("book") Book book) throws Exception {
        bookService.addBook(book);
        return "redirect:/";
    }

    // ���� ����� ����� ��� ����
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) throws Exception {
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);
        return "book-form";
    }

    // ����� ��� ����
    @PostMapping("/update")
    public String updateBook(@ModelAttribute("book") Book book) throws Exception {
        bookService.updateBook(book.getId(), book.getTitle(), book.getAuthor(), book.getGenre(), book.getPublicationYear());
        return "redirect:/";
    }

    // ����� ���
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable String id) throws Exception {
        bookService.deleteBook(id);
        return "redirect:/";
    }

    // ���� ���� ���
    @GetMapping("/{id}")
    public String viewBook(@PathVariable String id, Model model) throws Exception {
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);
        return "book-details";
    }
}
