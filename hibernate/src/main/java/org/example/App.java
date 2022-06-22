package org.example;


import org.example.model.Director;
import org.example.model.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Configuration configuration = new Configuration().addAnnotatedClass(Director.class)
                .addAnnotatedClass(Movie.class);
        try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            Director director = session.get(Director.class, 1);
            Movie movie = session.get(Movie.class, 3);
            director.getMovies().remove(movie);
            session.remove(movie);

            session.getTransaction().commit();
        }

    }
}
