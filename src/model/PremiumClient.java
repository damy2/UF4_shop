package model;

public class PremiumClient extends Client {

	int points;
	final int MEMBER_ID = 456;
	final double BALANCE = 50;
	private int member_id = MEMBER_ID;
	private Amount balance = new Amount(BALANCE);
	private String name;
	
	public PremiumClient(String name) {
		super(name);
		this.name = name;
	}
	
	public boolean pay(Amount amount) {
		this.setPoints( (int) amount.getValue() / 10);  // Points Awarded depending on the money of sales (1 point every 10€ rounded down)
		this.balance.setValue(this.balance.getValue() - amount.getValue());
		if (this.balance.getValue() < 0) {
			return false;
		}
		return true;
	}
	
	public int getMember_id() {
		return member_id;
	}

	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}

	public Amount getBalance() {
		return balance;
	}

	public void setBalance(Amount balance) {
		this.balance = balance;
	}




	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

}
