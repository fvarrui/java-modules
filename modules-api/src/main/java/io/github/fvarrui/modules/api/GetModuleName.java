package io.github.fvarrui.modules.api;

public class GetModuleName {

	public static void main(String[] args) {
		
		printModuleInfo(com.sun.net.httpserver.HttpsServer.class);
		printModuleInfo(GetModuleName.class);

	}
	
	public static void printModuleInfo(Class<?> clazz) {
		Module moduleInfo = clazz.getModule();
		System.out.println("=====================================");
		System.out.println("Analyzed class: " + clazz.getCanonicalName());
		System.out.println("Module name and version: " + moduleInfo.getDescriptor().toNameAndVersion());
		System.out.println("Exported: " + moduleInfo.getDescriptor().exports());
		System.out.println("Requires: " + moduleInfo.getDescriptor().requires());
	}

}
