package com.khushi.producthub.contoller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.khushi.producthub.dto.ProductRequestDTO;
import com.khushi.producthub.dto.ProductResponseDTO;
import com.khushi.producthub.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

	private final ProductService service;

	@Operation(summary = "Get all products with pagination")
	@GetMapping
	public ResponseEntity<Page<ProductResponseDTO>> getAll(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size) {
		return ResponseEntity.ok(service.getAll(page, size));
	}

	@Operation(summary = "Get product by id")
	@GetMapping("/{id}")
	public ResponseEntity<ProductResponseDTO> getById(@PathVariable Long id) {
		return ResponseEntity.ok(service.getById(id));
	}

	@Operation(summary = "Create new product")
	@PostMapping
	public ResponseEntity<ProductResponseDTO> create(@Valid @RequestBody ProductRequestDTO dto) {
		return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
	}

	@Operation(summary = "Update product")
	@PutMapping("/{id}")
	public ResponseEntity<ProductResponseDTO> update(@PathVariable Long id, @Valid @RequestBody ProductRequestDTO dto) {
		return ResponseEntity.ok(service.update(id, dto));
	}

	@Operation(summary = "Delete product")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
