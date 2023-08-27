package com.jsp.CloneAPIBookMyShow.DTO;



import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter

public class AddressDto {
	
	private long addressId;
	private int flatNo;
	@NotNull(message = "area cannot be null")
	@NotBlank(message = "area cannot be blank")
	private String area;
	private String landMark;
	private String city;
	private String state;
	private String country;
	private long pincode;
}
