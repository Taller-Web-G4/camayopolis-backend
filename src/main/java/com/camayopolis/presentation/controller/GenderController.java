package com.camayopolis.presentation.controller;

import com.camayopolis.presentation.dto.GenderDto;
import com.camayopolis.service.interfaces.IGenderService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gender")
public class GenderController {
    private final IGenderService genderService;

    public GenderController(IGenderService genderService) {
        this.genderService = genderService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<GenderDto>> getAllGenders() {
        List<GenderDto> genres = genderService.getAllGenders();

        if (genres.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(genres);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<GenderDto> getGenderById(@PathVariable Integer id) {
        return genderService.getGenderById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<GenderDto> createGender(@Valid @RequestBody GenderDto genderDto) {
        return genderService.createGender(genderDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<GenderDto> updateGender(@PathVariable Integer id, @Valid @RequestBody GenderDto genderDto) {
        return genderService.updateGender(id, genderDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteGender(@PathVariable Integer id) {
        if (!genderService.existsById(id)) {
            ResponseEntity.notFound().build();
        }

        genderService.deleteGender(id);
        return ResponseEntity.noContent().build();
    }
}
