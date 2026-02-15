package com.khushi.producthub.mapper;

import org.springframework.stereotype.Component;

import com.khushi.producthub.dto.ProductRequestDTO;
import com.khushi.producthub.dto.ProductResponseDTO;
import com.khushi.producthub.entity.Product;

@Component
public class ProductMapper {
	public Product toEntity(ProductRequestDTO dto) {
		Product product = new Product();
		product.setName(dto.getName());
		product.setDescription(dto.getDescription());
		product.setPrice(dto.getPrice());
		return product;
	}

	public void updateEntity(Product product, ProductRequestDTO dto) {
		product.setName(dto.getName());
		product.setDescription(dto.getDescription());
		product.setPrice(dto.getPrice());
	}

	public ProductResponseDTO toDTO(Product product) {
		return ProductResponseDTO.builder().id(product.getId()).name(product.getName())
				.description(product.getDescription()).price(product.getPrice()).build();
	}
}
