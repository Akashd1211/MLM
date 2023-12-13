package com.example.mlm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mlm.entity.Referral;

/**
 * Repository interface for handling database operations related to referrals.
 * Extends JpaRepository for basic CRUD operations.
 */
public interface ReferralRepository extends JpaRepository<Referral, Long> {

    /**
     * Retrieves a list of referrals based on the referred user's ID and referral level.
     *
     * @param referredUserId The ID of the user who has been referred.
     * @param level          The referral level for which referrals are to be retrieved.
     * @return A list of referrals for the specified referred user and level.
     */
    List<Referral> findByReferred_IdAndLevel(Long referredUserId, int level);

    /**
     * Retrieves a list of referrals based on the referrer's user ID.
     *
     * @param userId The ID of the user who is the referrer.
     * @return A list of referrals for the specified referrer user.
     */
    List<Referral> findByReferrer_Id(Long userId);
}
