package com.example.mlm.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import java.util.Optional;

import com.example.mlm.entity.Referral;
import com.example.mlm.entity.User;
import com.example.mlm.repository.UserRepository;
import com.example.mlm.service.ReferralService;
import com.example.mlm.service.UserService;
/**
 * Service implementation for handling user-related operations.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    private final ReferralService referralService;

    /**
     * Constructor for UserServiceImpl.
     *
     * @param referralService The ReferralService used for handling referral-related operations.
     */
    @Autowired
    public UserServiceImpl(ReferralService referralService) {
        this.referralService = referralService;
    }

    /**
     * Registers a new user and records the user as their own referrer (level 0).
     *
     * @param user The user to be registered.
     * @return The registered user.
     */
    @Override
    public User registerUser(User user) {
        // Save the user to the database
        user = userRepository.save(user);

        // Record the user as their own referrer (level 0)
        referralService.recordReferral(user.getId(), user.getId());

        return user;
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param userId The ID of the user to be retrieved.
     * @return The user with the specified ID, or null if not found.
     */
    @Override
    public User getUserById(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        return userOptional.orElse(null);
    }

    /**
     * Retrieves a list of direct referrals for a specific user (level 1).
     *
     * @param userId The ID of the user for whom direct referrals are to be retrieved.
     * @return A list of direct referrals for the specified user.
     */
    @Override
    public List<Referral> getDirectReferrals(Long userId) {
        // Retrieve direct referrals for a user (level 1)
        return referralService.getReferralsByUser(userId);
    }

    /**
     * Retrieves a list of all referrals for a specific user, including referrals at different levels.
     *
     * @param userId The ID of the user for whom all referrals are to be retrieved.
     * @return A list of all referrals for the specified user.
     */
    @Override
    public List<Referral> getAllReferrals(Long userId) {
        // Start recursive retrieval of all referrals
        return getAllReferralsRecursive(userId, 0);
    }

    /**
     * Recursively retrieves all referrals for a user at different levels.
     *
     * @param userId        The ID of the user for whom referrals are to be retrieved.
     * @param currentLevel  The current referral level being processed.
     * @return A list of all referrals for the specified user at different levels.
     */
    private List<Referral> getAllReferralsRecursive(Long userId, int currentLevel) {
        List<Referral> allReferrals = new ArrayList<>();

        // Retrieve direct referrals for the current level
        List<Referral> directReferrals = referralService.getReferralsByUserAndLevel(userId, currentLevel);
        allReferrals.addAll(directReferrals);

        // Recursively retrieve referrals for the next level
        for (Referral directReferral : directReferrals) {
            User referredUser = directReferral.getReferred();
            List<Referral> referralsOfNextLevel = getAllReferralsRecursive(referredUser.getId(), currentLevel + 1);
            allReferrals.addAll(referralsOfNextLevel);
        }

        return allReferrals;
    }
}
