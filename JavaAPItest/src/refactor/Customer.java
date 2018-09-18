package refactor;
/**
 * 
 * 
 * 
 * �ع������ʽ
 * ���ǵ���һ�죬���Ұѡ��ع�����ȫ�����������ϣ����͸�����ʦʱ�����ﾹȻ�����������һ˿�Ȼ������һֻϰ�ߵĶ�����ǰ���һ��ϰ�ߵ����ʽ������֮ǰ�ĺܳ�һ��ʱ���
 * ϰ����ÿ�����Ϸ����Ȿ�飬ϰ�������ְ�����д��mail���͸�Martin Fowler ������ϰ�����Ķ�Martin��ʱ�����ĵĻ��ţ�ϰ�������Ǳ���ӡ�ģ����Դֲڵ��鱾�Ϲ�������
 * ��ϰ�������ڴ��Ͼ׽���ζ��Щ����һ���Ӣ����ʿ��ֿ��ǵ���䣬ϰ���˱�������������Ͽյ�������������ķ��ٴ�Ⱦ������ɽ��Ҷ���������ʽҲ�͸�һ�����ˡ�
 * 
 * �ع����ڵ�ʱ������˼���������Ҹе��𺳡�����������������ڡ��������ϣ��������������������ƣ����ռ��еĹ淶��д��׼��һ�Ĵ��룬���ǽṹ���������ٵ�����RAD�߸���ȫ֪ȫ�ܡ����񻰣�
 * �ý�����������(crack)�ķ�ʽ������⣬�ڻ����ѭ��������ʵ���������ǽ⹹��������Kent Beck �� Martin Fowler ����վ��һ��XP�����ݶ����Ͻ��ķ������������ع�����--
 * �Ҳ�֪����˭�����refactoringһ�ʷ���Ϊ���ع������������Ĳ�����ȴ���˵㾦֮�ʡ�
 * 
 * ��һֱ�����ģʽ�İ����ߡ��������ҵ�˼���У��������Ӧ����һ�����������--��Ȼ������������ά������������ģ�������ѧ�ң�����ģʽ�����ģʽ�����ǵģ���������һЩ����Ľ������������
 * ׷���������ԵĿ��������ǣ�Joshua kerievksy����ƪ�����ġ�ģʽ��XP������¼�ڡ����ޱ���о���һ�飩�����׵�ָ���������ǰ��ʹ�����ģʽ�������¹��ȹ��̣�����һ���п����ʵ����ƾ��
 * �����������޷�д��ʹ�õĴ��룬����ʵ�á������ѹ��һ�е�Ҫ�ء���һƪ��ֹͣ���ȹ��̡���ʼ��Joshua׫д refactoring to patterns ϵ�����¡�
 * ��λ��̫�����������Ե����ͷ�ԣ�����ط���������ĺ�ṹ�����·�������ģʽ�ڷ��ٱ仯��Internetʱ���������ֹ�Եģ������ع���������
 * �������ģʽ�����ҵ�ʱ�����ֱ�ʱ�����ģ���Ϊ���Ǽǵò���ô���С�
 * 1�����ȣ�����ľ�η�ӵ���������ȥ�����ڼ�������������ˮһ����ͨ�ļ����������������η��
 * 2.�ҵ����ʵĿ������ߣ���������һ����java�ˣ���ô������ʵĹ��߾���Eclipse����ѧ��ʹ�����е��Զ����Ժ��ع����ܣ�Ȼ���ٲ���ʹ�ñ�����ܵ��κμ�����������
 * ����Ա������֮һ������Ҫ��Ϊ�Ȿ���������ڿ�
 * 3.��󣬼�ʹ����ȫ�������Ȿ���е����ж�����Ҳǧ��Ҫ�����˴��ꡣ�����ǵ��Ŷ������Ա������˵�������û�е�Ԫ���Ժ��ع�����û�취д���롣��
 * 
 * Extract Method �� Move Field ���������ܺ�ǳ�ԣ�����Ҫ�������ģ���Ϊ������༼�������������ɵؽ����ع��Ĺؼ��������������Щ�ع�׼�򽫰�����һ��һС�����޸���Ĵ��룬
 * ��ͼ����˹����еķ��ա��ܿ���ͻ����Щ�ع�׼��������Ƽ����Լ��Ŀ����ʵ��У����������Ͽڡ�
 * 
 * field method ������������һ�������������� class����ĳЩ�����һ������(method)
 * 
 * ͨ���ع���������ҳ��ı��ƽ��㣬��ᷢ����ν��Ʋ�����һ�ж�����ǰ�ᣬ���������������������𽥸��ֳ�������ϵͳ���������У������ѧϰ���ǿ����ƣ��ڼ�����Ļ���������һ��
 * �����ڿ��������г����������õ���ơ�
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * */
import java.util.Enumeration;

//�˿�
public class Customer {
	private String _name;//����
	private Vector _rentals = new Vector();//����¼

	public Customer(String name) {
		// TODO Auto-generated constructor stub
		_name = name;
	}
	public void addRental(Rental arg) {
		_rentals.addElement(arg);
	}
	public String getName() {
		return _name;
	}
	
	public String statement() {
		double totalAmount = 0; //�����ѽ��
		int frequentRenterPoints = 0; //���ͻ���
		Enumeration<E> rentals = _retals.elements();
		String result = "Rental Record for" + getName() +"\n";
		
		while(rentals.hasMoreElements()) {
			double thisAmount = 0;
			Rental each = (Rental) rentals.nextElement();//ȡ��һ������¼
			//determine amounts for each line
			switch(each.getMovie().getPriceCode()) {//ȡ��ӰƬ����۸�
			case Movie.REGULAR: //��ͨƬ
				thisAmount +=2;
			    if(each.getDaysRented()>2)
			    	thisAmount +=(each.getDaysRented()-2)*1.5;
			    break;
			case Movie.NEW_RELEASE: //��Ƭ
				thisAmount += each.getDaysRented()*3;
				break;
			case Movie.CHILDRENS: //��ͯƬ	
				thisAmount += 1.5;
				if(each.getDaysRented()>3)
					thisAmount += (each.getDaysRented()-3)*1.5;
				break;
			
			}
			// add frequent renter points (�ۼ� ���ͻ���)
			frequentRenterPoints ++;
			// add bonus for a two day new release tental
			if((each.getMovie().getPriceCode() == Movie.NEW_RELEASE && each.getDaysRented()>1)
					frequentRenterPoints ++;
			//show figures for this rental (��ʾ�˱��������)
			result += "\t" + each.getMovie().getTitle() + "\t" + String.valueOf(thisAmount)+ "\n";
			totalAmount += thisAmount;
		}
		//add footer lines (��β��ӡ)
		result += "Amount owed is"+ String.valueOf(totalAmount)+"\n";
		result += "You earned "+ String.valueOf(frequentRenterPoints)+"frement renter points";
		return result;
	}
    /**
     * �Դ���ʼ���������
     * �����ʼ�����������ʲôӡ��?�һ�˵����Ƶò��ã����Һ����Բ�������������񣬶�������һ��С������Щȱ����ʵû��ʲô��ϵ�����ٶ����Ե����һ����
     * �ĳ���û�д���������Ǹ���ϵͳ�о��д����Ե�һ�Σ���ô�Ҿ����Ҫ�����������Ϣ��ҡ�ˡ�Customer�����Ǹ�������statement()��������ʵ��̫����
     * �����˺ܶ�ԭ��Ӧ��������class��ɵ����顣
     * 
     * ������ˣ����������������������������ֻ����ѧ�����ϵ��жϣ�ֻ�ǶԳ�ª�������������������޸����ϵͳ֮ǰ��ȷ��ˡ��������Ų����ں�����ò��ÿ��ء�
     * ���ǵ����Ǵ����޸�ϵͳ��ʱ�򣬾��漰�����ˣ������ں���Щ�����ϵͳ�Ǻ����޸ĵģ���Ϊ�����ҵ��޸ĵ㡣��������ҵ��޸ĵ㣬����Ա�ͺ��п��ܷ����Ӷ����롾���桿
     * 
     * �������������ǵ��û�ϣ����ϵͳ��һ���޸ġ���������ϣ����HTML��ʽ��ӡ���������Ϳ���ֱ������ҳ����ʾ����ǳ����ϳ���
     * */
	
}
