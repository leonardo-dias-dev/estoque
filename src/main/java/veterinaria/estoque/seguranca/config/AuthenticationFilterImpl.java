package veterinaria.estoque.seguranca.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

/**
 * Filtro para redirecionar o usuário para tela HOME, caso ele fizer um
 * requisição para a tela de login e já estiver logado.
 * 
 */
@Component
public class AuthenticationFilterImpl extends GenericFilterBean {

	private static final String URL_LOGIN = "/candidato/login.xhtml";
	private static final String URL_HOME = "/candidato/pagina-inicial.xhtml";
	
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
		HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

		String requestURI = httpServletRequest.getRequestURI();

		if (isAuthenticated() && requestURI.indexOf(URL_LOGIN) >= 0) {
			httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + URL_HOME);
		} else {
			filterChain.doFilter(httpServletRequest, httpServletResponse);
		}
	}

	private boolean isAuthenticated() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication authentication = securityContext.getAuthentication();
		
		return authentication != null;
	}

}
