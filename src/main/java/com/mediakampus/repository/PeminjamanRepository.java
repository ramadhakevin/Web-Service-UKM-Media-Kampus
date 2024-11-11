package com.mediakampus.repository;

import com.mediakampus.entity.Peminjaman;
import com.mediakampus.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PeminjamanRepository extends JpaRepository<Peminjaman, Long> {
    // Mencari peminjaman berdasarkan pengguna
    List<Peminjaman> findByUser(User user);

    // Mencari peminjaman berdasarkan ID dan User
    Optional<Peminjaman> findByIdPeminjamanAndUser(Long idPeminjaman, User user);

    // Mencari peminjaman berdasarkan ID barang (inventaris)
    List<Peminjaman> findByIdBarang(Long idBarang);
}
