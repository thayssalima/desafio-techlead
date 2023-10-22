package com.techlead.desafioapi.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.techlead.desafioapi.entity.Livros;

@Repository
public interface LivrosRepository extends JpaRepository<Livros, Long>{
    
    Optional<Livros> findByNomeAndAutor(String nome, String autor);

    @Query("SELECT l FROM Livros l ORDER BY l.nome")
    List<Livros>findByAllOrderByNome();
}
