package vn.fis.training.ordermanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.fis.training.ordermanagement.domain.Customer;
import vn.fis.training.ordermanagement.repository.CustomerRepository;
import vn.fis.training.ordermanagement.service.CustomerService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer findById(Long id) {
        if (customerRepository.findById(id).isPresent()) {
            return customerRepository.findById(id).get();
        } else {
            return null;
        }
    }

    public Customer createCustomer(Customer customer) {
        if (findByMobileNumber(customer.getMobile()) != null) {  // có tồn tại cus có sdt đó
            return null;
        } else {
            customerRepository.save(customer);
            return customer;
        }
    }

    @Override
    @Transactional
    public Customer updateCustomer(Customer customer) {
        Optional<Customer> updateCus = customerRepository.findById(customer.getId());
        if (updateCus.isPresent()) {
            customerRepository.save(customer);
            return customer;
        }
        return null;
    }

    @Override
    public boolean deleteCustomerById(Long customerId) {
        Optional<Customer> deleteCus = customerRepository.findById(customerId);
        if (deleteCus.isPresent()) {
            customerRepository.deleteById(customerId);
            return true;
        }
        return false;
    }


    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findByMobileNumber(String mobileNumber) {
        List<Customer> list = customerRepository.findAll()
                .stream()
                .filter(customer -> mobileNumber.equals(customer.getMobile()))
                .collect(Collectors.toList());
        if (list.isEmpty()) {
            return null;
        } else {
            return list.stream().findFirst().get();
        }
    }


}
