package com.jfsfeb.stockmanagementsystemwithjpawithhibernet.services;

import java.util.List;

import com.jfsfeb.stockmanagementsystemwithjpawithhibernet.dao.InvestorDAO;
import com.jfsfeb.stockmanagementsystemwithjpawithhibernet.dto.InvestorBean;
import com.jfsfeb.stockmanagementsystemwithjpawithhibernet.dto.StockBean;
import com.jfsfeb.stockmanagementsystemwithjpawithhibernet.exception.StockManagementSystemExceptions;
import com.jfsfeb.stockmanagementsystemwithjpawithhibernet.factory.StockManagementSystemFactory;
import com.jfsfeb.stockmanagementsystemwithjpawithhibernet.validations.StockManagementSystemValidations;

public class InvestorServiceImplementation implements InvestorService {

	private InvestorDAO dao = StockManagementSystemFactory.getInvestorDAOImplementation();
	private StockManagementSystemValidations validation = new StockManagementSystemValidations();

	@Override
	public boolean investorRegistration(InvestorBean investor) {

		if (validation.validateByUserId(Integer.toString(investor.getId()))) {

			if (validation.validateByName(investor.getName())) {

				if (validation.validateByEmail(investor.getEmail())) {

					if (validation.passwordValidation(investor.getPassword())) {

						if (validation.validateByPhoneNumber(Long.toString(investor.getMobile_number()))) {

							return dao.investorRegistration(investor);
						} else {
							throw new StockManagementSystemExceptions(
									"Enter valid mobile number, should contain only 10 numbers");
						}
					} else {
						throw new StockManagementSystemExceptions(
								"Enter valid password, should start with capital letter, contain atleast 4 characters before special charater, 1 special character and 3 numbers ");
					}
				} else {
					throw new StockManagementSystemExceptions("Enter valid emailId, Eg:xyz@gmail.com");
				}
			} else {
				throw new StockManagementSystemExceptions("Enter valid name, should contain only characters");
			}
		} else {
			throw new StockManagementSystemExceptions("Enter valid investorId, should contain exactly 3 digits");
		}

	}

	@Override
	public InvestorBean investorLogin(String email, String password) {

		if (validation.validateByEmail(email)) {

			if (validation.passwordValidation(password)) {

				return dao.investorLogin(email, password);
			} else {
				throw new StockManagementSystemExceptions("Enter valid password");
			}
		} else {
			throw new StockManagementSystemExceptions("Enter valid emailId, Eg:xyz@gmail.com");
		}
	}

	public StockBean searchStock(int stockId) {

		if (validation.validateByStocksId(Integer.toString(stockId))) {
			return dao.searchStock(stockId);
		} else {
			throw new StockManagementSystemExceptions("Enter valid stock id, should have only 3 digits");
		}

	}

	@Override
	public boolean buyStock(int investorId, int stockid) {

		if (validation.validateByUserId(Integer.toString(investorId))) {

			if (validation.validateByStocksId(Integer.toString(stockid))) {

				return dao.buyStock(investorId, stockid);
			}else {
				throw new StockManagementSystemExceptions("Enter valid stock id, should contain only 3 digits");
			}
		} else {
			throw new StockManagementSystemExceptions("Enter valid investor id, should contain only 3 digits");
		} 

	}

	@Override
	public List<StockBean> getAllStocks() {

		return dao.getAllStocks();
	}

}
