package io.github.fvarrui.modules.modular;

import io.github.fvarrui.modules.modular.internal.ModularPrivateClass;

public class ModularPublicClass {
	
	public static void print() {
		System.out.println("I'm public in a module!");
		ModularPrivateClass.print();
	}

}
