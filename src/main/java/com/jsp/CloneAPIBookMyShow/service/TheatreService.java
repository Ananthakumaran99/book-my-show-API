package com.jsp.CloneAPIBookMyShow.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.CloneAPIBookMyShow.DAO.AddressDao;
import com.jsp.CloneAPIBookMyShow.DAO.OwnerDao;
import com.jsp.CloneAPIBookMyShow.DAO.TheatreDao;
import com.jsp.CloneAPIBookMyShow.DTO.TheatreDto;
import com.jsp.CloneAPIBookMyShow.entity.Address;
import com.jsp.CloneAPIBookMyShow.entity.Owner;
import com.jsp.CloneAPIBookMyShow.entity.Theatre;
import com.jsp.CloneAPIBookMyShow.exception.AddressIdNotFoundException;
import com.jsp.CloneAPIBookMyShow.exception.OwnerIdNotFoundException;
import com.jsp.CloneAPIBookMyShow.exception.TheatreAlreadypresentInThisAddressException;
import com.jsp.CloneAPIBookMyShow.exception.TheatreIdNotFoundException;
import com.jsp.CloneAPIBookMyShow.util.ResponseStructure;

@Service
public class TheatreService {

	@Autowired
	private TheatreDao Dao;

	@Autowired
	private OwnerDao ownerDao;

	@Autowired
	private AddressDao addressDao;

	@Autowired
	private ModelMapper modelMapper;

	public ResponseEntity<ResponseStructure<Theatre>> saveTheatre(long ownerId, long addressId, TheatreDto theatreDto) {
		Owner owner=ownerDao.findOwnerById(ownerId);
		if(owner!=null) {

			Address address=addressDao.getAddressById(addressId);
			if(address!=null) {
               Theatre addressTheatre=address.getTheatre();
               if(addressTheatre!=null) {
            	   throw new TheatreAlreadypresentInThisAddressException("Sorry address is mapped to other theatre");
               }
				Theatre theatre=this.modelMapper.map(theatreDto, Theatre.class);
				theatre.setOwner(owner);
				theatre.setAddress(address);
//				update owner
			     if(owner.getTheatre().isEmpty()) {
			    	 List<Theatre> list=new ArrayList<Theatre>();
			    	 list.add(theatre);
			    	 owner.setTheatre(list);
			     }else {
			    	 List<Theatre> list=owner.getTheatre();
			    	 list.add(theatre);
			    	 owner.setTheatre(list);
			     }
//				update address
			     address.setTheatre(theatre);
			     
//			     add theatre
			     Theatre dbTheatre=Dao.saveTheatre(theatre);
			     ResponseStructure<Theatre>  structure=new ResponseStructure<Theatre>();
			     structure.setMessage("theatre added successfully");
				structure.setStatus(HttpStatus.CREATED.value());
				structure.setData(dbTheatre);
				return new ResponseEntity<ResponseStructure<Theatre>>(structure,HttpStatus.CREATED);
			}else {

				throw new AddressIdNotFoundException("Sorry failed to add theatre");
			}
		}else {

			throw new OwnerIdNotFoundException("Sorry failed to add theatre");
		}
	}

	public ResponseEntity<ResponseStructure<Theatre>> updateTheatre(long theatreId, TheatreDto theatreDto) {
		Theatre theatre = this.modelMapper.map(theatreDto, Theatre.class);
		Theatre dbTheatre = Dao.updateTheatre(theatreId, theatre);
		if (dbTheatre != null) {
			ResponseStructure<Theatre> structure = new ResponseStructure<Theatre>();
			structure.setMessage("theatre Updated successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(dbTheatre);
			return new ResponseEntity<ResponseStructure<Theatre>>(structure, HttpStatus.OK);
		} else {
//			raise one exception
			throw new TheatreIdNotFoundException("sorry failed to update theatre");

		}
	}

	public ResponseEntity<ResponseStructure<Theatre>> getTheatreById(long theatreId) {

		Theatre dbTheatre = Dao.getTheatreById(theatreId);
		if (dbTheatre != null) {
			ResponseStructure<Theatre> structure = new ResponseStructure<Theatre>();
			structure.setMessage("theatre fetched successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(dbTheatre);
			return new ResponseEntity<ResponseStructure<Theatre>>(structure, HttpStatus.FOUND);
		} else {
//			raise one exception
			throw new TheatreIdNotFoundException("sorry failed to fetch theatre");

		}
	}

	public ResponseEntity<ResponseStructure<Theatre>> deleteTheatreById(long theatreId) {

		Theatre dbTheatre = Dao.getTheatreById(theatreId);
		if (dbTheatre != null) {
			ResponseStructure<Theatre> structure = new ResponseStructure<Theatre>();
			structure.setMessage("theatre deleted successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(dbTheatre);
			return new ResponseEntity<ResponseStructure<Theatre>>(structure, HttpStatus.FOUND);
		} else {
//			raise one exception
			throw new TheatreIdNotFoundException("sorry failed to delete theatre");

		}
	}

}
