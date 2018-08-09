package com.revature.screens;

import java.util.Scanner;

import com.revature.beans.CurrentUser;
import com.revature.beans.User;
import com.revature.daos.AccountDao;
import com.revature.daos.UserDao;

public class ViewBalanceScreen implements Screen{
	private UserDao ud = UserDao.currentUserDao;
	private AccountDao ad = AccountDao.currentAccountDao;
	private Scanner scan = new Scanner(System.in);
	
	public Screen start() {
		User user = ud.findByUsernameAndPassword(CurrentUser.username, CurrentUser.password);
		
		System.out.println("Big Bank Balance");
		System.out.println("-------------------");
		
		for(int id : user.getAccountIds()) {
			System.out.println("Account with ID " + id + " has balance: $" + ad.findByAccountId(id).getBalance());
		}
		
		System.out.println("Hit enter to continue...");
		scan.nextLine();
		
		return new HomeScreen();
	}
	
}
