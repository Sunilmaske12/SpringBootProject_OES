package com.springBoot.jsp.OES.controller;

import java.util.List;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.springBoot.jsp.OES.entity.Customer;
import com.springBoot.jsp.OES.entity.Order;
import com.springBoot.jsp.OES.entity.OrderDetails;
import com.springBoot.jsp.OES.entity.User;
import com.springBoot.jsp.OES.securityConfig.CustomUserDetails;
import com.springBoot.jsp.OES.service.OrderServices;
import com.springBoot.jsp.OES.service.UserServices;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import com.springBoot.jsp.OES.service.ContactServices;
import com.springBoot.jsp.OES.service.CustomerServices;

@Controller
public class OrderController {
	
	@Autowired
	private OrderServices orderServices;
	
	
	@Autowired
	private CustomerServices customerServices;
	
	@Autowired
	private UserServices userServices;
	
	@Autowired
	HttpServletRequest request;
	
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
	
	
	@GetMapping("/User/MyOrders")
	public String myMyOrders(Model model, @AuthenticationPrincipal CustomUserDetails userDetail) {
	List<Order> allOrders = orderServices.getAllOrderById(userDetail.getId());
	String[] customerName =new String[allOrders.size()];
	int i=0;
	for(Order l:allOrders) {
		customerName[i] = customerServices.getCustomerName(l.getAddress_Id());
		i++;
	}
	
	model.addAttribute("allOrders", allOrders);
	model.addAttribute("customerName" , customerName);
		return "MyOrders";
	}
	
	@GetMapping("/User/OrderDetails{oid},{aid}")
	public String myOrderDetails(Model model, @PathVariable("oid") int Oid,  @PathVariable("aid") int Aid) {
		Customer customer = customerServices.getCustomerById(Aid);
		List<OrderDetails> orderDetails = orderServices.getOrderDetailById(Oid); 
		int totalPrice = 0, charges=0;
		for(OrderDetails od:orderDetails) {
			totalPrice += (Integer.parseInt(od.getProduct_Price())*Integer.parseInt(od.getProduct_Quantity()));
		}
		if(totalPrice<=999) {
			charges = 100; 
		}
	model.addAttribute("customerDetails", customer);
	model.addAttribute("OrderDetails", orderDetails);
	model.addAttribute("charges", charges);
	model.addAttribute("tPrice", totalPrice);
		return "OrderDetails";
	}
	
	@GetMapping("/Admin/viewOrderAdmin")
	public String getAdminOrderPage(Model model) {
		List<Order> allOrders = orderServices.getAllOrder();
		String[] customerName=new String[allOrders.size()];
				int i=0;
				for(Order o:allOrders) {
					customerName[i] = customerServices.getCustomerName(String.valueOf(o.getAddress_Id()));
							i++;
				}
		
		model.addAttribute("customerName", customerName);
		model.addAttribute("allOrders", allOrders);
		return "ViewOrderAdmin";
	}
	
	@GetMapping("/orderDetailsAdmin{orderId},{addressId}")
	public String getAdminOrderDetailsPage(Model model, @PathVariable("orderId") int oid, @PathVariable("addressId") int aid) {
		orderServices.updateSeenOrder(oid);
		List<OrderDetails> orderdetails = orderServices.getOrderDetailById(oid);
		Customer getCustomer = customerServices.getCustomerById(aid);
		int totalPrice = 0, charges=0;
		for(OrderDetails od:orderdetails) {
			totalPrice += (Integer.parseInt(od.getProduct_Price())*Integer.parseInt(od.getProduct_Quantity()));
		}
		if(totalPrice<=999) {
			charges = 100; 
		}
	model.addAttribute("charges", charges);
	model.addAttribute("tPrice", totalPrice);
	
		model.addAttribute("getCustomer", getCustomer);
		model.addAttribute("orderdetails", orderdetails);
		return "OrderDetailsAdmin";
	}
	
	@GetMapping("/updateStatus,{action},{oid}")
	public String updateBannerStatus(Model model, @PathVariable("action") String Action, @PathVariable("oid") int oid) {
		orderServices.updateStatus(Action, oid);
		return "redirect:/Admin/viewOrderAdmin";
	}
	
	
	@GetMapping("/User/processToCheckout")
	public String checkoutPage(Model model, @AuthenticationPrincipal CustomUserDetails user) {
		
		String cartLength = request.getParameter("cartlegth");
		String totalAmount = request.getParameter("TotalAmount");
		List<Customer> getAllAddress = customerServices.getAllCustomerWithAddress(user.getId());
		if(cartLength!=null) model.addAttribute("cartLength", (Integer.parseInt(cartLength)));
		if(cartLength!=null) model.addAttribute("totalAmount", (Integer.parseInt(totalAmount)));
		model.addAttribute("AllAddress", getAllAddress);
		model.addAttribute("customer", new Customer());
		return "checkout";
	}
	
	@PostMapping("/User/makeOrder,{amount},{cartLength}")
	public String makeOrder(@PathVariable("amount") int amount,@PathVariable("cartLength") int cartLength, Model model) {
		String address_id = request.getParameter("address_id");
		int payment = amount*100;
		
		try {
			RazorpayClient client=new RazorpayClient("rzp_test_7TxMC9UB8XmbM0", "r6r09iYcHJk4WkPxByNFhHkn");
			JSONObject obj=new JSONObject();
		obj.put("amount", payment);
		obj.put("currency", "INR");
		obj.put("receipt", "txn_1234"); 
		
		//creating order
		com.razorpay.Order order=client.orders.create(obj);
		System.out.println(order);	
		String o_id=order.get("id");
		model.addAttribute("razorpayPaymentId", o_id);
		} catch (RazorpayException e) {
			e.printStackTrace();
		}
		model.addAttribute("cartLength", cartLength);
		model.addAttribute("address_id", address_id);
		model.addAttribute("a",1);
		return "orderPlace";
	}
	
	
	
	@RequestMapping(value="/sendCart", method = RequestMethod.POST)
	public ModelAndView settingOrderDetails1(@RequestParam(value="pid[]") Integer[] pid, @RequestParam(value="pname[]") String[] pname
			, @RequestParam(value="pquantity[]") Integer[] pquantity , @RequestParam(value="pprice[]") Integer[] pprice, HttpSession session) {
		
		session.setAttribute("pid", pid);
		session.setAttribute("pname", pname);
		session.setAttribute("pquantity", pquantity);
		session.setAttribute("pprice", pprice);
		return null;
	}
	
	
	@PostMapping("/User/orderPlace")
	public String orderPlace(@AuthenticationPrincipal CustomUserDetails user, HttpSession session) {
		String AddressId= request.getParameter("addressId");
		String paymentId = request.getParameter("paymentId");
		String paymentMode;
		if(paymentId==null || paymentId=="") {
			paymentMode="Cash On Delivery";
		}else {
			paymentMode="Online Paid";
		}
		
		int TotalPrice=Integer.parseInt(request.getParameter("tprice"));
		 String razopayOrderId=request.getParameter("razorpayOrdertId");
		 Order order=orderServices.saveOrder(razopayOrderId, AddressId, user.getId(), TotalPrice, paymentMode);
		 
		 
		// Saving order details
		 Integer[] pid= (Integer[]) session.getAttribute("pid");
		 Integer[] pprice= (Integer[]) session.getAttribute("pprice");
		 Integer[] pquantity= (Integer[]) session.getAttribute("pquantity");
		 String[] pname= (String[]) session.getAttribute("pname");
			 
		 for(int i=0; i<pid.length; i++) {
			 OrderDetails orderdetail=new OrderDetails();
			 orderdetail.setOid(order.getOrder_Id());
			 orderdetail.setProduct_Id(pid[i]);
			 orderdetail.setProduct_Name(pname[i]);
			 orderdetail.setProduct_Price(String.valueOf(pprice[i]));
			 orderdetail.setProduct_Quantity(String.valueOf(pquantity[i]));
			 OrderDetails od= orderServices.savaProductDetails(orderdetail);
		 }
		
		return "redirect:/User/MyOrders";
	}
	
	@GetMapping("/showInvoicePage/{oid},{aid}")
	public String getInvoicePage(@PathVariable("oid") int oid, @PathVariable("aid") int aid, Model model) {
		Customer customer = customerServices.getCustomerById(aid);
		List<OrderDetails> orderDetails= orderServices.getOrderDetailById(oid);
		int totalPrice = 0, charges=0;
		for(OrderDetails od:orderDetails) {
			totalPrice += (Integer.parseInt(od.getProduct_Price())*Integer.parseInt(od.getProduct_Quantity()));
		}
		if(totalPrice<=999) {
			charges = 100; 
		}
	
		model.addAttribute("charges", charges);
		model.addAttribute("tPrice", totalPrice);
		model.addAttribute("customer", customer);
		model.addAttribute("orderDetails", orderDetails);
		return "Invoice";
	}
		

}
