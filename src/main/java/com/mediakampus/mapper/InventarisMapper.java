package com.mediakampus.mapper;

import com.mediakampus.dto.InventarisDto;
import com.mediakampus.entity.Inventaris;

public class InventarisMapper {

    // Mengonversi InventarisDto ke Inventaris (entity)
    public static Inventaris mapToInventaris(InventarisDto inventarisDto) {
        return Inventaris.builder()
                .id(inventarisDto.getId())
                .namaBarang(inventarisDto.getNamaBarang())
                .jumlahTotal(inventarisDto.getJumlahBarang()) // Mapping jumlahBarang ke jumlahTotal
                .jumlahDipinjam(inventarisDto.getJumlahDipinjam())
                .jumlahTersedia(inventarisDto.getJumlahTersedia())
                .build();
    }

    // Mengonversi Inventaris (entity) ke InventarisDto
    public static InventarisDto mapToInventarisDto(Inventaris inventaris) {
        return InventarisDto.builder()
                .id(inventaris.getId())
                .namaBarang(inventaris.getNamaBarang())
                .jumlahBarang(inventaris.getJumlahTotal()) // Mapping jumlahTotal ke jumlahBarang
                .jumlahDipinjam(inventaris.getJumlahDipinjam())
                .jumlahTersedia(inventaris.getJumlahTersedia())
                .build();
    }
}
