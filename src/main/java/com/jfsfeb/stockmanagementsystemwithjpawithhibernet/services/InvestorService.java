package com.jfsfeb.stockmanagementsystemwithjpawithhibernet.services;

import java.util.List;

import com.jfsfeb.stockmanagementsystemwithjpawithhibernet.dto.InvestorBean;
import com.jfsfeb.stockmanagementsystemwithjpawithhibernet.dto.StockBean;

public interface InvestorService {
	boolean investorRegistration(InvestorBean investor);

	InvestorBean investorLogin(String email, String password);

	public StockBean searchStock(int stockId);

	boolean buyStock(int investorId, int stockid);

	List<StockBean> getAllStocks();

}
