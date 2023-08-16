package com.Service.Catalog.Services;

import com.Service.Catalog.Entities.Brand;
import com.Service.Catalog.Entities.Product;
import com.Service.Catalog.Repositories.BrandRepository;
import com.Service.Catalog.Repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BrandService {
    private final BrandRepository brandRepository;

    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public List<Brand> getAllProducts(){
        return brandRepository.findAll();
    }

    public Brand findByName(String name){
        return brandRepository.findByName(name).orElseThrow(() -> new IllegalStateException("Brand doesn't exist"));
    }

    public void addBrand(Brand brand){
        Optional<Brand> brandOptional = brandRepository.findByName(brand.getName());
        if(brandOptional.isPresent()){
            throw new IllegalStateException("Brand with already exists");
        }
        brandRepository.save(brand);
    }

    @Transactional
    public void updateBrand(String name, Brand request){
        Brand brand = brandRepository.findByName(name) .orElseThrow(() -> new IllegalStateException("Brand with the name " +name+" doesn't exist"));
        if(request.getName() != null && request.getName().length() > 0 && !Objects.equals(brand.getName(), request.getName())) {
            brand.setName(request.getName());
        }
        if(request.getContact() != null && request.getContact().length() > 0 && !Objects.equals(brand.getContact(), request.getContact())){
            brand.setContact(request.getContact());
        }
        if(request.getLocation() != null && request.getLocation().length() > 0 && !Objects.equals(brand.getLocation(), request.getLocation())){
            brand.setLocation(request.getLocation());
        }
    }

    public void deleteBrand(String name){
        boolean exists = brandRepository.findByName(name).isPresent();
        if(!exists){
            throw new IllegalStateException("Brand with the name "+name+" does not exist");
        }
        brandRepository.deleteByName(name);
    }
}
