package com.example.mlm.entity;

/**
 * Entity class for User
 */
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    /**
     * Default constructor for User entity.
     */
    public User() {
        super();
    }

    /**
     * Parameterized constructor for User entity.
     *
     * @param id       The unique identifier for the user.
     * @param name     The name of the user.
     * @param email    The email address of the user.
     * @param password The password of the user.
     * @param role     The role of the user.
     */
    public User(Long id, String name, String email, String password, String role) {
        super();
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    /**
     * Get the unique identifier of the user.
     *
     * @return The user's id.
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the unique identifier of the user.
     *
     * @param id The user's id to set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the name of the user.
     *
     * @return The user's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the user.
     *
     * @param name The user's name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the email address of the user.
     *
     * @return The user's email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the email address of the user.
     *
     * @param email The user's email address to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get the password of the user.
     *
     * @return The user's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the password of the user.
     *
     * @param password The user's password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get the role of the user.
     *
     * @return The user's role.
     */
    public String getRole() {
        return role;
    }

    /**
     * Set the role of the user.
     *
     * @param role The user's role to set.
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Example method demonstrating how to calculate the total purchases of the user.
     *
     * @return The total purchases of the user.
     */
    public double getTotalPurchases() {
        return 500.0;
    }

    /**
     * Override of the toString() method to provide a string representation of the User entity.
     *
     * @return String representation of the User entity.
     */
    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", role=" + role
                + "]";
    }
}
