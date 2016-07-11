package com.oa.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "employee")
public class Employee {
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;
	
	
	@Column(name = "enumber")
	private String enumber;
	
	
	@Column(name = "name", length = 50)
	private String name;
	
	@Column(name = "borrowcount")
	private Integer borrowcount;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}




	public String getEnumber() {
		return enumber;
	}


	public void setEnumber(String enumber) {
		this.enumber = enumber;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Integer getBorrowcount() {
		return borrowcount;
	}


	public void setBorrowcount(Integer borrowcount) {
		this.borrowcount = borrowcount;
	}
	
	
	

}
