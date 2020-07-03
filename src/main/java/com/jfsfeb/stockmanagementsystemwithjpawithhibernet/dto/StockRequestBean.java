package com.jfsfeb.stockmanagementsystemwithjpawithhibernet.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table (name = "investor_stocks_info")
public class StockRequestBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column
	@GeneratedValue
	private int rId;
	@Column(name ="stockid")
	private int stockId;
	@Column(name ="user_id")
	private int investorId;

}
