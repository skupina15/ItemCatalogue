package si.fri.rso.skupina15.entities;

import javax.persistence.*;
import java.util.List;

@Entity(name = "user")
@Table(name = "user")
@NamedQueries(value =
        {
                @NamedQuery(name = "si.fri.rso.skupina15.entities.User.getAll", query = "SELECT u FROM user u"),
        })


public class User {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_user;

    @Column
    private String user_name;

    @Column
    private String email;

    //@JsonbTransient
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user",  cascade = CascadeType.REMOVE)
    private List<Event> events;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user",  cascade = CascadeType.REMOVE)
    private List<Registration> registrations;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user",  cascade = CascadeType.REMOVE)
    private List<Rent> rentals;

    //Getters and setters

    public Integer getId_user() {
        return id_user;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public List<Registration> getRegistrations() {
        return registrations;
    }

    public void setRegistrations(List<Registration> registrations) {
        this.registrations = registrations;
    }

    public List<Rent> getRentals() {
        return rentals;
    }

    public void setRentals(List<Rent> rentals) {
        this.rentals = rentals;
    }
}
