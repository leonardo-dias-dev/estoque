package veterinaria.estoque.util;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;

@Controller(value="registroSessao")
public class UtilRegistroSessao implements Serializable {

	private static final long serialVersionUID = 6492165539953598438L;

	@Autowired
	private SessionRegistry sessionRegistry;

	public SessionRegistry getSessionRegistry() {
		return sessionRegistry;
	}

	public void setSessionRegistry(SessionRegistry sessionRegistry) {
		this.sessionRegistry = sessionRegistry;
	}

}
