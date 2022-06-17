package stdbtt.springcourse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import stdbtt.springcourse.models.Book;

import java.util.List;

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> freeBooks(){
        return jdbcTemplate.query("SELECT book_id, title, author, year FROM book WHERE customer_id IS NULL", new BeanPropertyRowMapper<>(Book.class));
    }

    public void giveBook(Book book){
        jdbcTemplate.update("UPDATE book SET customer_id = ? WHERE book_id = ?", book.getCustomer_id(), book.getBook_id());
    }

    public List<Book> showBooksForCustomerId(int customer_id){
        return jdbcTemplate.query("SELECT * FROM book WHERE customer_id = ?",new Object[]{customer_id},  new BeanPropertyRowMapper<>(Book.class));
    }

    public void releaseBook(Book book){
        jdbcTemplate.update("UPDATE book SET customer_id = NULL WHERE book_id = ?", book.getBook_id());
    }

    public List<Book> takenBooks(){
        return jdbcTemplate.query("SELECT * FROM book WHERE customer_id IS NOT NULL ",  new BeanPropertyRowMapper<>(Book.class));
    }
}
