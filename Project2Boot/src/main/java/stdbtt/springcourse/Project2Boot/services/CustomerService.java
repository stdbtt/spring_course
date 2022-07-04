package stdbtt.springcourse.Project2Boot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stdbtt.springcourse.Project2Boot.models.Book;
import stdbtt.springcourse.Project2Boot.models.Customer;
import stdbtt.springcourse.Project2Boot.repositories.CustomerRepository;


import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service

public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    @Transactional(readOnly = true)
    public List<Customer> findAll(){
        return customerRepository.findAll();
    }

    @Transactional
    public void save(Customer customer){
        customerRepository.save(customer);
    }

    @Transactional(readOnly = true)
    public Customer findOne(int id){
        Customer customer = customerRepository.findById(id).orElse(null);
        if(customer!=null){
            List<Book> books = customer.getBooks();
            if(books.size()>0){
                for(Book book : books){
                    checkTimeOff(book);
                }
            }
        }
        return customer;
    }

    @Transactional
    public void update(Customer customer){
        customerRepository.save(customer);
    }

    @Transactional
    public void delete(Customer customer){
        customerRepository.delete(customer);
    }

    private void checkTimeOff(Book book){
        if(book.getAssignAt()!=null){
            Date currentDate = new Date();

            long diffInMillies = Math.abs(book.getAssignAt().getTime() - currentDate.getTime());
            long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
            if(diff>10) {
                book.setTimeOff(true);
            }
        }
    }
}
