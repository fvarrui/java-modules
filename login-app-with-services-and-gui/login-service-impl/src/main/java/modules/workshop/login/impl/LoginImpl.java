package modules.workshop.login.impl;

import org.apache.commons.codec.digest.DigestUtils;

import modules.workshop.login.api.Login;
import modules.workshop.login.api.LoginException;
import modules.workshop.login.impl.internal.Passwd;
import modules.workshop.login.impl.internal.User;

public class LoginImpl implements Login {
	
	private Passwd passwd;
	
	public LoginImpl() {
		this.passwd = new Passwd();
	}

	@Override
	public boolean login(String username, String password) throws LoginException {
		User user = passwd.findUser(username);
		String hashedPassword = DigestUtils.md5Hex(password);
		return (user != null && user.getPassword().equals(hashedPassword)); 
	}

}
