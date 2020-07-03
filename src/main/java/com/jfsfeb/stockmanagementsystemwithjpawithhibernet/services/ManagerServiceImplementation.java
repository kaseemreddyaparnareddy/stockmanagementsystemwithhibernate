package com.jfsfeb.stockmanagementsystemwithjpawithhibernet.services;

import com.jfsfeb.stockmanagementsystemwithjpawithhibernet.dao.ManagerDAO;
import com.jfsfeb.stockmanagementsystemwithjpawithhibernet.dto.ManagerBean;
import com.jfsfeb.stockmanagementsystemwithjpawithhibernet.dto.StockBean;
import com.jfsfeb.stockmanagementsystemwithjpawithhibernet.exception.StockManagementSystemExceptions;
import com.jfsfeb.stockmanagementsystemwithjpawithhibernet.factory.StockManagementSystemFactory;
import com.jfsfeb.stockmanagementsystemwithjpawithhibernet.validations.StockManagementSystemValidations;

public class ManagerServiceImplementation implements ManagerService {
	private ManagerDAO dao = StockManagementSystemFactory.getManagerDAOImplementation();
	private StockManagementSystemValidations validation = new StockManagementSystemValidations();

	@Override
	public ManagerBean managerLogin(String email, String password) {
		if (validation.validateByEmail(email)) {

			if (validation.passwordValidation(password)) {

				return dao.managerLogin(email, password);
			} else {
				throw new StockManagementSystemExceptions("Enter valid password");
			}
		} else {
			throw new StockManagementSystemExceptions("Enter valid emailId, Eg:xyz@gmail.com");
		}
	}

	@Override
	public boolean changePassword(int id, String oldPassword, String newPassword) {
		if (validation.validateByUserId(Integer.toString(id))) {

			if (validation.passwordValidation(oldPassword)) {
				if (validation.passwordValidation(newPassword)) {

					return dao.changePassword(id, oldPassword, newPassword);
				} else {
					throw new StockManagementSystemExceptions(
							"Enter valid password, should start with capital letter, contain atleast 4 characters before special charater, 1 special character and 3 numbers ");
				}
			} else {
				throw new StockManagementSystemExceptions(
						"Enter valid password, should start with capital letter, contain atleast 4 characters before special charater, 1 special character and 3 numbers ");
			}
		} else {
			throw new StockManagementSystemExceptions("Enter valid id, should contain only 3 digits");
		}

	}

	@Override
	public boolean addStock(StockBean bean) {
		if (validation.validateByName(bean.getStockName())) {
			if (validation.validateByPrice(Double.toString(bean.getPrice()))) {
				return dao.addStock(bean);
			} else {
				throw new StockManagementSystemExceptions("Enter valid price, should contain only 3 digits");
			}
		} else {
			throw new StockManagementSystemExceptions("Enter valid name, should contain only characters");
		}

	}

	@Override
	public boolean removeStock(int stockId) {
		if (validation.validateByStocksId(Integer.toString(stockId))) {

			return dao.removeStock(stockId);
		} else {
			throw new StockManagementSystemExceptions("Enter valid stock id, should contain exactly 3 digits");
		}
	}
}
