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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!StringUtils.hasText(username)) {
            throw new UsernameNotFoundException("Username cannot be empty");
        }

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

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