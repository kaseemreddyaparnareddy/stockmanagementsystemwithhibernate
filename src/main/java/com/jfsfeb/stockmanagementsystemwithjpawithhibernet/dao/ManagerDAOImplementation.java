package com.jfsfeb.stockmanagementsystemwithjpawithhibernet.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.jfsfeb.stockmanagementsystemwithjpawithhibernet.dto.StockBean;
import com.jfsfeb.stockmanagementsystemwithjpawithhibernet.exception.StockManagementSystemExceptions;
import com.jfsfeb.stockmanagementsystemwithjpawithhibernet.dao.ManagerDAO;
import com.jfsfeb.stockmanagementsystemwithjpawithhibernet.dto.ManagerBean;

public class ManagerDAOImplementation implements ManagerDAO {
	EntityManagerFactory factory = null;

	public ManagerBean managerLogin(String email, String password) {
		EntityManager manager = null;
		factory = Persistence.createEntityManagerFactory("TestPersistence");
		manager = factory.createEntityManager();
		String query = "select m from ManagerBean m where m.email = :emailId and m.password =:password and role='manager'";
		TypedQuery<ManagerBean> managerInfo = manager.createQuery(query, ManagerBean.class);
		managerInfo.setParameter("emailId", email);
		managerInfo.setParameter("password", password);
		try {
			return managerInfo.getSingleResult();
		} catch (Exception e) {
			throw new StockManagementSystemExceptions("Invalid Login Credentials");
		} finally {
			manager.close();
			factory.close();
		}
	}

	public boolean changePassword(int id, String oldPassword, String newPassword) {
		EntityManagerFactory factory = null;
		EntityManager manager = null;
		EntityTransaction transaction = null;

		ManagerBean user = new ManagerBean();
		String password = null;

		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();

			transaction.begin();
			user = manager.find(ManagerBean.class, id);
			password = user.getPassword();
			if (password.equals(oldPassword)) {
				user.setPassword(newPassword);
				transaction.commit();
			} else {
				throw new StockManagementSystemExceptions("Invalid Password");
			}
		} catch (StockManagementSystemExceptions e) {
			throw new StockManagementSystemExceptions(e.getMessage());
		} finally {
			manager.close();
			factory.close();
		}
		return true;
	}

	public boolean addStock(StockBean bean) {
		EntityManager manager = null;
		EntityTransaction transaction = null;
		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			manager.persist(bean);
			transaction.commit();
			return true;
		} catch (Exception e) {
			transaction.rollback();
			throw new StockManagementSystemExceptions("stock is already added");
		} finally {
			manager.close();
			factory.close();
		}
	}

	public boolean removeStock(int stockId) {

		EntityManager manager = null;
		EntityTransaction transaction = null;
		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			StockBean record = manager.find(StockBean.class, stockId);
			manager.remove(record);
			transaction.commit();
			return true;
		} catch (Exception e) {
			transaction.rollback();
			throw new StockManagementSystemExceptions("stock can't be removed");
		} finally {
			manager.close();
			factory.close();
		}

	}
}
