package com.techlead.desafioapi.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

    @Column(name = "data_emprestimo")
    private LocalDateTime data;

    @Column(name = "dias_emprestados")
    private Integer diasEmprestados;

    @Column(name = "emprestimo_ativo")
    private Boolean emprestimoAtivo; 

    @Column(name = "devolvido_livro")
    private Boolean devolvidoLivro; 

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_livro", referencedColumnName = "id")
    private Livros livro;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    private Usuario usuario;

    public Emprestimo(Integer diasEmprestados){
        this.data = LocalDateTime.now();
        this.emprestimoAtivo = false;
        this.diasEmprestados = diasEmprestados;
        this.devolvidoLivro = false;
    }
}
