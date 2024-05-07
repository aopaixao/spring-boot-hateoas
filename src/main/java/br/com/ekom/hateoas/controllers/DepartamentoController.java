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

import br.com.ekom.hateoas.entities.Departamento;
import br.com.ekom.hateoas.services.DepartamentoService;

@RestController
@RequestMapping("/departamentos")
public class DepartamentoController {
	
	@Autowired
	DepartamentoService departamentoService;

	@GetMapping
	public ResponseEntity<List<Departamento>> findAll(){
		return new 
				ResponseEntity<>(departamentoService.findAll(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Departamento> findById(@PathVariable Long id) {
		Departamento departamento = departamentoService.findById(id);
		
		if(departamento == null)
			return new 
					ResponseEntity<>(departamento, 
							HttpStatus.NOT_FOUND);
		else
			return new 
					ResponseEntity<>(departamento, 
							HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Departamento> save(@RequestBody Departamento departamento) {
		return new 
				ResponseEntity<>(departamentoService.save(departamento), 
						HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<Departamento> update(@RequestBody Departamento departamento) {
		return new 
				ResponseEntity<>(departamentoService.update(departamento), 
						HttpStatus.OK);
	}
	
	@DeleteMapping
	public ResponseEntity<String> deletarDepartamento(@RequestBody Departamento departamento) {
		if(departamentoService.delete(departamento))
			return new 
					ResponseEntity<>("{\"msg\":\"Deletado com Sucesso\"}", 
							HttpStatus.OK);
		else 
			return new 
					ResponseEntity<>("{\"msg\":\"Não foi possível deletar\"}", 
							HttpStatus.BAD_REQUEST);	
			
	}	
	
}