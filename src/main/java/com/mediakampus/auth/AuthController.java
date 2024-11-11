package com.mediakampus.auth;

import com.mediakampus.dto.UserDto;
import com.mediakampus.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    // Endpoint untuk login
    @Operation(summary = "User login to get access token.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Email and access token",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Page.class))),
            @ApiResponse(responseCode = "401",
                    description = "Invalid credentials", content = @Content)
    })

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody AuthRequest request) {
        try {
            // Mencoba melakukan autentikasi menggunakan username dan password
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );

            // Jika autentikasi berhasil, generate JWT token menggunakan email dari request
            String accessToken = jwtUtil.generateAccessToken(request.getEmail());

            // Proses autentikasi
            AuthResponse response = new AuthResponse(request.getEmail(), accessToken);
            return ResponseEntity.ok().body(response);

        } catch (BadCredentialsException ex) {
            System.out.println("Login error: " + ex.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");

        } catch (Exception ex) {
            System.out.println("Unexpected error: " + ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred during authentication");
        }
    }

    // Endpoint untuk registrasi pengguna baru
    @Operation(summary = "Register a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "User Register successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDto.class))),
            @ApiResponse(responseCode = "400",
                    description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "409",
                    description = "Conflict - User with the same email already exists",
                    content = @Content),
    })
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid UserDto request) {
        // Memanggil service untuk melakukan registrasi pengguna
        UserDto user = userService.createUser(request);
        return ResponseEntity.ok().body(user);
    }
}
