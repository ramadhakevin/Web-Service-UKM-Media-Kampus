package com.mediakampus.controller;

import com.mediakampus.dto.PeminjamanDto;
import com.mediakampus.service.PeminjamanService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/peminjaman")
public class PeminjamanController {

    @Autowired
    private PeminjamanService peminjamanService;

    // Endpoint untuk meminjam inventaris
    @PostMapping
    public ResponseEntity<PeminjamanDto> borrowInventaris(@RequestBody @Valid PeminjamanDto peminjamanDto) {
        PeminjamanDto newPeminjaman = peminjamanService.borrowInventaris(peminjamanDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newPeminjaman);
    }

    // Endpoint untuk mengembalikan inventaris
    @PutMapping("/return/{idPeminjaman}")
    public ResponseEntity<Void> returnInventaris(@PathVariable Long idPeminjaman, @RequestParam LocalDate tanggalPengembalian) {
        peminjamanService.returnInventaris(idPeminjaman, tanggalPengembalian);
        return ResponseEntity.noContent().build();
    }

    // Endpoint untuk melihat daftar peminjaman
    @GetMapping
    public ResponseEntity<List<PeminjamanDto>> getAllPeminjaman() {
        List<PeminjamanDto> peminjamanList = peminjamanService.getAllPeminjaman();
        return ResponseEntity.ok(peminjamanList);
    }

    // Endpoint untuk melihat peminjaman berdasarkan ID
    @GetMapping("/{idPeminjaman}")
    public ResponseEntity<PeminjamanDto> getPeminjamanById(@PathVariable Long idPeminjaman) {
        PeminjamanDto peminjamanDto = peminjamanService.getPeminjamanById(idPeminjaman);
        return ResponseEntity.ok(peminjamanDto);
    }

    // Endpoint untuk menghapus peminjaman
    @DeleteMapping("/{idPeminjaman}")
    public ResponseEntity<Void> deletePeminjaman(@PathVariable Long idPeminjaman) {
        peminjamanService.deletePeminjaman(idPeminjaman);
        return ResponseEntity.noContent().build();
    }
}
