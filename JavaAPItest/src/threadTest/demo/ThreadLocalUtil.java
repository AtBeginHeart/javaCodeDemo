package threadTest.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//http://www.cnblogs.com/dolphin0520/p/3920407.html
/**
 *https://blog.csdn.net/zxysshgood/article/details/78872907
 *ThreadLocal �����������ķ����̱߳��ر�����˵������˼�壬����ÿ���߳��Լ��ı��ر������򵥵���˵�������ڶ��߳�
 *�ĳ����и�������һ������빲������������е��߳�ʹ�á����Ǻܶ�����£�ÿ���̻߳�Թ���������иı䣬���磬һ��String
 *���͵�static�������߳̽�String��ֵΪ���߳�һ����Ȼ�������������߼���ʱ�򣬵ڶ����߳̽�String�ָĳ��ˡ��̶߳�"��
 *����ͻ�������⣬�߳�һ�ܵ�����Ը�String����ʹ�õ�ʱ�򣬾ͷ���ֵ�Ѿ����ı��ˡ�
 *ThreadLocal�ļ�ֵ�������˳�����������ÿ���߳�����Ա���������¼���档�������̶߳���String�ĸı���Ƕ��Լ��ڲ��ı䣬����Ӱ�����߳��е�Stringֵ 
 * */
public class ThreadLocalUtil {
    private static ThreadLocal<Object> threadLocal = new ThreadLocal<Object>();
    public static String baseString = "";
    public static ExecutorService executorService = Executors.newFixedThreadPool(4);
    private static void setTLocal(String value) {
    	threadLocal.set(value);
    }
    private static String getTlocal() {
		return "ThreadLocal����:"+Thread.currentThread().getName()+" : "+ threadLocal.get();
	}
	
	private static void setBLocal(String value) {
		baseString=value;
	}
 
	private static String getBlocal() {
		return "Baseocal����:   "+Thread.currentThread().getName()+" : "+baseString;
	}

	public static void main(String[] args)throws InterruptedException{
		Thread thread = new Thread(new Runnable() {
			public void run() {
				setTLocal("���߳�һ�������");
				try {
					//˯��һ�룬ģ���ڴ���ĳЩ����
					Thread.sleep(1000);
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(getTlocal());
			}
		});
		
		Thread thread2 = new Thread(new Runnable() {
			public void run() {
				setTLocal("���̶߳��������");
				System.out.println(getTlocal());
			}
		});
		executorService.execute(thread);
		executorService.execute(thread2);
		setTLocal("���̱߳������");
		System.out.println(getTlocal());
		Thread.sleep(1000);
		Thread thread3=new Thread(new Runnable() {
			public void run() {
				setBLocal("���߳�һ�������");
				try {
					//˯��һ�룬ģ���ڴ���ĳЩ����
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.err.println(getBlocal());
			}
		});
		
		Thread thread4=new Thread(new Runnable() {
			public void run() {
				setBLocal("���̶߳��������");
				System.err.println(getBlocal());
			}
		});
		
		executorService.execute(thread3);
		executorService.execute(thread4);
		
		setBLocal("���̱߳������");
		System.err.println(getBlocal());
	}
	/**
	 * ���������ThreadLocal����ı���û����Ϊ����ԭ��ı䡣����Basecal�ı��ˣ���Ϊ��ִ�е�ʱ�����߳�ִ������
	 * ������߳�һ��Sring�޸�Ϊ�����߳�һ���������������˯�ߣ��̶߳������޸ģ���StringΪ���̶߳�����ʱ�������ʱ��
	 * �ͱ�����������̶߳��ˡ�
	 * ��˾�������ThreadLocal�ļ�ֵ�����Ǳ�֤�߳�֮��ı���������Ӱ�졣
	 * 
	 * */
	
	
	/**
	 * 
	 *   2.ThreadLocal����ôʵ�ֵ��̱߳���
                   �Ӵ�������������ôʵ����������ܰɡ�
                    ThreadLocal�ڲ���Ҫ�ļ�����������   public T get()�� public void set(T value)�� public void remove()���ֱ��ǻ�ȡ���ر��������汾�ر�����ɾ������
                   ��get������ʼ��
                    public T get()��
                    
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
	 *   �ܼ򵥣������Ȼ�ȡ��ǰ����ִ�е��̣߳���ȡ��ǰִ�е��̵߳�ThreadLocalMap(ÿ��Thread����һ�����ֽ�threadLocals  ����ΪThreadLocal.ThreadLocalMap�ı���)���������ǰThread��ThreadLocalMap��Ϊ�գ����Ѿ�ͨ��set����initialValue��ʼ��������ֱ�ӷ����йض������͡�
               �����ǰ�̵߳�ThreadLocalMapΪ�գ�����setInitialValue������
                
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
                       �÷����������Ƕ�ThreadLocalMap���г�ʼ���������������дinitialValue����������д�ĳ�ʼ�������T��Ȼ���ڳ��Բ�һ�µ�ǰ�߳�ThreadLocalMap��û��ֵ����ֵ�򽫳�ʼ����ֵҲset��ȥ��û��ֵ�û��ʹ���һ���µ�map,�ѵ�ǰ�̺߳�T�����������ݸ�createMap��
               
  void createMap(Thread t, T firstValue) {
        t.threadLocals = new ThreadLocalMap(this, firstValue);
    }
            createMap�ܼ򵥣����ǰѵ�ǰ�̵߳�ThreadLocalMap�ó�ʼ����ֵ����һ�¡�
               
              public void set(T value)
              
    public void set(T value) {
        Thread t = Thread.currentThread();
        ThreadLocalMap map = getMap(t);
        if (map != null)
            map.set(this, value);
        else
            createMap(t, value);
    }
         ������setInitialValue��������ʵ�����̫���ˣ�ֻ�ǰѳ�ʼ�����Ǹ�ֵ������set ��valueֵ����������ThreadLocalMap���棬����û�б仯��

                    public void remove()
               
     public void remove() {
         ThreadLocalMap m = getMap(Thread.currentThread());
         if (m != null)
             m.remove(this);
     }
                   remove����Ҳ�ǳ��򵥣����ǻ�ȡ��ǰ�̵߳�ThreadLocalMap�������Ϊ�յĻ��������������              
	 * 
	 * */

}
