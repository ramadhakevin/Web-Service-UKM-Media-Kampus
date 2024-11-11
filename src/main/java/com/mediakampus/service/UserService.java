package com.mediakampus.service;

import com.mediakampus.dto.UserDto;
import com.mediakampus.entity.User;
import com.mediakampus.mapper.UserMapper;
import com.mediakampus.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Fungsi untuk membuat akun user baru
    public UserDto createUser(UserDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));  // Enkripsi password
        User user = UserMapper.mapToUser(userDto);
        user = userRepository.save(user);
        return UserMapper.mapToUserDto(user);  // Kembalikan UserDto setelah disimpan
    }

    public UserDto getUserByEmail(String email) {
        // Gunakan orElseThrow untuk menangani Optional
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
        return UserMapper.mapToUserDto(user);
    }

    // Fungsi untuk memperbarui profil user
    public UserDto updateUserProfile(UserDto userDto) {
        User user = UserMapper.mapToUser(userDto);
        user = userRepository.save(user);
        return UserMapper.mapToUserDto(user);  // Mengembalikan data yang sudah diperbarui
    }

    // Fungsi untuk mengganti password user
    public UserDto changePassword(Long userId, String newPassword) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.setPassword(passwordEncoder.encode(newPassword));  // Enkripsi password baru
        user = userRepository.save(user);
        return UserMapper.mapToUserDto(user);
    }

    // Fungsi untuk menghapus akun user
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
