package com.mediakampus.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Username is required")
    @Column(unique = true)
    private String username;

    @NotBlank(message = "Name is required")
    private String nama;

    @NotBlank(message = "Password is required")
    private String password;

    private String role;

    @Email(message = "Invalid email format")
    @Column(unique = true)
    private String email;

    @OneToMany(mappedBy = "user")
    private List<Peminjaman> peminjamanList;
}