package pwr.asi.java.hermes.CRM;

public class NotAdminException extends Exception{
	
	public NotAdminException() {}
	
	public NotAdminException(String statement) {
		super(statement);
	}

}
