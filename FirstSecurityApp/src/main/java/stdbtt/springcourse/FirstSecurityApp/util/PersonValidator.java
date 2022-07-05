package stdbtt.springcourse.FirstSecurityApp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import stdbtt.springcourse.FirstSecurityApp.models.Person;
import stdbtt.springcourse.FirstSecurityApp.security.PersonDetails;
import stdbtt.springcourse.FirstSecurityApp.services.PeopleService;

@Component
public class PersonValidator implements Validator {

    private final PeopleService peopleService;

    @Autowired
    public PersonValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        Person personDB = peopleService.findPersonByUsername(person.getUsername());
        if(personDB!=null){
            errors.rejectValue("username", "","Человек с таким именем пользователя уже существует");
        }
    }
}
