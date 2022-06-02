package modules.workshop.login.cli;

import java.util.Scanner;

import modules.workshop.login.api.Login;
import modules.workshop.login.api.LoginException;
import modules.workshop.login.dummy.DummyLogin;
import modules.workshop.login.ldap.LdapLogin;

public class Main {

	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws LoginException {
		
		String username = readString("Enter username: ");
		String password = readString("Enter password: ");
		
		Login dummy = new DummyLogin();
		Login ldap = new LdapLogin();
		if (dummy.login(username, password) || ldap.login(username, password)) {
			System.out.println(username + " is logged in!");
		} else {
			System.err.println(username + " credentials are invalid!");
		}
		
		scanner.close();
	}
	
	public static String readString(String prompt) {
		System.out.print(prompt);
		return scanner.nextLine();
	}

}
