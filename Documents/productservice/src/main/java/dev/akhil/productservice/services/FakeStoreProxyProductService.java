package dev.akhil.productservice.services;

import dev.akhil.productservice.dtos.FakeStoreProductDTO;
import dev.akhil.productservice.dtos.GenericProductDTO;
import dev.akhil.productservice.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service("fakeStoreProxyProductService")
public class FakeStoreProxyProductService implements ProductService{

    private final RestTemplateBuilder restTemplateBuilder;
    private String specificProductRequestUrl = "https://fakestoreapi.com/products/{id}";

    private String productRequestsBaseUrl = "https://fakestoreapi.com/products";
    @Override
    public GenericProductDTO getProductById(Long id) throws NotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> responseEntity = restTemplate.getForEntity(specificProductRequestUrl, FakeStoreProductDTO.class, id);
        FakeStoreProductDTO fakeStoreProductDTO = responseEntity.getBody();
        if(fakeStoreProductDTO == null){
            throw new NotFoundException("Product with id: " + id + " doesn't exist.");
        }
        return getGenericProductDTO(fakeStoreProductDTO);
    }

    @Override
    public GenericProductDTO createdProduct(GenericProductDTO product){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<GenericProductDTO> response = restTemplate.postForEntity(productRequestsBaseUrl, product, GenericProductDTO.class);
        return response.getBody();
    }

    @Override
    public List<GenericProductDTO> getAllProdcuts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO[]> response = restTemplate.getForEntity(productRequestsBaseUrl, FakeStoreProductDTO[].class);
        List<GenericProductDTO> ans = new ArrayList<>();
        for(FakeStoreProductDTO fakeStoreProductDTO : response.getBody()){
            ans.add(getGenericProductDTO(fakeStoreProductDTO));
        }
        return ans;
    }

    @Override
    public GenericProductDTO deleteProduct(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDTO.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDTO>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDTO.class);
        ResponseEntity<FakeStoreProductDTO> response = restTemplate.execute(specificProductRequestUrl, HttpMethod.DELETE, requestCallback, responseExtractor, id);

        return getGenericProductDTO(response.getBody());
    }


    @Override
    public GenericProductDTO updateProduct(GenericProductDTO product, Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(product, FakeStoreProductDTO.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDTO>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDTO.class);
        ResponseEntity<FakeStoreProductDTO> response = restTemplate.execute(specificProductRequestUrl, HttpMethod.PUT, requestCallback, responseExtractor, id);
        return getGenericProductDTO(response.getBody());
    }

    private GenericProductDTO getGenericProductDTO(FakeStoreProductDTO fakeStoreProductDTO){
        GenericProductDTO product = new GenericProductDTO();
        product.setId(fakeStoreProductDTO.getId());
        product.setTitle(fakeStoreProductDTO.getTitle());
        product.setCategory(fakeStoreProductDTO.getCategory());
        product.setPrice(fakeStoreProductDTO.getPrice());
        product.setImage(fakeStoreProductDTO.getImage());
        product.setDescription(fakeStoreProductDTO.getDescription());
        return product;
    }

}
