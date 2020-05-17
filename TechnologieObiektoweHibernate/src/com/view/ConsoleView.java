package com.view;

import java.util.List;
import java.util.Scanner;

import com.utils.exceptions.OperationCancelException;

public class ConsoleView implements View {

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
		for (int i = 0; i < array.length; i++) {
			System.out.println(i + ":" + array[i]);
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
