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

import com.jsp.CloneAPIBookMyShow.DTO.OwnerDto;
import com.jsp.CloneAPIBookMyShow.entity.Owner;
import com.jsp.CloneAPIBookMyShow.service.OwnerService;
import com.jsp.CloneAPIBookMyShow.util.ResponseStructure;

@RestController
@RequestMapping("/owner")
public class OwnerController {

	@Autowired
	private OwnerService ownerService;

	@PostMapping
	public ResponseEntity<ResponseStructure<OwnerDto>> saveOwner(@RequestBody Owner owner) {
		return ownerService.saveOwner(owner);
	}

	@GetMapping
	public ResponseEntity<ResponseStructure<OwnerDto>> findOwnerById(@RequestParam long ownerId) {
		return ownerService.findOwnerById(ownerId);
	}

	@DeleteMapping
	public ResponseEntity<ResponseStructure<OwnerDto>> deleteOwnerById(@RequestParam long ownerId) {
		return ownerService.deleteOwnerById(ownerId);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<OwnerDto>> updateOwnerById(@RequestParam long ownerId,@RequestBody Owner owner) {
		return ownerService.updateOwnerById(ownerId,owner);
	}

}
