package com.jsp.CloneAPIBookMyShow.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.CloneAPIBookMyShow.entity.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Long>{

}
