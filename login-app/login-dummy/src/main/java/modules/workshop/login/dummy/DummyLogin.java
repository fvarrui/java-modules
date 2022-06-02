package modules.workshop.login.dummy;

import org.apache.commons.codec.digest.DigestUtils;

import modules.workshop.login.api.Login;
import modules.workshop.login.api.LoginException;
import modules.workshop.login.dummy.internal.Passwd;
import modules.workshop.login.dummy.internal.User;

public class DummyLogin implements Login {
	
	private Passwd passwd;
	
	public DummyLogin() {
		this.passwd = new Passwd();
	}

	@Override
	public boolean login(String username, String password) throws LoginException {
		User user = passwd.findUser(username);
		String hashedPassword = DigestUtils.md5Hex(password);
		return (user != null && user.getPassword().equals(hashedPassword)); 
	}

}
