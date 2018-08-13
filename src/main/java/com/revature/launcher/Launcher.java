package com.revature.launcher;

import com.revature.screens.LoginScreen;
import com.revature.screens.Screen;

public class Launcher {
	public static void main(String[] args) {
		
//		System.out.println("Welcome to Big Bank!");
//		System.out.println("~~~~~~~~~~~~~~~~~~~~");
		
		System.out.println(" _______   __                  _______                       __ ");
		System.out.println("|       \\ |  \\                |       \\                     |  \\      ");
		System.out.println("| $$$$$$$\\ \\$$  ______        | $$$$$$$\\  ______   _______  | $$   __ ");
		System.out.println("| $$__/ $$|  \\ /      \\       | $$__/ $$ |      \\ |       \\ | $$  /  \\");
		System.out.println("| $$    $$| $$|  $$$$$$\\      | $$    $$  \\$$$$$$\\| $$$$$$$\\| $$_/  $$");
		System.out.println("| $$$$$$$\\| $$| $$  | $$      | $$$$$$$\\ /      $$| $$  | $$| $$   $$ ");
		System.out.println("| $$__/ $$| $$| $$__| $$      | $$__/ $$|  $$$$$$$| $$  | $$| $$$$$$\\ ");
		System.out.println("| $$    $$| $$ \\$$    $$      | $$    $$ \\$$    $$| $$  | $$| $$  \\$$\\");
		System.out.println(" \\$$$$$$$  \\$$ _\\$$$$$$$       \\$$$$$$$   \\$$$$$$$ \\$$   \\$$ \\$$   \\$$");
		System.out.println("             |  \\__| $$                                              ");
		System.out.println("              \\$$    $$                                              ");
		System.out.println("               \\$$$$$$                                               \n");
		
		Screen s = new LoginScreen();
		while(true) {
			s = s.start();
		}
	}
}