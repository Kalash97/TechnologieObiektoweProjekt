package com.View;

import java.util.List;
import java.util.Scanner;

public class ConsoleView implements View{

	private Scanner scanner = new Scanner(System.in);

	public void print(String msg) {
		System.out.println(msg);
	}
	
	public String read() {
		return scanner.nextLine();
	}

	@Override
	public void print(List<Object> list) {
		print(list.toArray());
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		scanner.close();
	}

	@Override
	public void print(Object[] array) {
		for(int i=0; i<array.length; i++) {
			System.out.println(i+":"+array[i]);
		}
	}
	
}
