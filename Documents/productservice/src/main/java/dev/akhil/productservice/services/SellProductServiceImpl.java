package dev.akhil.productservice.services;

import dev.akhil.productservice.dtos.GenericProductDTO;
import dev.akhil.productservice.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("sellProductServiceImpl")
public class SellProductServiceImpl implements ProductService{
    @Override
    public GenericProductDTO getProductById(Long id) {
        return null;
    }

    @Override
    public GenericProductDTO createdProduct(GenericProductDTO product) {
        return null;
    }

    @Override
    public List<GenericProductDTO> getAllProdcuts() {
        return null;
    }

    @Override
    public GenericProductDTO deleteProduct(Long id) {
        return null;
    }

    @Override
    public GenericProductDTO updateProduct(GenericProductDTO product, Long id) {
        return null;
    }
}
