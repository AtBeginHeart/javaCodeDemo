package threadTest.demo;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Atomic 变量自增运算测试
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
 * 运行结果200000
 * 使用AtomicInteger 代替 int后，程序输出了正确的结果，一切都要归功于incrementAndGet()方法的原子性。
 * 它的实现其实非常简单，如代码清单如下所示。
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
 * incrementAndGet()方法在一个无线循环中，不断尝试 将一个比当前值大1的新值赋值给自己。如果失败了，
 * 那说明在执行“获取-设置”操作的时候值已经有了修改，于是再次循环镜像下一次操作，直到设置成功为止。
 * 
 * */
