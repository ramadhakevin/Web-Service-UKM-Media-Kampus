package com.mediakampus.service;

import com.mediakampus.dto.InventarisDto;
import com.mediakampus.entity.Inventaris;
import com.mediakampus.mapper.InventarisMapper;
import com.mediakampus.repository.InventarisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventarisService {

    @Autowired
    private InventarisRepository inventarisRepository;

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
}
