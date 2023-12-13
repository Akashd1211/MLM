package com.example.mlm.controller;


import com.example.mlm.entity.User;
import com.example.mlm.service.CommissionService;
import com.example.mlm.service.ReferralService;
import com.example.mlm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
/**
 * Controller class for handling user-related HTTP requests.
 * Uses Spring's RestController annotation to indicate that it handles RESTful services.
 * Base mapping is set to "/api/users".
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    // Autowired dependencies for user-related services
    private final CommissionService commissionService;
    private final UserService userService;
    private final ReferralService referralService;

    /**
     * Constructor for UserController.
     * @param userService Injected UserService implementation.
     * @param referralService Injected ReferralService implementation.
     * @param commissionService Injected CommissionService implementation.
     */
    @Autowired
    public UserController(UserService userService, ReferralService referralService, CommissionService commissionService) {
        this.userService = userService;
        this.referralService = referralService;
        this.commissionService = commissionService;
    }

    /**
     * Handles HTTP POST requests to register a new user.
     * @param user The user information provided in the request body.
     * @return ResponseEntity containing the registered user and HTTP status code.
     */
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User registeredUser = userService.registerUser(user);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    /**
     * Handles HTTP GET requests to retrieve a user by ID.
     * @param userId The ID of the user to retrieve.
     * @return ResponseEntity containing the retrieved user or HTTP status code.
     */
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Handles HTTP GET requests to retrieve direct referrals of a user.
     * @param userId The ID of the user for whom to retrieve direct referrals.
     * @return ResponseEntity containing the list of direct referrals or HTTP status code.
     */
    @GetMapping("/{userId}/direct-referrals")
    public ResponseEntity<?> getDirectReferrals(@PathVariable Long userId) {
        return new ResponseEntity<>(referralService.getReferralsByUser(userId), HttpStatus.OK);
    }

    /**
     * Handles HTTP GET requests to retrieve all referrals (direct and indirect) of a user.
     * @param userId The ID of the user for whom to retrieve all referrals.
     * @return ResponseEntity containing the list of all referrals or HTTP status code.
     */
    @GetMapping("/{userId}/all-referrals")
    public ResponseEntity<?> getAllReferrals(@PathVariable Long userId) {
        return new ResponseEntity<>(userService.getAllReferrals(userId), HttpStatus.OK);
    }

    /**
     * Handles HTTP POST requests to record a referral between two users.
     * @param userId The ID of the user making the referral.
     * @param referredUserId The ID of the user being referred.
     * @return ResponseEntity with HTTP status code indicating success or failure.
     */
    @PostMapping("/{userId}/record-referral")
    public ResponseEntity<?> recordReferral(
            @PathVariable Long userId,
            @RequestParam Long referredUserId
    ) {
        referralService.recordReferral(userId, referredUserId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Handles HTTP POST requests to calculate commissions for a user.
     * @param userId The ID of the user for whom to calculate commissions.
     * @return ResponseEntity with HTTP status code indicating success or failure.
     */
    @PostMapping("/{userId}/calculate-commissions")
    public ResponseEntity<?> calculateCommissions(@PathVariable Long userId) {
        commissionService.calculateCommissions(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Handles HTTP GET requests to retrieve commissions of a user.
     * @param userId The ID of the user for whom to retrieve commissions.
     * @return ResponseEntity containing the list of commissions or HTTP status code.
     */
    @GetMapping("/{userId}/commissions")
    public ResponseEntity<?> getUserCommissions(@PathVariable Long userId) {
        return new ResponseEntity<>(commissionService.getUserCommissions(userId), HttpStatus.OK);
    }
}
