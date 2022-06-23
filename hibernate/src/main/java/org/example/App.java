package org.example;


import org.example.model.Item;
import org.example.model.Person;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class)
                .addAnnotatedClass(Item.class);

        try (SessionFactory sessionFactory = configuration.buildSessionFactory()){
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            Person person = session.get(Person.class, 1);
            System.out.println("ПОлучили человаека");
            session.getTransaction().commit();
            System.out.println("Закрываем первую сессию");
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            System.out.println("Внутри второяй транзакции");
            person = session.merge(person);
            //Hibernate.initialize(person.getItems());

            List<Item> items = session.createQuery("select Item from Item where Item" +
                    ".owner.id =:personId", Item.class).setParameter("personId", person.getId()).getResultList();
            session.getTransaction().commit();
            System.out.println("Вне второй сессии");



            System.out.println(person.getItems());
        }

    }
}
