package org.example.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Director")
public class Director {

    @Id
    @Column(name="director_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @OneToMany(mappedBy = "director")
    private List<Movie> movies;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    public Director(){}

    public Director(List<Movie> movies, String name, int age) {
        this.movies = movies;
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        movies.forEach(builder::append);
        return "Director{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
