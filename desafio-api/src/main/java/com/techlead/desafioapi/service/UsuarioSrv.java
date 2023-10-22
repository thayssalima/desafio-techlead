package com.techlead.desafioapi.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.techlead.desafioapi.entity.Usuario;
import com.techlead.desafioapi.entity.enums.PerfilEnum;
import com.techlead.desafioapi.exceptions.DesafioException;
import com.techlead.desafioapi.repository.UsuarioRepository;
import com.techlead.desafioapi.rest.dto.request.AlteracaoSenhaRequestDto;
import com.techlead.desafioapi.rest.dto.request.RecuperandoSenhaRequestDTO;
import com.techlead.desafioapi.rest.dto.request.UsuarioRequestDTO;
import com.techlead.desafioapi.rest.dto.response.UsuarioRespondeDTO;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.JWT;

@Service
public class UsuarioSrv implements UserDetailsService{
    @Autowired
    UsuarioRepository repository;

    @Autowired
	BCryptPasswordEncoder passwordEncoder;
    
    public UsuarioRespondeDTO cadastrarClientes(UsuarioRequestDTO dto){
        Optional<Usuario> usuarioOpt = repository.findByCpf(dto.getCpf());
        if(usuarioOpt.isPresent()){
            throw new DesafioException("Cpf já cadastrado no sistema!");
        }

        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(dto, usuario);
        usuario.setSenha(this.passwordEncoder.encode(dto.getSenha()));
        usuario.setPerfil(PerfilEnum.CLIENTE);
        repository.save(usuario);
        return UsuarioRespondeDTO.converterUsuarioDTO(usuario);
    }

    public Usuario getByCpf(String cpf){
        Optional<Usuario> opt = repository.findByCpf(cpf);
        if(!opt.isPresent()){
            throw new DesafioException("Usuário com esse cpf não encontrado!");
        }
        return opt.get();
    }

    @Override
    public UserDetails loadUserByUsername(String cpf) throws UsernameNotFoundException {
        Optional<Usuario> optUser = repository.findByCpf(cpf);

		if (!optUser.isPresent())
			throw new UsernameNotFoundException(cpf);

		Usuario user = optUser.get();

		Collection<GrantedAuthority> grupos = new ArrayList<>();
        grupos.add(new SimpleGrantedAuthority(user.getPerfil().toString()));

		return new User(user.getCpf(), user.getSenha(), grupos);
    }

    public DecodedJWT lerToken(String token) {
        String tokenSemBearer = token.replace("Bearer ", "");
        return  JWT.decode(tokenSemBearer);
    }

    public void alterarSenha(AlteracaoSenhaRequestDto dto,String token) {
        String cpfUsuario = this.lerToken(token).getSubject();
		Optional<Usuario> usuario = repository.findByCpf(cpfUsuario);
		String senhaAtualBase = usuario.get().getSenha();

		if (!this.passwordEncoder.matches(dto.getSenhaAtual(), senhaAtualBase)) {
			throw new DesafioException("Senha atual inválida");
		}

		usuario.get().setSenha(passwordEncoder.encode(dto.getNovaSenha()));
		repository.save(usuario.get());
	}

    public void recuperandoSenha(RecuperandoSenhaRequestDTO dto){
        Optional<Usuario> usuarioOpt= repository.findByCpf(dto.getCpf());

        if(!usuarioOpt.isPresent()){
            throw new DesafioException("Usuário não possui cadastro!");
        }      
        usuarioOpt.get().setSenha(this.passwordEncoder.encode(dto.getNovaSenha()));
        repository.save(usuarioOpt.get());
    }
}
