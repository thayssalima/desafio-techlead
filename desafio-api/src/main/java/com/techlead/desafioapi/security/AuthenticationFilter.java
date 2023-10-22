package com.techlead.desafioapi.security;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import io.jsonwebtoken.SignatureAlgorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techlead.desafioapi.config.SpringApplicationContext;
import com.techlead.desafioapi.entity.Usuario;
import com.techlead.desafioapi.exceptions.DesafioException;
import com.techlead.desafioapi.exceptions.FalhaNaAutenticacaoException;
import com.techlead.desafioapi.rest.dto.response.LoginResponseDto;
import com.techlead.desafioapi.rest.dto.response.LoginSuccessResponseDto;
import com.techlead.desafioapi.service.UsuarioSrv;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter{
    private static Logger log = LoggerFactory.getLogger(AuthenticationFilter.class);

	private final AuthenticationManager authenticationManager;

	private static void responseText(HttpServletResponse response, String content) throws IOException {
		response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
		byte[] bytes = content.getBytes(StandardCharsets.UTF_8);
		response.setContentLength(bytes.length);
		response.setStatus(400);
		response.getOutputStream().write(bytes);
		response.flushBuffer();
	}

	public AuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(final HttpServletRequest req, final HttpServletResponse res)
			throws AuthenticationException {
		try {

			if (isPreflight(req)) {
				res.setStatus(HttpServletResponse.SC_NO_CONTENT);
			} else {
				
				LoginResponseDto creds = new ObjectMapper().readValue(req.getInputStream(),
						LoginResponseDto.class);

				log.info("Authentication with cpf: {}", creds.getCpf());

				return authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(creds.getCpf(), creds.getSenha(), new ArrayList<>()));
			}

			return null;
		} catch (Exception e) {
			throw new DesafioException("Erro na autenticação");
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
			Authentication authentication) throws IOException, ServletException {

		String cpf = ((User) authentication.getPrincipal()).getUsername();

		UsuarioSrv userService = (UsuarioSrv) SpringApplicationContext.getBean("usuarioSrv");
		Usuario usuario = userService.getByCpf(cpf);

		Map<String, Object> claims = new HashMap<>();
		claims.put("authorities", authentication.getAuthorities());

		String token = Jwts.builder().setSubject(cpf)
				.setExpiration(new Date(System.currentTimeMillis() + SecurityUtil.EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SecurityUtil.getTokenSecret()).addClaims(claims).compact();

		ServletOutputStream stream = res.getOutputStream();

		List<String> perfis = new ArrayList<>();

		Collection<SimpleGrantedAuthority> auths = (Collection<SimpleGrantedAuthority>) authentication.getAuthorities();
		for (SimpleGrantedAuthority granted : auths) {
			perfis.add(granted.getAuthority());
		}

		LoginSuccessResponseDto success = LoginSuccessResponseDto.builder().cpf(usuario.getCpf()).token(token)
				.perfil(usuario.getPerfil()).nome(usuario.getNome()).idUsuario(usuario.getIdUsuario()).build();

		ObjectMapper mapper = new ObjectMapper();

		try (BufferedOutputStream out = new BufferedOutputStream(stream)) {
			out.write(mapper.writeValueAsString(success).getBytes());
		}

		res.addHeader("UserID", usuario.getCpf());
	}

	private boolean isPreflight(HttpServletRequest request) {
		return "OPTIONS".equals(request.getMethod());
	}
}
