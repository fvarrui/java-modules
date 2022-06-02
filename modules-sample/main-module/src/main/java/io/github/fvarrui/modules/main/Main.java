package io.github.fvarrui.modules.main;

import io.github.fvarrui.modules.modular.ModularPublicClass;
import io.github.fvarrui.modules.non_modular.NonModularPublicClass;
import io.github.fvarrui.modules.non_modular.internal.NonModularPrivateClass;
//import io.github.fvarrui.modules.modular.internal.ModularPrivateClass;

public class Main {

	public static void main(String[] args) {
		
		ModularPublicClass.print();
//		ModularPrivateClass.print(); // not possible!

		NonModularPublicClass.print();
		NonModularPrivateClass.print();
		
		printModuleInfo(ModularPublicClass.class);
		
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
