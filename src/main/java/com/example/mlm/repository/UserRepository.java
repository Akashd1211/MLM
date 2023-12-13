package com.example.mlm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mlm.entity.User;

/**
 * Repository interface for handling database operations related to users.
 * Extends JpaRepository for basic CRUD operations.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Saves a user to the database.
     *
     * @param user The user to be saved.
     * @return The saved user.
     */
    User save(User user);

    /**
     * Checks if a user with the specified email already exists in the database.
     *
     * @param email The email to check.
     * @return True if a user with the email already exists, false otherwise.
     */
    boolean existsByEmail(String email);

    /**
     * Retrieves a user by their ID.
     *
     * @param userId The ID of the user to retrieve.
     * @return An optional containing the user with the specified ID, or empty if not found.
     */
    Optional<User> findById(Long userId);
}
