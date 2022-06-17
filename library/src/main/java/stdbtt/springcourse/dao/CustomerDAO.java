package stdbtt.springcourse.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import stdbtt.springcourse.models.Book;
import stdbtt.springcourse.models.Customer;

import java.util.List;

@Component
public class CustomerDAO {

    private final JdbcTemplate jdbcTemplate;

    public CustomerDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Customer> showAll(){
        return jdbcTemplate.query("SELECT * FROM customer", new BeanPropertyRowMapper<>(Customer.class));
    }

    public Customer show(int id){
        return jdbcTemplate.query("SELECT * FROM customer WHERE customer_id = ?",new Object[]{id}, new BeanPropertyRowMapper<>(Customer.class)).
                stream().findAny().orElse(null);
    }

    public void addCustomer(Customer customer){
        jdbcTemplate.update("INSERT INTO customer(name, birth) VALUES(?,?)", customer.getName(), customer.getBirth());
    }

    public void deleteCustomer(Customer customer){
        jdbcTemplate.update("DELETE FROM customer WHERE customer_id = ?", customer.getCustomer_id());
    }
}
