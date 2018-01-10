package br.com.codechallenger;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.codechallenger.model.Aluno;
import br.com.codechallenger.repository.AlunoRepository;

@SpringBootApplication
public class CodeChallengerApplication implements CommandLineRunner{
	
	@Autowired
	private AlunoRepository alunoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CodeChallengerApplication.class, args);
	}
	
	@Override
	//alimenta tabela Aluno ao iniciar aplicação
	public void run(String... args) throws Exception {
		
		Aluno p1 = new Aluno(null, "Isabela Salles", 14,"Rua oito de novembro");
		Aluno p2 = new Aluno(null, "Allan Mayanei ", 17,"Rua oito de novembro");
		Aluno p3 = new Aluno(null, "Mauricio Felix ", 17,"Rua oito de novembro");
		Aluno p4 = new Aluno(null, "Alisson Moura", 17,"Rua oito de novembro");
		
		alunoRepository.save(Arrays.asList(p1,p2,p3,p4));
		
	}
}
