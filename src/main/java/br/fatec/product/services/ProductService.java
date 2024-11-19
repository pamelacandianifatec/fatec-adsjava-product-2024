package br.fatec.product.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fatec.product.dtos.ProductResponse;
import br.fatec.product.entities.product;
import br.fatec.product.mappers.ProductMapper;
import br.fatec.product.repositories.ProductRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository repository;

    public List<ProductResponse> getAllProducts(){
        return repository.findAll().stream().map( p -> ProductMapper.toDTO(p)).collect(Collectors.toList());
    }

    public product getProductById(long id){
        return repository.findById(id).orElseThrow(
            () -> new EntityNotFoundException("Produto não cadastrado")
        );
    }

    public void delete(long id){
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
        else{
            throw new EntityNotFoundException("Produto não cadastrado");
        }
    }

    public product save(product product){
        return repository.save(product);
    }

    public void update(product product, long id){
        product aux = repository.getReferenceById(id);
        
        aux.setCategory(product.getCategory());
        aux.setName(product.getName());
        aux.setPrice(product.getPrice());
        
        repository.save(aux);
    }


}
