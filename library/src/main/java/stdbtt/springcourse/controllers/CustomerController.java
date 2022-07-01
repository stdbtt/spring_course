package stdbtt.springcourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import stdbtt.springcourse.models.Customer;
import stdbtt.springcourse.services.BookService;
import stdbtt.springcourse.services.CustomerService;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;
    private final BookService bookService;

    @Autowired
    public CustomerController(CustomerService customerService, BookService bookService) {
        this.customerService = customerService;
        this.bookService = bookService;
    }

    @GetMapping("")
    public String showAll(Model model){
        model.addAttribute("customers", customerService.findAll());
        return "customer/customers";
    }

    @GetMapping("/{id}")
    public String show(Model model, @PathVariable("id") int id){
        model.addAttribute("customer",customerService.findOne(id));
        return "customer/customer";
    }

    @GetMapping("/new")
    public String createForm(@ModelAttribute("customer") Customer customer){
        return "customer/new";
    }

    @PostMapping("/new")
    public String create(Customer customer){
       customerService.save(customer);
        return "redirect:/customers/";
    }

    @GetMapping("/{id}/edit")
    public String editForm(Model model, @PathVariable("id") int id){
        model.addAttribute("customer",customerService.findOne(id));
        return "/customer/edit";
    }

    @PatchMapping("/{id}")
    public String edit(Customer customer, @PathVariable("id") int id){
        customerService.update(customer);
        return"redirect:/customers/"+id ;
    }


    @DeleteMapping("/{id}")
    public String delete(Customer customer){
       customerService.delete(customer);
        return "redirect:/customers/";
    }
}
