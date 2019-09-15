package gamestore.domain.entities;

import gamestore.domain.entities.enums.Role;
import gamestore.domain.validators.email.Email;
import gamestore.domain.validators.password.Password;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    private String email;

    private String password;

    private String fullName;

    private Role role;

    private Set<Game> games;

    private Set<Game> shoppingCart;

    public User() {
        this.games = new HashSet<>();
        this.shoppingCart = new HashSet<>();
    }

    @Column(name = "email", nullable = false, unique = true)
    @Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "password", nullable = false)
    @Password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "full_name", nullable = false)
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @ManyToMany(targetEntity = Game.class)
    @JoinTable(
            name = "users_games",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "game_id", referencedColumnName = "id")
    )
    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }

    @ManyToMany(targetEntity = Game.class)
    @JoinTable(
            name = "shopping_cart",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "game_id", referencedColumnName = "id")
    )
    public Set<Game> getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(Set<Game> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }
}
