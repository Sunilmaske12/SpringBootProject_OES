package com.springBoot.jsp.OES.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
		return "index";
	}

	@GetMapping("/loginForm")
	public String getLoginPage(Model model) {
		// DailyBusiness dailyOnline
		return "loginform";
	}
	
	 @PostMapping("/Admin/updateAdmin")
	  public String updateAdmin(@ModelAttribute("userInfo") User user)
	  {
		  userServices.saveUser(user);
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
	
	
	

	@GetMapping("/Admin/addProduct")
	public String getAddProductPage(Model model) {
		List<Category> allCat = productService.getAllCategories();
			model.addAttribute("product", new Product());
		model.addAttribute("categories", allCat);
		return "Add-product";
	}
	
	@PostMapping("/Admin/saveProduct")
	public String saveProduct(Model model, @RequestParam("Image") MultipartFile file, @ModelAttribute Product product) {
		
		//setting image name
		
		if(file!=null) {
			product.setProd_imageName(file.getOriginalFilename());
		}
		
		//uploading image to folder
		File saveFile;
		try {
		
			saveFile = new ClassPathResource("static/img/product").getFile();
				Path path = Paths.get(saveFile.getAbsolutePath()+File.separator+file.getOriginalFilename());
				Files.copy(file.getInputStream(), path);
	
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		productService.saveProduct(product);
		
		return "redirect:/Admin/addProduct";
	}
	

	@GetMapping("/Admin/calender")
	public String getCalenderPage(Model model) {
		
		return "calender";
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
		return "dashbord";
	}

	
	 
}
