package stdbtt.springcourse.dao;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import stdbtt.springcourse.models.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Component
public class PersonDAO {

    private final EntityManager entityManager;

    @Autowired
    public PersonDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional(readOnly = true)
    public void testNPlus1(){
        Session session = entityManager.unwrap(Session.class);

       /* List<Person> people = session.createQuery(" select p from Person p", Person.class).getResultList();

        for (Person person : people){
            System.out.println("Person " + person.getName()+ ", items: " + person.getItems());
        }*/

        List<Person> people = session.createQuery("select DISTINCT p from Person p left join fetch p.items").getResultList();

        for (Person person : people){
            System.out.println("Person " + person.getName()+ ", items: " + person.getItems());
        }
    }
}
