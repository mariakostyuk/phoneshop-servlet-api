package com.es.phoneshop.model.product;

import java.util.*;
import java.util.stream.Collectors;

public class ArrayListProductDao implements ProductDao {
    private static List<Product> products;
    private static volatile ArrayListProductDao instance = null;

    public ArrayListProductDao() {
        products = new ArrayList<>();
    }

    public static ArrayListProductDao getInstance(){
        if (instance == null){
                    instance = new ArrayListProductDao();
        }
        return instance;
    }

    @Override
    public Optional<Product> getProduct(Long id) {
        return  products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Product> findProducts() {
        return products.stream()
                .filter(p->p.getPrice() != null)
                .collect(Collectors.toList());
    }

    @Override
    public void save(Product product)
    {
        products.add(product);
    }

    @Override
    public void delete(Long id) {
        products.remove(getProduct(id));
    }
}
