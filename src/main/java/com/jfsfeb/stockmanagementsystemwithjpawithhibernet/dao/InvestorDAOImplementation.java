package com.jfsfeb.stockmanagementsystemwithjpawithhibernet.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.jfsfeb.stockmanagementsystemwithjpawithhibernet.dto.InvestorBean;
import com.jfsfeb.stockmanagementsystemwithjpawithhibernet.dto.StockBean;
import com.jfsfeb.stockmanagementsystemwithjpawithhibernet.dto.StockRequestBean;
import com.jfsfeb.stockmanagementsystemwithjpawithhibernet.exception.StockManagementSystemExceptions;
import com.jfsfeb.stockmanagementsystemwithjpawithhibernet.dao.InvestorDAO;

public class InvestorDAOImplementation implements InvestorDAO {
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("TestPersistence");

	public boolean investorRegistration(InvestorBean investor) {
		EntityManager manager = null;
		EntityTransaction transaction = null;
		String jpql = null;
		boolean flag = false;
		try {
			manager = factory.createEntityManager();
			jpql = "select r from InvestorBean r ";
			TypedQuery<InvestorBean> query = manager.createQuery(jpql, InvestorBean.class);
			List<InvestorBean> users = query.getResultList();

			for (InvestorBean bean : users) {
				if (bean.getEmail().equalsIgnoreCase(investor.getEmail())) {
					throw new StockManagementSystemExceptions("This EmailId already exist");
				}

			}
			transaction = manager.getTransaction();
			transaction.begin();
			manager.persist(investor);
			flag = true;
			transaction.commit();
			return true;
		} catch (Exception e) {
			if (flag) {
				transaction.rollback();
			}
			throw new StockManagementSystemExceptions(e.getMessage());

		} finally {
			manager.close();
		}
	}

	public InvestorBean investorLogin(String email, String password) {

		EntityManager manager = null;

		manager = factory.createEntityManager();
		String query = "select i from InvestorBean i where i.email = :emailId and i.password =:password and role ='investor'";
		TypedQuery<InvestorBean> investorInfo = manager.createQuery(query, InvestorBean.class);
		investorInfo.setParameter("emailId", email);
		investorInfo.setParameter("password", password);
		try {
			return investorInfo.getSingleResult();
		} catch (Exception e) {
			throw new StockManagementSystemExceptions("Invalid Login Credentials");
		} finally {
			manager.close();
		}
	}

	public StockBean searchStock(int stockid) {
		EntityManager manager = null;
		try {
			manager = factory.createEntityManager();
			StockBean search = manager.find(StockBean.class, stockid);
			if (search == null) {
				throw new StockManagementSystemExceptions("No stocks Found");
			} else {
				return search;
			}
		} catch (Exception e) {
			throw new StockManagementSystemExceptions(e.getMessage());
		} finally {
			manager.close();
		}
	}

	public boolean buyStock(int investorId, int stockId) {
		EntityManager manager = null;
		EntityTransaction transaction = null;

		StockRequestBean stockInfo = new StockRequestBean();
		StockBean stockBean = new StockBean();
		InvestorBean user = new InvestorBean();
		String jpql = null;

		try {
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			stockBean = manager.find(StockBean.class, stockId);
			if (stockBean != null) {
				jpql = "select r from StockRequestBean r ";
				TypedQuery<StockRequestBean> query = manager.createQuery(jpql, StockRequestBean.class);
				List<StockRequestBean> list = query.getResultList();

				for (StockRequestBean requestInfo : list) {
					if (requestInfo.getStockId() == stockId) {
						throw new StockManagementSystemExceptions("This Stock Request is Already Placed By SomeOne ");
					}
				}
				user = manager.find(InvestorBean.class, investorId);
				int noOfStocks = user.getNumberOfStocks();
				if (noOfStocks < 3) {

					stockInfo.setInvestorId(investorId);
					stockInfo.setStockId(stockId);
					transaction.begin();
					manager.persist(stockInfo);
					transaction.commit();
					return true;
				} else {
					throw new StockManagementSystemExceptions("Limit exceeded to buy stocks");
				}
			} else {
				throw new StockManagementSystemExceptions("This Stock Is Not Available To Buy");
			}

		} catch (StockManagementSystemExceptions e) {
			transaction.rollback();
			throw new StockManagementSystemExceptions(e.getMessage());
		}

	}

	public List<StockBean> getAllStocks() {
		EntityManager manager = null;
		try {
			manager = factory.createEntityManager();
			String jpql = "select i from StockBean i";
			TypedQuery<StockBean> query = manager.createQuery(jpql, StockBean.class);
			List<StockBean> stockList = query.getResultList();
			if (stockList.isEmpty()) {
				throw new StockManagementSystemExceptions("No stocks present");
			} else {
				return stockList;
			}
		} catch (Exception e) {
			throw new StockManagementSystemExceptions(e.getMessage());
		} finally {
			manager.close();
		}
	}
}
