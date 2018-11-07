package veterinaria.estoque.seguranca.provider;

import javax.enterprise.inject.spi.CDI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import veterinaria.estoque.seguranca.UsuarioAutenticado;
import veterinaria.estoque.service.ServiceAcessoSistema;
import veterinaria.estoque.util.exceptions.AcessoException;

@Component
@Qualifier("authDB")
public class AuthenticationProviderJdbcImpl implements AuthenticationProvider {

	@Autowired
	private MessageDigestPasswordEncoder encoder;

	@Autowired
	private UserDetailsService userDetailsService;

	private ServiceAcessoSistema serviceAcessoSistema;
	
	public AuthenticationProviderJdbcImpl() {
		serviceAcessoSistema = CDI.current().select(ServiceAcessoSistema.class).get();
	}
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		try {
			String login = authentication.getName();
			String senha = authentication.getCredentials().toString();
			
			UsuarioAutenticado usuarioSpring  = (UsuarioAutenticado) userDetailsService.loadUserByUsername(login);
			
			serviceAcessoSistema.validarSenha(usuarioSpring.getUsuario(), encoder.encodePassword(senha, null));
			
			return new UsernamePasswordAuthenticationToken(usuarioSpring, senha, usuarioSpring.getAuthorities());
		} catch (AcessoException | UsernameNotFoundException e) {
			throw new BadCredentialsException(e.getMessage());
		}
	}

	@Override
    public boolean supports(Class<? extends Object> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }

}
