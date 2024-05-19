package exception;

public class LimitLoginException extends Exception{
	private int counter = 0;
	
	public LimitLoginException (int counter) {
		this.counter = counter;
	}
	
	@Override
	public String toString() {
		return "Error login, superados los " + this.counter + " intentos";
	}
	
}
