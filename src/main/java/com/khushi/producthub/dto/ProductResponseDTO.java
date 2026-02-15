package com.khushi.producthub.dto;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductResponseDTO {
	private Long id;
	private String name;
	private String description;
	private BigDecimal price;
}
