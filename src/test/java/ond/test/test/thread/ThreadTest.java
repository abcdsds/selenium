package ond.test.test.thread;

import java.util.HashMap;
import java.util.List;

import com.sun.jna.Memory;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HWND;

import ond.test.test.memory.User32Util;

public class ThreadTest extends NotifyingThread {

		
	public ThreadTest (HWND hwnd, Pointer process, int itemAddress , int start , int end, List list) {
		super.hwnd = hwnd;
		super.ThreadProcess = process;
		super.ThreadItemAddress = itemAddress;
		super.ThreadStart = start;
		super.ThreadEnd = end;
		super.list = list;
	}
		
	
	@Override
	public void doRun() {
		//int[] offsets = new int[0x01];
		
		for (int i = ThreadStart; i <= ThreadEnd; i++) {

			Memory mem = (User32Util.readMemory(ThreadProcess, ThreadItemAddress + offsets[0], 4));
			
			if (mem.getInt(0) == 5433508) {

				
				HashMap<String, Integer> locationXY = new HashMap<String, Integer>();

				Memory memLocationX = (User32Util.readMemory(ThreadProcess, ThreadItemAddress + offsets[0] + 0x0EC, 4));
				Memory memLocationY = (User32Util.readMemory(ThreadProcess, ThreadItemAddress + offsets[0] + 0x0F0, 4));

				Memory memItemNum1 = (User32Util.readMemory(ThreadProcess, ThreadItemAddress + offsets[0] + 0x048, 4));
				Memory memItemNum2 = (User32Util.readMemory(ThreadProcess, ThreadItemAddress + offsets[0] + 0x04C, 4));

				
				System.out.print(ThreadEnd+" X = " + memLocationX.getInt(0));
				System.out.print(" Y = " + memLocationY.getInt(0));

				System.out.println();

				if (memItemNum1.getInt(0) == 6 && memItemNum2.getInt(0) == 5) {
					System.out.println("µµ≈‰∏Æ ");
					locationXY.put("X", memLocationX.getInt(0));
					locationXY.put("Y", memLocationY.getInt(0));
					list.add(locationXY);
				}


			}

			if (i == ThreadEnd) {
				System.out.println(Integer.toHexString(offsets[0]));
			}
			
			offsets[0] = offsets[0] + User32Util.addValue;

		}
	}
}
