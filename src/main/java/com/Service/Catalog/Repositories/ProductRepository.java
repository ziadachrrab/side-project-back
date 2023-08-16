package com.Service.Catalog.Repositories;

import com.Service.Catalog.Entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE p.name = ?1")
    Optional<Product> findByName(String name);
    @Query("SELECT p FROM Product p WHERE p.code = ?1")
    Optional<Product> findByCode(String code);

    @Transactional
    @Modifying
    @Query(value = ("DELETE FROM Product WHERE name = ?1"))
    void deleteByName(String name);
}
