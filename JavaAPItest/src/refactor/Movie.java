package refactor;
/**
 * ӰƬ
 * */
public class Movie {
	public static final int CHILDRENS = 2;
	public static final int REGULAR = 0;
	public static final int NEW_RELEASE = 1;
	String _titile;
	int _priceCode;

	public Movie(String title,int priceCode) {
		 _titile = title;
	     _priceCode = priceCode;
	}
	public int getPriceCode() {
		return _priceCode;
	}
	public void setPriceCode(int arg) {
		_priceCode = arg;
	}
	public String getTitle() {
		return _titile;
	}
	

}
