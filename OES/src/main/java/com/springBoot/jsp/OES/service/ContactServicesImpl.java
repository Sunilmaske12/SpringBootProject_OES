package com.springBoot.jsp.OES.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springBoot.jsp.OES.dao.ChatRepository;
import com.springBoot.jsp.OES.dao.TicketRepository;
import com.springBoot.jsp.OES.entity.Chat;
import com.springBoot.jsp.OES.entity.ChatTicket;

@Service
public class ContactServicesImpl implements ContactServices {
	
	@Autowired
	private ChatRepository chatRepository;
	
	@Autowired
	private TicketRepository ticketRepository;
	
	@Override
	public List<ChatTicket> getChatByUserId(int id) {
		return ticketRepository.findByUserId(id);
	}

	@Override
	public List<Chat> getAllChatsById(String tid) {
		return   chatRepository.findByTicketid(tid);
	}

	@Override
	public int getNewQueryCount() {
		
		return  ticketRepository.countBySeen("No") ;
	}

	@Override
	public List<ChatTicket> getAllTickets() {
		return ticketRepository.findAll();
	}

	@Override
	public int saveTicket(ChatTicket ct) {
		return ticketRepository.save(ct).getTicketId();
	}

	@Override
	public void saveChats(Chat chat) {
		chatRepository.save(chat);
		
	}

	@Override
	public ChatTicket getTicketById(int tid) {
		return ticketRepository.findById(tid).get();
	}

	@Override
	public void updateTicketsSeen(int tid) {
		ChatTicket ticket= ticketRepository.findById(tid).get();
		ticket.setSeen("Yes");
		ticketRepository.save(ticket);
	}

	

}
