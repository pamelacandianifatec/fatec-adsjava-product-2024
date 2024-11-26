package br.fatec.product.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.fatec.product.dtos.ProductRequest;
import br.fatec.product.dtos.ProductResponse;
import br.fatec.product.services.ProductService;

@CrossOrigin
@RestController
@RequestMapping("products")

public class ProductController {
    
    @Autowired
    private ProductService service;

    @GetMapping
    public ResponseEntity <List<ProductResponse>> getProducts(){
        return ResponseEntity.ok(service.getAllProducts());
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductResponse> getProducts(@PathVariable long id){
        return ResponseEntity.ok(service.getProductById(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> uptadeProduct(@PathVariable long id, @Validated @RequestBody ProductRequest product){
        service.update(product, id);
        return ResponseEntity.ok().build();
    }

    @PostMapping()
    public ResponseEntity<ProductResponse> saveProduct(@Validated @RequestBody ProductRequest product){
        ProductResponse newProduct = service.save(product);
        return ResponseEntity.created(null).body(newProduct);
    }

}
