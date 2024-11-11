package com.mediakampus.repository;

import com.mediakampus.entity.Inventaris;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventarisRepository extends JpaRepository<Inventaris, Long> {
    Optional<Inventaris> findByNamaBarang(String namaBarang); // Untuk mencari barang berdasarkan nama
}
