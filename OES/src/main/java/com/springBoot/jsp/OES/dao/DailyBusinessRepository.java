package com.springBoot.jsp.OES.dao;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springBoot.jsp.OES.entity.DailyBusiness;

public interface DailyBusinessRepository extends JpaRepository<DailyBusiness, Integer> {
	
	@Query("select DailyOnlinePayment from DailyBusiness")
	int[] getDailyOnlineSell();

	@Query("select DailyCashOnDelivery from DailyBusiness")
	int[] getDailyCashOnDeliverySell();

	@Query("select Date from DailyBusiness")
	Date[] getDates();
	
	

}
