package com.mediakampus.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Peminjaman {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPeminjaman;

    @NotNull(message = "ID Barang is required")
    private Long idBarang;

    @NotBlank(message = "Nama Barang is required")
    private String namaBarang;

    @NotNull(message = "Jumlah Dipinjam is required")
    private int jumlahDipinjam;

    @NotNull(message = "Tanggal Peminjaman is required")
    private LocalDate tanggalPeminjaman;

    @NotNull(message = "Tanggal Pengembalian is required")
    private LocalDate tanggalPengembalian;

    // Relasi ManyToOne dengan User
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    // Misalkan entitas User memiliki field nama
    public String getPeminjam() {
        return user != null ? user.getNama() : null; // Pastikan User tidak null sebelum mengakses
    }
}