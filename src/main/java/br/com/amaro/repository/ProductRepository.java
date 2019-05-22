package br.com.amaro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import br.com.amaro.entity.Product;

@Transactional(readOnly = true)
public interface ProductRepository extends JpaRepository<Product, Long> {

}
