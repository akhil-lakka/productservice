package dev.akhil.productservice.services;

import dev.akhil.productservice.dtos.FakeStoreProductDTO;
import dev.akhil.productservice.dtos.GenericProductDTO;
import dev.akhil.productservice.models.Product;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Service("fakeStoreProxyProductService")
public class FakeStoreProxyProductService implements ProductService{

    private final RestTemplateBuilder restTemplateBuilder;
    private String getProductRequestUrl = "https://fakestoreapi.com/products/{id}";
    @Override
    public GenericProductDTO getProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> responseEntity = restTemplate.getForEntity(getProductRequestUrl, FakeStoreProductDTO.class, id);
        FakeStoreProductDTO fakeStoreProductDTO = responseEntity.getBody();
        GenericProductDTO product = new GenericProductDTO();
        product.setTitle(fakeStoreProductDTO.getTitle());
        product.setCategory(fakeStoreProductDTO.getCategory());
        product.setPrice(fakeStoreProductDTO.getPrice());
        product.setImage(fakeStoreProductDTO.getImage());
        product.setDescription(fakeStoreProductDTO.getDescription());
        return product;
    }
}
