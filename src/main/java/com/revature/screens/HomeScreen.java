package com.revature.screens;

import java.util.Scanner;

public class HomeScreen implements Screen {
	private Scanner scan = new Scanner(System.in);

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
			return new DepositScreen();
		case "2":
			return new WithdrawalScreen();
		case "3":
			return new ViewBalanceScreen();
		case "4":
			return new ViewTransactionHistoryScreen();
		case "5":
			return new ViewTransactionHistoryAdminScreen();
		case "6":
			return new FactsScreen();
		case "7":
			return new CreateAccountScreen();
		case "8":
			return new DeleteAccountScreen();
		case "9":
			System.exit(1);
			break;
		default:
			break;
		}

		return this;
	}

}
