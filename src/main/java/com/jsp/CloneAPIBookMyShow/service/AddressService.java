package com.jsp.CloneAPIBookMyShow.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.CloneAPIBookMyShow.DAO.AddressDao;
import com.jsp.CloneAPIBookMyShow.DTO.AddressDto;

import com.jsp.CloneAPIBookMyShow.entity.Address;
import com.jsp.CloneAPIBookMyShow.exception.AddressIdNotFoundException;

import com.jsp.CloneAPIBookMyShow.util.ResponseStructure;

@Service
public class AddressService {


	
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AddressDao addressDao;

	public ResponseEntity<ResponseStructure<Address>> saveAddress(AddressDto addressDto) {

		Address address = this.modelMapper.map(addressDto, Address.class);
		Address dbAddress = addressDao.saveAddress(address);
		if (dbAddress != null) {
			ResponseStructure<Address> structure = new ResponseStructure<Address>();
			structure.setMessage("Address data saved successfully");
			structure.setData(dbAddress);
			structure.setStatus(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Address>>(structure, HttpStatus.CREATED);
		}

		throw new AddressIdNotFoundException("sorry failed to save address");
	}

	public ResponseEntity<ResponseStructure<Address>> findAddressById(long addressId) {
		Address dbAddress = addressDao.getAddressById(addressId);
		if (dbAddress != null) {
			ResponseStructure<Address> structure = new ResponseStructure<Address>();
			structure.setMessage("Address data fetched successfully");
			structure.setData(dbAddress);
			structure.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<Address>>(structure, HttpStatus.FOUND);
		}
		throw new AddressIdNotFoundException("sorry failed to fetch address");	}

	public ResponseEntity<ResponseStructure<Address>> updateAddressById(long addressId, AddressDto addressDto) {
		Address address =this.modelMapper.map(addressDto, Address.class);
		Address dbAddress = addressDao.updateAddressById(addressId, address);
		
		if (dbAddress != null) {
			ResponseStructure<Address> structure = new ResponseStructure<Address>();
			structure.setMessage("Address data updated successfully");
			structure.setData(dbAddress);
			structure.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<Address>>(structure, HttpStatus.FOUND);
		}
		throw new AddressIdNotFoundException("sorry failed to update address");
	}

	public ResponseEntity<ResponseStructure<Address>> deleteAddressById(long addressId) {
		Address dbAddress = addressDao.deleteAddressById(addressId);
		if (dbAddress != null) {
			ResponseStructure<Address> structure = new ResponseStructure<Address>();
			structure.setMessage("Address data deleted successfully");
			structure.setData(dbAddress);
			structure.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<Address>>(structure, HttpStatus.FOUND);
		}
		throw new AddressIdNotFoundException("sorry failed to delete address");
	}
}
