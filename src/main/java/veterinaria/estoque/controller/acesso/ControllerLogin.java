package veterinaria.estoque.controller.acesso;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

@Named
@ViewScoped
public class ControllerLogin implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private FacesContext facesContext;
	
	private String usuario;
	
	@PostConstruct
	public void init() {
		
	}
	
	public void viewAction() {
		
	}
	
	public void entrar() throws ServletException, IOException {
		ServletRequest servletRequest = (ServletRequest) facesContext.getExternalContext().getRequest();
		ServletResponse servletResponse = (ServletResponse) facesContext.getExternalContext().getResponse();
		RequestDispatcher requestDispatcher = servletRequest.getRequestDispatcher("/appLogin");
		
		requestDispatcher.forward(servletRequest, servletResponse);
		
		facesContext.responseComplete();
	}
	
	public void sair() throws ServletException, IOException {
		ServletRequest servletRequest = (ServletRequest) facesContext.getExternalContext().getRequest();
		ServletResponse servletResponse = (ServletResponse) facesContext.getExternalContext().getResponse();
		RequestDispatcher requestDispatcher = servletRequest.getRequestDispatcher("/appLogout");
		
		requestDispatcher.forward(servletRequest, servletResponse);
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

}
