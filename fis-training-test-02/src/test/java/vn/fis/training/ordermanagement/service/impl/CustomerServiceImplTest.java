package vn.fis.training.ordermanagement.service.impl;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.fis.training.ordermanagement.domain.Customer;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CustomerServiceImplTest {

    @Autowired
    CustomerServiceImpl customerService;

    @Test
    @Order(1)
    void createCustomer() {
        // Arrange
        Customer customer1 = new Customer("Pham Hoang Long", "123", "HCM CITY");
        customer1.setId(1L);
        // Action
        Customer createCus = customerService.createCustomer(customer1);

        // Assert
        assertEquals(customer1,createCus);
    }

    @Test
    @Order(2)
    void findById() {
        String mobile = "123";
        assertEquals(mobile, customerService.findById(1L).getMobile());
    }

    @Test
    @Order(3)
    void updateCustomer() {
        Customer customer = new Customer("Pham Hoang Long", "123", "HCM CITY");
        customer.setId(1L);
        customer.setAddress("Address has changed");

        Customer updateCus = customerService.updateCustomer(customer);

        assertEquals("Address has changed", updateCus.getAddress());
    }

    @Test
    @Order(4)
    void findAll() {
        Customer customer2 = new Customer("Number 2", "2222", "HCM CITY"); // id = 2
        customer2.setId(2L);
        Customer temp = customerService.createCustomer(customer2);

        assertEquals(2, customerService.findAll().size());
    }

    @Test
    @Order(5)
    void deleteCustomerById() {
        Customer customer3 = new Customer("Number 3", "3333", "HCM CITY"); // id = 3
        customer3.setId(3L);
        Customer temp = customerService.createCustomer(customer3);
        assertEquals(true, customerService.deleteCustomerById(2L)); // xóa id = 2
        assertEquals(2, customerService.findAll().size());
    }



    @Test
    @Order(6)
    void findByMobileNumber_Exist() {
        // Arrange
        String mobileNumber1 = "123";

        // Action
        Customer findCus = customerService.findByMobileNumber(mobileNumber1);

        // Assert
        assertNotNull(findCus);
    }

    @Test
    @Order(7)
    void findByMobileNumber_NotExist() {
        // Arrange
        String mobileNumber2 = "000";

        // Action
        Customer findCusByMobileNumber = customerService.findByMobileNumber(mobileNumber2);

        // Assert
        assertNull(findCusByMobileNumber);
    }
}