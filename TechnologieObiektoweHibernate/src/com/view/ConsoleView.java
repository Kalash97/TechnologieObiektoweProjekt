package com.view;

import java.util.List;
import java.util.Scanner;

import com.utils.exceptions.OperationCancelException;

public class ConsoleView implements View {

	private Scanner scanner = new Scanner(System.in);

	public void print(String msg) {
		System.out.println(msg);
	}
	
	public void printDelimeter(){
		print("");
	}
	
	@Override
	public void printWithEndingDelimeter(String msg) {
		print(msg);
		printDelimeter();
	}

	public String read() {
		return scanner.nextLine();
	}
	
	@Override
	public String readProperty(String message) {
		print(message);
		return read();
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
		for (int i = 0; i < array.length; i++) {
			print(i + ":" + array[i]);
		}
	}

	@Override
	public long getValidNumber(String string) {
		print(string);
		while (true) {
			String line = scanner.nextLine();
			try {
				return Long.parseLong(line);
			} catch (NumberFormatException e) {
				print("Podaj prawid³owy numer");
			}
		}
	}

	@Override
	public long getValidNumberCancellable(String string) {
		print(string);
		while (true) {
			String line = scanner.nextLine();
			if("cancel".equalsIgnoreCase(line)) {
				throw new OperationCancelException("Canceling action");
			}
			try {
				return Long.parseLong(line);
			} catch (NumberFormatException e) {
				print("Podaj prawid³owy numer");
			}
		}
	}
}
