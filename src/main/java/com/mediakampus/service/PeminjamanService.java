package com.mediakampus.service;

import com.mediakampus.dto.PeminjamanDto;
import com.mediakampus.entity.Peminjaman;
import com.mediakampus.mapper.PeminjamanMapper;
import com.mediakampus.repository.PeminjamanRepository;
import com.mediakampus.repository.InventarisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mediakampus.entity.Inventaris;


import java.time.LocalDate;
import java.util.List;

@Service
public class PeminjamanService {

    @Autowired
    private PeminjamanRepository peminjamanRepository;

    @Autowired
    private InventarisRepository inventarisRepository;

    // Fungsi untuk melakukan peminjaman inventaris
    public PeminjamanDto borrowInventaris(PeminjamanDto peminjamanDto) {
        // Cek ketersediaan stok
        Long inventarisId = peminjamanDto.getIdBarang();
        Inventaris inventaris = inventarisRepository.findById(inventarisId).orElseThrow(() -> new RuntimeException("Inventaris not found"));
        if (inventaris.getJumlahTersedia() < peminjamanDto.getJumlahDipinjam()) {
            throw new RuntimeException("Stok tidak mencukupi");
        }

        // Kurangi stok barang yang dipinjam
        inventaris.setJumlahTersedia(inventaris.getJumlahTersedia() - peminjamanDto.getJumlahDipinjam());
        inventaris.setJumlahDipinjam(inventaris.getJumlahDipinjam() + peminjamanDto.getJumlahDipinjam());
        inventarisRepository.save(inventaris);

        // Simpan peminjaman
        Peminjaman peminjaman = PeminjamanMapper.mapToPeminjaman(peminjamanDto);
        peminjaman = peminjamanRepository.save(peminjaman);

        return PeminjamanMapper.mapToPeminjamanDto(peminjaman);
    }

    // Fungsi untuk mengembalikan barang
    public void returnInventaris(Long idPeminjaman, LocalDate tanggalPengembalian) {
        Peminjaman peminjaman = peminjamanRepository.findById(idPeminjaman).orElseThrow(() -> new RuntimeException("Peminjaman not found"));
        Inventaris inventaris = inventarisRepository.findById(peminjaman.getIdBarang()).orElseThrow(() -> new RuntimeException("Inventaris not found"));

        // Update jumlah stok setelah barang dikembalikan
        inventaris.setJumlahTersedia(inventaris.getJumlahTersedia() + peminjaman.getJumlahDipinjam());
        inventaris.setJumlahDipinjam(inventaris.getJumlahDipinjam() - peminjaman.getJumlahDipinjam());
        inventarisRepository.save(inventaris);

        // Update tanggal pengembalian
        peminjaman.setTanggalPengembalian(tanggalPengembalian);
        peminjamanRepository.save(peminjaman);
    }

    // Fungsi untuk melihat semua peminjaman
    public List<PeminjamanDto> getAllPeminjaman() {
        List<Peminjaman> peminjamanList = peminjamanRepository.findAll();
        return peminjamanList.stream()
                .map(PeminjamanMapper::mapToPeminjamanDto)
                .toList();
    }

    // Fungsi untuk menghapus peminjaman
    public void deletePeminjaman(Long idPeminjaman) {
        peminjamanRepository.deleteById(idPeminjaman);
    }

    // Fungsi untuk melihat peminjaman berdasarkan ID
    public PeminjamanDto getPeminjamanById(Long idPeminjaman) {
        Peminjaman peminjaman = peminjamanRepository.findById(idPeminjaman)
                .orElseThrow(() -> new RuntimeException("Peminjaman not found"));
        return PeminjamanMapper.mapToPeminjamanDto(peminjaman);
    }
}
