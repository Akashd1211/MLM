package com.example.mlm.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.mlm.entity.Commission;
import com.example.mlm.entity.Referral;
import com.example.mlm.entity.User;
import com.example.mlm.repository.CommissionRepository;
import com.example.mlm.service.CommissionService;
import com.example.mlm.service.ReferralService;
import com.example.mlm.service.UserService;

/**
 * Service implementation for handling commission-related operations.
 */
@Service
public class CommissionServiceImpl implements CommissionService {

    @Autowired
    private CommissionRepository commissionRepository;

    private final ReferralService referralService;
    private final UserService userService;

    @Autowired
    public CommissionServiceImpl(UserService userService, ReferralService referralService) {
        this.userService = userService;
        this.referralService = referralService;
    }

    // Constants for commission calculation
    private static final int MAX_LEVELS = 3;
    private static final double COMMISSION_RATE_LEVEL1 = 0.10;
    private static final double COMMISSION_RATE_LEVEL2 = 0.05;

    /**
     * Calculates commissions for a specific user based on referral levels and rates.
     * 
     * @param userId The ID of the user for whom commissions are to be calculated.
     */
    @Override
    @Transactional
    public void calculateCommissions(Long userId) {
        // Fetch user information
        User user = userService.getUserById(userId);
        List<Commission> commissions = new ArrayList<>();

        // Calculate commissions for each level up to the maximum levels
        for (int level = 1; level <= MAX_LEVELS; level++) {
            // Get commission rate for the current level
            double commissionRate = getCommissionRate(level);
            // Calculate commission amount for the current level
            double commissionAmount = calculateCommissionForLevel(user, level, commissionRate);

            // Create Commission object and add it to the list if commission is greater than zero
            if (commissionAmount > 0) {
                Commission commission = new Commission();
                commission.setUser(user);
                commission.setLevel(level);
                commission.setAmount(commissionAmount);
                commissions.add(commission);
            }
        }

        // Save calculated commissions to the database
        commissionRepository.saveAll(commissions);
    }

    /**
     * Retrieves commissions for a specific user.
     * 
     * @param userId The ID of the user for whom commissions are to be retrieved.
     * @return A list of commissions for the specified user.
     */
    @Override
    public List<Commission> getUserCommissions(Long userId) {
        // Retrieve commissions for a specific user
        return commissionRepository.findByUser_Id(userId);
    }

    /**
     * Gets the commission rate based on the referral level.
     * 
     * @param level The referral level.
     * @return The commission rate for the specified level.
     */
    private double getCommissionRate(int level) {
        // Define commission rates based on levels
        switch (level) {
            case 1:
                return COMMISSION_RATE_LEVEL1;
            case 2:
                return COMMISSION_RATE_LEVEL2;
            default:
                return 0.0;
        }
    }

    /**
     * Calculates commission for a specific referral level and user.
     * 
     * @param user           The user for whom the commission is calculated.
     * @param level          The referral level.
     * @param commissionRate The commission rate for the specified level.
     * @return The calculated commission amount.
     */
    private double calculateCommissionForLevel(User user, int level, double commissionRate) {
        // Calculate commission for a specific level
        List<Referral> referrals = referralService.getReferralsByUserAndLevel(user.getId(), level);
        double totalCommission = 0.0;

        // Calculate total commission for all referrals at the given level
        for (Referral referral : referrals) {
            totalCommission += calculateCommissionForReferredUser(referral.getReferred());
        }

        // Multiply total commission by the commission rate for the current level
        return totalCommission * commissionRate;
    }

    /**
     * Calculates commission for a referred user based on their total purchases.
     * 
     * @param referredUser The referred user.
     * @return The calculated commission amount.
     */
    private double calculateCommissionForReferredUser(User referredUser) {
        // Calculate commission for a referred user based on their total purchases
        double totalPurchases = referredUser.getTotalPurchases();
        double commissionPercentage = 0.05; // Adjust commission percentage as needed

        // Return the calculated commission amount
        return totalPurchases * commissionPercentage;
    }
}
