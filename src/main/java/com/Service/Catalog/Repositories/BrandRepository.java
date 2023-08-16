package com.Service.Catalog.Repositories;

import com.Service.Catalog.Entities.Brand;
import com.Service.Catalog.Entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
    @Query("SELECT b FROM Brand b WHERE b.name = ?1")
    Optional<Brand> findByName(String name);

    @Transactional
    @Modifying
    @Query(value = ("DELETE FROM Brand WHERE name = ?1"))
    void deleteByName(String name);
}
