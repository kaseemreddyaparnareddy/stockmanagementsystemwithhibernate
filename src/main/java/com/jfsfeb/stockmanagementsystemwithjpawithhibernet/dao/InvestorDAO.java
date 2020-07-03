package com.jfsfeb.stockmanagementsystemwithjpawithhibernet.dao;

import java.util.List;

import com.jfsfeb.stockmanagementsystemwithjpawithhibernet.dto.InvestorBean;
import com.jfsfeb.stockmanagementsystemwithjpawithhibernet.dto.StockBean;

public interface InvestorDAO {
	boolean investorRegistration(InvestorBean investor);

	InvestorBean investorLogin(String email, String password);

	public StockBean searchStock(int stockint);

	boolean buyStock(int investorId, int stockid);

	List<StockBean> getAllStocks();

}
