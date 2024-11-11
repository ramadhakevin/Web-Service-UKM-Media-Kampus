package com.mediakampus.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PeminjamanDto {
    private Long idPeminjaman;
    private Long idBarang;
    private String namaBarang;
    private int jumlahDipinjam;
    private Date tanggalPeminjaman;
    private Date tanggalPengembalian;
    private int jumlahHariTerlambat; // Dihitung hanya jika lewat dari tanggal pengembalian
}
