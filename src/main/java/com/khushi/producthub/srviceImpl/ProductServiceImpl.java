package com.khushi.producthub.srviceImpl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.khushi.producthub.dto.ProductRequestDTO;
import com.khushi.producthub.dto.ProductResponseDTO;
import com.khushi.producthub.entity.Product;
import com.khushi.producthub.exception.ResourceNotFoundException;
import com.khushi.producthub.mapper.ProductMapper;
import com.khushi.producthub.repository.ProductRepository;
import com.khushi.producthub.service.ProductService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService {

	private final ProductRepository repository;
	private final ProductMapper mapper;

	@Override
	@Transactional(readOnly = true)
	public Page<ProductResponseDTO> getAll(int page, int size) {
		Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
		return repository.findAll(pageable).map(mapper::toDTO);
	}

	@Override
	@Transactional(readOnly = true)
	public ProductResponseDTO getById(Long id) {
		Product product = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
		return mapper.toDTO(product);
	}

	@Override
	public ProductResponseDTO create(ProductRequestDTO dto) {
		Product product = mapper.toEntity(dto);
		return mapper.toDTO(repository.save(product));
	}

	@Override
	public ProductResponseDTO update(Long id, ProductRequestDTO dto) {
		Product product = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));

		mapper.updateEntity(product, dto);
		return mapper.toDTO(repository.save(product));
	}

	@Override
	public void delete(Long id) {
		if (!repository.existsById(id)) {
			throw new ResourceNotFoundException("Product not found with id: " + id);
		}
		repository.deleteById(id);
	}

}