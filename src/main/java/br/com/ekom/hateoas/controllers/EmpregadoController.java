package br.com.ekom.hateoas.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
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
	ResponseEntity<CollectionModel<EntityModel<Empregado>>> findAll() {

		List<EntityModel<Empregado>> empregados = StreamSupport.stream(empregadoService.findAll().spliterator(), false)
				.map(empregado -> EntityModel.of(empregado, //
						linkTo(methodOn(EmpregadoController.class).findById(empregado.getEmpregadoId())).withSelfRel(), //
						linkTo(methodOn(EmpregadoController.class).findAll()).withRel("empregados"))) //
				.collect(Collectors.toList());

		return ResponseEntity.ok( //
				CollectionModel.of(empregados, //
						linkTo(methodOn(EmpregadoController.class).findAll()).withSelfRel()));
	}	
	
	@GetMapping("/{id}")
	ResponseEntity<EntityModel<Empregado>> findById(@PathVariable Long id) {

		return empregadoService.findById(id) //
				.map(empregado -> EntityModel.of(empregado, //
						linkTo(methodOn(EmpregadoController.class).findById(empregado.getEmpregadoId())).withSelfRel(), //
						linkTo(methodOn(EmpregadoController.class).findAll()).withRel("empregados"))) //
				.map(ResponseEntity::ok) //
				.orElse(ResponseEntity.notFound().build());
	}	
	

	public ResponseEntity<Empregado> findByIds(@PathVariable Long id) {
		Empregado empregado = empregadoService.findById(id).orElse(null);
		
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
	public ResponseEntity<?> save(@RequestBody Empregado empregado) {

		try {
			Empregado novoEmpregado = empregadoService.save(empregado);

			EntityModel<Empregado> empregadoResource = EntityModel.of(novoEmpregado, //
					linkTo(methodOn(EmpregadoController.class).findById(novoEmpregado.getEmpregadoId())).withSelfRel());

			return ResponseEntity //
					.created(new URI(empregadoResource.getRequiredLink(IanaLinkRelations.SELF).getHref())) //
					.body(empregadoResource);
		} catch (URISyntaxException e) {
			return ResponseEntity.badRequest().body("Não foi possível criar o recurso " + empregado);
		}
	}

	@PutMapping("/{id}")
	ResponseEntity<?> update(@RequestBody Empregado empregado, @PathVariable Long id) {

		Empregado empregadoToUpdate = empregado;
		empregadoToUpdate.setEmpregadoId(id);
		empregadoService.save(empregadoToUpdate);

		Link newlyCreatedLink = linkTo(methodOn(EmpregadoController.class).findById(id)).withSelfRel();

		try {
			return ResponseEntity.noContent().location(new URI(newlyCreatedLink.getHref())).build();
		} catch (URISyntaxException e) {
			return ResponseEntity.badRequest().body("Não foi possível atualizar o recurso " + empregadoToUpdate);
		}
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