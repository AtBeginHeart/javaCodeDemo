package jasontestDemo;

public class TryCatch {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TryCatch c = new TryCatch();
		c.method();

	}

	public TryCatch() {
		// TODO Auto-generated constructor stub
	}
	public void method() {
		try {
			System.out.println("A");
			int i = 10/0;
			System.out.println("B");
		}
		catch(Exception e) {
		System.out.println("C");	
		}
		finally {
			System.out.println("D");
		}
	}

	

}
