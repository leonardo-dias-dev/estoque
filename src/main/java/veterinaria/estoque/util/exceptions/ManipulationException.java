package veterinaria.estoque.util.exceptions;

public class ManipulationException extends Exception {

	private static final long serialVersionUID = 1L;

	public ManipulationException(String message) {
		super(message);
	}
	
	public ManipulationException(String message, Throwable e) {
		super(message, e);
	}
	
	public ManipulationException(Throwable e) {
		super(e);
	}

}