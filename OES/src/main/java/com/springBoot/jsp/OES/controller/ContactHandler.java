package com.springBoot.jsp.OES.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.springBoot.jsp.OES.entity.Chat;
import com.springBoot.jsp.OES.entity.ChatTicket;
import com.springBoot.jsp.OES.entity.User;
import com.springBoot.jsp.OES.securityConfig.CustomUserDetails;
import com.springBoot.jsp.OES.service.ContactServices;
import com.springBoot.jsp.OES.service.UserServices;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

@Controller
public class ContactHandler {
	
	@Autowired
	private ContactServices cs;
	
	@Autowired
	private UserServices userServices;
	
	@Autowired
	HttpServletRequest req;
		
	@GetMapping("/User/contact")
	public String contactPage(Model m, @AuthenticationPrincipal CustomUserDetails user) {
		List<ChatTicket> chatTickets = cs.getChatByUserId(user.getId());
		m.addAttribute("tickets", chatTickets);
		return "contact";
	}
	
	@GetMapping("/User/allMyChats,{ticketId},{status}")
	public String getMyChats(@PathVariable("ticketId") int tid, @PathVariable("status") String status, Model m ) {
		List<Chat> allChats = cs.getAllChatsById(String.valueOf(tid));
		m.addAttribute("status", status);
		m.addAttribute("chats", allChats);
		m.addAttribute("chat", new Chat());
		m.addAttribute("ticketId", tid);
		return "Chat";
	}
	
	@PostMapping("/User/createQuery")
	public String createQuery( @AuthenticationPrincipal CustomUserDetails userDetail) {
		String msg=req.getParameter("message");
		ChatTicket ct=new ChatTicket();
			ct.setUser_Id(userDetail.getId());
			ct.setUser_Name(userDetail.getName());
			int tid =cs.saveTicket(ct);
			
		Chat chat=new Chat();
			chat.setUserName(userDetail.getName());
			chat.setTicket_Id(String.valueOf(tid));
			chat.setChat(msg);
			chat.setSender(userDetail.getName());
			cs.saveChats(chat);
		return "redirect:/User/contact";
	}
	
	@PostMapping("/User/sendChat")
	public String sendChats(@AuthenticationPrincipal CustomUserDetails user) {
		System.out.println(req.getParameter("message"));
		Chat chat=new Chat();
			chat.setTicket_Id(req.getParameter("Ticket_Id"));
			chat.setChat(req.getParameter("message"));
			chat.setUserName(user.getUsername());
			chat.setSender(user.getUsername());
		cs.saveChats(chat);
		return "redirect:/User/contact";
		
	}
	
	@PostMapping("/Admin/sendChat")
	public String sendChatsToUser(@AuthenticationPrincipal CustomUserDetails user, Model model) {
		System.out.println(req.getParameter("message"));
		Chat chat=new Chat();
			chat.setTicket_Id(req.getParameter("Ticket_Id"));
			chat.setChat(req.getParameter("message"));
			chat.setAdmin_Manager(user.getUsername());
			chat.setSender(user.getUsername());
		cs.saveChats(chat);
		
		return "redirect:/Admin/support";
		
	}
	
	@GetMapping("/Admin/support")
	public String getSupportPageAdmin(Model model, @AuthenticationPrincipal CustomUserDetails userDetail) {
		User adminInfo = userServices.getUserById(userDetail.getId());
		List<ChatTicket> chatTickets = cs.getAllTickets();
		model.addAttribute("aminInfo", adminInfo);
		model.addAttribute("tickets", chatTickets);
		return "support";
		
	}
	
	@GetMapping("/Admin/allMyChats,{ticketId}")
	public String getMyChatsAdmin(@PathVariable("ticketId") int tid, Model m ) {
		List<Chat> allChats = cs.getAllChatsById(String.valueOf(tid));
		//m.addAttribute("status", status);
		m.addAttribute("chats", allChats);
		return "AdminChat";
	}

}
