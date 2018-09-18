package OutOfMemory;

import java.util.ArrayList;
import java.util.List;

/**
 * VM Args : -XX:PermSize=10M -XX:MaxPermSize=10M
 * */
/**
 * ���Ҫ������ʱ��������������ݣ���򵥵���������ʹ�� String.intern()���Native�������÷�����������:��������Ѿ�����һ�����ڴ�String������ַ���
 * ���򷵻ش����������ַ�����String���󣻷��򣬽���String����������ַ�����ӵ��������У����ҷ��ش�String�����ã��������ڷ������ڣ����ǿ���ͨ��--XX:PermSize��
 * ���Ƴ����ش�С���Ӷ�����������г����ص�������
 * */
public class RuntimeConstantPoolOOM {
	public static void main(String[] args) {
		//ʹ��List�����ų��������ã�����Full GC���ճ�������Ϊ
		List<String> list = new ArrayList<String>();
		//10MB��PermSize ��integer ��Χ���㹻����OOM��
		int i = 0;
		while(true) {
			list.add(String.valueOf(i++).intern());
			//git�ϴ�����
		}
	}
}
