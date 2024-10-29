package br.fatec.product.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.fatec.product.entities.product;

public interface ProductRepository extends JpaRepository<product,Long> {
    
}
