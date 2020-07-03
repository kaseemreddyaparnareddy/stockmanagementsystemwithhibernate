package com.jfsfeb.stockmanagementsystemwithjpawithhibernet.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "company_info")
public class CompanyBean implements Serializable {
	@Id
	@Column
	private int companyId;
	@Column
	private String companyName;
	@Column(name = "companyBranch")
	private String branch;
	@Column(name = "companyState")
	private String state;

}
