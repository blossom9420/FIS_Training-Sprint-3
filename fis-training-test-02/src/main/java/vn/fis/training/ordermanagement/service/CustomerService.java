package vn.fis.training.ordermanagement.service;

import org.springframework.stereotype.Service;
import vn.fis.training.ordermanagement.domain.Customer;

import java.util.List;

@Service
public interface CustomerService {
    Customer createCustomer(Customer customer);
    Customer updateCustomer(Customer customer);
    boolean deleteCustomerById(Long customerId);
    List<Customer> findAll();
    Customer findByMobileNumber(String mobileNumber);
}
