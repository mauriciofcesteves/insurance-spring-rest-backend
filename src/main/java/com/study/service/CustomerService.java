package com.study.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.model.Customer;
import com.study.repository.CustomerRepository;

/**
 * Service Implementation for managing Customer.
 */
@Service
@Transactional
public class CustomerService {

    private final Logger log = LoggerFactory.getLogger(CustomerService.class);
    
    @Autowired
    private CustomerRepository customerRepository;

    /**
     * Save a customer.
     *
     * @param customer the entity to save
     * @return the persisted entity
     */
    public Customer save(Customer customer) {
        log.debug("Request to save Customer : {}", customer);
        Customer result = customerRepository.save(customer);
        return result;
    }

    /**
     *  Get all the customers.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<Customer> findAll(Pageable pageable) {
        log.debug("Request to get all Customers");
        Page<Customer> result = customerRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one customer by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public Customer findOne(Long id) {
        log.debug("Request to get Customer : {}", id);
        Customer customer = customerRepository.findOne(id);
        return customer;
    }

    /**
     *  Get a list of customers by name.
     *
     *  @param name the name of the entity
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<Customer> findByName(String name) {
        log.debug("Request to get Customers by Name : {}", name);
        List<Customer> customers = customerRepository.findByName(name);
        return customers;
    }
    
    /**
     *  Delete the  customer by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Customer : {}", id);
        customerRepository.delete(id);
    }
}
