package veterinaria.estoque.seguranca;

import java.util.Collection;

import javax.enterprise.context.Dependent;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import veterinaria.estoque.model.entidades.Usuario;

@Dependent
public class UsuarioAutenticado implements UserDetails {

	private static final long serialVersionUID = 1L;

	private Usuario usuario;
	
	Collection<? extends GrantedAuthority> authorities;
	
	public UsuarioAutenticado(Usuario usuario, Collection<? extends GrantedAuthority> authorities) {
		this.usuario = usuario;
		this.authorities = authorities;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return null;
	}

	@Override
	public String getUsername() {
		return usuario.getLogin();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, "password", "id", "role", "description", "registrationDate", "enabled");
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj, "password", "id", "role", "description", "registrationDate", "enabled");
	}

	public Usuario getUsuario() {
		return usuario;
	}

}
