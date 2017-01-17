package com.study.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.study.model.Customer;
import com.study.service.CustomerService;
import com.study.web.util.HeaderUtil;
import com.study.web.util.PaginationUtil;

/**
 * REST @Customer Services.
 * 
 * @author Mauricio Esteves
 */
@RestController
public class CustomerResource {

	private final Logger log = LoggerFactory.getLogger(CustomerResource.class);
	
	@Autowired
	private CustomerService customerService;

    /**
     * POST  /customers : Create a new customer.
     *
     * @param customer the customer to create
     * @return the ResponseEntity with status 201 (Created) and with body the new customer, or with status 400 (Bad Request) if the customer has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
	@RequestMapping(value="/customers", 
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer)
			throws URISyntaxException {
		log.debug("REST request to save Customer : {})", customer);
		if (customer.getId() != null) {
			return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("customer", "idexists", "A new customer cannot already have an ID")).body(null);
		}
		Customer result = customerService.save(customer);
		return ResponseEntity.created(
				new URI("/customers/"+result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert("customer", 
						result.getId().toString())).body(result);
	}

    /**
     * PUT  /customers : Updates an existing customer.
     *
     * @param customer the customer to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated customer,
     * or with status 400 (Bad Request) if the customer is not valid,
     * or with status 500 (Internal Server Error) if the customer couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/customers",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> updateCustomer(@Valid @RequestBody Customer customer) throws URISyntaxException {
        log.debug("REST request to update Customer : {}", customer);
        if (customer.getId() == null) {
            return createCustomer(customer);
        }
        Customer result = customerService.save(customer);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("customer", customer.getId().toString()))
            .body(result);
    }

	/**
	 * GET  /customers : get all the customers.
	 * 
	 * ResponseEntity is meant to represent the entire HTTP response. You can control anything 
	 * that goes into it: status code, headers, and body.
	 * 
	 * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of customers in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
	@RequestMapping(value = "/customers",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Customer>> getAllCustomers(Pageable pageable)
		throws URISyntaxException {
		log.debug("REST request to get a page of Customers");
		Page<Customer> page = customerService.findAll(pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/customers");
		return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
	}

	/**
	 * GET  /customers:name : : get all the customers by name.
	 * 
	 * ResponseEntity is meant to represent the entire HTTP response. You can control anything 
	 * that goes into it: status code, headers, and body.
	 * 
	 * @param name the name of the customer to search
     * @return the ResponseEntity with status 200 (OK) and the list of customers in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
	@RequestMapping(value = "/customers/{name}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Customer>> getCustomersByName(@PathVariable String name)
		throws URISyntaxException {
		log.debug("REST request to get a page of Customers by name");
		List<Customer> customers = customerService.findByName(name);
		return new ResponseEntity<>(customers, HttpStatus.OK);
	}
    
    /**
     * GET  /customers/:id : get the "id" customer.
     *
     * @param id the id of the customer to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the customer, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/customers/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> getCustomer(@PathVariable Long id) {
        log.debug("REST request to get Customer : {}", id);
        Customer customer = customerService.findOne(id);
        return Optional.ofNullable(customer)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /customers/:id : delete the "id" customer.
     *
     * @param id the id of the customer to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/customers/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        log.debug("REST request to delete Customer : {}", id);
        customerService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("customer", id.toString())).build();
    }
}