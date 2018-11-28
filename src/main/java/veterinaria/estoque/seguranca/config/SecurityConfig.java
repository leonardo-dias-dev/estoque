package veterinaria.estoque.seguranca.config;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.filter.GenericFilterBean;

import veterinaria.estoque.seguranca.handler.AuthenticationFailureHandlerImpl;
import veterinaria.estoque.seguranca.handler.AuthenticationSucessHandlerImpl;
import veterinaria.estoque.seguranca.handler.LogoutSuccessHandlerImpl;
import veterinaria.estoque.seguranca.provider.AuthenticationProviderJdbcImpl;
import veterinaria.estoque.util.exceptions.MeuSecurityConfigException;

@Configuration
@ComponentScan(basePackages="veterinaria.estoque.seguranca", basePackageClasses=veterinaria.estoque.util.UtilRegistroSessao.class)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Inject
	private LogoutSuccessHandlerImpl logoutSuccessHandlerImpl;

	@Autowired
	private AuthenticationFailureHandlerImpl authenticationFailureHandlerImpl;

	@Autowired
	private AuthenticationSucessHandlerImpl authenticationSucessHandlerImpl;

	@Autowired
	@Qualifier("authDB")
	private AuthenticationProviderJdbcImpl authenticationProviderJdbcImpl;

	@Autowired
	public void configureGlobalAuthDB(AuthenticationManagerBuilder authenticationManagerBuilder) throws MeuSecurityConfigException {
		try {
			authenticationManagerBuilder.authenticationProvider(authenticationProviderJdbcImpl);
		} catch (Exception e) {
			throw new MeuSecurityConfigException("Nao foi possivel iniciar o provedor de autenticacao do Spring Security", e);
		}
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws MeuSecurityConfigException {
		try {
			httpSecurity.csrf().disable().headers().frameOptions().sameOrigin();

			httpSecurity = adicionarRegrasAsPaginas(httpSecurity);
			
			httpSecurity
				.formLogin()
					.loginProcessingUrl("/appLogin")
					.loginPage("/login.xhtml")
					.successHandler(authenticationSucessHandlerImpl)
					.failureHandler(authenticationFailureHandlerImpl);
			httpSecurity
				.logout()
					.logoutUrl("/appLogout")
					.logoutSuccessHandler(logoutSuccessHandlerImpl)
					.invalidateHttpSession(true)
					.deleteCookies("JSESSIONID");

			httpSecurity
				.exceptionHandling()
					.accessDeniedPage("/access-denied.xhtml");

			httpSecurity
				.sessionManagement()
					.invalidSessionUrl("/login.xthml?invalidSessionUrl=true")
					.sessionAuthenticationErrorUrl("/login.xhtml?sessionError=true")
					.maximumSessions(1)
					.sessionRegistry(getSessionRegistry())
					.expiredUrl("/login.xthml?expired=true");

			httpSecurity.httpBasic();
			httpSecurity.addFilterAfter(authenticationFilter(), BasicAuthenticationFilter.class);
		} catch (Exception e) {
			throw new MeuSecurityConfigException("Nao foi possivel iniciar as configuracoes do Spring security", e);
		}
	}

	private HttpSecurity adicionarRegrasAsPaginas(HttpSecurity httpSecurity) throws MeuSecurityConfigException {
		try {
			httpSecurity.authorizeRequests()
					// Qualquer um acessa a pagina de login
					.antMatchers("/login.xhtml").permitAll()

					// basta estar autenticado
					.antMatchers("/sobre.xhtml").authenticated()
					.antMatchers("/home.xhtml").authenticated()
					
					// Cadastros
					.antMatchers("/cadastro/cadastro.xhtml").authenticated()
					.antMatchers("/cadastro/usuario/consulta-usuario.xhtml").authenticated()
					.antMatchers("/cadastro/usuario/cadastra-usuario.xhtml").authenticated()
					
					.antMatchers("/cadastro/produto/consulta-produto.xhtml").authenticated()
					.antMatchers("/cadastro/produto/cadastra-produto.xhtml").authenticated()
					
					// Entrada
					.antMatchers("/entrada/consulta-entrada.xhtml").authenticated()
					.antMatchers("/entrada/cadastra-entrada.xhtml").authenticated()
					
					// Estoque
					.antMatchers("/estoque/consulta-estoque.xhtml").authenticated()
					
					// Perfil
					.antMatchers("/perfil/meus-dados.xhtml").authenticated()
					.antMatchers("/perfil/senha-acesso.xhtml").authenticated()

					// NEGADO OUTRAS URL's
					.anyRequest().denyAll();
			
			return httpSecurity;
		} catch (Exception e) {
			throw new MeuSecurityConfigException("Não foi possivel adicionar as permissões para as URL's do sistema", e);
		}
	}

	@Override
	public void configure(WebSecurity webSecurity) throws Exception {
		webSecurity.ignoring()
			.antMatchers("/resources/**")
			.antMatchers("/javax.faces.resource/**")
			.antMatchers("/error.xhtml")
			.antMatchers("/404.xhtml");
	}

	@Bean(name = "messageDigestPasswordEncoder")
	public MessageDigestPasswordEncoder passwordEncoder() {
		return new ShaPasswordEncoder(256);
	}

	@Bean
	public SessionRegistry getSessionRegistry() {
		return new SessionRegistryImpl();
	}

	@Bean
	public HttpSessionEventPublisher createSession() {
		return new HttpSessionEventPublisher();
	}

	@Bean
	public GenericFilterBean authenticationFilter() {
		return new AuthenticationFilterImpl();
	}
	
}