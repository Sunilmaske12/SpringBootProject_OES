package com.springBoot.jsp.OES.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.springBoot.jsp.OES.entity.Product;


public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	public List<Product> findBycid(int cid);
	
	@Query("select SUM(prod_price*prod_quantity) from Product p where p.cid=:cid")
	public Long getProductionByCatId(@Param("cid") int i);
	

	
	@Query("select SUM(prod_price*prod_quantity) from Product ")
	public long getProduction();
	
	
}

