package com.revature.screens;

import java.util.Scanner;

import com.revature.beans.Account;

public class FactsScreen implements Screen{
	private Scanner scan = new Scanner(System.in);
	
	public Screen start() {
		System.out.println();
		System.out.println("Big Bank Facts");
		System.out.println("---------------");
		System.out.println("#1 Bank in Existence...Fact");
		System.out.println("Top rated bank in the Big Bank Magazine");
		System.out.println("Low, low interest rate loans (82% APR!!!)");
		System.out.println("Press enter to continue...");
		scan.nextLine();
		
		return new HomeScreen();
	}
}
