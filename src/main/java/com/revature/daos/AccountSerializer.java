package com.revature.daos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.revature.beans.Account;

public class AccountSerializer implements AccountDao {

	@Override
	public void createAccount(Account a) {
		if (a == null) {
			return;
		}
		File f = new File("src/main/resources/accounts/" + a.getId() + ".txt");
//		System.out.println(f.getName());
		if (f.exists()) {
			return;
		}
		try {
			f.createNewFile();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return;
		}
		try (ObjectOutputStream oos = new ObjectOutputStream(
				new FileOutputStream("src/main/resources/accounts/" + a.getId() + ".txt"))) {

			oos.writeObject(a);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	@Override
	public Account findByAccountId(int accountId) {
				try (ObjectInputStream ois = new ObjectInputStream(
						new FileInputStream("src/main/resources/accounts/" + accountId + ".txt"))) {

					Account a = (Account) ois.readObject(); // retrieve the account if possible
					
					return a;
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
//					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
//					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
//					e.printStackTrace();
				}
				return null;
	}

	@Override
	public void updateAccount(Account a) {
		if (a == null) {
			return;
		}
		File f = new File("src/main/resources/accounts/" + a.getId() + ".txt");
//		System.out.println(f.getName());
		if (!f.exists()) {
			return;
		}
		
		try (ObjectOutputStream oos = new ObjectOutputStream(
				new FileOutputStream("src/main/resources/accounts/" + a.getId() + ".txt"))) {

			oos.writeObject(a);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void deleteAccount(Account a) {
		if (a == null) {
			return;
		}
		File f = new File("src/main/resources/accounts/" + a.getId() + ".txt");
//		System.out.println(f.getName());
		if (f.exists()) {
			f.delete();
		}
		
		

	}

}
