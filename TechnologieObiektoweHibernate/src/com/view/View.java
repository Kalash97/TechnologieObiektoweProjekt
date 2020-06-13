package com.view;

import java.util.List;

public interface View {

	public void print(String msg);
	
	public void printSubMessage(String msg);
	
	public void printDelimeter();
	
	public void printWithEndingDelimeter(String msg);
	
	public String read();
	
	public String readProperty(String message);
	
	public void print(List<Object> list);
	
	public void print(Object[] array);

	public long getValidNumber(String string);

	public long getValidNumberCancellable(String string);
}
