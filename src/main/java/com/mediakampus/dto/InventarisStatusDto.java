package com.mediakampus.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventarisStatusDto {
    private Long id; // ID inventaris
    private String namaBarang; // Nama barang
    private int jumlahDipinjam; // Jumlah barang yang sedang dipinjam
    private int jumlahTersedia; // Jumlah barang yang tersedia
    private String peminjam; // Nama peminjam jika barang dipinjam
}