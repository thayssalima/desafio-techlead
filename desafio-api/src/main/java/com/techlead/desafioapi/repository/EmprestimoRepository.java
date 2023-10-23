package com.techlead.desafioapi.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.techlead.desafioapi.entity.Emprestimo;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long>{
    //@Query("SELECT emp FROM Emprestimo emp ORDER BY emp.emprestimoAtivo == true")
    List<Emprestimo> findAllByOrderByEmprestimoAtivoAscDataDesc();
}
