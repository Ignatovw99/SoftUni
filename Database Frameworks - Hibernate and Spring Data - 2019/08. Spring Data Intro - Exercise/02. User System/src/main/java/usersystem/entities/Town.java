package usersystem.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "towns")
public class Town {

    private Long id;

    private String name;

    private Country country;

    private Set<User> usersBorn;

    private Set<User> usersCurrentlyLiving;

    public Town() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "name", nullable = false, unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne(targetEntity = Country.class, optional = false)
    @JoinColumn(name = "country_id", referencedColumnName = "id")
    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @OneToMany(targetEntity = User.class, mappedBy = "bornTown")
    public Set<User> getUsersBorn() {
        return usersBorn;
    }

    public void setUsersBorn(Set<User> usersBorn) {
        this.usersBorn = usersBorn;
    }

    @OneToMany(targetEntity = User.class, mappedBy = "currentlyLivingTown")
    public Set<User> getUsersCurrentlyLiving() {
        return usersCurrentlyLiving;
    }

    public void setUsersCurrentlyLiving(Set<User> usersCurrentlyLiving) {
        this.usersCurrentlyLiving = usersCurrentlyLiving;
    }
}
