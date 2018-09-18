package headStackMethodArea;

public class Sample {//运行时，jvm把appmain的信息都放入方法区
/*
 * 范例名称
 * */
	private  String name; //new Sample实例后，name引用放入栈里，name对象放入堆里
	/**
	 * 构造方法
	 * */
	public Sample(String name) {
		// TODO Auto-generated constructor stub
		this.name = name;
	}
	
	/**
	 * 输出
	 * */
	public void printName() {//print 方法本身放入方法区里。
		System.out.println(name);
	}

}
