package modules.workshop.login.api;

public interface Login {
	
	public boolean login(String username, String password) throws LoginException;

}
