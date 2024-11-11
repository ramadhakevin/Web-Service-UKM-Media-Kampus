package com.mediakampus.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventarisDto {

    private Long id;
    private String namaBarang;
    private Integer jumlahBarang;
    private Integer jumlahDipinjam;
    private Integer jumlahTersedia;
}

