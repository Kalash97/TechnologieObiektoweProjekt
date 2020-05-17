package com.view;

import java.util.List;

public interface View {

	public void print(String msg);
	
	public String read();
	
	public void print(List<Object> list);
	
	public void print(Object[] array);

	public long getValidNumber(String string);

	public long getValidNumberCancellable(String string);
	
}