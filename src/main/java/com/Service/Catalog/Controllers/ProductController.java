package com.Service.Catalog.Controllers;

import com.Service.Catalog.Entities.Product;
import com.Service.Catalog.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")

public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public List<Product> getProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/find/{name}")
    public void findProduct(@PathVariable("name") String name){
        productService.findByName(name);
    }
    @PostMapping("/add")
    public void registerProduct(@RequestBody Product product){
        productService.addProduct(product);
    }
    @PutMapping("/update/{name}")
        public void updateProduct(@PathVariable("name") String name, @RequestBody Product request){
            productService.updateProduct(name, request);
    }

    @DeleteMapping("/delete/{name}")
    public void deleteProduct(@PathVariable("name") String name){
        productService.deleteProduct(name);
    }
}
