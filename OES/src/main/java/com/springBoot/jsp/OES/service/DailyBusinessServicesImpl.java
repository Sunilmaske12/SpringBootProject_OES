package com.springBoot.jsp.OES.service;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springBoot.jsp.OES.dao.DailyBusinessRepository;

@Service
public class DailyBusinessServicesImpl implements DailyBusinessServices {
	
	@Autowired
	private DailyBusinessRepository dailyBusinessRepository;
	
	@Override
	public int[] getDailyOnlineSell() {
		return dailyBusinessRepository.getDailyOnlineSell();
	}

	@Override
	public int[] getDailyCashOnDeliverySell() {
		return dailyBusinessRepository.getDailyCashOnDeliverySell();
	}

	@Override
	public Date[] getDates() {
		return dailyBusinessRepository.getDates();
	}

}
