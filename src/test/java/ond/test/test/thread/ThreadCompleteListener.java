package ond.test.test.thread;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HWND;

public interface ThreadCompleteListener {
	void notifyOfThreadComplete(final Thread thread, HWND hwnd, final Pointer process) throws InterruptedException;
}