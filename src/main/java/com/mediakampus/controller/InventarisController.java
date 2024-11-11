package com.mediakampus.controller;

import com.mediakampus.dto.InventarisDto;
import com.mediakampus.service.InventarisService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventaris")
public class InventarisController {

    @Autowired
    private InventarisService inventarisService;

    // Endpoint untuk menambah inventaris
    @PostMapping
    public ResponseEntity<InventarisDto> addInventaris(@RequestBody @Valid InventarisDto inventarisDto) {
        InventarisDto newInventaris = inventarisService.addInventaris(inventarisDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newInventaris);
    }

    // Endpoint untuk mengubah data inventaris
    @PutMapping
    public ResponseEntity<InventarisDto> updateInventaris(@RequestBody @Valid InventarisDto inventarisDto) {
        InventarisDto updatedInventaris = inventarisService.updateInventaris(inventarisDto);
        return ResponseEntity.ok(updatedInventaris);
    }

    // Endpoint untuk menghapus inventaris
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventaris(@PathVariable Long id) {
        inventarisService.deleteInventaris(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoint untuk melihat daftar inventaris
    @GetMapping
    public ResponseEntity<List<InventarisDto>> getAllInventaris() {
        List<InventarisDto> inventarisList = inventarisService.getAllInventaris();
        return ResponseEntity.ok(inventarisList);
    }

    // Endpoint untuk melihat inventaris berdasarkan ID
    @GetMapping("/{id}")
    public ResponseEntity<InventarisDto> getInventarisById(@PathVariable Long id) {
        InventarisDto inventarisDto = inventarisService.getInventarisById(id);
        return ResponseEntity.ok(inventarisDto);
    }
}
