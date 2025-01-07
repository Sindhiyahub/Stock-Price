package com.stockPrice.app.Service;

// Alias to differentiate between Spring's User

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.stockPrice.app.Repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fetch the user from the database
        Optional<com.stockPrice.app.entity.User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        com.stockPrice.app.entity.User appUser = userOptional.get();

        // Map the User entity to a Spring Security UserDetails object
        return User.builder()
                .username(appUser.getUsername())
                .password(appUser.getPassword()) // Ensure the password is hashed in the database
                .roles(appUser.getRole()) // Role should be in the format "ROLE_USER"
                .build();
    }
}

