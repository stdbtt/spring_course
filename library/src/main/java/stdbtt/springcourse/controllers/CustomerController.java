package stdbtt.springcourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import stdbtt.springcourse.dao.BookDAO;
import stdbtt.springcourse.dao.CustomerDAO;
import stdbtt.springcourse.models.Customer;

import java.util.List;

@Controller
@RequestMapping("/library/customers")
public class CustomerController {
    private final BookDAO bookDAO;
    private final CustomerDAO customerDAO;

    @Autowired
    public CustomerController(BookDAO bookDAO, CustomerDAO customerDAO) {
        this.bookDAO = bookDAO;
        this.customerDAO = customerDAO;
    }

    @GetMapping("")
    public String customers(Model model){
        List<Customer> customerList = customerDAO.showAll();
        model.addAttribute("customers", customerDAO.showAll());
        return "customer/customers";
    }

    @GetMapping("/{id}")
    public String customerInfo(Model model, @PathVariable("id") int id){
        model.addAttribute("customer", customerDAO.show(id));
        model.addAttribute("books", bookDAO.showBooksForCustomerId(id));
        return "customer/customer";
    }

    @GetMapping("/new")
    public String createForm(@ModelAttribute("customer") Customer customer){
        return "customer/createCustomer";
    }

    @PostMapping("/new")
    public String create(Customer customer){
        customerDAO.addCustomer(customer);
        return "redirect:/library/customers";
    }

    @GetMapping("/delete")
    public String deleteForm(Model model, @ModelAttribute("customer") Customer customer){
        model.addAttribute("customers", customerDAO.showAll());
        return "customer/delete";
    }

    @PatchMapping("/delete")
    public String delete(Customer customer){
        customerDAO.deleteCustomer(customer);
        return "redirect:/library/customers";
    }
}
