package com.techlead.desafioapi.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.techlead.desafioapi.entity.Emprestimo;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long>{
    List<Emprestimo> findByDevolvidoLivroOrderByEmprestimoAtivoAscDataDesc(Boolean devolvidoLivro);
    Optional<Emprestimo> findByLivro_idLivros(Long idLivros);
}
