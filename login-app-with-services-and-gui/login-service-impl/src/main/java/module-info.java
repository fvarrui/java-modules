module login.impl {
	
	// implemented api 
	requires login.api;
	
	// to hash passwords with md5
	requires org.apache.commons.codec;
	
	// this module provides "LoginImpl", an implementation of the "Login" interface
	provides modules.workshop.login.api.Login with modules.workshop.login.impl.LoginImpl;
	
}