package com.webaction.test.listeners;

import java.util.List;

public interface TestListener {

	public void onTestCompleted(List<Method> methodList);
}