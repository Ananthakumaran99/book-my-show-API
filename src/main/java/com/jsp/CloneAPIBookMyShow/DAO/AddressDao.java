package com.jsp.CloneAPIBookMyShow.DAO;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.CloneAPIBookMyShow.Repository.AddressRepo;
import com.jsp.CloneAPIBookMyShow.entity.Address;

@Repository
public class AddressDao {

	@Autowired
	private AddressRepo addressRepo;

	public Address saveAddress(Address address) {
		return addressRepo.save(address);

	}

	public Address updateAddressById(long addressId, Address address) {
		Optional<Address> optional = addressRepo.findById(addressId);
		if (optional.isPresent()) {
			address.setAddressId(addressId);
			address.setTheatre(optional.get().getTheatre());
			addressRepo.save(address);
			return address;
		}
		return null;
	}

	public Address deleteAddressById(long addressId) {
		Optional<Address> optional = addressRepo.findById(addressId);
		if (optional.isPresent()) {
			addressRepo.delete(optional.get());
			return optional.get();
		}
		return null;
	}

	public Address getAddressById(long addressId) {
		Optional<Address> optional = addressRepo.findById(addressId);
		if (optional.isPresent()) {

			return optional.get();
		}
		return null;
	}

}

