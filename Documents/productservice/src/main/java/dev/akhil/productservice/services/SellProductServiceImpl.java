package dev.akhil.productservice.services;

import dev.akhil.productservice.dtos.GenericProductDTO;
import dev.akhil.productservice.models.Product;
import org.springframework.stereotype.Service;

@Service("sellProductServiceImpl")
public class SellProductServiceImpl implements ProductService{
    @Override
    public GenericProductDTO getProductById(Long id) {
        return null;
    }
}
