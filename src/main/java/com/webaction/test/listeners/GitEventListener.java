package com.webaction.test.listeners;

import java.util.List;

public interface GitEventListener {

	public void onGitCommit(List<Method> methodList);
}