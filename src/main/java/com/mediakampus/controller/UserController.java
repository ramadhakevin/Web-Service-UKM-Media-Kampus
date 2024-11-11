package com.mediakampus.controller;

import com.mediakampus.dto.UserDto;
import com.mediakampus.service.UserService;
import com.mediakampus.auth.AuthRequest;
import com.mediakampus.auth.AuthResponse;
import com.mediakampus.auth.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    // Endpoint untuk mendapatkan profil pengguna
    @GetMapping("/profile")
    public ResponseEntity<UserDto> getProfile(@RequestParam String email) {
        UserDto userDto = userService.getUserByEmail(email);
        return ResponseEntity.ok(userDto);
    }

    // Endpoint untuk memperbarui profil pengguna
    @PutMapping("/profile")
    public ResponseEntity<UserDto> updateProfile(@RequestBody @Valid UserDto userDto) {
        UserDto updatedUser = userService.updateUserProfile(userDto);
        return ResponseEntity.ok(updatedUser);
    }

    // Endpoint untuk mengganti password
    @PutMapping("/change-password")
    public ResponseEntity<UserDto> changePassword(@RequestParam Long userId, @RequestParam String newPassword) {
        UserDto updatedUser = userService.changePassword(userId, newPassword);
        return ResponseEntity.ok(updatedUser);
    }

    // Endpoint untuk menghapus akun pengguna
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteUser(@RequestParam Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}
