package com.springBoot.jsp.OES.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.springBoot.jsp.OES.entity.Category;
import com.springBoot.jsp.OES.entity.Product;
import com.springBoot.jsp.OES.entity.User;
import com.springBoot.jsp.OES.securityConfig.CustomUserDetails;
import com.springBoot.jsp.OES.service.ContactServices;
import com.springBoot.jsp.OES.service.OrderServices;
import com.springBoot.jsp.OES.service.ProductService;
import com.springBoot.jsp.OES.service.UserServices;

@Controller
public class ProductHandler {

	@Autowired
	private ProductService productService;

	@Autowired
	private UserServices userServices;

	@Autowired
	private OrderServices orderServices;

	@Autowired
	private ContactServices contactServices;

	@ModelAttribute
	public void commonData(Model model, @AuthenticationPrincipal CustomUserDetails userDetail) {
		if (userDetail != null) {
			User adminInfo = userServices.getUserById(userDetail.getId());
			int newUsers = userServices.getNewUserCount();
			int newOrders = orderServices.getNewOrdersCount();
			int newQuery = contactServices.getNewQueryCount();
			int notificationCount = 0;
			if (newUsers != 0)
				notificationCount += 1;
			if (newOrders != 0)
				notificationCount += 1;
			if (newQuery != 0)
				notificationCount += 1;

			model.addAttribute("aminInfo", adminInfo);
			model.addAttribute("userInfo", adminInfo);
			model.addAttribute("userId", adminInfo.getId());
			model.addAttribute("newUsers", newUsers);
			model.addAttribute("newOrders", newOrders);
			model.addAttribute("newQuery", newQuery);
			model.addAttribute("notificationCount", notificationCount);
		}

	}

	@GetMapping("/allProduct")
	public String getShopGridPage(Model model) {
		List<Product> allProduct = productService.getAllProduct();
		List<Category> allCategory = productService.getAllCategories();
		model.addAttribute("allProduct", allProduct);
		model.addAttribute("allCategory", allCategory);
		return "shop-grid";
	}

	@GetMapping("/allProduct{cid}")
	public String getProductByCategory(Model model, @PathVariable("cid") int cid) {
		List<Product> allProduct = productService.getProductByCategory(cid);
		List<Category> allCategory = productService.getAllCategories();
		model.addAttribute("allProduct", allProduct);
		model.addAttribute("allCategory", allCategory);
		return "shop-grid";
	}

	@GetMapping("/productDetails{id},{cid}")
	public String getProductDetail(Model model, @PathVariable("cid") int cid, @PathVariable("id") int id) {
		List<Product> allProduct = productService.getProductByCategory(cid);
		Product product = productService.getProductById(id);
		model.addAttribute("allProduct", allProduct);
		model.addAttribute("product", product);
		return "Product-details";
	}

	@GetMapping("/Admin/viewProduct")
	public String getviewProductPage(Model model) {
		List<Product> allProduct = productService.getAllProduct();
		model.addAttribute("allProduct", allProduct);
		return "View_product";
	}

	@GetMapping("/Admin/productOperation,{Action},{Pid}")
	public String userOperation(Model model, @PathVariable("Action") String action,
			@PathVariable("Pid") int productId) {
		switch (action) {
		case "DELETE":
			productService.deleteUserById(productId);
			// case "EDIT": userServices.editUserById();
		}
		return "redirect:/viewProduct";
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
	if (file == null) {
			return "redirect:/Admin/addProduct";

		} else {

			try {
				InputStream is = file.getInputStream();
				File saveFile = new ClassPathResource("./static/img/product").getFile();
				Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + file.getOriginalFilename());
				Files.copy(is, path, StandardCopyOption.REPLACE_EXISTING);

			} catch (IOException e) {
				e.printStackTrace();
			}
			product.setProd_imageName(file.getOriginalFilename());
			productService.saveProduct(product);

			return "redirect:/Admin/viewProduct";
		}
	}

}
