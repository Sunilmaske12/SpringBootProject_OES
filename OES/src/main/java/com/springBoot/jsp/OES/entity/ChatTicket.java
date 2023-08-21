package com.springBoot.jsp.OES.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name="ticket_query")
public class ChatTicket {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Ticket_Id")
	private int ticketId;
	private int User_Id;
	private String User_Name;
	private String Date =  LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
	private String Time =LocalTime.now().format(DateTimeFormatter.ofPattern("H:m a"));
	private String Status;
	private String Admin_Manager;
	private String seen="No";
	
	
	public int getTicketId() {
		return ticketId;
	}
	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}
	public int getUser_Id() {
		return User_Id;
	}
	public void setUser_Id(int user_Id) {
		User_Id = user_Id;
	}
	public String getUser_Name() {
		return User_Name;
	}
	public void setUser_Name(String user_Name) {
		User_Name = user_Name;
	}
	public String getDate() {
		return Date;
	}
	public String getTime() {
		return Time;
	}
	public void setTime(String time) {
		Time = time;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public String getAdmin_Manager() {
		return Admin_Manager;
	}
	public void setAdmin_Manager(String admin_Manager) {
		Admin_Manager = admin_Manager;
	}
	public String getSeen() {
		return seen;
	}
	public void setSeen(String seen) {
		this.seen = seen;
	}
	
	

}
