package com.webaction.test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.List;
import java.util.Iterator;

public class NotificationManager {

	private static NotificationManager manager;
	private BlockingQueue<GitEventListener> gitEventQueue;
	private BlockingQueue<TestListener> testEventQueue;
	private final int BLOCKING_QUEUE_SIZE = 10;

	private NotificationManager() {
		gitEventQueue = new ArrayBlockingQueue<GitEventListener>(BLOCKING_QUEUE_SIZE);
		testEventQueue = new ArrayBlockingQueue<TestListener>(BLOCKING_QUEUE_SIZE);
	}

	public static synchronized NotificationManager getInstance() {
		if(manager == null) 
			manager = new NotificationManager();
		return manager;
	}	

	public synchronized void addEventListener(Object object) {
		if(object instanceof GitEventListener)
			gitEventQueue.add((GitEventListener) object);
		else 
			testEventQueue.add((TestListener) object);
	}

	public synchronized void removeEventListener(Object object) {
		if(object instanceof GitEventListener) 
			gitEventQueue.remove((GitEventListener) object);
		else testEventQueue.remove((TestListener) object);
	}

	public synchronized void onGitEvent(List<Method> methodList) {
		Iterator<GitEventListener> it = gitEventQueue.iterator();
    	while(it.hasNext()){
      			it.next().onGitCommit(methodList);
    	}
	}

	public synchronized void onTestEvent(List<Method> methodList) {
		Iterator<TestListener> it = testEventQueue.iterator();
    	while(it.hasNext()){
      		it.next().onTestCompleted(methodList);
    	}
	}
}