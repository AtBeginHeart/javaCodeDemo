package threadTest.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//http://www.cnblogs.com/dolphin0520/p/3920407.html
/**
 *https://blog.csdn.net/zxysshgood/article/details/78872907
 *ThreadLocal 正如他的中文翻译线程本地变量所说，顾明思义，就是每个线程自己的本地变量。简单的来说，就是在多线程
 *的程序中个，我们一般会申请共享变量。给所有的线程使用。但是很多情况下，每个线程会对共享变量进行改变，比如，一个String
 *类型的static变量，线程将String赋值为“线程一”，然后在跑其他的逻辑的时候，第二个线程将String又改成了“线程二"，
 *那马就会出现问题，线程一跑到后面对该String进项使用的时候，就发现值已经被改变了。
 *ThreadLocal的价值就体现了出来。就是在每个线程里面对变量单独记录保存。这样，线程而对String的改变就是对自己内部改变，不会影响别的线程中的String值 
 * */
public class ThreadLocalUtil {
    private static ThreadLocal<Object> threadLocal = new ThreadLocal<Object>();
    public static String baseString = "";
    public static ExecutorService executorService = Executors.newFixedThreadPool(4);
    private static void setTLocal(String value) {
    	threadLocal.set(value);
    }
    private static String getTlocal() {
		return "ThreadLocal保存:"+Thread.currentThread().getName()+" : "+ threadLocal.get();
	}
	
	private static void setBLocal(String value) {
		baseString=value;
	}
 
	private static String getBlocal() {
		return "Baseocal保存:   "+Thread.currentThread().getName()+" : "+baseString;
	}

	public static void main(String[] args)throws InterruptedException{
		Thread thread = new Thread(new Runnable() {
			public void run() {
				setTLocal("子线程一保存变量");
				try {
					//睡眠一秒，模拟在处理某些程序
					Thread.sleep(1000);
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(getTlocal());
			}
		});
		
		Thread thread2 = new Thread(new Runnable() {
			public void run() {
				setTLocal("子线程而保存变量");
				System.out.println(getTlocal());
			}
		});
		executorService.execute(thread);
		executorService.execute(thread2);
		setTLocal("主线程保存变量");
		System.out.println(getTlocal());
		Thread.sleep(1000);
		Thread thread3=new Thread(new Runnable() {
			public void run() {
				setBLocal("子线程一保存变量");
				try {
					//睡眠一秒，模拟在处理某些程序
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.err.println(getBlocal());
			}
		});
		
		Thread thread4=new Thread(new Runnable() {
			public void run() {
				setBLocal("子线程二保存变量");
				System.err.println(getBlocal());
			}
		});
		
		executorService.execute(thread3);
		executorService.execute(thread4);
		
		setBLocal("主线程保存变量");
		System.err.println(getBlocal());
	}
	/**
	 * 看到结果，ThreadLocal保存的变量没有因为其他原因改变。但是Basecal的变了，因为当执行的时候，主线程执行完了
	 * 输出，线程一将Sring修改为“子线程一保存变量”，进入睡眠，线程而进行修改，改String为子线程二，这时候输出的时候，
	 * 就变成了两个子线程二了。
	 * 如此就体现了ThreadLocal的价值，就是保证线程之间的变量处理互不影响。
	 * 
	 * */
	
	
	/**
	 * 
	 *   2.ThreadLocal是怎么实现的线程本地
                   从代码上来看看怎么实现了这个功能吧。
                    ThreadLocal内部主要的几个方法就是   public T get()， public void set(T value)， public void remove()，分别是获取本地变量，保存本地变量和删除变量
                   从get方法开始看
                    public T get()：
                    
public T get() {
        Thread t = Thread.currentThread();
        ThreadLocalMap map = getMap(t);
        if (map != null) {
            ThreadLocalMap.Entry e = map.getEntry(this);
            if (e != null) {
                @SuppressWarnings("unchecked")
                T result = (T)e.value;
                return result;
            }
        }
        return setInitialValue();

	 * 
	 *  ThreadLocalMap getMap(Thread t) {
        return t.threadLocals;

	 * */
	/*
	 * 
	 * 
	 *   很简单，就是先获取当前正在执行的线程，获取当前执行的线程的ThreadLocalMap(每个Thread都有一个名字叫threadLocals  类型为ThreadLocal.ThreadLocalMap的变量)。如果，当前Thread的ThreadLocalMap不为空，即已经通过set或者initialValue初始化过，则直接返回有关对象类型。
               如果当前线程的ThreadLocalMap为空，则处理setInitialValue方法。
                
private T setInitialValue() {
        T value = initialValue();
        Thread t = Thread.currentThread();
        ThreadLocalMap map = getMap(t);
        if (map != null)
            map.set(this, value);
        else
            createMap(t, value);
        return value;
    }
                       该方法的作用是对ThreadLocalMap进行初始化。首先如果有重写initialValue方法，则将重写的初始化对象给T，然后在尝试查一下当前线程ThreadLocalMap有没有值，有值则将初始化的值也set进去，没有值得话就创建一个新的map,把当前线程和T当做参数传递给createMap。
               
  void createMap(Thread t, T firstValue) {
        t.threadLocals = new ThreadLocalMap(this, firstValue);
    }
            createMap很简单，就是把当前线程的ThreadLocalMap用初始化的值保存一下。
               
              public void set(T value)
              
    public void set(T value) {
        Thread t = Thread.currentThread();
        ThreadLocalMap map = getMap(t);
        if (map != null)
            map.set(this, value);
        else
            createMap(t, value);
    }
         看完了setInitialValue方法，其实这个就太简单了，只是把初始化的那个值换成了set 的value值，保存在了ThreadLocalMap里面，其他没有变化。

                    public void remove()
               
     public void remove() {
         ThreadLocalMap m = getMap(Thread.currentThread());
         if (m != null)
             m.remove(this);
     }
                   remove方法也非常简单，就是获取当前线程的ThreadLocalMap，如果不为空的话，把他清除掉。              
	 * 
	 * */

}
