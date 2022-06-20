package vn.fis.training.ordermanagement.service;

import vn.fis.training.ordermanagement.domain.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(Product product);

    Product findById(Long id);

    Product updateProduct(Product product);
    boolean deleteProductById(Long productId);
    List<Product> findAll();
    Product findByProductName(String nameProduct);
}
