package com.jfsfeb.stockmanagementsystemwithjpawithhibernet.services;

import com.jfsfeb.stockmanagementsystemwithjpawithhibernet.dto.ManagerBean;
import com.jfsfeb.stockmanagementsystemwithjpawithhibernet.dto.StockBean;

public interface ManagerService {

	ManagerBean managerLogin(String email, String password);

	boolean changePassword(int id, String oldPassword, String newPassword);

	boolean addStock(StockBean bean);

	boolean removeStock(int stockId);

}