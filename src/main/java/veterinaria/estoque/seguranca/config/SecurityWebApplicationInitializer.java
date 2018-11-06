package veterinaria.estoque.seguranca.config;

import java.util.EnumSet;

import javax.servlet.DispatcherType;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {

	public SecurityWebApplicationInitializer() {
		super(SecurityConfig.class);
	}

	
	protected EnumSet<javax.servlet.DispatcherType> getSecurityDispatcherTypes() {
		EnumSet<javax.servlet.DispatcherType> fd = super.getSecurityDispatcherTypes();

		// permitir redirecionamento do managedbean
		fd.add(DispatcherType.FORWARD);
		fd.add(DispatcherType.REQUEST);
		fd.add(DispatcherType.INCLUDE);
		fd.add(DispatcherType.ERROR);

		return fd;
	}
}