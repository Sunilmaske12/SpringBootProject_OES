package com.springBoot.jsp.OES.service;

import java.sql.Date;

public interface DailyBusinessServices {

	int[] getDailyOnlineSell();

	int[] getDailyCashOnDeliverySell();

	Date[] getDates();

}
