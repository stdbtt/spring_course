 package stdbtt.springcourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import stdbtt.springcourse.dao.BookDAO;
import stdbtt.springcourse.dao.CustomerDAO;
import stdbtt.springcourse.models.Book;

@Controller
@RequestMapping("/library/books")
public class BookController {
    private final BookDAO bookDAO;
    private final CustomerDAO customerDAO;

    @Autowired
    public BookController(BookDAO bookDAO, CustomerDAO customerDAO) {
        this.bookDAO = bookDAO;
        this.customerDAO = customerDAO;
    }

    @GetMapping("/free")
    public String freeBooks(Model model){
        model.addAttribute("books", bookDAO.freeBooks());
        return "book/freeBooks";
    }

    @GetMapping("/give")
    public String giveBookForm(Model model, @ModelAttribute("book") Book book){
        model.addAttribute("books", bookDAO.freeBooks());
        model.addAttribute("customers", customerDAO.showAll());
        return "book/give";
    }

    @PatchMapping("/give")
    public String giveBook(Book book){
        bookDAO.giveBook(book);
        int id = book.getCustomer_id();
        return "redirect:/library/customers/"+id;
    }

    @GetMapping("/release")
    public String releaseBookForm(Model model, @ModelAttribute("book") Book book){
        model.addAttribute("books", bookDAO.takenBooks());
        return "book/release";
    }

    @PatchMapping("/release")
    public String releaseBook(Book book){
        bookDAO.releaseBook(book);
        return "redirect:/library/books/free";
    }

}
