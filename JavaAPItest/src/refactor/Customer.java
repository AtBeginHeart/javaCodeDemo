package refactor;
/**
 * 
 * 
 * 
 * 重构的生活方式
 * 还记得那一天，当我把《重构》的全部译稿整理完毕，发送给侯老师时，心里竟然不经意地有了一丝惘然。我是一只习惯的动物，总是安于一种习惯的生活方式。在那之前的很长一段时间里，
 * 习惯了每天晚上翻译这本书，习惯了随手把问题写成mail发送给Martin Fowler 先生，习惯了阅读Martin及时而耐心的回信，习惯了在那本复印的，略显粗糙的书本上勾勾画画
 * ，习惯了躺在窗上咀嚼回味那些带有一点点英国绅士矜持口吻的语句，习惯了背后嗡嗡作响的老空调。。。当深秋的风再次染红了香山的叶，这种生活方式也就告一段落了。
 * 
 * 重构，在当时，它的思想足以令我感到震撼。软件自有其美感所在。软件工程希望建立完美的需求与设计，按照既有的规范编写标准划一的代码，这是结构的美；快速迭代和RAD颠覆【全知全能】的神话，
 * 用近乎刀劈斧砍(crack)的方式解决问题，在混沌的循环往复中实现需求，这是解构的美；而Kent Beck 与 Martin Fowler 两人站在一起，XP那敏捷而又严谨的方法论演绎了重构的美--
 * 我不知道是谁最初把refactoring一词翻译为【重构】，或许无心插柳，却成了点睛之笔。
 * 
 * 我一直是设计模式的爱好者。曾经在我的思想中，软件开发应该有一个【理想国】--当然，在这个理想国维持着完美秩序的，不是哲学家，而是模式。设计模式给我们的，不仅仅是一些问题的解决方案，更有
 * 追求完美理性的渴望。但是，Joshua kerievksy在那篇著名的《模式与XP》（收录于《极限编程研究》一书）中明白地指出：在设计前期使用设计模式常常导致过度工程，这是一个残酷的现实，单凭对
 * 完美的最求无法写出使用的代码，而【实用】是软件压倒一切的要素。从一篇《停止过度工程》开始，Joshua撰写 refactoring to patterns 系列文章。
 * 这位犹太人用它民族性的睿智头脑，敏锐地发现了软件的后结构主义道路。而设计模式在飞速变化的Internet时代重新闪现光辉的，又是重构的力量。
 * 而《设计模式》，我到时放在手边时常翻阅，因为总是记得不那么真切。
 * 1，首先，把你的敬畏扔到大西洋里去，对于即将变得像空气与水一样普通的技术，你无须对它敬畏；
 * 2.找到合适的开发工具（如果你和我一样是java人，那么这个合适的工具就是Eclipse），学会使用其中的自动测试和重构功能，然后再参数使用本书介绍的任何技术。懒惰是
 * 程序员的美德之一，绝不要因为这本书让你变得勤快
 * 3.最后，即使你完全掌握了这本书中的所有东西，也千万不要跟别人吹嘘。在我们的团队里，程序员常常会说：【如果没有单元测试和重构，我没办法写代码。】
 * 
 * Extract Method 和 Move Field 看起来可能很浅显，但不要掉以轻心，因为理解这类技术正是有条不紊地进行重构的关键。本书所提的这些重构准则将帮助你一次一小步地修改你的代码，
 * 这就减少了过程中的风险。很快你就会吧这些重构准则和其名称假如自己的开发词典中，并且郎朗上口。
 * 
 * field method 拉出来构成另一个函数，或是在 class，把某些代码从一个函数(method)
 * 
 * 通过重构：你可以找出改变的平衡点，你会发现所谓设计不再是一切动作的前提，而是在整个开发过程中逐渐浮现出来。在系统构筑过程中，你可以学习如何强化设计；期间带来的互动可以让一个
 * 程序在开发过程中持续保有良好的设计。
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

//顾客
public class Customer {
	private String _name;//姓名
	private Vector _rentals = new Vector();//租借记录

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
		double totalAmount = 0; //总消费金额
		int frequentRenterPoints = 0; //常客积点
		Enumeration<E> rentals = _retals.elements();
		String result = "Rental Record for" + getName() +"\n";
		
		while(rentals.hasMoreElements()) {
			double thisAmount = 0;
			Rental each = (Rental) rentals.nextElement();//取得一笔租借记录
			//determine amounts for each line
			switch(each.getMovie().getPriceCode()) {//取得影片出租价格
			case Movie.REGULAR: //普通片
				thisAmount +=2;
			    if(each.getDaysRented()>2)
			    	thisAmount +=(each.getDaysRented()-2)*1.5;
			    break;
			case Movie.NEW_RELEASE: //新片
				thisAmount += each.getDaysRented()*3;
				break;
			case Movie.CHILDRENS: //儿童片	
				thisAmount += 1.5;
				if(each.getDaysRented()>3)
					thisAmount += (each.getDaysRented()-3)*1.5;
				break;
			
			}
			// add frequent renter points (累加 常客积点)
			frequentRenterPoints ++;
			// add bonus for a two day new release tental
			if((each.getMovie().getPriceCode() == Movie.NEW_RELEASE && each.getDaysRented()>1)
					frequentRenterPoints ++;
			//show figures for this rental (显示此笔租借数据)
			result += "\t" + each.getMovie().getTitle() + "\t" + String.valueOf(thisAmount)+ "\n";
			totalAmount += thisAmount;
		}
		//add footer lines (结尾打印)
		result += "Amount owed is"+ String.valueOf(totalAmount)+"\n";
		result += "You earned "+ String.valueOf(frequentRenterPoints)+"frement renter points";
		return result;
	}
    /**
     * 对此起始程序的评价
     * 这个起始程序给你留下什么印象?我会说它设计得不好，而且很明显不符合面向对象精神，对于这样一个小程序，这些缺点其实没有什么关系。快速而随性地设计一个简单
     * 的程序并没有错，但如果这是复杂系统中具有代表性的一段，那么我就真的要对这个程序信息动摇了。Customer里面那个长长的statement()做的事情实在太多了
     * 它做了很多原本应该由其他class完成的事情。
     * 
     * 即便如此，这个程序还是能正常工作。所以这只是美学意义上的判断，只是对丑陋代码的厌恶，是吗？在我们修改这个系统之前的确如此。编译器才不会在乎代码好不好看呢。
     * 但是当我们打算修改系统的时候，就涉及到了人，而人在乎这些。差劲的系统是很难修改的，因为很难找到修改点。如果很难找到修改点，程序员就很有可能犯错，从而引入【臭虫】
     * 
     * 在这个例子里，我们的用户希望对系统做一点修改。首先他们希望以HTML格式打印报表，这样就可以直接在网页上显示，这非常符合潮流
     * */
	
}
