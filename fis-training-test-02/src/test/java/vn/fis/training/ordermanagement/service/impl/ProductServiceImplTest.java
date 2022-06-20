package vn.fis.training.ordermanagement.service.impl;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.fis.training.ordermanagement.domain.Product;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProductServiceImplTest {

    @Autowired
    ProductServiceImpl productService;


    @Test
    @Order(1)
    void createProduct() {
        Product product = new Product(1L, "product 1", 2.55);
        productService.createProduct(product);
        assertEquals(1, productService.findAll().size());
    }

    @Test
    @Order(2)
    void findById() {
        Product product = new Product(1L, "product 1", 2.55);
        assertEquals(product, productService.findById(1L));
    }

    @Test
    @Order(3)
    void updateProduct() {
        Product product = new Product(1L, "product 1 has been updated", 2.55);
        Product updatePro = productService.updateProduct(product);
        assertEquals(true, "product 1 has been updated".equals(updatePro.getName()));
    }

    @Test
    @Order(4)
    void findAll() {
        Product product = new Product(2L, "product 2", 4.55);
        productService.createProduct(product);
        assertEquals(2, productService.findAll().size());
    }


    @Test
    @Order(5)
    void deleteProductById_Exist() {
        Product product = new Product(3L, "product 3", 4.55);
        productService.createProduct(product);
        productService.deleteProductById(2L);
        assertEquals(2, productService.findAll().size());
    }

    @Test
    @Order(6)
    void deleteProductById_NotExist() {
        boolean result = productService.deleteProductById(222L);
        assertFalse(result);
    }

    @Test
    @Order(7)
    void findByProductName_Exist() {
        String name = "product 3";
        Product product = productService.findByProductName(name);
        assertNotNull(product);
    }

    @Test
    @Order(8)
    void findByProductName_NotExist() {
        String name = "product is not exist";
        Product product = productService.findByProductName(name);
        assertNull(product);
    }
}