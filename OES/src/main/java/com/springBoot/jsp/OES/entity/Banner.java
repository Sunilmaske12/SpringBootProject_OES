package com.springBoot.jsp.OES.entity;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Banner {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="B_Id")
	int banner_no;
	@Column(name="B_Name")
	String banner_name;
	String date = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
	@Column(name="Status")
	String action;
	String Banner_image;
	public Banner() {
		super();
	}
	public int getBanner_no() {
		return banner_no;
	}
	public void setBanner_no(int banner_no) {
		this.banner_no = banner_no;
	}
	public String getBanner_name() {
		return banner_name;
	}
	public void setBanner_name(String banner_name) {
		this.banner_name = banner_name;
	}
	public String getDate() {
		return date;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getBanner_image() {
		return Banner_image;
	}
	public void setBanner_image(String banner_image) {
		Banner_image = banner_image;
	}
	@Override
	public String toString() {
		return "Banner [banner_no=" + banner_no + ", banner_name=" + banner_name + ", Date=" + date + ", action="
				+ action + ", Banner_image=" + Banner_image + "]";
	}
	
	
	
}
