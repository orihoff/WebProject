package BookRepo.controller;

import BookRepo.entity.Book;
import BookRepo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/")
    public String listBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "home";
    }

    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("book", new Book());
        
        System.out.println("new book hello world");
        return "book-form";
    }

    @PostMapping("/save")
    public String saveBook(@ModelAttribute("book") Book book) {
    	System.out.println("save book");
        if (book.getId() == null || book.getId().isEmpty()) {
            bookService.addBook(book);
        } else {
            //bookService.updateBook(book);
        	System.out.println("failed");
        }
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) throws Exception {
        model.addAttribute("book", bookService.getBookById(id));
        return "book-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable String id) {
        try {
			bookService.deleteBook(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String viewBook(@PathVariable String id, Model model) {
        model.addAttribute("book", bookService.getBookById(id));
        return "book-details";
    }
}