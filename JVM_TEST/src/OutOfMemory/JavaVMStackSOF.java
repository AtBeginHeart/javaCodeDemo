package OutOfMemory;

public class JavaVMStackSOF {
    
	private int stackLength = 1;
	public void stackLeak() {
		stackLength++;
		stackLeak();
	}
    public static void main(String[] args) throws Throwable{
    	JavaVMStackSOF oom = new JavaVMStackSOF();
    	try {
    		oom.stackLeak();
    	}catch(Throwable e) {
    		System.out.println("stack length:" + oom.stackLength);
    		throw e;
    	}
    }

}
/**
 * 结果：
 * Exception in thread "main" stack length:6766
java.lang.StackOverflowError
	at OutOfMemory.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:7)
	at OutOfMemory.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:8)
	at OutOfMemory.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:8)
	at OutOfMemory.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:8)
	at OutOfMemory.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:8)
	at OutOfMemory.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:8)
	at OutOfMemory.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:8)
	at OutOfMemory.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:8)
	at OutOfMemory.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:8)
	at OutOfMemory.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:8)
	at OutOfMemory.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:8)
	at OutOfMemory.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:8)
	at OutOfMemory.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:8)
	at OutOfMemory.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:8)
	at OutOfMemory.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:8)
	at OutOfMemory.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:8)
	at OutOfMemory.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:8)
	at OutOfMemory.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:8)
	at OutOfMemory.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:8)
	at OutOfMemory.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:8)
	at OutOfMemory.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:8)
	at OutOfMemory.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:8)
	at OutOfMemory.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:8)
	at OutOfMemory.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:8)
	at OutOfMemory.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:8)
	at OutOfMemory.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:8)
	at OutOfMemory.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:8)
	at OutOfMemory.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:8)
 * 
 * */

/**
 * 实验结果表明：在单个线程下，无论是由于栈帧太大，还是虚拟机栈容量太小，当内存无法分配的时候，虚拟机抛出的都是StackOverflowError异常。
 * 
 * 
 * */
