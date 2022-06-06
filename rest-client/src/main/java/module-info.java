module rest.client {
	
	opens modules.workshop.rest.client;

	requires com.fasterxml.jackson.annotation;
	requires jakarta.xml.bind;
	requires jakarta.ws.rs;
	
}

