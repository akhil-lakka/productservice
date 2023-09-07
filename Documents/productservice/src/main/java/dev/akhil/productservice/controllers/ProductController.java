package dev.akhil.productservice.controllers;

import dev.akhil.productservice.dtos.GenericProductDTO;
import dev.akhil.productservice.exceptions.NotFoundException;
import dev.akhil.productservice.models.Product;
import dev.akhil.productservice.services.FakeStoreProxyProductService;
import dev.akhil.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<GenericProductDTO> getAllProducts(){
        return productService.getAllProdcuts();
    }

    @GetMapping("{id}")
    public GenericProductDTO getProductById(@PathVariable("id") Long id) throws NotFoundException {
        return productService.getProductById(id);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<GenericProductDTO> deleteProductById(@PathVariable("id") Long id){
        ResponseEntity<GenericProductDTO> responseEntity = new ResponseEntity<>(
                productService.deleteProduct(id), HttpStatus.OK
        );
        return responseEntity;
    }

    @PostMapping
    public GenericProductDTO createProduct(@RequestBody GenericProductDTO product){
        return productService.createdProduct(product);
//        System.out.println("product" + product);
    }

    @PutMapping("{id}")
    public GenericProductDTO updateProductById(@PathVariable("id") Long id, @RequestBody GenericProductDTO product){
        return productService.updateProduct(product, id);
    }
}
