package modules.workshop.login.api;

/**
 * Login service interface
 * @author fvarrui
 */
public interface Login {
	
	/**
	 * Check if user credentials are valid 
	 * @param username Username
	 * @param password Password
	 * @return true if credentials are valid
	 * @throws LoginException If something went wrong
	 */
	public boolean login(String username, String password) throws LoginException;

}
