package com.techlead.desafioapi.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import com.techlead.desafioapi.entity.Usuario;
import com.techlead.desafioapi.entity.enums.PerfilEnum;
import com.techlead.desafioapi.repository.UsuarioRepository;
@Component
public class InserindoUsuarioAdministrador implements CommandLineRunner{
    @Autowired
    UsuarioRepository userRepository;

    @Autowired
	BCryptPasswordEncoder passwordEncoder;

    @Value("${admin.password}")
    private String adminPass;

    @Value("${admin.password}")
    private String adminBibliotecario;

    @Override
    public void run(String...args) throws Exception {
        Optional<Usuario> usuarioOpt = userRepository.findByCpf("36174272001");
        if(!usuarioOpt.isPresent()){
            Usuario admin = new Usuario("36174272001", "administrador@gmail.com", "Admin");
            admin.setSenha(this.passwordEncoder.encode(this.adminPass));
            userRepository.save(admin);
        }
        Optional<Usuario> usuarioOptBiblio = userRepository.findByCpf("61182391001");
        if(!usuarioOptBiblio.isPresent()){
            Usuario admin = new Usuario("61182391001", "bibliotecario@gmail.com", "Bibliotecário");
            admin.setSenha(this.passwordEncoder.encode(this.adminPass));
            admin.setPerfil(PerfilEnum.BIBLIOTECARIO);
            userRepository.save(admin);
        }
    }
    
}
