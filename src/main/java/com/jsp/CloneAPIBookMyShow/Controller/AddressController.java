package com.jsp.CloneAPIBookMyShow.Controller;

import javax.validation.Valid;

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

import com.jsp.CloneAPIBookMyShow.DTO.AddressDto;

import com.jsp.CloneAPIBookMyShow.entity.Address;

import com.jsp.CloneAPIBookMyShow.service.AddressService;
import com.jsp.CloneAPIBookMyShow.util.ResponseStructure;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/address")
public class AddressController {

	@Autowired
	private AddressService service;

	@ApiOperation(value = "Save Address ", notes = "API is used to save the Address")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "successfully created") })
	@PostMapping
	public ResponseEntity<ResponseStructure<Address>> saveAddress(@Valid @RequestBody AddressDto addressDto) {
		return service.saveAddress(addressDto);
	}

	@ApiOperation(value = "Update Address ", notes = "API is used to update the Address")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "successfully updated"),
			@ApiResponse(code = 404, message = "id not found for Address") })
	@GetMapping
	public ResponseEntity<ResponseStructure<Address>> findAddressById(@RequestParam long addressId) {
		return service.findAddressById(addressId);
	}

	@ApiOperation(value = "Delete Address ", notes = "API is used to delete the Address")
	@ApiResponses(value = { @ApiResponse(code = 302, message = "successfully deleted"),
			@ApiResponse(code = 404, message = "id not found for Address") })
	@DeleteMapping
	public ResponseEntity<ResponseStructure<Address>> deleteAddressById(@RequestParam long addressId) {
		return service.deleteAddressById(addressId);
	}

	@ApiOperation(value = "Select Address ", notes = "API is used to select the Address")
	@ApiResponses(value = { @ApiResponse(code = 302, message = "successfully selected"),
			@ApiResponse(code = 404, message = "id not found for Address") })
	@PutMapping
	public ResponseEntity<ResponseStructure<Address>> updateOwnerById(@RequestParam long addressId,
			@RequestBody AddressDto addressDto) {
		return service.updateAddressById(addressId, addressDto);
	}

}
