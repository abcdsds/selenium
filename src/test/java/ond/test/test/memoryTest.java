package ond.test.test;

import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;

public class memoryTest {

	final static long baseAddress = 0x10002AFA8L;
    final static int[] offsets = new int[]{0x01};
    
    static Kernel32 kernel32 = (Kernel32) Native.loadLibrary("kernel32",Kernel32.class);
    static User32 user32 = (User32) Native.loadLibrary("user32", User32.class);
    
    public static int PROCESS_VM_READ= 0x0010;
    public static int PROCESS_VM_WRITE = 0x0020;
    public static int PROCESS_VM_OPERATION = 0x0008;
    
	public static void main(String[] args) {
		long main = 0x1F08A060;
		// TODO Auto-generated method stub
		 //int pid = getProcessId("바람의 나라 추억서버 https://memorybaram.com/");
		 //System.out.println(pid);
		 Pointer process = openProcess(PROCESS_VM_READ|PROCESS_VM_WRITE|PROCESS_VM_OPERATION, 34324);
		 System.out.println(process);	
		 
		 System.out.println(findDynAddress(process,offsets,main));
		 long address = findDynAddress(process,offsets,main);
		 Memory mems = (readMemory(process,address,4));
		 
		 int scores = mems.getInt(0);
		 System.out.println(scores);
		 
		 for (int i=1; i<2000000; i++) {
			
			 //long address = findDynAddress(process,offsets,0x1F069CA0);
			 Memory mem = (readMemory(process,main+offsets[0],4));
			 long addressss = findDynAddress(process,offsets,main);
			 
			 int score = mem.getInt(0);
			 long scoreLong = mem.getInt(0);
			 //kernel32.ReadProcessMemory(process, 0x1F069CA0, offsets[0], 4, null);
			 
			 //System.out.println("hex = " +Integer.toHexString(offsets[0]));
			 //System.out.println("value = " + score);

			 System.out.println(scoreLong);
			 if (addressss == 0x1F08A18C) {
				 System.out.println("dddd");
				 System.out.println(main+offsets[0]);
			 }
			 
//			 if (score == 1) {
////				 long a = 0x1F069CA0+offsets[0];
////				 long b = Long.parseLong(Integer.toHexString(6));
////				 if ((readMemory(process,a+b,4).getInt(0)) == 153) {
////					 System.out.println("hex = " +Integer.toHexString(offsets[0]));
////					 System.out.println("value = " + score);
////				 }
//				 System.out.println("hex = " +Integer.toHexString(offsets[0]));
//				 System.out.println("value = " + score);
//			 }
			 
			 offsets[0] = offsets[0]+1;
		 }
		 
		    
	}
	
	public static Memory readMemory(Pointer process, long address, int bytesToRead) {
	    IntByReference read = new IntByReference(0);
	    Memory output = new Memory(bytesToRead);

	    kernel32.ReadProcessMemory(process, address, output, bytesToRead, read);
	    return output;
	}
	
	
	
	public static long findDynAddress(Pointer process, int[] offsets, long baseAddress)
	{

	    long pointer = baseAddress;

	    int size = 4;
	    Memory pTemp = new Memory(size);
	    long pointerAddress = 0;

	    for(int i = 0; i < offsets.length; i++)
	    {
	        if(i == 0)
	        {
	             kernel32.ReadProcessMemory(process, pointer, pTemp, size, null);
	        }

	        pointerAddress = ((pTemp.getInt(0)+offsets[i]));

	        if(i != offsets.length-1)
	             kernel32.ReadProcessMemory(process, pointerAddress, pTemp, size, null);


	    }

	    return pointerAddress;
	}
	
	public static int getProcessId(String window) {
	     IntByReference pid = new IntByReference(0);
	     user32.GetWindowThreadProcessId(user32.FindWindowA(null, window), pid);

	     return pid.getValue();
	}

	public static Pointer openProcess(int permissions, int pid) {
	     Pointer process = kernel32.OpenProcess(permissions, true, pid);
	     return process;
	}

}
