package com.test.crm.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProductStorage implements Serializable {

    private List<Product> products = new ArrayList<>();

    public void add(Product product) {
        products.add(product);
    }

    public void clear() {
        products.clear();
    }

    public List<Product> getAll() {
        return products;
    }

    public List<Product> addAll(List<Product> productList) {
        if (productList != null) products.addAll(productList);
        return products;
    }
}
