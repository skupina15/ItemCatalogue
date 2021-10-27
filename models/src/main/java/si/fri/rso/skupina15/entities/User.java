package si.fri.rso.skupina15.entities;

import javax.persistence.*;
import java.util.List;

@Entity(name = "user")
@Table(name = "user")
@NamedQueries(value =
        {
                @NamedQuery(name = "si.fri.prpo.skupina5.entitete.User.getAll", query = "SELECT u FROM user u"),
        })


public class User {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_user;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private String email;

    //@JsonbTransient
    //@OneToMany(fetch = FetchType.LAZY, mappedBy = "profesor",  cascade = CascadeType.REMOVE)
    //private List<Tema> tema;
}
