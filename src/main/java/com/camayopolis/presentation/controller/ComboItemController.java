package com.camayopolis.presentation.controller;

import com.camayopolis.presentation.dto.ComboItemRequestDto;
import com.camayopolis.presentation.dto.ComboItemResponseDto;
import com.camayopolis.service.interfaces.IComboItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comboItem")
public class ComboItemController {
    private final IComboItemService comboItemService;

    public ComboItemController(IComboItemService comboItemService) {
        this.comboItemService = comboItemService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ComboItemResponseDto>> getAllComboItems() {
        List<ComboItemResponseDto> comboItems = comboItemService.getAllComboItems();

        if (comboItems.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(comboItems);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<ComboItemResponseDto> getComboItemById(@PathVariable Integer id) {
        Optional<ComboItemResponseDto> comboItem = comboItemService.getComboItemById(id);
        return comboItem.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<Optional<ComboItemRequestDto>> createComboItem(@RequestBody ComboItemRequestDto comboItemDto) {
        Optional<ComboItemRequestDto> createdComboItem = comboItemService.createComboItem(comboItemDto);
        return ResponseEntity.ok(createdComboItem);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ComboItemRequestDto> updateComboItem(@PathVariable Integer id, @RequestBody ComboItemRequestDto comboItemDto) {
        Optional<ComboItemRequestDto> updatedComboItem = comboItemService.updateComboItem(id, comboItemDto);

        return updatedComboItem.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public void deleteComboItem(@PathVariable Integer id) {
        comboItemService.deleteComboItem(id);
    }
}
