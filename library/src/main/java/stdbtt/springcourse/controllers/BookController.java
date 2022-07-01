 package stdbtt.springcourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import stdbtt.springcourse.models.Book;
import stdbtt.springcourse.models.Customer;
import stdbtt.springcourse.services.BookService;
import stdbtt.springcourse.services.CustomerService;
import stdbtt.springcourse.util.Search;

import java.util.Date;
import java.util.List;
import java.util.Optional;

 @Controller
@RequestMapping("/books")
public class BookController {
     private final CustomerService customerService;
     private final BookService bookService;

    @Autowired
    public BookController(CustomerService customerService, BookService bookService) {
         this.customerService = customerService;
         this.bookService = bookService;
    }

    @GetMapping("")
    public String showAll(Model model, @RequestParam(name = "page", required = false) Integer page,
                          @RequestParam(name = "books_per_page", required = false) Integer booksPerPage,
                          @RequestParam(name = "sort_by_year", required = false) Boolean isSortByYear){
       model.addAttribute("books", bookService.findAll(page, booksPerPage, isSortByYear));
        return "book/books";
    }


    @GetMapping("/{id}")
    public String show(Model model, @PathVariable("id") int id, @ModelAttribute("customer") Customer customer){
        model.addAttribute("book", bookService.findOne(id));
        model.addAttribute("customers", customerService.findAll());
        return "book/book";
    }

    @GetMapping("/new")
    public String addForm(@ModelAttribute("book") Book book){
        return "book/new";
    }

    @PostMapping("/new")
    public String add(Book book){
        bookService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String editForm(Model model, @PathVariable("id") int id){
      model.addAttribute("book",bookService.findOne(id));
        return "/book/edit";
    }

    @PatchMapping("/{id}")
    public String edit(Book book, @PathVariable("id") int id){
        bookService.update(book);
        return"redirect:/books/"+id;
    }

    @PatchMapping("/{id}/assign")
    public String assign(Customer customer, @PathVariable("id") int id){
        bookService.assign(id, customer);
        return"redirect:/books/"+id;
    }

    @PatchMapping("/{id}/release")
    public String release(Book book, @PathVariable("id") int id){
        bookService.release(book);
        return "redirect:/books/"+id;
    }

    @DeleteMapping("/{id}")
    public String delete(Book book){
        bookService.delete(book);
        return "redirect:/books";
    }

    @PostMapping("/search")
     public String search(Model model, @RequestParam(name = "request", required = false)String request){
        if(request!=null){
            model.addAttribute("books", bookService.findBooksStartWith(request));
        }
        return "book/search";
    }

    @GetMapping("/search")
     public String searchPage(){
        return "book/search";
    }

}
