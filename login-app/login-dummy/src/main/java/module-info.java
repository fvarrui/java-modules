module login.dummy {
	
	exports modules.workshop.login.dummy;

	requires transitive login.api;
	requires org.apache.commons.codec;
	
}