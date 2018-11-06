package veterinaria.estoque.seguranca.handler;

import java.io.IOException;

import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Named
public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException authenticationException) throws IOException, ServletException {
		String location  = getLocation(httpServletRequest, authenticationException);

		httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
		httpServletResponse.sendRedirect(location);
		httpServletResponse.getWriter().flush();
	}

	private String getLocation(HttpServletRequest httpServletRequest, AuthenticationException authenticationException) {
		return String.format("%s/admin/login.xhtml?%s=true", httpServletRequest.getContextPath(), authenticationException.getMessage());
	}

}
