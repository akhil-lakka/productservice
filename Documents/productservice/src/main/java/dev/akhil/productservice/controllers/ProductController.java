package dev.akhil.productservice.controllers;

import dev.akhil.productservice.dtos.GenericProductDTO;
import dev.akhil.productservice.models.Product;
import dev.akhil.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    ProductService productService;
//    @Value("${productservice.type}")
//    private static final String qualifier;

    public ProductController(@Qualifier("fakeStoreProxyProductService") ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    public void getAllProducts(){

    }

    @GetMapping("{id}")
    public GenericProductDTO getProductById(@PathVariable("id") Long id){
        return productService.getProductById(id);
    }

    @DeleteMapping("{id}")
    public void deleteProductById(@PathVariable("id") Long id){

    }

    @PostMapping
    public void createProduct(){

    }

    @PutMapping("{id}")
    public void updateProductById(@PathVariable("id") Long id){

    }
}
