package com.mediakampus.mapper;

import com.mediakampus.dto.PeminjamanDto;
import com.mediakampus.entity.Peminjaman;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class PeminjamanMapper {

    // Mengonversi PeminjamanDto ke Peminjaman (entity)
    public static Peminjaman mapToPeminjaman(PeminjamanDto peminjamanDto) {
        // Konversi tanggal dari Date ke LocalDate
        LocalDate tanggalPeminjaman = convertToLocalDateViaInstant(peminjamanDto.getTanggalPeminjaman());
        LocalDate tanggalPengembalian = convertToLocalDateViaInstant(peminjamanDto.getTanggalPengembalian());

        return Peminjaman.builder()
                .idPeminjaman(peminjamanDto.getIdPeminjaman())
                .idBarang(peminjamanDto.getIdBarang())
                .namaBarang(peminjamanDto.getNamaBarang())
                .jumlahDipinjam(peminjamanDto.getJumlahDipinjam())
                .tanggalPeminjaman(tanggalPeminjaman)
                .tanggalPengembalian(tanggalPengembalian)
                .build();
    }

    // Mengonversi Peminjaman (entity) ke PeminjamanDto
    public static PeminjamanDto mapToPeminjamanDto(Peminjaman peminjaman) {
        // Konversi tanggal dari LocalDate ke Date
        Date tanggalPeminjaman = convertToDateViaSqlDate(peminjaman.getTanggalPeminjaman());
        Date tanggalPengembalian = convertToDateViaSqlDate(peminjaman.getTanggalPengembalian());

        return PeminjamanDto.builder()
                .idPeminjaman(peminjaman.getIdPeminjaman())
                .idBarang(peminjaman.getIdBarang())
                .namaBarang(peminjaman.getNamaBarang())
                .jumlahDipinjam(peminjaman.getJumlahDipinjam())
                .tanggalPeminjaman(tanggalPeminjaman)
                .tanggalPengembalian(tanggalPengembalian)
                .build();
    }

    // Mengonversi Date ke LocalDate
    private static LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    // Mengonversi LocalDate ke Date
    private static Date convertToDateViaSqlDate(LocalDate dateToConvert) {
        return java.sql.Date.valueOf(dateToConvert);
    }
}
