module login.impl {
	
	requires login.api;
	requires org.apache.commons.codec;
	
	provides modules.workshop.login.api.Login with modules.workshop.login.impl.LoginImpl;
	
}