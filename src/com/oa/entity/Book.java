package com.oa.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "book")
public class Book {
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;
	
	
	@Column(name = "sn")
	private String sn;
	
	
	@Column(name = "title")
	private String title;

	@Column(name = "total_copies")
	private Integer totalcopy ;
	
	@Column(name = "left_copies")
	private Integer leftcopy ;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getTotalcopy() {
		return totalcopy;
	}

	public void setTotalcopy(Integer totalcopy) {
		this.totalcopy = totalcopy;
	}

	public Integer getLeftcopy() {
		return leftcopy;
	}

	public void setLeftcopy(Integer leftcopy) {
		this.leftcopy = leftcopy;
	}
	
	
	
	

}
