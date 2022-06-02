package modules.workshop.hello;

import javax.swing.JOptionPane;

public class HelloModules {

	public static void main(String[] args) {
		// show a dialog 
		JOptionPane.showMessageDialog(
				null, 
				"Welcome to the wonderful world of Java modules!", // dialog mesage 
				"Java Modules", // dialog title 
				JOptionPane.INFORMATION_MESSAGE // dialog type
			);
		
		printModuleInfo(HelloModules.class);
		printModuleInfo(JOptionPane.class);
	}
	
	public static void printModuleInfo(Class<?> clazz) {
		Module moduleInfo = clazz.getModule();
		System.out.println("=====================================");
		System.out.println("Analyzed class: " + clazz.getCanonicalName());
		if (moduleInfo.getDescriptor() != null) {
			System.out.println("Module name and version: " + moduleInfo.getDescriptor().toNameAndVersion());
			System.out.println("Exported: " + moduleInfo.getDescriptor().exports());
			System.out.println("Requires: " + moduleInfo.getDescriptor().requires());
		} else {
			System.out.println("Running in legacy mode! No modules information");
		}
	}

}
