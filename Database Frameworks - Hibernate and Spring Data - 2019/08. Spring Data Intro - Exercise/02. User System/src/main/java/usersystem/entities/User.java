package usersystem.entities;

import org.hibernate.validator.constraints.Length;
import usersystem.annotations.email.Email;
import usersystem.annotations.password.Password;
import usersystem.constants.Constants;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    private Long id;

    private String username;

    private String password;

    private String email;

    private LocalDate registrationDate;

    private LocalDate lastTimeLoggedIn;

    private Integer age;

    private Boolean deleted;

    private Town bornTown;

    private Town currentlyLivingTown;

    private String firstName;

    private String lastName;

    private String fullName;

    private Set<User> friends;

    private Set<Album> albums;

    public User() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "username", nullable = false, unique = true)
    @Length(min = 4, max = 30, message = Constants.USERNAME_INVALID_LENGTH)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password", nullable = false)
    @Password(
            minLength = 6,
            maxLength = 50,
            containsLowercase = true,
            containsUppercase = true,
            containsDigit = true,
            containsSpecialSymbol = true
    )
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "email", nullable = false, unique = true)
    @Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "registered_on")
    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Column(name = "last_time_logged_in")
    public LocalDate getLastTimeLoggedIn() {
        return lastTimeLoggedIn;
    }

    public void setLastTimeLoggedIn(LocalDate lastTimeLoggedIn) {
        this.lastTimeLoggedIn = lastTimeLoggedIn;
    }

    @Column(name = "age")
    @Min(value = 1, message = Constants.AGE_BELOW_LOWER_BOUND)
    @Max(value = 120, message = Constants.AGE_ABOVE_UPPER_BOUND)
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Column(name = "is_deleted")
    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @ManyToOne(targetEntity = Town.class)
    @JoinColumn(name = "born_town_id", referencedColumnName = "id")
    public Town getBornTown() {
        return bornTown;
    }

    public void setBornTown(Town bornTown) {
        this.bornTown = bornTown;
    }

    @ManyToOne(targetEntity = Town.class)
    @JoinColumn(name = "currently_living_town_id", referencedColumnName = "id")
    public Town getCurrentlyLivingTown() {
        return currentlyLivingTown;
    }

    public void setCurrentlyLivingTown(Town currentlyLivingTown) {
        this.currentlyLivingTown = currentlyLivingTown;
    }

    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Transient
    public String getFullName() {
        return fullName;
    }

    public void setFullName() {
        this.fullName = this.firstName + " " + this.lastName;
    }

    @ManyToMany(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinTable(
            name = "friends",
            joinColumns = @JoinColumn(name = "first_user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "second_user_id", referencedColumnName = "id")
    )
    public Set<User> getFriends() {
        return friends;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }

    @OneToMany(targetEntity = Album.class, mappedBy = "user", cascade = CascadeType.ALL)
    public Set<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }
}
