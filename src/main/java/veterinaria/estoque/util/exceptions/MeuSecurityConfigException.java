package veterinaria.estoque.util.exceptions;

public class MeuSecurityConfigException extends Exception {

	private static final long serialVersionUID = 1L;

	public MeuSecurityConfigException(String message) {
		super(message);
	}
	
	public MeuSecurityConfigException(String message, Throwable e) {
		super(message, e);
	}
	
	public MeuSecurityConfigException(Throwable e) {
		super(e);
	}

}