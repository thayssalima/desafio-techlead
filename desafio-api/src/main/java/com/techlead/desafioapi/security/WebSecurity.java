package com.techlead.desafioapi.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import com.techlead.desafioapi.service.UsuarioSrv;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter{
    private final UsuarioSrv userDetailsService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final UsuarioSrv usuarioService;
	
	public WebSecurity(UsuarioSrv userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder, UsuarioSrv usuarioService) {
		this.userDetailsService = userDetailsService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.usuarioService = usuarioService;
	}

    @Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic().disable().csrf().disable().authorizeRequests()
			.antMatchers("/v2/api-docs", "/actuator/**", "/configuration/**", "/swagger-ui.html", "/swagger-resources/**", "/webjars/**").permitAll().anyRequest()
			.permitAll().and()
			.addFilterBefore(new CorsFilter(), AuthenticationFilter.class)
			.addFilter(getAuthenticationFilter())
			.addFilter(new AuthorizationFilter(authenticationManager(), usuarioService)).sessionManagement()
	    	.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}
	
    public AuthenticationFilter getAuthenticationFilter() throws Exception {
    	final AuthenticationFilter filter = new AuthenticationFilter(authenticationManager());
    	filter.setFilterProcessesUrl("/usuarios/autenticar");
    	return filter;
    }

	@Bean
	public HttpFirewall allowUrlEncodedSlashHttpFirewall() {
		StrictHttpFirewall firewall = new StrictHttpFirewall();
		firewall.setAllowUrlEncodedSlash(true);
		firewall.setAllowSemicolon(true);
		return firewall;
	}

	@Override
	public void configure(org.springframework.security.config.annotation.web.builders.WebSecurity web) throws Exception {
		super.configure(web);
		web.httpFirewall(allowUrlEncodedSlashHttpFirewall());
	}
}
