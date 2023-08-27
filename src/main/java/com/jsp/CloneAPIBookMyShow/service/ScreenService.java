package com.jsp.CloneAPIBookMyShow.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.CloneAPIBookMyShow.DAO.ScreenDao;
import com.jsp.CloneAPIBookMyShow.DAO.TheatreDao;
import com.jsp.CloneAPIBookMyShow.DTO.ScreenDto;
import com.jsp.CloneAPIBookMyShow.entity.Screen;
import com.jsp.CloneAPIBookMyShow.entity.Seat;
import com.jsp.CloneAPIBookMyShow.entity.Theatre;
import com.jsp.CloneAPIBookMyShow.enums.ScreenAvailability;
import com.jsp.CloneAPIBookMyShow.enums.ScreenStatus;
import com.jsp.CloneAPIBookMyShow.enums.SeatType;
import com.jsp.CloneAPIBookMyShow.exception.ScreenIdNotFoundException;
import com.jsp.CloneAPIBookMyShow.exception.TheatreIdNotFoundException;
import com.jsp.CloneAPIBookMyShow.util.ResponseStructure;

@Service
public class ScreenService {

	@Autowired
	private TheatreDao theatreDao;

	@Autowired
	private ScreenDao screenDao;

	@Autowired
	private ModelMapper modelMapper;

	public ResponseEntity<ResponseStructure<ScreenDto>> addScreen(long theatreId, ScreenDto screenDto) {
		Theatre theatre = theatreDao.getTheatreById(theatreId);
		if (theatre != null) {
			Screen screen = this.modelMapper.map(screenDto, Screen.class);
//             screen variable you are having no of classic seat ,gold,premium seat
//             screen is having seat object???not present and i want to add it
//             screen is having theater ?no but we are having theater object them i will set it(theater)

			List<Seat> seats = new ArrayList<Seat>();
			for (int a = screen.getNoOfClassicSeats(); a > 0; a--) {
				Seat seat = new Seat();
				seat.setSeatType(SeatType.CLASSIC);
				seat.setScreen(screen);
				seats.add(seat);

			}
			for (int b = screen.getNoOfPremiumSeats(); b > 0; b--) {
				Seat seat = new Seat();
				seat.setSeatType(SeatType.PREMIUM);
				seat.setScreen(screen);
				seats.add(seat);

			}
			for (int c = screen.getNoOfGoldSeats(); c > 0; c--) {
				Seat seat = new Seat();
				seat.setSeatType(SeatType.GOLD);
				seat.setScreen(screen);
				seats.add(seat);

			}
			screen.setTheatre(theatre);
			screen.setSeats(seats);
			screen.setScreenAvailability(ScreenAvailability.NOT_ALLOTTED);
			screen.setScreenStatus(ScreenStatus.AVAILABLE);
			Screen dbsScreen = screenDao.saveScreen(screen);
//            update the theater
			if (theatre.getScreen().isEmpty()) {
				List<Screen> screens = new ArrayList<Screen>();
				screens.add(dbsScreen);
				theatre.setScreen(screens);
				theatreDao.updateTheatre(theatreId, theatre);
			} else {
				List<Screen> screens = theatre.getScreen();
				screens.add(dbsScreen);
				theatre.setScreen(screens);
				theatreDao.updateTheatre(theatreId, theatre);
			}
			ResponseStructure<ScreenDto> structure = new ResponseStructure<ScreenDto>();
			structure.setMessage("Screen saved successfully");
			structure.setStatus(HttpStatus.CREATED.value());
			structure.setData(dbsScreen);
			return new ResponseEntity<ResponseStructure<ScreenDto>>(structure, HttpStatus.CREATED);
		} else {
			throw new TheatreIdNotFoundException("failed to add screen");
		}

	}
	
	public ResponseEntity<ResponseStructure<ScreenDto>> updateScreen(long screenId,ScreenDto screenDto){
		Screen screen=this.modelMapper.map(screenDto, Screen.class);
		Screen dbScreen = screenDao.updateScreen(screenId,screen);
		if (dbScreen!=null) {
			ScreenDto dto=this.modelMapper.map(dbScreen, ScreenDto.class);
			ResponseStructure<ScreenDto> structure = new ResponseStructure<ScreenDto>();
			structure.setMessage("Screen updated successfully");
			structure.setStatus(HttpStatus.CREATED.value());
			structure.setData(dto);
			return new ResponseEntity<ResponseStructure<ScreenDto>>(structure, HttpStatus.CREATED);
			
			
		} else {

			throw new ScreenIdNotFoundException("sorry failed to update screen");
		}
	}

	public ResponseEntity<ResponseStructure<ScreenDto>> getScreenById(long screenId) {
		Screen dbScreen=screenDao.getSceenById(screenId);
		if(dbScreen!=null) {
			ScreenDto dto1=this.modelMapper.map(dbScreen, ScreenDto.class);
			ResponseStructure<ScreenDto> structure=new ResponseStructure<ScreenDto>();
			structure.setMessage("Screen Fetched successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(dto1);
			return new ResponseEntity<ResponseStructure<ScreenDto>>(structure,HttpStatus.FOUND);
		}else {
			throw new ScreenIdNotFoundException("Sorry failed to get screen");
		}
	}

	public ResponseEntity<ResponseStructure<ScreenDto>> deleteScreenById(long screenId) {
		Screen dbScreen=screenDao.deleteScreenById(screenId);
		if(dbScreen!=null) {
			ScreenDto dto1=this.modelMapper.map(dbScreen, ScreenDto.class);
			ResponseStructure<ScreenDto> structure=new ResponseStructure<ScreenDto>();
			structure.setMessage("Screen Deleted successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(dto1);
			return new ResponseEntity<ResponseStructure<ScreenDto>>(structure,HttpStatus.FOUND);
		}else {
			throw new ScreenIdNotFoundException("Sorry failed to Delete screen");
		}
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
