package com.example.mlm.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.mlm.entity.Referral;
import com.example.mlm.entity.User;
import com.example.mlm.repository.ReferralRepository;
import com.example.mlm.service.ReferralService;
import com.example.mlm.service.UserService;

/**
 * Service implementation for handling referral-related operations.
 */
@Service
public class ReferralServiceImpl implements ReferralService {

    @Autowired
    private ReferralRepository referralRepository;

    @Autowired
    private ApplicationContext applicationContext;

    /**
     * Records a referral between a referrer and a referred user in the database.
     *
     * @param referrerId      The ID of the user who is the referrer.
     * @param referredUserId  The ID of the user who is being referred.
     */
    @Override
    @Transactional
    public void recordReferral(Long referrerId, Long referredUserId) {
        // Retrieve the referrer and referred users
        User referrer = getUserService().getUserById(referrerId);
        User referredUser = getUserService().getUserById(referredUserId);

        // Create a new Referral object
        Referral referral = new Referral();
        referral.setReferrer(referrer);
        referral.setReferred(referredUser);
        referral.setLevel(0); // Assuming level 0 for direct referrals

        // Save the referral in the database
        referralRepository.save(referral);
    }

    /**
     * Retrieves a list of direct referrals for a specific user.
     *
     * @param userId The ID of the user for whom direct referrals are to be retrieved.
     * @return A list of direct referrals for the specified user.
     */
    @Override
    public List<Referral> getReferralsByUser(Long userId) {
        // Retrieve direct referrals for a specific user
        return referralRepository.findByReferrer_Id(userId);
    }

    /**
     * Retrieves a list of referrals for a specific user and referral level.
     *
     * @param userId The ID of the user for whom referrals are to be retrieved.
     * @param level  The referral level.
     * @return A list of referrals for the specified user and level.
     */
    @Override
    public List<Referral> getReferralsByUserAndLevel(Long userId, int level) {
        // Retrieve referrals for a specific user and level
        return referralRepository.findByReferred_IdAndLevel(userId, level);
    }

    /**
     * Retrieves the UserService bean from the application context.
     *
     * @return The UserService bean.
     */
    private UserService getUserService() {
        return applicationContext.getBean(UserService.class);
    }
}
