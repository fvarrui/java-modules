module rest.client {
	opens io.github.fvarrui.modules.rest;

	requires com.fasterxml.jackson.annotation;
	requires jakarta.xml.bind;
	requires jakarta.ws.rs;
}

