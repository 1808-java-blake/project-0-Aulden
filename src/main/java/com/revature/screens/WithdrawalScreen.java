package com.revature.screens;

import java.util.Scanner;

import com.revature.beans.CurrentUser;
import com.revature.beans.User;
import com.revature.beans.Account;
import com.revature.daos.AccountDao;
import com.revature.daos.UserDao;

public class WithdrawalScreen implements Screen{
	private Scanner scan = new Scanner(System.in);
	private UserDao ud = UserDao.currentUserDao;
	private AccountDao ad = AccountDao.currentAccountDao;
	private User u = ud.findByUsernameAndPassword(CurrentUser.username, CurrentUser.password);
	
	public Screen start() {
		
		while(true) {
		System.out.println("Big Bank Withdrawal");
		System.out.println("-----------------");
		System.out.print("Account IDs: ");
		//loop through all account ids for user
		for(int id : u.getAccountIds()) {
			System.out.print(id + ", ");
		}
		System.out.println();
		
		System.out.println("Enter the account number you would like to withdraw from, or 'exit' to return");
		String in = scan.nextLine();
		
		if("exit".equalsIgnoreCase(in)) {
			break;
		}
		else if(u.getAccountIds().contains(Integer.parseInt(in))){
			System.out.print("Enter amount to withdraw: $");
			String withdrawal = scan.nextLine();
			try {
				Account a = ad.findByAccountId(Integer.parseInt(in));
				a.withdraw(Integer.parseInt(withdrawal));
				ad.updateAccount(a);
			}
			catch(Exception ex) {
				System.out.println("Error: Please try again");
				ex.printStackTrace();
			}
		}
		else{
			System.out.println("Invalid input, please try again");
		}
		
	}
		
		return new HomeScreen();
		
	}
}
