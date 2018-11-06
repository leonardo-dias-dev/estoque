package veterinaria.estoque.util.cdi.produces;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import veterinaria.estoque.util.cdi.qualifiers.QLogger;

@ApplicationScoped
public class ProdutorJSF {

	@Produces
	@RequestScoped
	public FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}

	@Produces
	@RequestScoped
	public ExternalContext getExternalContext() {
		return getFacesContext().getExternalContext();
	}

	@Produces
	@RequestScoped
	public Flash getFlash() {
		return FacesContext.getCurrentInstance().getExternalContext().getFlash();
	}

	@Produces
	@RequestScoped
	public HttpServletResponse getHttpServletResponse() {
		return ((HttpServletResponse) getExternalContext().getResponse());
	}

	@Produces
	@QLogger
	public Log criaLogger(InjectionPoint ip) {
		return LogFactory.getLog(ip.getMember().getDeclaringClass().getName());
	}

/*	@Produces
	@QCandidatoAutenticado
	private Candidato createUsuario() {
		Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
		UsuarioAutenticado usuarioSpring = null;

		if (principal instanceof UsernamePasswordAuthenticationToken) {
			UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) principal;

			usuarioSpring = (UsuarioAutenticado) auth.getPrincipal();
		} else if (principal instanceof RememberMeAuthenticationToken) {
			RememberMeAuthenticationToken auth = (RememberMeAuthenticationToken) principal;

			usuarioSpring = (UsuarioAutenticado) auth.getPrincipal();
		}

		return usuarioSpring.getCandidato();
	}*/
	
/*	@Produces
	@QAdministradorAutenticado
	private Administrador createAdministrador() {
		Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
		UsuarioAutenticado usuarioSpring = null;

		if (principal instanceof UsernamePasswordAuthenticationToken) {
			UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) principal;

			usuarioSpring = (UsuarioAutenticado) auth.getPrincipal();
		} else if (principal instanceof RememberMeAuthenticationToken) {
			RememberMeAuthenticationToken auth = (RememberMeAuthenticationToken) principal;

			usuarioSpring = (UsuarioAutenticado) auth.getPrincipal();
		}

		return usuarioSpring.getAdministrador();
	}*/

}
