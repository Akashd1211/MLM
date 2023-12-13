package com.example.mlm.service;

import java.util.List;

import com.example.mlm.entity.Referral;

/**
 * Service interface for managing referral-related operations.
 */
public interface ReferralService {

    /**
     * Records a referral between a referrer and a referred user.
     *
     * @param referrerId      The unique identifier of the referrer user.
     * @param referredUserId  The unique identifier of the referred user.
     */
    void recordReferral(Long referrerId, Long referredUserId);

    /**
     * Retrieves all referrals associated with a specific user.
     *
     * @param userId The unique identifier of the user.
     * @return A list of Referral objects representing all referrals associated with the user.
     */
    List<Referral> getReferralsByUser(Long userId);

    /**
     * Retrieves referrals of a user at a specific referral level.
     *
     * @param userId       The unique identifier of the user.
     * @param currentLevel The referral level for which referrals are to be retrieved.
     * @return A list of Referral objects representing referrals of the user at the specified level.
     */
    List<Referral> getReferralsByUserAndLevel(Long userId, int currentLevel);
}
