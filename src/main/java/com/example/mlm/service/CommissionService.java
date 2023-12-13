package com.example.mlm.service;

import java.util.List;

import com.example.mlm.entity.Commission;

/**
 * Service interface for managing commission-related operations.
 */
public interface CommissionService {

    /**
     * Calculates commissions for a specific user based on their referrals and levels.
     *
     * @param userId The unique identifier of the user for whom commissions are to be calculated.
     */
    void calculateCommissions(Long userId);

    /**
     * Retrieves all commissions associated with a specific user.
     *
     * @param userId The unique identifier of the user.
     * @return A list of Commission objects representing all commissions associated with the user.
     */
    List<Commission> getUserCommissions(Long userId);
}
