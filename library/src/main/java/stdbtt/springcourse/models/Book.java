package stdbtt.springcourse.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "year")
    private int year;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    private Customer customer;

    @Column(name = "assign_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date assignAt;

    @Transient
    private boolean isFree = true;

    @Transient
    private int customerId;

    @Transient
    private boolean isTimeOff;

    public Book() {
    }

    public int getId() {
        return id;
    }

    public void setId(int book_id) {
        this.id = book_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Date getAssignAt() {
        return assignAt;
    }

    public void setAssignAt(Date assignAt) {
        this.assignAt = assignAt;
    }

    public boolean isTimeOff() {
        return isTimeOff;
    }

    public void setTimeOff(boolean timeOff) {
        isTimeOff = timeOff;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                ", customerId=" + customerId +
                '}';
    }
}
