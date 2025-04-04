package com.scentstyle.model;

import com.scentstyle.model.Product;
import java.util.ArrayList;
import java.util.List;

public class ProductManager {
    private List<Product> productList = new ArrayList<>();

    public ProductManager() {
        loadSampleProducts();
    }

    private void loadSampleProducts() {
        productList.add(new Product(1, "Perfume A", "Fragrance", 500, 10));
        productList.add(new Product(2, "Perfume B", "Fragrance", 700, 15));
        productList.add(new Product(3, "Perfume C", "Fragrance", 900, 20)); 
   }


    public List<Product> getProductList() {
        return productList;
    }
}