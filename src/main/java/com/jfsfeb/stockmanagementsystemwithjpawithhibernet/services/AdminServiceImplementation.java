package com.jfsfeb.stockmanagementsystemwithjpawithhibernet.services;

import java.util.List;

import com.jfsfeb.stockmanagementsystemwithjpawithhibernet.dao.AdminDAO;
import com.jfsfeb.stockmanagementsystemwithjpawithhibernet.dto.AdminBean;
import com.jfsfeb.stockmanagementsystemwithjpawithhibernet.dto.CompanyBean;
import com.jfsfeb.stockmanagementsystemwithjpawithhibernet.dto.InvestorBean;
import com.jfsfeb.stockmanagementsystemwithjpawithhibernet.dto.ManagerBean;
import com.jfsfeb.stockmanagementsystemwithjpawithhibernet.dto.StockRequestBean;
import com.jfsfeb.stockmanagementsystemwithjpawithhibernet.exception.StockManagementSystemExceptions;
import com.jfsfeb.stockmanagementsystemwithjpawithhibernet.factory.StockManagementSystemFactory;
import com.jfsfeb.stockmanagementsystemwithjpawithhibernet.validations.StockManagementSystemValidations;
import com.jfsfeb.stockmanagementsystemwithjpawithhibernet.validations.Validations;

public class AdminServiceImplementation implements AdminService {
	private AdminDAO dao = StockManagementSystemFactory.getAdminDAOImplementation();
	private Validations validation = new StockManagementSystemValidations();

	@Override
	public AdminBean adminLogin(String email, String password) {
		if (validation.validateByEmail(email)) {

			if (validation.passwordValidation(password)) {

				return dao.adminLogin(email, password);
			} else {
				throw new StockManagementSystemExceptions("Enter valid password");
			}
		} else {
			throw new StockManagementSystemExceptions("Enter valid emailId, Eg:xyz@gmail.com");
		}
	}

	@Override
	public boolean addManager(ManagerBean managerBean) {

		if (validation.validateByName(managerBean.getName())) {

			if (validation.validateByEmail(managerBean.getEmail())) {

				if (validation.passwordValidation(managerBean.getPassword())) {

					return dao.addManager(managerBean);
				} else {
					throw new StockManagementSystemExceptions(
							"Enter valid password, should start with capital letter, contain atleast 4 characters before special charater, 1 special character and 3 numbers ");
				}
			} else {
				throw new StockManagementSystemExceptions("Enter valid emailId,Eg:xyz@gmail.com");
			}
		} else {
			throw new StockManagementSystemExceptions("Enter valid name, should contain only characters");
		}
	}

	@Override
	public boolean addCompany(CompanyBean companyBean) {
		if (validation.validateByName(companyBean.getCompanyName())) {

			if (validation.validateByName(companyBean.getBranch())) {

				if (validation.validateByName(companyBean.getState())) {

					return dao.addCompany(companyBean);
				} else {
					throw new StockManagementSystemExceptions(
							"Enter valid company state, should contain only characters");
				}
			} else {
				throw new StockManagementSystemExceptions("Enter valid company branch, should contain only characters");
			}
		} else {
			throw new StockManagementSystemExceptions("Enter valid company name, should contain only characters");
		}
	}

	@Override
	public List<InvestorBean> getAllInvestors() {

		return dao.getAllInvestors();
	}

	@Override
	public List<StockRequestBean> investorRequest() {

		return dao.investorRequest();
	}

	@Override
	public boolean acceptStock(int rId) {
		if (validation.validateById(Integer.toString(rId))) {
				return dao.acceptStock(rId);
			} else {
				throw new StockManagementSystemExceptions("Enter valid id, should contain only digits");
			}
	}

	@Override
	public boolean removeManager(int userId) {
		if (validation.validateByUserId(Integer.toString(userId))) {

			return dao.removeManager(userId);
		} else {
			throw new StockManagementSystemExceptions("Enter valid manager id, should contain only 3 digits");
		}
	}

	@Override
	public boolean removeCompany(int companyId) {
		if (validation.validateByUserId(Integer.toString(companyId))) {

			return dao.removeCompany(companyId);
		} else {
			throw new StockManagementSystemExceptions("Enter valid company id, should contain only 3 digits");
		}
	}


	@Override
	public List<ManagerBean> getAllManagers() {

		return dao.getAllManagers();
	}

	@Override
	public List<CompanyBean> getAllCompanies() {

		return dao.getAllCompanies();
	}

}
