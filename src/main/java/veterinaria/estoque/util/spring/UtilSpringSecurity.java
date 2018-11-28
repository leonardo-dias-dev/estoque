package veterinaria.estoque.util.spring;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.Dependent;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import veterinaria.estoque.model.entidades.Usuario;
import veterinaria.estoque.seguranca.UsuarioAutenticado;

@Dependent
public class UtilSpringSecurity implements Serializable {

	private static final long serialVersionUID = 1L;

	public void atualizarUsuarioNaSessao(Usuario usuario) {
		UsuarioAutenticado usuarioAutenticado = new UsuarioAutenticado(usuario, getOperacoes(usuario));
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				usuarioAutenticado, usuarioAutenticado.getPassword(), usuarioAutenticado.getAuthorities());

		SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

		RequestContextHolder.currentRequestAttributes().setAttribute("SPRING_SECURITY_CONTEXT",
				usernamePasswordAuthenticationToken, RequestAttributes.SCOPE_GLOBAL_SESSION);
	}

	public List<GrantedAuthority> getOperacoes(Usuario usuario) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		if (usuario.isAdministrador()) {
			SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_ADMINISTRADOR");
			
			authorities.add(simpleGrantedAuthority);
		}

		return authorities;
	}

}
