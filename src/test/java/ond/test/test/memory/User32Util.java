package ond.test.test.memory;

import java.awt.Point;
import java.util.HashMap;
import java.util.Random;

import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.LPARAM;
import com.sun.jna.platform.win32.WinDef.RECT;
import com.sun.jna.platform.win32.WinDef.WPARAM;
import com.sun.jna.ptr.IntByReference;

import ond.test.test.thread.NotifyingThread;

public class User32Util {

	protected static int PROCESS_VM_READ = 0x0010;
	protected static int PROCESS_VM_WRITE = 0x0020;
	protected static int PROCESS_VM_OPERATION = 0x0008;

	protected static Kernel32 kernel32 = (Kernel32) Native.loadLibrary("kernel32", Kernel32.class);
	private static User32 user32 = (User32) Native.loadLibrary("user32", User32.class);

	protected static int WM_KEYDOWN = User32.INSTANCE.WM_KEYDOWN;
	protected static int WM_KEYUP = User32.INSTANCE.WM_KEYUP;
	protected static int WM_CHAR = User32.INSTANCE.WM_CHAR;
	protected static int WM_LBUTTONDOWN = 0x0201;
	protected static int WM_LBUTTONUP = 0x0202;

	private static int VK_A = 0x20;

	protected final static long baseAddress = 0x10002AFA8L;
	protected final static int[] offsets = new int[] { 0x00 };
	protected final static int questMessage = 0x001734F4;
	protected final static int enterMessage = 0x00183590;
	protected final static int ocrLocation = 0x00173176;
	protected final static int charLocation = 0x0055DB04;

	private static int locationX = 0;
	private static int locationY = 0;
	private static RECT rect;
	protected final static int WM_SPACEBAR = 0x20;
	protected final static int WM_ENTER = 0x0D; // 숫자패드 엔터 0x6C
	protected final static int WM_DOWN = 0x28;
	protected final static int WM_UP = 0x26;
	protected final static int WM_RIGHT = 0x27;
	protected final static int WM_LEFT = 0x25;
	protected final static int WM_ESC = 0x1B;
	protected final static int WM_SINGLE_QUTOR = 0xDE;
	protected final static int WM_TAB = 0x09;
	protected final static int WM_NUM1 = 0x31;
	protected final static int WM_COMMA = 0xBC;
	private static int lastMove = 0;
	public static int addValue = 2;

	public static void click(HWND hwnd, int msg, WPARAM wParam, LPARAM lParam) {

		User32.INSTANCE.PostMessage(hwnd, 0x201, wParam, lParam);
		User32.INSTANCE.PostMessage(hwnd, 0x202, wParam, lParam);
	}

	public static void pressKey(HWND hwnd, WPARAM wParam) {
		User32.INSTANCE.PostMessage(hwnd, WM_KEYDOWN, wParam, new WinDef.LPARAM(0));
		User32.INSTANCE.PostMessage(hwnd, WM_KEYUP, wParam, new WinDef.LPARAM(0));
	}

	public static void pressNum(HWND hwnd, WPARAM wParam) {

		User32.INSTANCE.PostMessage(hwnd, WM_KEYDOWN, wParam, new WinDef.LPARAM(0));
	}

	public static void pressKeyForSpell(HWND hwnd, WPARAM wParam) {
		pressKey(hwnd, wParam);
	}

	public static LPARAM makeLparam(long val) {
		return new WinDef.LPARAM(val);
	}

	public static WPARAM makeWparam(long val) {
		return new WinDef.WPARAM(val);
	}

	public static Pointer openProcess(int permissions, int pid) {
		Pointer process = kernel32.OpenProcess(permissions, true, pid);
		return process;
	}

	public static int MAKELPARAM(int p, int p_2) {
		return ((p_2 << 16) | (p & 0xFFFF));
	}

	public static Memory readMemory(Pointer process, long address, int bytesToRead) {
		IntByReference read = new IntByReference(0);
		Memory output = new Memory(bytesToRead);

		kernel32.ReadProcessMemory(process, address, output, bytesToRead, read);
		return output;
	}

	public static Memory writeMemory(Pointer process, long address, int bytesToRead, int changeVal) {
		IntByReference initWritten = new IntByReference(0);
		Memory toWrite = new Memory(bytesToRead);
		toWrite.setInt(0, changeVal);

		kernel32.WriteProcessMemory(process, address, toWrite, bytesToRead, initWritten);
		return toWrite;
	}

	public static long findDynAddress(Pointer process, int[] offsets, long baseAddress) {

		long pointer = baseAddress;

		int size = 4;
		Memory pTemp = new Memory(size);
		long pointerAddress = 0;

		for (int i = 0; i < offsets.length; i++) {
			if (i == 0) {
				kernel32.ReadProcessMemory(process, pointer, pTemp, size, null);
			}

			pointerAddress = ((pTemp.getInt(0) + offsets[i]));

			if (i != offsets.length - 1)
				kernel32.ReadProcessMemory(process, pointerAddress, pTemp, size, null);

		}

		return pointerAddress;
	}

	public static int getProcessId(HWND hwnd) {

		// IntByReference pid = new IntByReference(0);
		// user32.GetWindowThreadProcessId(user32.FindWindow(null, window), pid);

		IntByReference pid = new IntByReference();
		User32.INSTANCE.GetWindowThreadProcessId(hwnd, pid);

		return pid.getValue();
	}

	public static RECT getScreenSize(HWND hwnd) {

		if (rect == null) {
			rect = new RECT();
		}

		User32.INSTANCE.GetWindowRect(hwnd, rect);
		System.out.println(rect.right - rect.left);
		System.out.println(rect.bottom - rect.top);
		return rect;
	}

	public static void runMove(HWND hwnd, Pointer process, long address, long address2, int x, int y, int doAct)
			throws InterruptedException {

		////////////////////////////////////////////////////////////
		Random rand = new Random();
		boolean firstAble = rand.nextBoolean(); // true X먼저 , false Y먼저
		int goalLocationX = x;
		int goalLocationY = y;
		int tempLocationX = 0;
		int tempLocationY = 0;
		int stuckTempCntUpX = 0;
		int stuckTempCntDownX = 0;
		int stuckTempCntUpY = 0;
		int stuckTempCntDownY = 0;
		int stuckTempLocationX = 0;
		int stuckTempLocationY = 0;
		int stuckCntX = 0;
		int stuckCntY = 0;
		////////////////////////////////////////////////////////////

		while (true) {

			// int lastMove = 0; // 0 위 1 아래 2 좌 3 우
			////////////////////////////////////////////////////////////
			Memory mem2 = (readMemory(process, address, 4));
			int charLocationX = mem2.getInt(0);

			Memory mem3 = (readMemory(process, address2, 4));
			int charLocationY = (mem3.getInt(0));

			System.out.println("현재위치 X =" + charLocationX + " Y = " + charLocationY);

			////////////////////////////////////////////////////////////

			if ((goalLocationX == charLocationX) && (goalLocationY == charLocationY)) {
				if (doAct > 0) {
					multiplePressKey(hwnd, doAct, 4);
				}
				break;
			}

			System.out.println(stuckCntX + " : " + stuckCntY);

			if (firstAble) {

				if (charLocationX == tempLocationX && charLocationY == tempLocationY) {
					stuckCntX++;
					firstAble = false;

					if (stuckCntX > 5) {

						System.out.println("X 막힘!  X = " + stuckCntX);

						stuckCntX = 0;
						stuckTempLocationX = charLocationX;
						switch (rand.nextInt(2)) {
						case 0:
							multiplePressKey(hwnd, WM_UP, 3);

							if (goalLocationX < charLocationX) {

								multiplePressKey(hwnd, WM_LEFT, 3);
							}

							if (goalLocationX > charLocationX) {

								multiplePressKey(hwnd, WM_RIGHT, 3);
							}

							stuckTempCntUpX++;
							break;
						case 1:
							multiplePressKey(hwnd, WM_DOWN, 3);

							if (goalLocationX < charLocationX) {

								multiplePressKey(hwnd, WM_LEFT, 3);
							}

							if (goalLocationX > charLocationX) {

								multiplePressKey(hwnd, WM_RIGHT, 3);
							}

							stuckTempCntDownX++;
							break;
						}
					}

				} else {
					stuckCntX = 0;
					stuckCntY = 0;
				}

				if (goalLocationX < charLocationX) {

					if (lastMove == 2) {
						multiplePressKey(hwnd, WM_LEFT, 2);
					} else {
						multiplePressKey(hwnd, WM_LEFT, 3);
					}
				}

				if (goalLocationX > charLocationX) {
					if (lastMove == 3) {
						multiplePressKey(hwnd, WM_RIGHT, 2);
					} else {
						multiplePressKey(hwnd, WM_RIGHT, 3);
					}
				}

				if (goalLocationX == charLocationX) {
					firstAble = false;
				}

			} else {

				if (charLocationX == tempLocationX && charLocationY == tempLocationY) {
					stuckCntY++;
					firstAble = true;

					if (stuckCntY > 5) {

						System.out.println("Y 막힘!  Y = " + stuckCntY);

						stuckCntY = 0;
						switch (rand.nextInt(2)) {
						case 0:
							multiplePressKey(hwnd, WM_RIGHT, 3);

							if (goalLocationY < charLocationY) {

								multiplePressKey(hwnd, WM_UP, 3);
							}

							if (goalLocationY > charLocationY) {

								multiplePressKey(hwnd, WM_DOWN, 3);
							}

							break;
						case 1:
							multiplePressKey(hwnd, WM_LEFT, 3);

							if (goalLocationY < charLocationY) {

								multiplePressKey(hwnd, WM_UP, 3);
							}

							if (goalLocationY > charLocationY) {

								multiplePressKey(hwnd, WM_DOWN, 3);
							}

							break;
						}
					}
				} else {
					stuckCntY = 0;
					stuckCntX = 0;
				}

				if (goalLocationY < charLocationY) {
					if (lastMove == 0) {
						multiplePressKey(hwnd, WM_UP, 2);
					} else {
						multiplePressKey(hwnd, WM_UP, 3);
					}
				}

				if (goalLocationY > charLocationY) {
					if (lastMove == 1) {
						multiplePressKey(hwnd, WM_DOWN, 2);
					} else {
						multiplePressKey(hwnd, WM_DOWN, 3);
					}
				}

				if (goalLocationY == charLocationY) {

					firstAble = true;
				}

			}

			tempLocationX = charLocationX;
			tempLocationY = charLocationY;
		}

	}

	public static void multiplePressKey(HWND hwnd, long value, int count) throws InterruptedException {

		if (value == WM_UP) {
			System.out.println("Up " + count);
			lastMove = 0;
		}
		if (value == WM_DOWN) {
			System.out.println("Down " + count);
			lastMove = 1;
		}
		if (value == WM_LEFT) {
			System.out.println("Left " + count);
			lastMove = 2;
		}
		if (value == WM_RIGHT) {
			System.out.println("Right " + count);
			lastMove = 3;
		}
		System.out.println("press Key " + value + " : count " + count);
		for (int i = 0; i < count; i++) {
			Thread.sleep(100);
			pressKey(hwnd, new WinDef.WPARAM(value));
		}

	}

	public static void clickMessage(HWND hwnd, Pointer process, int clickx, int clicky) throws InterruptedException {

		Point point = new Point(clickx, clicky);
		int clickvalue = MAKELPARAM(point.x, point.y);
		WinDef.WPARAM wParam = new WinDef.WPARAM(0);
		WinDef.LPARAM lParam = new WinDef.LPARAM(clickvalue);
		////////////////////////////////////////////////////////////

		click(hwnd, clickvalue, wParam, lParam);
		multiplePressKey(hwnd, WM_TAB, 5);
		multiplePressKey(hwnd, WM_ENTER, 1);

		// -948648068
		// -1409286112
		// 2149432
		// 2142868 엔터 다운 엔터
		// -1053702676
		// -951800264
		// -959447008 엔터 다운 엔터
		// 2148164

		Thread.sleep(1000);

		while (true) {
			Memory mem = (readMemory(process, enterMessage, 4));
			int resNum = mem.getInt(0);
			System.out.println(resNum);

			if (resNum == -948648068 || resNum == -1409286112 || resNum == 2149432 || resNum == -1053702676
					|| resNum == -951800264) {
				System.out.println(resNum);
				Thread.sleep(100);
				pressKey(hwnd, new WinDef.WPARAM(WM_ENTER));
			}
			if (resNum == 2142868 || resNum == -959447008) {
				pressKey(hwnd, new WinDef.WPARAM(WM_ENTER));
				Thread.sleep(500);
				pressKey(hwnd, new WinDef.WPARAM(WM_DOWN));
				Thread.sleep(100);
				pressKey(hwnd, new WinDef.WPARAM(WM_ENTER));
			}

			if (resNum == 2148164) {
				Thread.sleep(100);
				pressKey(hwnd, new WinDef.WPARAM(WM_ENTER));
				System.out.println("종료 " + resNum);
				break;
			}
		}

	}

}
