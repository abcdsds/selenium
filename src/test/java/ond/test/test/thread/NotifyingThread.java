package ond.test.test.thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import com.sun.jna.Memory;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HWND;

import ond.test.test.memory.User32Util;

public abstract class NotifyingThread extends Thread {


	protected int[] offsets = new int[0x01];
	protected HWND hwnd;
	protected Pointer ThreadProcess;
	protected int ThreadItemAddress;
	protected int ThreadStart;
	protected int ThreadEnd;
	protected List list;
	
	
	private final Set<ThreadCompleteListener> listeners = new CopyOnWriteArraySet<ThreadCompleteListener>();

	public final void addListener(final ThreadCompleteListener listener) {
		listeners.add(listener);
	}

	public final void removeListener(final ThreadCompleteListener listener) {
		listeners.remove(listener);
	}

	private final void notifyListeners() throws InterruptedException {
		for (ThreadCompleteListener listener : listeners) {
			listener.notifyOfThreadComplete(this,hwnd,ThreadProcess);
		}
	}
	
	public void run() {
		try {
			doRun();
		} finally {
			try {
				notifyListeners();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	protected abstract void doRun();

}