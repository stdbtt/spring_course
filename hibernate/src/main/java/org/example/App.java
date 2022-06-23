package org.example;


import org.example.model.Principal;
import org.example.model.School;
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
        Configuration configuration = new Configuration().addAnnotatedClass(Principal.class)
                .addAnnotatedClass(School.class);

        try (SessionFactory sessionFactory = configuration.buildSessionFactory()){
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            School school = (School) session.createQuery("FROM School WHERE schoolNumber = 11").getSingleResult();
            Principal principal = new Principal("Boss", 33);
            school.setPrincipal(principal);
            principal.setSchool(school);
            session.save(principal);


            session.getTransaction().commit();
        }

    }
}
