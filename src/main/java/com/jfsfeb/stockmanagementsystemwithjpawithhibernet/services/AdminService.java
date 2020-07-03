package com.jfsfeb.stockmanagementsystemwithjpawithhibernet.services;

import java.util.List;

import com.jfsfeb.stockmanagementsystemwithjpawithhibernet.dto.AdminBean;
import com.jfsfeb.stockmanagementsystemwithjpawithhibernet.dto.CompanyBean;
import com.jfsfeb.stockmanagementsystemwithjpawithhibernet.dto.InvestorBean;
import com.jfsfeb.stockmanagementsystemwithjpawithhibernet.dto.ManagerBean;
import com.jfsfeb.stockmanagementsystemwithjpawithhibernet.dto.StockRequestBean;

public interface AdminService {
	AdminBean adminLogin(String email, String password);

	boolean addManager(ManagerBean managerBean);

	boolean removeManager(int userId);

	boolean addCompany(CompanyBean companyBean);

	boolean removeCompany(int companyId);

	List<InvestorBean> getAllInvestors();

	List<StockRequestBean> investorRequest();

	boolean acceptStock(int rId);

	List<ManagerBean> getAllManagers();

	List<CompanyBean> getAllCompanies();
}
