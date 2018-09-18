package OutOfMemory;
/**
 * 注意  特别提示一下，如果读者要尝试运行上面这段代码，记得要先保存当前的工作，由于在Windows平台的虚拟机中，Java的线程是映射到操作系统的内核线程上的，所以
 * 上述代码执行时有较大的风险，可能会导致操作系统假死。
 * */
/**
 * 如果测试时不限于单线程，通过不断地建立线程的方式倒是可以产生内存溢出异常，如下代码。但是，这样产生的内存溢出异常与栈空间是否足够大并不存在任何联系
 * ，或者准确的说，在这种情况下，给每个线程的栈分配的内存越大，反而越容易产生内存溢出异常。
 * 
 *  原因其实不难理解，操作系统分配给每个进程的内存是有限制的，譬如32位的Windows限制为2GB.虚拟机提供了参数来控制Java堆和方法区的这两部分内存的最大值
 *  剩余的内存为2GB(操作系统限制)减去Xmx(最大堆容量)，再减去MaxPermSize(最大方法区容量)，程序计数器消耗内存很小，可以忽略掉。如果虚拟机进程本省耗费的内存
 *  不计算在内，剩下的内存就由虚拟机栈和本地方法栈“瓜分”了。每个线程分配到的栈容量越大，可以建立的线程数量自然就越少，建立线程时就越容易把剩下的内存耗尽。
 *  
 *  这一点读者需要在开发多线程应用的时候特别注意，出现StackOverflowError异常时有错误堆栈可以阅读，相对来说，比较容易找到问题的所在。而且，如果使用虚拟机默认参数
 *  栈深度在大多数情况下（因为每个方法压入栈的帧大小并不是一样的，所以只能说大多数情况下）达到1000-2000完全没有问题，对于正常的方法调用（包括地柜），这个深度应该完全够用了。
 *  但是，如果是建立过多线程导致的内存溢出，在不能减少线程数或者更换64位虚拟机的情况下，就只能通多减少最大堆和减少栈容量来换取更多的线程。如果没有这方面经验，这种通过“减少内存”
 *  的手段来解决内存溢出的方式会比较难以想到。
 * */
public class JavaVMStackOOM {
       private void dontStop() {
    	   while(true) {
    		   
    	   }
       }
       public void stackLeakByThread() {
    	   while(true) {
    		   Thread thread = new Thread(new Runnable() {
    			   @Override
    			   public void run() {
    				   dontStop();
    			   }
    		   });
    		   thread.start();
    	   }
       }
       
       public static void main(String[] args) throws Throwable{
    	   JavaVMStackOOM oom = new JavaVMStackOOM();
    	   oom.stackLeakByThread();
       }
}
