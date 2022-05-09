package io.github.fvarrui.modules.api;

public class GetModuleName {

	public static void main(String[] args) {
		
		Class<?> clazz = com.sun.net.httpserver.HttpsServer.class;

		Module moduleInfo = clazz.getModule();
		
		System.out.println("Analyzed class: " + clazz.getCanonicalName());
		System.out.println("Module name and version: " + moduleInfo.getDescriptor().toNameAndVersion());
		System.out.println("Exported: " + moduleInfo.getDescriptor().exports());

	}

}
