package stdbtt.springcourse.Project2Boot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stdbtt.springcourse.Project2Boot.models.Customer;


@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
}
