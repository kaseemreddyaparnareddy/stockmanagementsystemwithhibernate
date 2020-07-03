package com.jfsfeb.stockmanagementsystemwithjpawithhibernet.factory;

import com.jfsfeb.stockmanagementsystemwithjpawithhibernet.dao.AdminDAO;
import com.jfsfeb.stockmanagementsystemwithjpawithhibernet.dao.AdminDAOImplementation;
import com.jfsfeb.stockmanagementsystemwithjpawithhibernet.dao.InvestorDAO;
import com.jfsfeb.stockmanagementsystemwithjpawithhibernet.dao.InvestorDAOImplementation;
import com.jfsfeb.stockmanagementsystemwithjpawithhibernet.dao.ManagerDAO;
import com.jfsfeb.stockmanagementsystemwithjpawithhibernet.dao.ManagerDAOImplementation;
import com.jfsfeb.stockmanagementsystemwithjpawithhibernet.services.AdminService;
import com.jfsfeb.stockmanagementsystemwithjpawithhibernet.services.AdminServiceImplementation;
import com.jfsfeb.stockmanagementsystemwithjpawithhibernet.services.InvestorService;
import com.jfsfeb.stockmanagementsystemwithjpawithhibernet.services.InvestorServiceImplementation;
import com.jfsfeb.stockmanagementsystemwithjpawithhibernet.services.ManagerService;
import com.jfsfeb.stockmanagementsystemwithjpawithhibernet.services.ManagerServiceImplementation;

public class StockManagementSystemFactory {

	private StockManagementSystemFactory() {
		
	}
	public static AdminDAO getAdminDAOImplementation() {
		AdminDAO admin = new AdminDAOImplementation();
		return admin;
	}
	public static InvestorDAO getInvestorDAOImplementation() {
		InvestorDAO investor = new InvestorDAOImplementation();
		return investor;
	}
	public static ManagerDAO getManagerDAOImplementation() {
		ManagerDAO manager = new ManagerDAOImplementation();
		return manager;
	}
	public static AdminService getAdminServiceImplementation() {
		AdminService adminService = new AdminServiceImplementation();
		return adminService;
	}
	public static ManagerService getManagerServiceImplementation() {
		ManagerService managerService = new ManagerServiceImplementation();
		return managerService;
	}
	public static InvestorService getInvestorServiceImplementation() {
		InvestorService investorService = new InvestorServiceImplementation();
		return investorService;
	}
}
