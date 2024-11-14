package com.camayopolis.presentation.controller;

import com.camayopolis.presentation.dto.ProductDto;
import com.camayopolis.service.interfaces.IProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> categories = productService.getAllProducts();

        if (categories.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Integer id) {
        return productService.getProductById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<Optional<ProductDto>> createProduct(@Valid @RequestBody ProductDto product) {
        Optional<ProductDto> createdProduct = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Integer id, @Valid @RequestBody ProductDto product) {
        Optional<ProductDto> updatedProduct = productService.updateProduct(id, product);

        return updatedProduct.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
        if (!productService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
