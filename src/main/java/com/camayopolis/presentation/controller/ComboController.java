package com.camayopolis.presentation.controller;

import com.camayopolis.presentation.dto.ComboDto;
import com.camayopolis.presentation.dto.MovieDto;
import com.camayopolis.service.interfaces.IComboService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/combo")
public class ComboController {
    private final IComboService comboService;

    public ComboController(IComboService comboService) {
        this.comboService = comboService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ComboDto>> getAllCombos() {
        List<ComboDto> combos = comboService.getAllCombos();

        if (combos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(combos);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<ComboDto> getComboById(@PathVariable Integer id) {
        Optional<ComboDto> combo = comboService.getComboById(id);
        return combo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<Optional<ComboDto>> createCombo(@Valid @RequestBody ComboDto comboDto) {
        Optional<ComboDto> createdCombo = comboService.createCombo(comboDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCombo);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ComboDto> updateCombo(@PathVariable Integer id, @Valid @RequestBody ComboDto comboDTO) {
        Optional<ComboDto> updatedCombo = comboService.updateCombo(id, comboDTO);

        return updatedCombo.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCombo(@PathVariable Integer id) {
        comboService.deleteCombo(id);
    }
}
