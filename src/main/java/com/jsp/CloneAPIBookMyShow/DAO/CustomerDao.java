package com.jsp.CloneAPIBookMyShow.DAO;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.CloneAPIBookMyShow.Repository.CustomerRepo;
import com.jsp.CloneAPIBookMyShow.entity.Customer;

@Repository
public class CustomerDao {

	@Autowired
	private CustomerRepo repo;

	public Customer saveCustomer(Customer customer) {
		return repo.save(customer);

	}

	public Customer updateCustomerById(long customerId, Customer customer) {
		Optional<Customer> optional = repo.findById(customerId);
		if (optional.isPresent()) {
			customer.setCustomerId(customerId);

			repo.save(customer);
			return customer;
		}
		return null;
	}

	public Customer deleteCustomerById(long customerId) {
		Optional<Customer> optional = repo.findById(customerId);
		if (optional.isPresent()) {
			repo.delete(optional.get());
			return optional.get();
		}
		return null;
	}

	public Customer getCustomerById(long customerId) {
		Optional<Customer> optional = repo.findById(customerId);
		if (optional.isPresent()) {

			return optional.get();
		}
		return null;
	}
}
