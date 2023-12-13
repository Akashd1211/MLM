package com.example.mlm.service;

import java.util.List;

import com.example.mlm.entity.Referral;
import com.example.mlm.entity.User;

/**
 * Service interface for managing user-related operations.
 */
public interface UserService {
    
    /**
     * Registers a new user.
     *
     * @param user The User object to be registered.
     * @return The registered User object.
     */
    User registerUser(User user);

    /**
     * Retrieves a user by their unique identifier.
     *
     * @param userId The unique identifier of the user.
     * @return The User object corresponding to the given userId.
     */
    User getUserById(Long userId);

    /**
     * Retrieves the direct referrals of a user.
     *
     * @param userId The unique identifier of the user.
     * @return A list of Referral objects representing the direct referrals of the user.
     */
    List<Referral> getDirectReferrals(Long userId);

    /**
     * Retrieves all referrals of a user, including direct and indirect referrals.
     *
     * @param userId The unique identifier of the user.
     * @return A list of Referral objects representing all referrals of the user.
     */
    List<Referral> getAllReferrals(Long userId);
}
