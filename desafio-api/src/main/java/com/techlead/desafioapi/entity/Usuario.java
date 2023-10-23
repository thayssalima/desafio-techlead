package com.techlead.desafioapi.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import org.hibernate.validator.constraints.br.CPF;
import com.techlead.desafioapi.entity.enums.PerfilEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idUsuario;

    @CPF
    @Column(name = "cpf" , length = 11 , unique = true)
    private String cpf;

    @Column(name = "senha" , length = 100)
    private String senha;

    @Email
    @Column(name = "email" , length = 100)
    private String email;

    @Column(name = "nome" , length = 100)
    private String nome;

    @Column(name = "perfil")
    @Enumerated(EnumType.STRING)
    private PerfilEnum perfil;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<Livros> listaLivros;

    @OneToOne(mappedBy = "usuario")
    private Emprestimo emprestimo;

    public Usuario(String cpf, String email,String nome){
        this.cpf = cpf;
        this.email = email;
        this.nome = nome;
        this.perfil = PerfilEnum.ADMINISTRADOR;
    }
}
