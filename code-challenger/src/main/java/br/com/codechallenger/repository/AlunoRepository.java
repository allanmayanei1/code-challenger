package br.com.codechallenger.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.codechallenger.model.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long>{

}
