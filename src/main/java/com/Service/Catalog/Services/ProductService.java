package com.Service.Catalog.Services;

import com.Service.Catalog.Entities.Product;
import com.Service.Catalog.Repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product findByName(String name){
        return productRepository.findByName(name).orElseThrow(() -> new IllegalStateException("Product doesn't exist"));
    }

    public void addProduct(Product product){
        Optional<Product> productOptional = productRepository.findByName(product.getName());
        Optional<Product> productOptional1 = productRepository.findByCode(product.getCode());
        if(productOptional.isPresent() || productOptional1.isPresent()){
            throw new IllegalStateException("Product with already exists");
        }
        productRepository.save(product);
    }

    @Transactional
    public void updateProduct(String name, Product request){
        Product product = productRepository.findByName(name) .orElseThrow(() -> new IllegalStateException("Product with the name " +name+" doesn't exist"));
        if(request.getBrand() != null && !Objects.equals(product.getBrand(), request.getBrand())){
            product.setBrand(request.getBrand());
        }
        if(request.getInStock() >0 && request.getInStock() <=500 && !Objects.equals(product.getInStock(), request.getInStock())){
            product.setInStock(request.getInStock());
        }
        if(request.getPrice() != null && request.getPrice()>=0 &&!Objects.equals(product.getPrice(), request.getPrice())){
            product.setPrice(request.getPrice());
        }
        if(request.getName() != null && request.getName().length() > 0 && !Objects.equals(product.getName(), request.getName())) {
            product.setName(request.getName());
        }
        if(request.getCode() != null && request.getCode().length() > 0 && !Objects.equals(product.getCode(), request.getCode())){
            product.setCode(request.getCode());
        }
        if(request.getCondition() != null && request.getCondition().length() > 0 && !Objects.equals(product.getCondition(), request.getCondition())){
            product.setCondition(request.getCondition());
        }
    }

    public void deleteProduct(String name){
        boolean exists = productRepository.findByName(name).isPresent();
        if(!exists){
            throw new IllegalStateException("Product with the name "+name+" does not exist");
        }
        productRepository.deleteByName(name);
    }
}
