package com.Service.Catalog.Controllers;

import com.Service.Catalog.Entities.Brand;
import com.Service.Catalog.Entities.Product;
import com.Service.Catalog.Services.BrandService;
import com.Service.Catalog.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brands")
public class BrandController {
    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @Autowired

    @GetMapping("/all")
    public List<Brand> getProducts(){
        return brandService.getAllProducts();
    }
    @PostMapping("/add")
    public void registerBrand(@RequestBody Brand brand){
        brandService.addBrand(brand);
    }
    @PutMapping("/update/{name}")
    public void updateBrand(@PathVariable("name") String name, @RequestBody Brand request){
        brandService.updateBrand(name, request);
    }

    @DeleteMapping("/delete/{name}")
    public void deleteBrand(@PathVariable("name") String name){
        brandService.deleteBrand(name);
    }
}
