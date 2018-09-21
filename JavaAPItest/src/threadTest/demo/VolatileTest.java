package threadTest.demo;
/**
 * volatile 变量自增运算测试
 * @author hsz
 * */
public class VolatileTest {
	public static volatile int race = 0;
	public static void increase() {
		race++;
	}
	private static final int THREADS_COUNT = 20;
	public static void main(String[] args) {
		Thread[] threads = new Thread[THREADS_COUNT];
		for(int i = 0; i<THREADS_COUNT;i++) {
			threads[i] = new Thread(new Runnable() {
				@Override
				public void run() { 
					for(int i = 0;i<10000;i++) {
						increase();
					}
				}
			});
			threads[i].start();
		}
		//等待所有累加线程都结束
		while(Thread.activeCount()>1) Thread.yield();
		System.out.println(race);	
	}

}
/**
 * 
 * 这段代码发起了20个线程，每个线程对race变量进行10000次自增操作，如果这段代码能够正确并发的话。最后输出的结果应该是20000.读者运行完这段代码之后，并
 * 不会获得期望的结果，而且会发现每次运行程序，输出的结果都不一样，都是一个小于200000的数字，这是为什么呢？
 * 问题就出现在自增运算“race++”只中，我们用javap反编译这段代码后，会得到代码清单12-2，发现只有一行代码的increase()方法在Class文件中是由4条字节码
 * 指令构成的(return 指令不是由race++产生的，这条指令可以不算)，从字节码层面上很容易就分析出并发失败的原因了：当getstatic指令吧race的值渠道操作栈顶是，
 * volatile 关键字保证了race的值在此时是正确的，但是在执行iconst_1、iadd这些指令的时候，其他线程可能已经把race的值加大了，而在操作栈顶的值就变成了过期的
 * 的数据，所以 putstatic指令执行后就可能把较小的race值同步回主内存之中。
 * */

/**
 * 
 * 由于volatile变量只能保证可见性，在不符合一下两条规则的运算场景中，我们仍然要通过加锁(使用synchronized 或 java.util.concurrent中的原子类)来保证
 * 原子性。
 * */

