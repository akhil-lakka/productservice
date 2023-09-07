package dev.akhil.productservice.services;

import dev.akhil.productservice.dtos.GenericProductDTO;
import dev.akhil.productservice.exceptions.NotFoundException;
import dev.akhil.productservice.models.Product;

import java.util.List;

public interface ProductService {
    
    GenericProductDTO getProductById(Long id) throws NotFoundException;

    GenericProductDTO createdProduct(GenericProductDTO product);

    List<GenericProductDTO> getAllProdcuts();

    GenericProductDTO deleteProduct(Long id);

    GenericProductDTO updateProduct(GenericProductDTO product, Long id);
}
