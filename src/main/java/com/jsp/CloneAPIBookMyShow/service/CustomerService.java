package com.jsp.CloneAPIBookMyShow.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.CloneAPIBookMyShow.DAO.CustomerDao;
import com.jsp.CloneAPIBookMyShow.DTO.CustomerDto;
import com.jsp.CloneAPIBookMyShow.entity.Customer;
import com.jsp.CloneAPIBookMyShow.exception.AddressIdNotFoundException;
import com.jsp.CloneAPIBookMyShow.exception.CustomerIdNotFoundException;
import com.jsp.CloneAPIBookMyShow.util.ResponseStructure;

@Service
public class CustomerService {

	@Autowired
	private CustomerDao dao;

	@Autowired
	private ModelMapper modelMapper;

	public ResponseEntity<ResponseStructure<CustomerDto>> saveCustomer(Customer customer) {

		Customer dbCustomer = dao.saveCustomer(customer);
		CustomerDto dto = this.modelMapper.map(dbCustomer, CustomerDto.class);
		if (dbCustomer != null) {
			ResponseStructure<CustomerDto> structure = new ResponseStructure<CustomerDto>();
			structure.setMessage("Customer data saved successfully");
			structure.setData(dto);
			structure.setStatus(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<CustomerDto>>(structure, HttpStatus.CREATED);
		}

		throw new CustomerIdNotFoundException("sorry failed to save customer");
	}

	public ResponseEntity<ResponseStructure<CustomerDto>> updateCustomerById(long customerId, Customer customer) {

		Customer dbCustomer = dao.updateCustomerById(customerId, customer);
		CustomerDto dto = this.modelMapper.map(dbCustomer, CustomerDto.class);
		if (dbCustomer != null) {
			ResponseStructure<CustomerDto> structure = new ResponseStructure<CustomerDto>();
			structure.setMessage("Customer data updated successfully");
			structure.setData(dto);
			structure.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<CustomerDto>>(structure, HttpStatus.FOUND);
		}
		throw new CustomerIdNotFoundException("sorry failed to update customer");
	}

	public ResponseEntity<ResponseStructure<CustomerDto>> findCustomerById(long customerId) {
		Customer dbCustomer = dao.getCustomerById(customerId);
		if (dbCustomer != null) {
			ResponseStructure<CustomerDto> structure = new ResponseStructure<CustomerDto>();
			structure.setMessage("Customer data fetched successfully");
			structure.setData(dbCustomer);
			structure.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<CustomerDto>>(structure, HttpStatus.FOUND);
		}
		throw new AddressIdNotFoundException("sorry failed to fetch Customer");
	}

	public ResponseEntity<ResponseStructure<CustomerDto>> deleteCustomerById(long customerId) {
		Customer dbCustomer = dao.deleteCustomerById(customerId);
		if (dbCustomer != null) {
			ResponseStructure<CustomerDto> structure = new ResponseStructure<CustomerDto>();
			structure.setMessage("Customer data fetched successfully");
			structure.setData(dbCustomer);
			structure.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<CustomerDto>>(structure, HttpStatus.FOUND);
		}
		throw new AddressIdNotFoundException("sorry failed to delete Customer");
	}

}
