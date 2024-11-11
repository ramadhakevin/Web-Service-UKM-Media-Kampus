package com.mediakampus.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "inventaris")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Inventaris {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String namaBarang;

    @Column(nullable = false)
    private int jumlahTotal;

    private int jumlahDipinjam;
    private int jumlahTersedia;
}
