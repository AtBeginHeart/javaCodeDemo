package OutOfMemory;

import java.util.ArrayList;
import java.util.List;

//TODO ��δ��벻Ҫ����ִ�У��п��ܻὫ���Ը����ģ���Ҫ�ǿ�������Ե��������ڴ棬��ռ�������ڴ���Դ��
//TODO ���Խ�����������
/**
 * VM Args: -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 * java.lang.Object[]
 * */
public class HeadOOM {
	
    static class OOMObject{
    	
    }
	public HeadOOM() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) {
		List<OOMObject> list = new ArrayList<OOMObject>();
		
		while(true) {
			list.add(new OOMObject());
		}
	}

}
/**
 * ���н��:
 * java.lang.OutofMemoryError:Java heap space
 * Dumping heap to java_pid3404.hprof ...
 * Heap dump file created [22045981 bytes in 0.663 secs]
java.lang.OutOfMemoryError: Java heap space
Dumping heap to D:\job ...
Unable to create D:\job: File exists
Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
	at java.util.Arrays.copyOf(Unknown Source)
	at java.util.Arrays.copyOf(Unknown Source)
	at java.util.ArrayList.grow(Unknown Source)
	at java.util.ArrayList.ensureExplicitCapacity(Unknown Source)
	at java.util.ArrayList.ensureCapacityInternal(Unknown Source)
	at java.util.ArrayList.add(Unknown Source)
	at OutOfMemory.HeadOOM.main(HeadOOM.java:23)

 * */
