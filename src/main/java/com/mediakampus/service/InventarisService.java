package com.mediakampus.service;

import com.mediakampus.dto.InventarisDto;
import com.mediakampus.entity.Inventaris;
import com.mediakampus.mapper.InventarisMapper;
import com.mediakampus.repository.InventarisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
// Tambahkan import yang diperlukan
import com.mediakampus.dto.InventarisStatusDto;
import com.mediakampus.entity.Peminjaman; // Misalkan Anda memiliki entitas Peminjaman
import com.mediakampus.repository.PeminjamanRepository; // Repository untuk Peminjaman

@Service
public class InventarisService {

    @Autowired
    private InventarisRepository inventarisRepository;

    @Autowired
    private PeminjamanRepository peminjamanRepository; // Tambahkan ini jika Anda memiliki repository untuk peminjaman

    // Fungsi untuk menambahkan inventaris barang baru
    public InventarisDto addInventaris(InventarisDto inventarisDto) {
        Inventaris inventaris = InventarisMapper.mapToInventaris(inventarisDto);
        inventaris = inventarisRepository.save(inventaris);
        return InventarisMapper.mapToInventarisDto(inventaris);
    }

    // Fungsi untuk mengubah data inventaris barang
    public InventarisDto updateInventaris(InventarisDto inventarisDto) {
        Inventaris inventaris = InventarisMapper.mapToInventaris(inventarisDto);
        inventaris = inventarisRepository.save(inventaris);
        return InventarisMapper.mapToInventarisDto(inventaris);
    }

    // Fungsi untuk menghapus data inventaris
    public void deleteInventaris(Long id) {
        inventarisRepository.deleteById(id);
    }

    // Fungsi untuk mendapatkan seluruh daftar inventaris
    public List<InventarisDto> getAllInventaris() {
        List<Inventaris> inventarisList = inventarisRepository.findAll();
        return inventarisList.stream()
                .map(InventarisMapper::mapToInventarisDto)
                .toList();
    }

    // Fungsi untuk mendapatkan inventaris berdasarkan ID
    public InventarisDto getInventarisById(Long id) {
        Inventaris inventaris = inventarisRepository.findById(id).orElseThrow(() -> new RuntimeException("Inventaris not found"));
        return InventarisMapper.mapToInventarisDto(inventaris);
    }

    // Fungsi untuk mendapatkan status peminjaman inventaris
    public InventarisStatusDto getInventarisStatus(Long id) {
        Inventaris inventaris = inventarisRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventaris not found"));

        // Ambil data peminjaman terkait
        List<Peminjaman> peminjamanList = peminjamanRepository.findByIdBarang(id); // Menggunakan metode baru

        // Hitung jumlah yang dipinjam dan yang tersedia
        int jumlahDipinjam = peminjamanList.stream().mapToInt(Peminjaman::getJumlahDipinjam).sum(); // Jumlah total yang dipinjam
        int jumlahTersedia = inventaris.getJumlahTotal() - jumlahDipinjam; // Misalkan Anda memiliki field jumlah di entitas Inventaris

        // Buat dan kembalikan InventarisStatusDto
        return new InventarisStatusDto(
                inventaris.getId(),
                inventaris.getNamaBarang(),
                jumlahDipinjam,
                jumlahTersedia,
                jumlahDipinjam > 0 ? peminjamanList.get(0).getPeminjam() : null // Ambil nama peminjam jika ada
        );
    }
}
