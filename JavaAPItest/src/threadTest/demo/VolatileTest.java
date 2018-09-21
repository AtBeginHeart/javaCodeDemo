package threadTest.demo;
/**
 * volatile ���������������
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
		//�ȴ������ۼ��̶߳�����
		while(Thread.activeCount()>1) Thread.yield();
		System.out.println(race);	
	}

}
/**
 * 
 * ��δ��뷢����20���̣߳�ÿ���̶߳�race��������10000�����������������δ����ܹ���ȷ�����Ļ����������Ľ��Ӧ����20000.������������δ���֮�󣬲�
 * �����������Ľ�������һᷢ��ÿ�����г�������Ľ������һ��������һ��С��200000�����֣�����Ϊʲô�أ�
 * ����ͳ������������㡰race++��ֻ�У�������javap��������δ���󣬻�õ������嵥12-2������ֻ��һ�д����increase()������Class�ļ�������4���ֽ���
 * ָ��ɵ�(return ָ�����race++�����ģ�����ָ����Բ���)�����ֽ�������Ϻ����׾ͷ���������ʧ�ܵ�ԭ���ˣ���getstaticָ���race��ֵ��������ջ���ǣ�
 * volatile �ؼ��ֱ�֤��race��ֵ�ڴ�ʱ����ȷ�ģ�������ִ��iconst_1��iadd��Щָ���ʱ�������߳̿����Ѿ���race��ֵ�Ӵ��ˣ����ڲ���ջ����ֵ�ͱ���˹��ڵ�
 * �����ݣ����� putstaticָ��ִ�к�Ϳ��ܰѽ�С��raceֵͬ�������ڴ�֮�С�
 * */

/**
 * 
 * ����volatile����ֻ�ܱ�֤�ɼ��ԣ��ڲ�����һ��������������㳡���У�������ȻҪͨ������(ʹ��synchronized �� java.util.concurrent�е�ԭ����)����֤
 * ԭ���ԡ�
 * */

