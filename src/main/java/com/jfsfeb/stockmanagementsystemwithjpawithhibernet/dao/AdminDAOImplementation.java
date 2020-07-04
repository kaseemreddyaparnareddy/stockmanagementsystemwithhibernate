package com.jfsfeb.stockmanagementsystemwithjpawithhibernet.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.jfsfeb.stockmanagementsystemwithjpawithhibernet.dto.AdminBean;
import com.jfsfeb.stockmanagementsystemwithjpawithhibernet.dto.CompanyBean;
import com.jfsfeb.stockmanagementsystemwithjpawithhibernet.dto.InvestorBean;
import com.jfsfeb.stockmanagementsystemwithjpawithhibernet.dto.ManagerBean;
import com.jfsfeb.stockmanagementsystemwithjpawithhibernet.dto.StockBean;
import com.jfsfeb.stockmanagementsystemwithjpawithhibernet.dto.StockRequestBean;
import com.jfsfeb.stockmanagementsystemwithjpawithhibernet.exception.StockManagementSystemExceptions;

public class AdminDAOImplementation implements AdminDAO {
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("TestPersistence");

	public AdminBean adminLogin(String email, String password) {

		EntityManager manager = null;
		manager = factory.createEntityManager();
		String query = "select a from AdminBean a where a.email = :emailId and a.password =:password and role='admin'";
		TypedQuery<AdminBean> adminInfo = manager.createQuery(query, AdminBean.class);
		adminInfo.setParameter("emailId", email);
		adminInfo.setParameter("password", password);
		try {
			return adminInfo.getSingleResult();
		} catch (Exception e) {
			throw new StockManagementSystemExceptions("Invalid Login Credentials");
		} finally {
			manager.close();
		}
	}

	public boolean addManager(ManagerBean managerBean) {
		EntityManager manager = null;
		EntityTransaction transaction = null;
		String jpql = null;
		boolean flag = false;
		try {
			manager = factory.createEntityManager();
			jpql = "select m from ManagerBean m ";
			TypedQuery<ManagerBean> query = manager.createQuery(jpql, ManagerBean.class);
			List<ManagerBean> users = query.getResultList();

			for (ManagerBean bean : users) {
				if (bean.getEmail().equalsIgnoreCase(managerBean.getEmail())) {
					throw new StockManagementSystemExceptions("This EmailId already exist ");
				}

			}
			transaction = manager.getTransaction();
			transaction.begin();
			manager.persist(managerBean);
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

	public boolean removeManager(int userId) {
		EntityManager manager = null;
		EntityTransaction transaction = null;
		try {
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			ManagerBean record = manager.find(ManagerBean.class, userId);
			manager.remove(record);
			transaction.commit();
			return true;
		} catch (Exception e) {
			transaction.rollback();
			throw new StockManagementSystemExceptions("Manager can't be removed");
		} finally {
			manager.close();
		}

	}

	public boolean addCompany(CompanyBean companyBean) {
		EntityManager manager = null;
		EntityTransaction transaction = null;
		try {
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			manager.persist(companyBean);
			transaction.commit();
			return true;
		} catch (Exception e) {
			transaction.rollback();
			throw new StockManagementSystemExceptions("Company is already added");
		} finally {
			manager.close();
		}
	}

	public boolean removeCompany(int companyId) {
		EntityManager manager = null;
		EntityTransaction transaction = null;
		try {
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			CompanyBean record = manager.find(CompanyBean.class, companyId);
			manager.remove(record);
			transaction.commit();
			return true;
		} catch (Exception e) {
			transaction.rollback();
			throw new StockManagementSystemExceptions("Company can't be removed");
		} finally {
			manager.close();
		}

	}

	public List<InvestorBean> getAllInvestors() {
		EntityManager manager = null;
		try {
			manager = factory.createEntityManager();
			String jpql = "select i from InvestorBean i where role='investor'";
			TypedQuery<InvestorBean> query = manager.createQuery(jpql, InvestorBean.class);
			List<InvestorBean> investorList = query.getResultList();
			if (investorList.isEmpty()) {
				throw new StockManagementSystemExceptions("No investors present");
			} else {
				return investorList;
			}
		} catch (Exception e) {
			throw new StockManagementSystemExceptions(e.getMessage());
		} finally {
			manager.close();
		}
	}

	public List<StockRequestBean> investorRequest() {
		EntityManager manager = null;
		try {
			manager = factory.createEntityManager();
			String jpql = "select s from StockRequestBean s";
			TypedQuery<StockRequestBean> query = manager.createQuery(jpql, StockRequestBean.class);
			List<StockRequestBean> stockRequestList = query.getResultList();
			if (stockRequestList.isEmpty()) {
				throw new StockManagementSystemExceptions("No stocks requests found");
			} else {
				return stockRequestList;
			}
		} catch (Exception e) {
			throw new StockManagementSystemExceptions(e.getMessage());
		} finally {
			manager.close();
		}
	}

	public boolean acceptStock(int rId) {
		EntityManager manager = null;
		EntityTransaction transaction = null;

		StockRequestBean info = new StockRequestBean();
		StockBean stockInfo = new StockBean();
		InvestorBean user = new InvestorBean();

		int noOfStocks = 0;
		int reqStockId = 0;
		int reqUserId = 0;
		try {
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();

			transaction.begin();
			info = manager.find(StockRequestBean.class, rId);
			transaction.commit();
			if (info != null) {
				reqStockId = info.getStockId();
				reqUserId = info.getInvestorId();
				transaction.begin();
				String jpql = "select r from InvestorBean r";
				TypedQuery<InvestorBean> query = manager.createQuery(jpql, InvestorBean.class);
				user = manager.find(InvestorBean.class, reqUserId);
				noOfStocks = user.getNumberOfStocks();
				if (noOfStocks < 3) {
				++noOfStocks;
				System.out.println("No Of Stocks Bought" + noOfStocks);

				user.setNumberOfStocks(noOfStocks);
				transaction.commit();
				} else {
					throw new StockManagementSystemExceptions("Limit exceeded to accept stocks");
				}
				transaction.begin();
				stockInfo = manager.find(StockBean.class, reqStockId);
				transaction.commit();
//				transaction.begin();
//				info = manager.find(StockRequestBean.class,rId);
//				manager.remove(info);
//				transaction.commit();
			} else {
				throw new StockManagementSystemExceptions("Invalid Request Id");
			}

		} catch (Exception e) {
			transaction.rollback();
			throw new StockManagementSystemExceptions(e.getMessage());
		} finally {
			manager.close();
		}

		return true;
	}

	@Override
	public List<ManagerBean> getAllManagers() {
		EntityManager manager = null;
		try {
			manager = factory.createEntityManager();
			String jpql = "select m from ManagerBean m where role='manager'";
			TypedQuery<ManagerBean> query = manager.createQuery(jpql, ManagerBean.class);
			List<ManagerBean> managerList = query.getResultList();
			if (managerList.isEmpty()) {
				throw new StockManagementSystemExceptions("No managers present");
			} else {
				return managerList;
			}
		} catch (Exception e) {
			throw new StockManagementSystemExceptions(e.getMessage());
		} finally {
			manager.close();
		}
	}

	@Override
	public List<CompanyBean> getAllCompanies() {
		EntityManager manager = null;
		try {
			manager = factory.createEntityManager();
			String jpql = "select c from CompanyBean c";
			TypedQuery<CompanyBean> query = manager.createQuery(jpql, CompanyBean.class);
			List<CompanyBean> companyList = query.getResultList();
			if (companyList.isEmpty()) {
				throw new StockManagementSystemExceptions("No companies present");
			} else {
				return companyList;
			}
		} catch (Exception e) {
			throw new StockManagementSystemExceptions(e.getMessage());
		} finally {
			manager.close();
		}
	}
}
