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
 * Entity class for Referral
 */
@Entity
@Table(name = "referrals")
public class Referral {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "referrer_id", nullable = false)
    private User referrer;

    @ManyToOne
    @JoinColumn(name = "referred_id", nullable = false)
    private User referred;

    @Column(name = "level")
    private Integer level;

    /**
     * Default constructor for Referral entity.
     */
    public Referral() {
        super();
    }

    /**
     * Parameterized constructor for Referral entity.
     *
     * @param id       The unique identifier for the referral.
     * @param referrer The referrer User object.
     * @param referred The referred User object.
     * @param level    The referral level.
     */
    public Referral(Long id, User referrer, User referred, Integer level) {
        super();
        this.id = id;
        this.referrer = referrer;
        this.referred = referred;
        this.level = level;
    }

    /**
     * Get the unique identifier of the referral.
     *
     * @return The referral id.
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the unique identifier of the referral.
     *
     * @param id The referral id to set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the referrer User object.
     *
     * @return The referrer User.
     */
    public User getReferrer() {
        return referrer;
    }

    /**
     * Set the referrer User object.
     *
     * @param referrer The referrer User to set.
     */
    public void setReferrer(User referrer) {
        this.referrer = referrer;
    }

    /**
     * Get the referred User object.
     *
     * @return The referred User.
     */
    public User getReferred() {
        return referred;
    }

    /**
     * Set the referred User object.
     *
     * @param referred The referred User to set.
     */
    public void setReferred(User referred) {
        this.referred = referred;
    }

    /**
     * Get the referral level.
     *
     * @return The referral level.
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * Set the referral level.
     *
     * @param level The referral level to set.
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * Override of the toString() method to provide a string representation of the Referral entity.
     *
     * @return String representation of the Referral entity.
     */
    @Override
    public String toString() {
        return "Referral [id=" + id + ", referrer=" + referrer + ", referred=" + referred + ", level=" + level + "]";
    }
}
