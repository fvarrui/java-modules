package io.github.fvarrui.modules.non_modular;

import io.github.fvarrui.modules.non_modular.internal.NonModularPrivateClass;

public class NonModularPublicClass {
	
	public static void print() {
		System.out.println("I'm public in an automatic module!");
		NonModularPrivateClass.print();
	}

}
