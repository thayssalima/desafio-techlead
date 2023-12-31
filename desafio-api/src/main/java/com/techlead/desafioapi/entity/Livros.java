package com.techlead.desafioapi.entity;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "livros")
public class Livros {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idLivros;

    @Column(name = "nome" , length = 100)
    private String nome;

    @Column(name = "autor" , length = 100)
    private String autor;

    @Column(name = "livro_disponivel")
    private Boolean livroDisponivel; 

    @Column(name = "quantidade_estoque")
    private Integer quantidadeEstoque; 

    @CreationTimestamp
    private LocalDate dataCadastro;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    private Usuario usuario;

    @OneToMany(mappedBy = "livro", fetch = FetchType.LAZY)
    private List<Emprestimo> listaEmprestimos;
}
