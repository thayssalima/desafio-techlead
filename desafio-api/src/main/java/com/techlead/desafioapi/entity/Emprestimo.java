package com.techlead.desafioapi.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "emprestimo")
public class Emprestimo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idEmprestimo;

    @Column(name = "data")
    private LocalDateTime data;

    @Column(name = "dias_emprestados")
    private Integer diasEmprestados;

    @Column(name = "emprestimo_ativo")
    private Boolean emprestimoAtivo; 

    @OneToOne
    @JoinColumn(name = "id_livro", referencedColumnName = "id", unique = true)
    private Livros livro;

    @OneToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id", unique = true)
    private Usuario usuario;

    public Emprestimo(Integer diasEmprestados){
        this.data = LocalDateTime.now();
        this.emprestimoAtivo = false;
        this.diasEmprestados = diasEmprestados;
    }
}
