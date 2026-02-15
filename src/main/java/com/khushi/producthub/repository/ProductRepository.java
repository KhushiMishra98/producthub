package com.khushi.producthub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.khushi.producthub.entity.Product;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
