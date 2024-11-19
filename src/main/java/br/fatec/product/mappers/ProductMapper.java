package br.fatec.product.mappers;

import br.fatec.product.dtos.ProductRequest;
import br.fatec.product.dtos.ProductResponse;
import br.fatec.product.entities.product;

public class ProductMapper {
    
    public static product toEntity(ProductRequest productRequest){
        product product = new product();
        product.setName(productRequest.name());
        product.setPrice(productRequest.price());
        product.setCategory(productRequest.category());

        return product;
    }

    public static ProductResponse toDTO(product product){
        return new ProductResponse(product.getId(), product.getName(), product.getPrice(), product.getCategory());
    }



}
