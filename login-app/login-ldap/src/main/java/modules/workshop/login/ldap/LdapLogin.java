package modules.workshop.login.ldap;

import java.io.IOException;

import org.apache.directory.api.ldap.model.exception.LdapException;
import org.apache.directory.ldap.client.api.LdapConnection;
import org.apache.directory.ldap.client.api.LdapNetworkConnection;

import modules.workshop.login.api.Login;
import modules.workshop.login.api.LoginException;

public class LdapLogin implements Login {

	// organization ID given by JumpCloud (lika an ID token)
	private static final String ORG_ID = "616d58fc59fe3e7e11ad6fda";
	
	// LDAP server name or IP address
	private static final String LDAP_SERVER = "ldap.jumpcloud.com";
	
	// LDAP server port 
	private static final int LDAP_PORT = 636;
	
	// use SSL connection
	private static final boolean LDAP_USE_SSL = true;
	
	// DN (Distinguished Name) base
	private static final String BASE_DN = "ou=Users,o=" + ORG_ID + ",dc=jumpcloud,dc=com";
	
	// binding string (user FQDN)
	private static final String BIND_STRING = "uid=%s," + BASE_DN;

	public boolean login(String username, String password) throws LoginException {
		LdapConnection connection = null;
		try {
			connection = new LdapNetworkConnection(LDAP_SERVER, LDAP_PORT, LDAP_USE_SSL);
			connection.setTimeOut(0); // 0 = infinite timeout
			connection.bind(String.format(BIND_STRING, username), password); // login in LDAP server using sername and password 
			return true; // if "bind" process doesn't fail, authentication is valid
		} catch (LdapException e) {
			return false; // if login fails
		} finally {
			
			// always close LDAP server connection
			try {
				connection.close();
			} catch (IOException e) {
				throw new LoginException(e);
			} 
			
		}
	}
	

}
