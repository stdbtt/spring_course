package org.example;


import org.example.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class);


        try (SessionFactory sessionFactory = configuration.buildSessionFactory()){
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            Person person1 = new Person("Test1", 31);
            Person person2 = new Person("Test2", 32);
            Person person3 = new Person("Test3", 33);
            session.save(person1);
            session.save(person2);
            session.save(person3);

            session.getTransaction().commit();
        }


    }
}
