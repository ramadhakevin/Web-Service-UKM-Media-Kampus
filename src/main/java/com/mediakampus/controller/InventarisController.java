package com.mediakampus.controller;

import com.mediakampus.dto.InventarisDto;
import com.mediakampus.dto.InventarisStatusDto;
import com.mediakampus.service.InventarisService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventaris")
public class InventarisController {

    @Autowired
    private InventarisService inventarisService;

    // Endpoint untuk menambah inventaris
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<InventarisDto> addInventaris(@RequestBody @Valid InventarisDto inventarisDto) {
        InventarisDto newInventaris = inventarisService.addInventaris(inventarisDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newInventaris);
    }

    // Endpoint untuk mengubah data inventaris
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<InventarisDto> updateInventaris(@PathVariable Long id, @RequestBody @Valid InventarisDto inventarisDto) {
        inventarisDto.setId(id); // Set ID untuk inventaris yang akan diupdate
        InventarisDto updatedInventaris = inventarisService.updateInventaris(inventarisDto);
        return ResponseEntity.ok(updatedInventaris);
    }

    // Endpoint untuk menghapus inventaris
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventaris(@PathVariable Long id) {
        inventarisService.deleteInventaris(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoint untuk melihat daftar inventaris
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping
    public ResponseEntity<List<InventarisDto>> getAllInventaris() {
        List<InventarisDto> inventarisList = inventarisService.getAllInventaris();
        return ResponseEntity.ok(inventarisList);
    }

    // Endpoint untuk melihat inventaris berdasarkan ID
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<InventarisDto> getInventarisById(@PathVariable Long id) {
        InventarisDto inventarisDto = inventarisService.getInventarisById(id);
        return ResponseEntity.ok(inventarisDto);
    }

    // Endpoint untuk melihat status peminjaman inventaris
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/{id}/status")
    public ResponseEntity<InventarisStatusDto> getInventarisStatus(@PathVariable Long id) {
        InventarisStatusDto statusDto = inventarisService.getInventarisStatus(id);
        return ResponseEntity.ok(statusDto);
    }
}