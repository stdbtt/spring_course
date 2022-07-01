package stdbtt.springcourse.models;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "year_of_birth")
    private int yearOfBirth;

    @OneToMany(mappedBy = "customer")
    private List<Book> books;

    @Transient
    private boolean isBooksEmpty = true;



    public Customer() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public boolean isBooksEmpty() {
        return isBooksEmpty;
    }

    public void setBooksEmpty(boolean booksEmpty) {
        isBooksEmpty = booksEmpty;
    }



    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", yearOfBirth=" + yearOfBirth +
                '}';
    }
}
