module login.ldap {
	
	exports modules.workshop.login.ldap;
	
	requires transitive login.api;
	requires org.apache.directory.ldap.api.all;
	
}