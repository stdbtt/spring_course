package stdbtt.springcourse.FirstSecurityApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stdbtt.springcourse.FirstSecurityApp.models.Person;
import stdbtt.springcourse.FirstSecurityApp.repositories.PeopleRepository;
import stdbtt.springcourse.FirstSecurityApp.security.PersonDetails;

import java.util.Optional;

@Service
public class PeopleService {
    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Transactional(readOnly = true)
    public Person findPersonByUsername(String username){
        return  peopleRepository.findByUsername(username).orElse(null);
    }
}
