package com.stockPrice.app.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.stockPrice.app.Repository.UserRepository;
import com.stockPrice.app.entity.User;
import com.stockPrice.app.util.JwtUtil;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String registerUser(User user) {
        // Encode the user's password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User registered successfully!";
    }


}


