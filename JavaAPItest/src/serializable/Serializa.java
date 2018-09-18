package serializable;
//https://www.cnblogs.com/ysocean/p/6870069.html
public class Serializa {
     /**
      * 
      * 1.什么是序列化与反序列化?
      * 序列化：指把堆内存中的java对象数据，通过某种方式把对象存储到磁盘文件中或者传递给其他网络节点（在网络上传输）。这个过程称为
      * 序列化。通俗来说就是将数据结构或对象转换成二进制串的过程
      * 
      * 反序列化：把磁盘文件中的对象数据或者把网络节点上的对象数据，恢复成Java对象模型的过程。也就是将在序列化过程中所生成的
      * 二进制串转换成数据结构或者对象的过程
      * 
      * 2.为什么药做序列化？
      *  1.在分布式系统中，此时需要把对象在网络上传输，就得把对象数据转换为二进制形式，需要共享的数据的javaBean对象，都得做序列化
      *  2.服务器钝化：如果服务器发现某些对象好久没活动了，那么服务器就会把这些内存中的对象持久化在本地磁盘文件中（java对象转换为二进制文件）
      *  如果服务器发现某些对象需要活动时，先去内存中寻找，找不到再去磁盘文件中反序列化我们的对象数据，恢复成java对象。这样能节省服务器内存。
      *  3.java怎么进行序列化？
      *  1.需要做序列化的对象的类，必须实现序列化接口：java.lang.Serializable接口（这是一个标志接口，没有任何抽象方法），java中大多数类
      *  都实现了该接口，比如String , Integer
      *  2底层会判断，如果当前对象是Serializable的实例，才允许做序列化，java对象 instanceof Serializable 来判断。
      *  3在java中使用对象流来完成序列化和反序列化
      *  ObjectOutputStream:通过writeObject()方法做序列化操作
      *  ObjectInputStream:通过readObject()方法做反序列化操作
      *  step 1创建一个javaBean对象
      *  step 2使用ObjectOutputStream 对象实现序列化
      * */
	public Serializa() {
		// TODO Auto-generated constructor stub
	}

}
