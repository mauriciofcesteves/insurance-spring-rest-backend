package com.study.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.study.model.Customer;

/**
 * JpaRepository extends PagingAndSortingRepository that extends CRUDRepository.
 * 
 * -CrudRepository mainly provides CRUD functions.
 * -PagingAndSortingRepository provide methods to do pagination and sorting records.
 * -JpaRepository provides some JPA related method such as flushing the persistence context and delete record in a batch.
 */
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {

	List<Customer> findByName(@Param("name") String name);

}