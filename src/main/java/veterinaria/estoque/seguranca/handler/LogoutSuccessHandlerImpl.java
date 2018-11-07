package veterinaria.estoque.seguranca.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Service;

@Service
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {

	@Override
	public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
		String location = getLocation(httpServletRequest);
		
		httpServletResponse.setStatus(HttpStatus.OK.value());
		httpServletResponse.sendRedirect(location);
		httpServletResponse.getWriter().flush();
	}
	
	private String getLocation(HttpServletRequest httpServletRequest) {
		return String.format("%s/home.xhtml", httpServletRequest.getContextPath());
	}
	
}
