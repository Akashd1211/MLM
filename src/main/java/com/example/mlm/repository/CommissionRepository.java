package com.example.mlm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mlm.entity.Commission;

/**
 * Repository interface for handling database operations related to commissions.
 * Extends JpaRepository for basic CRUD operations.
 */
public interface CommissionRepository extends JpaRepository<Commission, Long> {

    /**
     * Retrieves a list of commissions associated with a specific user.
     *
     * @param userId The ID of the user for whom commissions are to be retrieved.
     * @return A list of commissions associated with the specified user.
     */
    List<Commission> findByUser_Id(Long userId);
}

