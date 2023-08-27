package com.jsp.CloneAPIBookMyShow.DAO;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.CloneAPIBookMyShow.Repository.ProductioHouseRepo;
import com.jsp.CloneAPIBookMyShow.entity.ProductionHouse;

@Repository
public class ProductionHouseDao {

	@Autowired
	private ProductioHouseRepo productioHouseRepo;

	public ProductionHouse saveProductionHouse(ProductionHouse productionHouse) {
		return productioHouseRepo.save(productionHouse);
	}

	public ProductionHouse updateProductionHouse(long productionHouseId, ProductionHouse productionHouse) {
		Optional<ProductionHouse> optional = productioHouseRepo.findById(productionHouseId);
		if (optional.isPresent()) {
			// update the data
			productionHouse.setProductionHouseId(productionHouseId);
			productionHouse.setOwner(optional.get().getOwner());
			productionHouse.setMovies(optional.get().getMovies());
			productioHouseRepo.save(productionHouse);
			return productionHouse;
		}
		return null;
	}

	public ProductionHouse getProductionHouseById(long productionHouseId) {
		Optional<ProductionHouse> optional = productioHouseRepo.findById(productionHouseId);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	public ProductionHouse deleteProductionHouseById(long houseId) {
		Optional<ProductionHouse> optional = productioHouseRepo.findById(houseId);
		if (optional.isPresent()) {
			ProductionHouse house = optional.get();

			house.setOwner(null);
			house.setMovies(null);

			productioHouseRepo.delete(house);
			return optional.get();

		}
		return null;
	}

}
