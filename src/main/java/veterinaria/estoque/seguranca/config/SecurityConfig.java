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
import veterinaria.estoque.seguranca.provider.AuthenticationProviderLdapImpl;
import veterinaria.estoque.util.exceptions.MeuSecurityConfigException;

@Configuration
@ComponentScan(basePackages="veterinaria.estoque", basePackageClasses=veterinaria.estoque.util.UtilRegistroSessao.class)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Inject
	private LogoutSuccessHandlerImpl logoutSuccessHandlerImpl;

	@Autowired
	private AuthenticationFailureHandlerImpl authenticationFailureHandlerImpl;

	@Autowired
	private AuthenticationSucessHandlerImpl authenticationSucessHandlerImpl;

	@Autowired
	@Qualifier("authLDAP")
	private AuthenticationProviderLdapImpl authenticationProviderLdapImpl;

	@Autowired
	public void configureGlobalAuthDB(AuthenticationManagerBuilder authenticationManagerBuilder) throws MeuSecurityConfigException {
		try {
			authenticationManagerBuilder.authenticationProvider(authenticationProviderLdapImpl);
		} catch (Exception e) {
			throw new MeuSecurityConfigException("Nao foi possivel iniciar o provedor de autenticacao do Spring Security", e);
		}
	}

	@Override
	protected void configure(HttpSecurity http) throws MeuSecurityConfigException {
		try {
			http.csrf().disable().headers().frameOptions().sameOrigin();

			http = adicionarRegrasAsPaginas(http);
			http
				.formLogin()
					.loginProcessingUrl("/appLogin")
					.loginPage("/login.xhtml")
					.successHandler(authenticationSucessHandlerImpl)
					.failureHandler(authenticationFailureHandlerImpl);
			http
				.logout()
					.logoutUrl("/appLogout")
					.logoutSuccessHandler(logoutSuccessHandlerImpl)
					.invalidateHttpSession(true)
					.deleteCookies("JSESSIONID");

			http
				.exceptionHandling()
					.accessDeniedPage("/access-denied.xhtml");

			http
				.sessionManagement()
					.invalidSessionUrl("/login.xthml?invalidSessionUrl=true")
					.sessionAuthenticationErrorUrl("/login.xhtml?sessionError=true")
					.maximumSessions(1)
					.sessionRegistry(getSessionRegistry())
					.expiredUrl("/login.xthml?expired=true");

			http.httpBasic();
			http.addFilterAfter(authenticationFilter(), BasicAuthenticationFilter.class);
		} catch (Exception e) {
			throw new MeuSecurityConfigException("Nao foi possivel iniciar as configuracoes do Spring security", e);
		}
	}

	private HttpSecurity adicionarRegrasAsPaginas(HttpSecurity http) throws MeuSecurityConfigException {
		try {
			http.authorizeRequests()
					// Qualquer um acessa a pagina de login
					.antMatchers("/login.xhtml").permitAll()

					// basta estar autenticado
					.antMatchers("/sobre.xhtml").authenticated()
					.antMatchers("/home.xhtml").authenticated()

					// NEGADO OUTRAS URL's
					.anyRequest().denyAll();
			
			return http;
		} catch (Exception e) {
			throw new MeuSecurityConfigException("Não foi possivel adicionar as permissões para as URL's do sistema", e);
		}
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**").antMatchers("/javax.faces.resource/**").antMatchers("/error.xhtml")
				.antMatchers("/404.xhtml").antMatchers("/api/v1/**") // FIXME
				.antMatchers("/ImpressaoServlet");
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
	
//	@Bean
//    public JndiObjectFactoryBean myBean() {
//        JndiObjectFactoryBean factory = new JndiObjectFactoryBean();
//        factory.setJndiName("java:global/simetryacloud/SingletonTipoAutenticacao");
//        return factory;
//    }
}
