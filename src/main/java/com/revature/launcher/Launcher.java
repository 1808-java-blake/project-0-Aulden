package com.revature.launcher;

import com.revature.screens.LoginScreen;
import com.revature.screens.Screen;

public class Launcher {
	public static void main(String[] args) {
		
		System.out.println("Welcome to Big Bank!");
		System.out.println("~~~~~~~~~~~~~~~~~~~~");
		
		Screen s = new LoginScreen();
		while(true) {
			s = s.start();
		}
	}
}