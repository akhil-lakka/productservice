package dev.akhil.productservice.services;

import dev.akhil.productservice.dtos.GenericProductDTO;
import dev.akhil.productservice.models.Product;

public interface ProductService {
    
    GenericProductDTO getProductById(Long id);
}
