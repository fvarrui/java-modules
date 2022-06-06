package modules.workshop.login.cli;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import modules.workshop.login.api.Login;
import modules.workshop.login.api.LoginException;
import modules.workshop.login.dummy.DummyLogin;
import modules.workshop.login.ldap.LdapLogin;

public class Main {

	private static Scanner scanner = new Scanner(System.in);
	
	// available login services
	private static final List<Login> LOGIN_SERVICES = Arrays.asList(
				new DummyLogin(),	// passwd file
				new LdapLogin() 	// ldap server
			);

	public static void main(String[] args) throws LoginException {
		
		String username = readString("Enter username: ");
		String password = readString("Enter password: ");
		
		if (login(username, password)) {
			System.out.println(username + " is logged in!");
		} else {
			System.err.println(username + " credentials are invalid!");
		}
		
		scanner.close();
	}
	
	/**
	 * Check if user credentials are valid against all login services
	 * @param username Username
	 * @param password Password
	 * @return true if the user exists
	 * @throws LoginException If something went wrong
	 */
	public static boolean login(String username, String password) throws LoginException {
		for (Login service : LOGIN_SERVICES) {
			if (service.login(username, password)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Ask for a string in the console 
	 * @param prompt Prompt text shown in the console
	 * @return Entered string
	 */
	public static String readString(String prompt) {
		System.out.print(prompt);
		return scanner.nextLine();
	}

}
