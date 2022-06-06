package modules.workshop.rest.client;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;

public class RestClient {

	private static final String BASE_URL = "https://dog.ceo/api/";

	private Client client = ClientBuilder.newClient();

	public BreedsList getBreedsList() {
		return client
				.target(BASE_URL)
				.path("breeds/list/all")
				.request(MediaType.APPLICATION_JSON)
				.get(BreedsList.class);
	}

	public static void main(String[] args) {

		printModuleInfo(RestClient.class);
		printModuleInfo(ClientBuilder.class);

		RestClient client = new RestClient();
		System.out.println(client.getBreedsList());
				
	}
	
	public static final void printModuleInfo(Class<?> clazz) {
		Module moduleInfo = clazz.getModule();	
		System.out.println("Analyzed class: " + clazz.getCanonicalName());
		if (moduleInfo.getDescriptor() != null) {
			System.out.println("Module name and version: " + moduleInfo.getDescriptor().toNameAndVersion());
			System.out.println("Exported: " + moduleInfo.getDescriptor().exports());
		} else {
			System.out.println("Module descriptor not found!!!");			
		}
		System.out.println("=====================================" );
	}

}
