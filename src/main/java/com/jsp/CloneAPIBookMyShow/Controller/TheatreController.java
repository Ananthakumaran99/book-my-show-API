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

import com.jsp.CloneAPIBookMyShow.DTO.TheatreDto;
import com.jsp.CloneAPIBookMyShow.entity.Theatre;
import com.jsp.CloneAPIBookMyShow.service.TheatreService;
import com.jsp.CloneAPIBookMyShow.util.ResponseStructure;

@RestController
@RequestMapping("/theatre")
public class TheatreController {

	@Autowired
	private TheatreService service;

	@PostMapping
	private ResponseEntity<ResponseStructure<Theatre>> saveTheatre(@RequestParam long ownerId,
			@RequestParam long addressId, @RequestBody TheatreDto theatreDto) {

		return service.saveTheatre(ownerId, addressId, theatreDto);
	}

	@PutMapping
	private ResponseEntity<ResponseStructure<Theatre>> updateTheatre(@RequestParam long theatreId,
			@RequestBody TheatreDto theatreDto) {

		return service.updateTheatre(theatreId, theatreDto);
	}

	@GetMapping

	private ResponseEntity<ResponseStructure<Theatre>> findTheatreById(@RequestParam long theatreId) {

		return service.getTheatreById(theatreId);
	}

	@DeleteMapping
	

	private ResponseEntity<ResponseStructure<Theatre>> deleteTheatreById(@RequestParam long theatreId) {

		return service.deleteTheatreById(theatreId);
	}
}
