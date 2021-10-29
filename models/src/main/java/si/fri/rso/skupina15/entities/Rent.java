package si.fri.rso.skupina15.entities;

import javax.persistence.*;

@Entity(name = "rent")
@Table(name = "rent")
@NamedQueries(value =
        {
                @NamedQuery(name = "si.fri.rso.skupina15.entities.Registration.getAll", query = "SELECT r FROM rent r")
        })
public class Rent {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_rent;

    @Column
    private String registration_date;

    @ManyToOne//(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private User user;

    //@JsonbTransient
    @ManyToOne//(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_event")
    private Event e;

    // Getters and Setters

    public Integer getId_rent() {
        return id_rent;
    }

    public void setId_rent(Integer id_rent) {
        this.id_rent = id_rent;
    }

    public String getRegistration_date() {
        return registration_date;
    }

    public void setRegistration_date(String registration_date) {
        this.registration_date = registration_date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Event getE() {
        return e;
    }

    public void setE(Event e) {
        this.e = e;
    }
}
