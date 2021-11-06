
public class Card {
	
	String suite;
	int value;
	
	Card(String suite, int value) {
		this.suite = suite;
		this.value = value;
		
	}
	
	public int getVal() {
		return value;
	}
	
	public void setVal(int num) {
		value = num;
	}
	
	public String getSuit() {
		return suite;
	}
	
}
