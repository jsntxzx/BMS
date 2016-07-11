package com.oa.entity;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "record")
public class Record {
	
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;
	
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "bid")
	@Cascade({ org.hibernate.annotations.CascadeType.REFRESH })
	private Book bid;	
	

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "eid")
	@Cascade({ org.hibernate.annotations.CascadeType.REFRESH })
	private Employee eid;
	
	@Column(name = "status")
	private Integer status;

	
	@Column(name = "add_time")
	private Date addtime;
	
	
	@Column(name = "ret_time")
	private Date rettime;
	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Book getBid() {
		return bid;
	}


	public void setBid(Book bid) {
		this.bid = bid;
	}


	public Employee getEid() {
		return eid;
	}


	public void setEid(Employee eid) {
		this.eid = eid;
	}


	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}


	public Date getAddtime() {
		return addtime;
	}


	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}


	public Date getRettime() {
		return rettime;
	}


	public void setRettime(Date rettime) {
		this.rettime = rettime;
	}

	
}
