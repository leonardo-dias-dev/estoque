package veterinaria.estoque.util.exceptions;

public class NegocioException extends Exception {

	private static final long serialVersionUID = 1L;

	private String message;
	
	public NegocioException(String message) {
		super(message);
		this.message = message;
	}
	
	public NegocioException(String message, Throwable e) {
		super(message, e);
		this.message = message;
	}
	
	public NegocioException(Throwable e) {
		super(e);
	}

	@Override
	public String getMessage() {
		return message;
	}
	
}