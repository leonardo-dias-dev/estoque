package veterinaria.estoque.util.spring;

import java.io.Serializable;

import javax.enterprise.context.Dependent;

@Dependent
public class UtilSpringSecurity implements Serializable {

	private static final long serialVersionUID = 1L;

/*	public void atualizarCandidatoNaSessao(Candidato candidato) {
		UsuarioAutenticado usuarioAutenticado = new UsuarioAutenticado(candidato, getOperacoesCandidato(candidato));
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				usuarioAutenticado, usuarioAutenticado.getPassword(), usuarioAutenticado.getAuthorities());

		SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

		RequestContextHolder.currentRequestAttributes().setAttribute("SPRING_SECURITY_CONTEXT",
				usernamePasswordAuthenticationToken, RequestAttributes.SCOPE_GLOBAL_SESSION);
	}*/

/*	public void atualizarAdministradorNaSessao(Administrador administrador) {
		UsuarioAutenticado usuarioAutenticado = new UsuarioAutenticado(administrador, getOperacoesAdministrador(administrador));
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				usuarioAutenticado, usuarioAutenticado.getPassword(), usuarioAutenticado.getAuthorities());

		SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

		RequestContextHolder.currentRequestAttributes().setAttribute("SPRING_SECURITY_CONTEXT",
				usernamePasswordAuthenticationToken, RequestAttributes.SCOPE_GLOBAL_SESSION);
	}*/

/*	public List<GrantedAuthority> getOperacoesCandidato(Candidato usuario) {
		List<GrantedAuthority> authorities = new ArrayList<>();

		authorities.add(new SimpleGrantedAuthority("ROLE_CANDIDATO"));

		return authorities;
	}*/

/*	public List<GrantedAuthority> getOperacoesAdministrador(Administrador administrador) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		if (administrador.getTipoConta().equals(TipoContaAdministrador.ADMINISTRADOR)) {
			String regra = String.format("ROLE_%S", TipoRegras.ADMINISTRADOR.getValor());
			
			authorities.add(new SimpleGrantedAuthority(regra));
		} else {
			String regra = String.format("ROLE_%S", TipoRegras.OPERADOR.getValor());
			
			authorities.add(new SimpleGrantedAuthority(regra));
		}

		return authorities;
	}*/

}
