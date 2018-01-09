package br.com.codechallenger.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.codechallenger.event.RecursoCriadoEvent;
import br.com.codechallenger.model.Aluno;
import br.com.codechallenger.repository.AlunoRepository;


@RestController
@RequestMapping("/alunos")
public class AlunoResource {
	
	@Autowired
	private AlunoRepository alunoRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public List<Aluno> listar(){
		return alunoRepository.findAll();
	}
	
	@PostMapping
	public ResponseEntity<Aluno> criar(@Valid @RequestBody Aluno aluno, HttpServletResponse response){
		Aluno alunoSalva = alunoRepository.save(aluno);
		publisher.publishEvent(new RecursoCriadoEvent(this	, response, alunoSalva.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(alunoSalva);
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		alunoRepository.delete(codigo);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Aluno> buscarPeloCodigo(@PathVariable Long codigo) {
		Aluno aluno = alunoRepository.findOne(codigo);
		return aluno != null ? ResponseEntity.ok(aluno) : ResponseEntity.notFound().build();
	}
	
}
