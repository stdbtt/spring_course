package org.example.model;

import jakarta.persistence.*;

@Entity
@Table(name = "item")
public class Item {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private int id;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person owner;

    @Column(name = "name")
    private String name;

    public Item(){}

    public Item(String name) {
        this.name = name;
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

    public void setName(String itemName) {
        this.name = itemName;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        System.out.println("setOwner");
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", itemName='" + name + '\'' +
                ", owner=" + owner.getName()+
                '}';
    }
}
