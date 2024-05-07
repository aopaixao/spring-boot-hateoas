package br.com.ekom.hateoas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ekom.hateoas.entities.Departamento;
import br.com.ekom.hateoas.exceptions.NoSuchElementException;
import br.com.ekom.hateoas.repositories.DepartamentoRepository;

@Service
public class DepartamentoService {
	@Autowired
	DepartamentoRepository departamentoRepository;
	
	public List<Departamento> findAll(){
		return departamentoRepository.findAll();
	}
	
	public Departamento findById(Long id) {
		
		return departamentoRepository.findById(id)
		        .orElseThrow(() -> new NoSuchElementException("Departamento", id));
		        
	}
	
	public Departamento save(Departamento Departamento) {
		return departamentoRepository.save(Departamento);
	}
	
	public Departamento update(Departamento Departamento) {
		return departamentoRepository.save(Departamento);
	}

	public Boolean delete(Departamento Departamento) {
		if(Departamento == null)
			return false;
		
		Departamento DepartamentoExistente = findById(Departamento.getDepartamentoId());
		
		if(DepartamentoExistente == null)
			return false;
		
		departamentoRepository.delete(Departamento);
		
		Departamento DepartamentoContinuaExistindo = 
				findById(Departamento.getDepartamentoId());
		
		if(DepartamentoContinuaExistindo == null)
			return true;

		return false;
	}	
}
