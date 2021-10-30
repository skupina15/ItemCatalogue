package si.fri.rso.skupina15.entities;

import javax.persistence.*;
import java.util.List;

@Entity(name = "item")
@Table(name = "item")
@NamedQueries(value =
        {
                @NamedQuery(name = "si.fri.rso.skupina15.entities.Item.getAll", query = "SELECT i FROM item i"),
                @NamedQuery(name = "si.fri.rso.skupina15.entities.Item.getByOwner", query = "SELECT i FROM item i where i.owner = :owner_id")})

public class Item {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_item;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private Float daily_price;

    @ManyToOne//(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private User owner;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item",  cascade = CascadeType.REMOVE)
    private List<Rent> rentals;

    @Column
    private List<String> tags;

    // Getters and setters
    public Integer getId_item() {
        return id_item;
    }

    public void setId_item(Integer id_item) {
        this.id_item = id_item;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getDaily_price() {
        return daily_price;
    }

    public void setDaily_price(Float daily_price) {
        this.daily_price = daily_price;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<Rent> getRentals() {
        return rentals;
    }

    public void setRentals(List<Rent> rentals) {
        this.rentals = rentals;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
