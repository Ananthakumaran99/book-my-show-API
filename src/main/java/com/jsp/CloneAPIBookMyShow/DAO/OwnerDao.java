package com.jsp.CloneAPIBookMyShow.DAO;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.CloneAPIBookMyShow.Repository.OwnerRepo;
import com.jsp.CloneAPIBookMyShow.entity.Owner;

@Repository
public class OwnerDao {

	@Autowired
	private OwnerRepo ownerRepo;

	public Owner saveOwner(Owner owner) {
		return ownerRepo.save(owner);
	}

	public Owner findOwnerById(long ownerId) {
		Optional<Owner> owner = ownerRepo.findById(ownerId);
		if (owner.isPresent()) {
			return owner.get();
		} else {
			return null;
		}

	}

	public Owner deleteOwnerById(long ownerId) {
		Optional<Owner> owner = ownerRepo.findById(ownerId);
		if (owner.isPresent()) {
			ownerRepo.deleteById(ownerId);
			return owner.get();
		} else {
			return null;
		}

	}
	
	
	public Owner updateOwnerById(long ownerId,Owner owner) {
		Optional<Owner> optional = ownerRepo.findById(ownerId);
		if (optional.isPresent()) {
			owner.setOwnerId(ownerId);
			ownerRepo.save(owner);
			return owner;
			
		} else {
			return null;
		}

	}
}
