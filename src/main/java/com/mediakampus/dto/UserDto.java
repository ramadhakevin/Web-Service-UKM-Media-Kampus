package com.mediakampus.dto;

import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserDto implements UserDetails {
    private Long id;
    private String username;
    private String nama;
    private String password;
    private String role;
    private String email;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null; // Implement sesuai dengan kebutuhan, misalnya mengembalikan peran atau otoritas pengguna
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Mengembalikan true jika akun tidak pernah kadaluwarsa
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Mengembalikan true jika akun tidak terkunci
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Mengembalikan true jika kredensial (password) tidak pernah kadaluwarsa
    }

    @Override
    public boolean isEnabled() {
        return true; // Mengembalikan true jika akun pengguna aktif
    }
}
