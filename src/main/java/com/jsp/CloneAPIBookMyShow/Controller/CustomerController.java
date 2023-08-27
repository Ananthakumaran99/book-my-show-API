package com.jsp.CloneAPIBookMyShow.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.CloneAPIBookMyShow.DTO.CustomerDto;
import com.jsp.CloneAPIBookMyShow.entity.Customer;
import com.jsp.CloneAPIBookMyShow.service.CustomerService;
import com.jsp.CloneAPIBookMyShow.util.ResponseStructure;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService service;

	@PostMapping
	private ResponseEntity<ResponseStructure<CustomerDto>> saveCustomer(@RequestBody Customer customer) {
		return service.saveCustomer(customer);
	}

	@GetMapping
	private ResponseEntity<ResponseStructure<CustomerDto>> findCustomerById(@RequestParam long customerId) {
		return service.findCustomerById(customerId);

	}

	@PutMapping
	private ResponseEntity<ResponseStructure<CustomerDto>> updateCustomer(@RequestParam long customerId,
			@RequestBody Customer customer) {
		return service.updateCustomerById(customerId, customer);
	}
	@DeleteMapping
	private ResponseEntity<ResponseStructure<CustomerDto>> deleteCustomer(@RequestParam long customerId){
		return service.deleteCustomerById(customerId);
	}

}
