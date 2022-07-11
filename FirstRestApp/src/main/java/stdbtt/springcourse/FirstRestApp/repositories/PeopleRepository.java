package stdbtt.springcourse.FirstRestApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import stdbtt.springcourse.FirstRestApp.models.Person;

public interface PeopleRepository extends JpaRepository<Person, Integer> {
}
