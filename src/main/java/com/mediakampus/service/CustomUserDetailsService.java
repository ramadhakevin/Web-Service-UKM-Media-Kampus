package com.mediakampus.service;

import com.mediakampus.entity.User;
import com.mediakampus.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.StringUtils;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        if (!StringUtils.hasText(email)) {
            throw new UsernameNotFoundException("Email cannot be empty");
        }

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        // Pastikan role memiliki prefix "ROLE_"
        String role = StringUtils.hasText(user.getRole()) ?
                user.getRole().startsWith("ROLE_") ? user.getRole().substring(5) : user.getRole()
                : "USER";

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(role) // roles() secara otomatis menambahkan prefix "ROLE_"
                .build();
    }
}