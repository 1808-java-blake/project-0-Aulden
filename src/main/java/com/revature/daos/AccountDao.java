package com.revature.daos;

import com.revature.beans.Account;

public interface AccountDao {
public static final AccountDao currentAccountDao = new AccountSerializer();
	
	void createAccount(Account a);
	Account findByAccountId(int accountId);
	void updateAccount(Account a);
	void deleteAccount(Account a);
}
