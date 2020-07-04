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
@Table(name = "manager_info")
public class ManagerBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "user_id")
	private int userId;
	@Column
	private String name;
	@Column
	@ToString.Exclude
	private String password;
	@Column
	private String email;
	@Column(name = "company_name")
	private String companyName;
//	@Column
//	private String role;

}
