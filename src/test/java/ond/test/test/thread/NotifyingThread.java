package ond.test.test.thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import com.sun.jna.Memory;
import com.sun.jna.Pointer;

import ond.test.test.memory.User32Util;

public abstract class NotifyingThread extends Thread {

	int count;
	
	
	private final Set<ThreadCompleteListener> listeners = new CopyOnWriteArraySet<ThreadCompleteListener>();

	public final void addListener(final ThreadCompleteListener listener) {
		listeners.add(listener);
	}

	public final void removeListener(final ThreadCompleteListener listener) {
		listeners.remove(listener);
	}

	private final void notifyListeners() {
		for (ThreadCompleteListener listener : listeners) {
			listener.notifyOfThreadComplete(this,count);
		}
	}
	
	public void run() {
		try {
			doRun();
		} finally {
			notifyListeners();
		}

	}
	
	protected abstract void doRun();

}