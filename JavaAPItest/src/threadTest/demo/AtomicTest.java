package threadTest.demo;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Atomic ���������������
 * @author hsz
 * */
public class AtomicTest {

	public AtomicTest() {
		// TODO Auto-generated constructor stub
	}
	public static AtomicInteger race = new AtomicInteger(0);
	
	public static void increase() {
		race.incrementAndGet();
	}
	private static final int THREADS_COUNT = 20;
	
	public static void main(String[] args)throws Exception{
		Thread[] threads = new Thread[THREADS_COUNT];
		for (int i = 0; i<THREADS_COUNT;i++) {
			threads[i] = new Thread(new Runnable() {
				public void run() {
					for(int i = 0;i<10000;i++) {
						increase();
					}
				}
			});
			threads[i].start();
		}
		while(Thread.activeCount() > 1)
			Thread.yield();
		System.out.println(race);
	}

}
/**
 * ���н��200000
 * ʹ��AtomicInteger ���� int�󣬳����������ȷ�Ľ����һ�ж�Ҫ�鹦��incrementAndGet()������ԭ���ԡ�
 * ����ʵ����ʵ�ǳ��򵥣�������嵥������ʾ��
 * 
 * 
 * */
/**
 * 
 * Atomically increment by one the current value.
 * @return the previous value
 * */
/*public final int getAndIncrement() {
	for(;;) {
		int current = get();
		int next = current + 1;
		if(compareAndSet(current,next))
			return current;
	}
}*/
/**
 * incrementAndGet()������һ������ѭ���У����ϳ��� ��һ���ȵ�ǰֵ��1����ֵ��ֵ���Լ������ʧ���ˣ�
 * ��˵����ִ�С���ȡ-���á�������ʱ��ֵ�Ѿ������޸ģ������ٴ�ѭ��������һ�β�����ֱ�����óɹ�Ϊֹ��
 * 
 * */
