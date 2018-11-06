package veterinaria.estoque.util.exceptions;

public class AcessoException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public AcessoException(String mensagem) {
		super(mensagem);
	}
	
	public AcessoException(Throwable throwable) {
		super(throwable);
	}

}
