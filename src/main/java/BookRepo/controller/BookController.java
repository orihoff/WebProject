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
        try {
            model.addAttribute("books", bookService.getAllBooks());
        } catch (Exception e) {
            e.printStackTrace();
            // ניתן להחזיר עמוד שגיאה מותאם אישית במקום
            return "error-page";
        }
        return "home";
    }

    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("book", new Book());
        System.out.println("new book page");
        return "book-form";
    }

    @PostMapping("/save")
    public String saveBook(@ModelAttribute("book") Book book) {
        try {
            // אם ה-ID ריק => יצירת ספר חדש
            if (book.getId() == null || book.getId().isEmpty()) {
                bookService.addBook(book);
            } else {
                // אחרת => עדכון ספר קיים
                bookService.updateBook(book.getId(), 
                                       book.getTitle(),
                                       book.getAuthor(),
                                       book.getGenre(),
                                       book.getPublicationYear());
            }
        } catch (Exception e) {
            e.printStackTrace();
            // ניתן להחזיר עמוד שגיאה מותאם אישית במקום
            return "error-page";
        }
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        try {
            model.addAttribute("book", bookService.getBookById(id));
        } catch (Exception e) {
            e.printStackTrace();
            return "error-page";
        }
        return "book-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable String id) {
        try {
            bookService.deleteBook(id);
        } catch (Exception e) {
            e.printStackTrace();
            // ניתן להציג הודעת שגיאה מתאימה
        }
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String viewBook(@PathVariable String id, Model model) {
        try {
            model.addAttribute("book", bookService.getBookById(id));
        } catch (Exception e) {
            e.printStackTrace();
            return "error-page";
        }
        return "book-details";
    }
}
