package stdbtt.springcourse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stdbtt.springcourse.models.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
}
