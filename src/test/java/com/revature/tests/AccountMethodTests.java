package com.revature.tests;

import org.junit.Assert;
import org.junit.Test;

import com.revature.beans.Account;

public class AccountMethodTests {
	private static Account a = new Account();
	
	@Test
	public void negativeInputDeposit() {
		boolean result = a.deposit(-500);
		Assert.assertFalse(result);
	}
	
	@Test
	public void normalDeposit() {
		boolean result = a.deposit(500);
		Assert.assertTrue(result);
	}
	
	@Test
	public void negativeInputWithdrawal() {
		a.setBalance(500);
		boolean result = a.withdraw(-500);
		Assert.assertFalse(result);
	}
	
	@Test
	public void negativeBalanceAfterWithdrawal() {
		boolean result = a.withdraw(500);
		Assert.assertFalse(result);
	}
	
	@Test
	public void normalWithdrawal() {
		a.setBalance(500);
		boolean result = a.withdraw(500);
		Assert.assertTrue(result);
	}
}
