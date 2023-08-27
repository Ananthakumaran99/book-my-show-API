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

import com.jsp.CloneAPIBookMyShow.DTO.ProductionHouseDto;
import com.jsp.CloneAPIBookMyShow.entity.ProductionHouse;
import com.jsp.CloneAPIBookMyShow.service.ProductionHouseService;
import com.jsp.CloneAPIBookMyShow.util.ResponseStructure;
@RestController
@RequestMapping("/house")
public class ProductionHouseController {

	@Autowired
	private ProductionHouseService houseService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<ProductionHouse>> saveProductionHouse(@RequestParam long ownerId,@RequestBody ProductionHouseDto houseDto){
		return houseService.saveProductionHouse(ownerId, houseDto);
	}
	@PutMapping
	public ResponseEntity<ResponseStructure<ProductionHouse>> updateProductionHouse(@RequestParam long productionHouseId,@RequestBody ProductionHouseDto houseDto){
		return houseService.updateProductionHouse(productionHouseId,houseDto);
	}
	@GetMapping
	public ResponseEntity<ResponseStructure<ProductionHouse>> getProductionHouseById(@RequestParam long productionHouseId){
		return houseService.getProductionHouseById(productionHouseId);
	}
	@DeleteMapping
	public ResponseEntity<ResponseStructure<ProductionHouse>> deleteProductionHouseById(@RequestParam long productionHouseId){
		return houseService.deleteProductionHouseById(productionHouseId);
	}
}
