package com.View;

import java.util.List;

public interface View {

	public void print(String msg);
	
	public String read();
	
	public void print(List<Object> list);
	
	public void print(Object[] array);
}
