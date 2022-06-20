package vn.fis.training.ordermanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.fis.training.ordermanagement.domain.Product;
import vn.fis.training.ordermanagement.repository.ProductRepository;
import vn.fis.training.ordermanagement.service.ProductService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).get();
    }

    @Override
    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public boolean deleteProductById(Long productId) {
        Optional<Product> p = productRepository.findById(productId);
        if (p.isPresent()) {
            productRepository.delete(p.get());
            return true;
        }
        return false;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findByProductName(String nameProduct) {
        List<Product> list = productRepository.findAll()
                .stream()
                .filter(product -> nameProduct.equals(product.getName()))
                .collect(Collectors.toList());
        if (list.isEmpty()) {
            return null;
        } return list.stream().findFirst().get();
    }
}
