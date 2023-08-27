package com.jsp.CloneAPIBookMyShow.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.CloneAPIBookMyShow.entity.Movie;

public interface MovieRepo  extends  JpaRepository<Movie, Long>{

}
