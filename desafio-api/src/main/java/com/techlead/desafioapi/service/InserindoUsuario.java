package com.techlead.desafioapi.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import com.techlead.desafioapi.entity.Usuario;
import com.techlead.desafioapi.repository.UsuarioRepository;
@Component
public class InserindoUsuario implements CommandLineRunner{
    @Autowired
    UsuarioRepository userRepository;

    @Autowired
	BCryptPasswordEncoder passwordEncoder;

    @Value("${admin.password}")
    private String adminPass;

    @Override
    public void run(String...args) throws Exception {
        Optional<Usuario> usuarioOpt = userRepository.findByCpf("36174272001");
        if(!usuarioOpt.isPresent()){
            Usuario admin = new Usuario("36174272001", "teste@gmail.com", "Admin 1");
            admin.setSenha(this.passwordEncoder.encode(this.adminPass));
            userRepository.save(admin);
        }
        
    }
}
