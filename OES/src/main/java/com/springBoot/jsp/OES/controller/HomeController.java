package com.springBoot.jsp.OES.controller;


import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.springBoot.jsp.OES.entity.Banner;
import com.springBoot.jsp.OES.entity.Category;
import com.springBoot.jsp.OES.entity.Product;
import com.springBoot.jsp.OES.entity.User;
import com.springBoot.jsp.OES.securityConfig.CustomUserDetails;
import com.springBoot.jsp.OES.service.BannerServices;
import com.springBoot.jsp.OES.service.ContactServices;
import com.springBoot.jsp.OES.service.DailyBusinessServices;
import com.springBoot.jsp.OES.service.OrderServices;
import com.springBoot.jsp.OES.service.ProductService;
import com.springBoot.jsp.OES.service.UserServices;

import jakarta.servlet.http.HttpSession;


@Controller
public class HomeController {

	@Autowired
	private ProductService productService;

	@Autowired
	private BannerServices bannerService;
	
	@Autowired
	private UserServices userServices;
	
	@Autowired
	private OrderServices orderServices;
	
	@Autowired
	private ContactServices contactServices;
	
	@Autowired
	private DailyBusinessServices dailyBusinessServices;
	
	@Autowired
	private HttpSession httpSession;
	
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
	
	@RequestMapping("/")
		public String indexPage(Model model, @AuthenticationPrincipal CustomUserDetails userDetail) {
		List<Product> recentProducts = productService.getRecentProducts();
		List<Category> allCategories = productService.getAllCategories();
		List<Banner> allBanner = bannerService.getAllBanner();
		model.addAttribute("allCategories", allCategories);
		model.addAttribute("allBanner", allBanner);
		model.addAttribute("user", userDetail); 
		model.addAttribute("recentProducts", recentProducts);
		// String httpSession.setAttribute("loginSuccess");
		model.addAttribute("loginSuccess", (String)httpSession.getAttribute("loginSuccess"));
		httpSession.removeAttribute("loginSuccess");
		return "index";
	}
	
	 @PostMapping("/Admin/updateAdmin")
	  public String updateAdmin(@ModelAttribute("userInfo") User user)
	  {
		  userServices.saveUser(user);
		  httpSession.setAttribute("updateUser", "Done");
		return "redirect:/Admin/adminPannel";
	  }
	
	@GetMapping("/Admin/viewCategory")
	public String getviewCategorypage(Model model) {
		List<Category> allCategories = productService.getAllCategories();
		model.addAttribute("allCategories", allCategories);
		return "View_Category";
	}
	
	@GetMapping("/Admin/deleteCategory{cid}")
	public String deleteCategory(@PathVariable("cid") int cid) {
		productService.deleteCategoryById(cid);
		return "redirect:/Admin/viewCategory";
	}

	
	@GetMapping("/Admin/adminPannel")
	public String getDashboard(Model model, @AuthenticationPrincipal CustomUserDetails userDetail) {
	
		int totalUsers = userServices.getTotalUserCount();
		int totalOrders = orderServices.getTotalOrders();
		int totalSales = orderServices.getTotalSell();
		int[] statusPer= orderServices.getPercentageStatus();
		float[] categoryWiseProductionPer= productService.getCategoryWiseProductionPer();
		int[] dailyOnlineSell = dailyBusinessServices.getDailyOnlineSell();
		int[] dailyCashOnDeliverySell = dailyBusinessServices.getDailyCashOnDeliverySell();
		Date[] dates = dailyBusinessServices.getDates();
		String totalProduction = productService.getTotalProduction();
		model.addAttribute("totalUsers", totalUsers);
		model.addAttribute("totalOrders", totalOrders);
		model.addAttribute("totalSales", totalSales);
		model.addAttribute("totalEarning", (int)(totalSales*0.20));
		model.addAttribute("statusPer", statusPer);
		model.addAttribute("catWiseProductionPer", categoryWiseProductionPer);
		model.addAttribute("totalProduction", totalProduction);
		model.addAttribute("dailyOnlineSell",dailyOnlineSell);
		model.addAttribute("dailyCashOnDeliverySell",dailyCashOnDeliverySell);
		model.addAttribute("dates",dates);
		model.addAttribute("updateAdmin", httpSession.getAttribute("updateUser"));
		httpSession.removeAttribute("updateUser");
		return "dashbord";
	}

	
	 
}
