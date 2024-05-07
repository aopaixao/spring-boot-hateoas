package br.com.ekom.hateoas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ekom.hateoas.entities.Empregado;
import br.com.ekom.hateoas.services.EmpregadoService;

@RestController
@RequestMapping("/empregados")
public class EmpregadoController {
	
	@Autowired
	EmpregadoService empregadoService;

	@GetMapping
	public ResponseEntity<List<Empregado>> findAll(){
		return new 
				ResponseEntity<>(empregadoService.findAll(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Empregado> findById(@PathVariable Long id) {
		Empregado empregado = empregadoService.findById(id);
		
		if(empregado == null)
			return new 
					ResponseEntity<>(empregado, 
							HttpStatus.NOT_FOUND);
		else
			return new 
					ResponseEntity<>(empregado, 
							HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Empregado> save(@RequestBody Empregado empregado) {
		return new 
				ResponseEntity<>(empregadoService.save(empregado), 
						HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<Empregado> update(@RequestBody Empregado empregado) {
		return new 
				ResponseEntity<>(empregadoService.update(empregado), 
						HttpStatus.OK);
	}
	
	@DeleteMapping
	public ResponseEntity<String> deletarEmpregado(@RequestBody Empregado empregado) {
		if(empregadoService.delete(empregado))
			return new 
					ResponseEntity<>("{\"msg\":\"Deletado com Sucesso\"}", 
							HttpStatus.OK);
		else 
			return new 
					ResponseEntity<>("{\"msg\":\"Não foi possível deletar\"}", 
							HttpStatus.BAD_REQUEST);	
			
	}	
	
}