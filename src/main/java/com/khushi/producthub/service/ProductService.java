package com.khushi.producthub.service;

import org.springframework.data.domain.Page;

import com.khushi.producthub.dto.ProductRequestDTO;
import com.khushi.producthub.dto.ProductResponseDTO;

public interface ProductService {
	Page<ProductResponseDTO> getAll(int page, int size);

	ProductResponseDTO getById(Long id);

	ProductResponseDTO create(ProductRequestDTO dto);

	ProductResponseDTO update(Long id, ProductRequestDTO dto);

	void delete(Long id);
}
