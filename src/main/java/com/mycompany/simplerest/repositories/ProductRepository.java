package com.mycompany.simplerest.repositories;

import com.mycompany.simplerest.entities.Product;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class ProductRepository {

    private HashMap<String, Product> repository = new HashMap<>();

    public void save(Product product) {
        if (product.getId() == null) {
            product.setId(UUID.randomUUID().toString());
        }

        this.repository.put(product.getId(), product);
    }

    public List<Product> getAll() {
        return new ArrayList(this.repository.values());
    }

    public Product findById(String id) {
        return this.repository.get(id);
    }

    public void delete(Product product) {
        this.repository.remove(product.getId());
    }
}
