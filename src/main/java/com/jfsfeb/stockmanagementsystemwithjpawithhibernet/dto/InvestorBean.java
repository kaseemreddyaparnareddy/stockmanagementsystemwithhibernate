package com.jfsfeb.stockmanagementsystemwithjpawithhibernet.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "user_info")
public class InvestorBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "user_id")
	private int id;
	@Column
	private String name;
	@Column
	private String email;
	@Column
	@ToString.Exclude
	private String password;
	@Column(nullable = false)
	private int numberOfStocks;
	@Column(name = "mobileno")
	private long mobile_number;
	@Column
	private String role;

}
