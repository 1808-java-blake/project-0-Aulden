package com.revature.screens;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.beans.CurrentUser;

public class HomeScreen implements Screen {
	private Scanner scan = new Scanner(System.in);
	private Logger log = Logger.getRootLogger();

	public Screen start() {
		System.out.println("Please chose from following options:");
		System.out.println("Enter 1 to make a deposit");
		System.out.println("Enter 2 to make a withdrawal");
		System.out.println("Enter 3 to view balance");
		System.out.println("Enter 4 to view transaction history");
		System.out.println("Enter 5 to view user transaction history (ADMIN ONLY)");
		System.out.println("Enter 6 to view the facts");
		System.out.println("Enter 7 to create a bank account");
		System.out.println("Enter 8 to delete a bank account");
		System.out.println("Enter 9 to exit");
		
		String selection = scan.nextLine();
		
		switch (selection) {
		case "1":
			log.info(CurrentUser.username + " entered Deposit screen");
			return new DepositScreen();
		case "2":
			log.info(CurrentUser.username + " entered Withdrawal screen");
			return new WithdrawalScreen();
		case "3":
			log.info(CurrentUser.username + " entered View Balance screen");
			return new ViewBalanceScreen();
		case "4":
			log.info(CurrentUser.username + " entered View Transaction History screen");
			return new ViewTransactionHistoryScreen();
		case "5":
			log.info(CurrentUser.username + " entered Admin screen");
			return new ViewTransactionHistoryAdminScreen();
		case "6":
			log.info(CurrentUser.username + " entered Facts screen");
			return new FactsScreen();
		case "7":
			log.info(CurrentUser.username + " entered Create Account screen");
			return new CreateAccountScreen();
		case "8":
			log.info(CurrentUser.username + " entered Delete Account screen");
			return new DeleteAccountScreen();
		case "9":
			log.info(CurrentUser.username + " exited the application");
			System.out.println("Goodbye!");
			System.exit(1);
			break;
		default:
			break;
		}

		return this;
	}

}
