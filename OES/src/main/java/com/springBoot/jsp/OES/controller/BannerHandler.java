package com.springBoot.jsp.OES.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springBoot.jsp.OES.entity.Banner;
import com.springBoot.jsp.OES.entity.User;
import com.springBoot.jsp.OES.securityConfig.CustomUserDetails;
import com.springBoot.jsp.OES.service.BannerServices;
import com.springBoot.jsp.OES.service.ContactServices;
import com.springBoot.jsp.OES.service.OrderServices;
import com.springBoot.jsp.OES.service.UserServices;

@Controller
@RequestMapping("/Admin")
public class BannerHandler {
	
	@Autowired
	private BannerServices bannerServices;
	
	@Autowired 
	private UserServices userServices;
	
	@Autowired
	private OrderServices orderServices;
	
	@Autowired
	private ContactServices contactServices;
	
	@ModelAttribute
	public void commonData(Model model, @AuthenticationPrincipal CustomUserDetails userDetail)
	{
		if(userDetail !=null) {
			User adminInfo = userServices.getUserById(userDetail.getId());
			int newUsers = userServices.getNewUserCount();
			int newOrders = orderServices.getNewOrdersCount();
			int newQuery = contactServices.getNewQueryCount();
			int notificationCount = 0;
					if(newUsers!=0) notificationCount+=1;
					if(newOrders!=0) notificationCount+=1;
					if(newQuery!=0) notificationCount+=1;

			model.addAttribute("aminInfo", adminInfo);
			model.addAttribute("userInfo", adminInfo);
			model.addAttribute("userId", adminInfo.getId());
			model.addAttribute("newUsers", newUsers);
			model.addAttribute("newOrders", newOrders);
			model.addAttribute("newQuery", newQuery);
			model.addAttribute("notificationCount", notificationCount);
		}
		
	}
	
	@GetMapping("/allBanner")
	public String getBannerPage(Model model) {
		List<Banner> allBanner = bannerServices.getAllBanner();
		model.addAttribute("Banner", allBanner);
		return "Banner";
	}
	
	@GetMapping("/addBanner")
	public String addBanner(Model model) {
			return "AddBanner";
	}
	
	@GetMapping("/updateBannerStatus,{action},{Bid}")
	public String updateBannerStatus(Model model, @PathVariable("action") String Action, @PathVariable("Bid") int Bid) {
		bannerServices.updateStatus(Action, Bid);
		return "redirect:allBanner";
	}
	
	@GetMapping("/deleteBanner{Bid}")
	public String deleteBanner(Model model,  @PathVariable("Bid") int Bid) {
		bannerServices.deleteBanner(Bid);
			return "redirect:allBanner";
	}
}
//sunil