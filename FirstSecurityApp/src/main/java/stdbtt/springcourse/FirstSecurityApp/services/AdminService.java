package stdbtt.springcourse.FirstSecurityApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import stdbtt.springcourse.FirstSecurityApp.models.Person;
import stdbtt.springcourse.FirstSecurityApp.repositories.PeopleRepository;

import java.util.List;

@Service
public class AdminService {
    private final PeopleRepository peopleRepository;

    @Autowired
    public AdminService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> showUsers(){
        return peopleRepository.findAll();
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Person> showAdmins(){
        return peopleRepository.findPeopleByRole("ROLE_ADMIN");
    }
}
