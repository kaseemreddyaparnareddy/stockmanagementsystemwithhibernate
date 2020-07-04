package com.jfsfeb.stockmanagementsystemwithjpawithhibernet.controller;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.jfsfeb.stockmanagementsystemwithjpawithhibernet.dto.InvestorBean;
import com.jfsfeb.stockmanagementsystemwithjpawithhibernet.exception.StockManagementSystemExceptions;
import com.jfsfeb.stockmanagementsystemwithjpawithhibernet.factory.StockManagementSystemFactory;
import com.jfsfeb.stockmanagementsystemwithjpawithhibernet.services.InvestorService;

import lombok.extern.log4j.Log4j;

@Log4j
public class LoginController {
	static Scanner scanner = new Scanner(System.in);

	public static void MainPage() {
		AdminController adminController = new AdminController();
		InvestorController investorController = new InvestorController();
		ManagerController managerController = new ManagerController();
		InvestorService investorService = StockManagementSystemFactory.getInvestorServiceImplementation();
		int choose, choice;
		log.info("--------WELCOME TO STOCKS MANAGEMENT SYSTEM--------");
		do {
			log.info(" 1. Login if already had an account");
			log.info(" 2. Create Account");
			log.info("Enter your choice");
			choose = AdminController.checkChoice();
			if (choose == 1) {
				log.info(" 1. Admin Login");
				log.info(" 2. Investor Login");
				log.info(" 3. Manager Login");
				log.info("Enter your choice");
				choice = AdminController.checkChoice();
				switch (choice) {

				case 1:
					adminController.adminLogin();
					break;

				case 2:
					investorController.investorLogin();
					break;

				case 3:
					managerController.managerLogin();
					break;
				default:
					log.info("Invalid user");
				}

			} else {
				if (choose == 2) {
					log.info("Investor Registration");
					InvestorBean investor1 = new InvestorBean();
					log.info("Enter your id");
					int investorId = 0;
					long investorPhoneNumber = 0;
					String investorName = null, investorEmailId = null, investorPassword = null;
					investorId = (int) (Math.random() * 1000);
					if (investorId <= 100) {
						investorId = investorId + 100;
					}
					log.info(investorId);
					investor1.setId(investorId);
					log.info("Enter your name");
					try {
						investorName = scanner.next();
					} catch (StockManagementSystemExceptions smse) {
						log.error(smse.getMessage());
					}
					investor1.setName(investorName);
					log.info("Enter your email");
					try {
						investorEmailId = scanner.next();
					} catch (StockManagementSystemExceptions smse) {
						log.error(smse.getMessage());
					}
					investor1.setEmail(investorEmailId);
					log.info("Enter your password");
					try {
						investorPassword = scanner.next();
					} catch (StockManagementSystemExceptions smse) {
						log.error(smse.getMessage());
					}
					investor1.setPassword(investorPassword);
					log.info("Enter your Mobile number");
					try {
						investorPhoneNumber = scanner.nextLong();
					} catch (StockManagementSystemExceptions smse) {
						log.error(smse.getMessage());
					} catch (InputMismatchException e) {
						log.error(e.getMessage());
					}
					investor1.setMobile_number(investorPhoneNumber);
					investor1.setRole("investor");
					investor1.setNumberOfStocks(0);
					try {
						investorService.investorRegistration(investor1);
						log.info("Registered sucessfully");
					} catch (StockManagementSystemExceptions smse) {
						log.info(smse.getMessage());
					}

				}

			}

		} while (true);

	}
}
