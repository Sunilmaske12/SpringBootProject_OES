package com.springBoot.jsp.OES.controller;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.springBoot.jsp.OES.entity.Customer;
import com.springBoot.jsp.OES.entity.User;
import com.springBoot.jsp.OES.securityConfig.CustomUserDetails;
import com.springBoot.jsp.OES.service.ContactServices;
import com.springBoot.jsp.OES.service.CustomerServices;
import com.springBoot.jsp.OES.service.GmailSenderService;
import com.springBoot.jsp.OES.service.OrderServices;
import com.springBoot.jsp.OES.service.UserServices;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UserServices userServices;
	
	@Autowired
	private CustomerServices customerService;
	

	@Autowired
	private OrderServices orderServices;
	
	@Autowired
	private ContactServices contactServices;
	
	@Autowired
	private  GmailSenderService gmailSenderService;
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private HttpSession httpSession;
	
	@ModelAttribute
	public void commonData(Model model, @AuthenticationPrincipal CustomUserDetails userDetail, HttpSession session)
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
			session.setAttribute("userInfo", adminInfo);
			model.addAttribute("notificationCount", notificationCount);
		}
		
	}
	
	
	@GetMapping("/registrationForm")
	public String myRegistrationForm(Model model) {
		User u = new User();
		model.addAttribute("User", u);
		return "RegistrationForm";
	}
		
	  @PostMapping("/registerUser") 
	  public String registerUser(@ModelAttribute("User") User user)
	  {	
			user.setUser_pass(passwordEncoder.encode(user.getUser_pass()));
		  User myUser=userServices.registerUser(user); 
		  if(myUser!=null) {
			  String message = "You have successfully register in Online Electrical Shopee ";
			  gmailSenderService.sendMail(myUser.getUser_emailid(), "sunilmaske2001@gmail.com",myUser.getUser_name()
					  , message);
		  }
	  return  "loginform";
	  }
	  
	  @GetMapping("/customLogin")
	  public String getCustomLoginForm() {
		  return "loginform";
	  }
	  
	  @PostMapping("/User/editUser")
	  public String updateUser(@ModelAttribute("userInfo") User user)
	  {
		  userServices.saveUser(user);
		  return "redirect:/";
	  }
	  
	@GetMapping("/Admin/userList")
	public String getAllUserPage(Model model) {
			List<User> allUser = userServices.getAllUsers();
		model.addAttribute("userList", allUser);
		return "UserList";
	}
	
	@GetMapping("/Admin/userOperation,{Action},{Uid}")
	public String userOperation(Model model, @PathVariable("Action") String action, @PathVariable("Uid") int userId) {
		switch( action){
			case "DELETE":  userServices.deleteUserById(userId);
			//case "EDIT":  userServices.editUserById();
		}
		return "redirect:/userList";
	}
	
	@PostMapping("/User/saveAddress")
	public String saveAdress(@ModelAttribute("customer") Customer customer)
	{
		customerService.insertCustomer(customer);
		return "redirect:/User/processToCheckout";
	}
	
	@GetMapping("User/editAddressPage{id}")
	public String editAddressPage(Model model, @PathVariable("id") int aid)
	{
		Customer customer = customerService.getCustomerById(aid);
		model.addAttribute("customer", customer);
		return "EditAddress";
	}
		
	@GetMapping("/Admin/seenAllUsers")
	public String seenAllUsers()
	{
		userServices.seenAllUsers();
		return "redirect:/Admin/userList";
	}
	
	@GetMapping("/forgetPasswordPage")
	public String forgetPasswordPage() {
		return "forgotPassword";
	}
	
	@PostMapping("/forgetPassword")
	public String forgetPassword() {
		String email = request.getParameter("email");
		User user = userServices.getUserByEmail(email);
		if(user==null) {
			httpSession.setAttribute("errorMessage", "This is not a registered email");
			return "forgotPassword";
		}
		else {
			Random rad=new Random();
			int otpValue = rad.nextInt(999999);
			httpSession.setAttribute("otpValue", String.valueOf(otpValue));
			String text = "Your otp is " +otpValue;
			gmailSenderService.sendMail(email, "sunilmaske2001@gmail.com", "OES-OTP", text);
			return "EnterOtp";
		}
		
	}
	
	@PostMapping("/validateOtp")
	public String validateOtp(){
		String userOtp = request.getParameter("otp");
		String realOtp = (String)httpSession.getAttribute("otpValue");
		if(!userOtp.equals(realOtp)) {
			return "EnterOtp";
		}
		else {
			httpSession.removeAttribute("otpValue");
			return "newPassword";
		}
	}

}
