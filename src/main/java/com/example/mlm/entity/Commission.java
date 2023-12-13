package com.example.mlm.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Entity class for Commission
 */
@Entity
@Table(name = "commissions")
public class Commission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "level")
    private Integer level;

    /**
     * Default constructor for Commission entity.
     */
    public Commission() {
        super();
    }

    /**
     * Parameterized constructor for Commission entity.
     *
     * @param id     The unique identifier for the commission.
     * @param user   The User object associated with the commission.
     * @param amount The commission amount.
     * @param level  The commission level.
     */
    public Commission(Long id, User user, Double amount, Integer level) {
        super();
        this.id = id;
        this.user = user;
        this.amount = amount;
        this.level = level;
    }

    /**
     * Get the unique identifier of the commission.
     *
     * @return The commission id.
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the unique identifier of the commission.
     *
     * @param id The commission id to set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the User object associated with the commission.
     *
     * @return The User object.
     */
    public User getUser() {
        return user;
    }

    /**
     * Set the User object associated with the commission.
     *
     * @param user The User object to set.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Get the commission amount.
     *
     * @return The commission amount.
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * Set the commission amount.
     *
     * @param amount The commission amount to set.
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    /**
     * Get the commission level.
     *
     * @return The commission level.
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * Set the commission level.
     *
     * @param level The commission level to set.
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * Override of the toString() method to provide a string representation of the Commission entity.
     *
     * @return String representation of the Commission entity.
     */
    @Override
    public String toString() {
        return "Commission [id=" + id + ", user=" + user + ", amount=" + amount + ", level=" + level + "]";
    }
}
