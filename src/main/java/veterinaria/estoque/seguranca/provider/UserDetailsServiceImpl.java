package veterinaria.estoque.seguranca.provider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.enterprise.inject.spi.CDI;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import veterinaria.estoque.model.entidades.Perfil;
import veterinaria.estoque.model.entidades.Usuario;
import veterinaria.estoque.seguranca.UsuarioAutenticado;
import veterinaria.estoque.service.ServiceAcessoSistema;
import veterinaria.estoque.service.ServiceUsuario;
import veterinaria.estoque.util.exceptions.AcessoException;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private ServiceUsuario serviceUsuario;
	private ServiceAcessoSistema serviceAcessoSistema;

	public UserDetailsServiceImpl() {
		serviceUsuario = CDI.current().select(ServiceUsuario.class).get();
		serviceAcessoSistema = CDI.current().select(ServiceAcessoSistema.class).get();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			Usuario usuarioSistema = serviceUsuario.buscarPorLogin(username);
			
			serviceAcessoSistema.validarUsuario(usuarioSistema);

			Collection<? extends GrantedAuthority> operacoes = getOperacoes(usuarioSistema);
			
			return new UsuarioAutenticado(usuarioSistema, operacoes);
		} catch (AcessoException e) {
			throw new UsernameNotFoundException(e.getMessage());
		}
	}

	private Collection<? extends GrantedAuthority> getOperacoes(Usuario usuario) {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		
		for (Perfil perfil : usuario.getListaPerfil()) {
			String role = String.format("ROLE_%s", perfil.getCodigo());
			
			SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(role);
			
			authorities.add(simpleGrantedAuthority);
		}

		return authorities;
	}

}
