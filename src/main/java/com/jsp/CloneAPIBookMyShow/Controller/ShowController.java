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

import com.jsp.CloneAPIBookMyShow.DTO.MovieShowDto;
import com.jsp.CloneAPIBookMyShow.entity.MovieShow;
import com.jsp.CloneAPIBookMyShow.service.ShowService;
import com.jsp.CloneAPIBookMyShow.util.ResponseStructure;

@RestController
@RequestMapping("/show")
public class ShowController {

	@Autowired
	private ShowService service;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<MovieShow>> saveShow(@RequestParam long theatreId,@RequestBody MovieShowDto showDto){
	return service.addShow(theatreId,showDto);
	
	}
	@PutMapping
	public ResponseEntity<ResponseStructure<MovieShow>> updateMovieShow(@RequestParam long showId,@RequestBody MovieShowDto showDto){
		return service.updateShow(showId, showDto);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<MovieShow>> getMovieShow(@RequestParam long showId){
		return service.getShowById(showId);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<MovieShow>> deleteMovieShow(@RequestParam long showId){
		return service.deleteShowById(showId);
	}

}
