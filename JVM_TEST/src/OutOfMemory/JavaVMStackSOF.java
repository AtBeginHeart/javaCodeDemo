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
 * �����
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
 * ʵ�����������ڵ����߳��£�����������ջ̫֡�󣬻��������ջ����̫С�����ڴ��޷������ʱ��������׳��Ķ���StackOverflowError�쳣��
 * 
 * 
 * */
