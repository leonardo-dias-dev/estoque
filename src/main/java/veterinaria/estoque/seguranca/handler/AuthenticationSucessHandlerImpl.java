package veterinaria.estoque.seguranca.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationSucessHandlerImpl implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
		String location = getLocation(httpServletRequest);

		httpServletResponse.sendRedirect(location);
	}

	private String getLocation(HttpServletRequest httpServletRequest) {
		return String.format("%s/home.xhtml", httpServletRequest.getContextPath());
	}

}
