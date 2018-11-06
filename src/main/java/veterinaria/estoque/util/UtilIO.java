package veterinaria.estoque.util;

import java.io.InputStream;
import java.io.Serializable;

import javax.enterprise.context.Dependent;

@Dependent
public class UtilIO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public InputStream obterInputStreamAtravesDaRaizDoClasspath(String path) {
		try {
			InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
			return resourceAsStream;
		} catch (Exception e) {
			throw new IllegalArgumentException("Não foi possivel encontrar o recurso com esse endereço");
		}
	}
	
}
