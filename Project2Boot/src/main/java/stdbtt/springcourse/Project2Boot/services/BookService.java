package stdbtt.springcourse.Project2Boot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stdbtt.springcourse.Project2Boot.models.Book;
import stdbtt.springcourse.Project2Boot.models.Customer;
import stdbtt.springcourse.Project2Boot.repositories.BookRepository;
import stdbtt.springcourse.Project2Boot.repositories.CustomerRepository;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service

public class BookService {

    private final BookRepository bookRepository;

    private final CustomerRepository customerRepository;

    @Autowired
    public BookService(BookRepository bookRepository, CustomerRepository customerRepository) {
        this.bookRepository = bookRepository;
        this.customerRepository = customerRepository;
    }

    public void findAll(int page, int booksPerPage, boolean isSortByYear){

    }

    @Transactional(readOnly = true)
    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    @Transactional
    public void save(Book book){
        bookRepository.save(book);
    }

    @Transactional(readOnly = true)
    public Book findOne(int id){
        return bookRepository.findById(id).orElse(null);
    }

    @Transactional
    public void update(Book book){
        save(book);
    }

    @Transactional
    public void assign(int id, Customer customer){
        Book bookForDB = findOne(id);
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
        Date date=null;
        try {
            date = sdf.parse("06/24/2017");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        bookForDB.setAssignAt(date);
        bookForDB.setCustomer(customerRepository.findById(customer.getId()).orElse(null));
    }

    @Transactional
    public void release(Book book){
        Book bookForDB = findOne(book.getId());
        bookForDB.setAssignAt(null);
        bookForDB.setCustomer(null);
    }

    @Transactional
    public void delete(Book book){
        release(book);
        bookRepository.delete(book);
    }

    public List<Book> findAll(Integer page, Integer booksPerPage) {
        return bookRepository.findAll(PageRequest.of(page, booksPerPage)).getContent();
    }

    public List<Book> findAll(Boolean isSortByYear){
        if(isSortByYear)
            return bookRepository.findAll(Sort.by("year"));
        else
            return findAll();
    }

    public List<Book> findAll(Integer page, Integer booksPerPage, Boolean isSortByYear) {
        if(page != null && booksPerPage != null)  {
            if(isSortByYear != null)
                return bookRepository.findAll(PageRequest.of(page, booksPerPage,Sort.by("year") )).getContent();
            else
                return findAll(page,booksPerPage);
        }
        else {
            if(isSortByYear != null)
                return findAll(isSortByYear);
            else
                return findAll();
        }
    }

    public List<Book> findBooksStartWith(String request){
        return bookRepository.findBooksByTitleStartingWith(request);
    }
}
