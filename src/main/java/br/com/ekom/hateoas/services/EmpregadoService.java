package br.com.ekom.hateoas.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ekom.hateoas.entities.Empregado;
import br.com.ekom.hateoas.exceptions.NoSuchElementException;
import br.com.ekom.hateoas.repositories.EmpregadoRepository;

@Service
public class EmpregadoService {
	@Autowired
	EmpregadoRepository empregadoRepository;
	
	public Iterable<Empregado> findAll(){
		return empregadoRepository.findAll();
	}
	
	public Optional<Empregado> findById(Long id) {
		return Optional.ofNullable(empregadoRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Empregado", id)));
		        
	}
	
	public Empregado save(Empregado Empregado) {
		return empregadoRepository.save(Empregado);
	}
	
	public Empregado update(Empregado Empregado) {
		return empregadoRepository.save(Empregado);
	}

	public Boolean delete(Empregado Empregado) {
		if(Empregado == null)
			return false;
		
		Empregado EmpregadoExistente = findById(Empregado.getEmpregadoId()).get();
		
		if(EmpregadoExistente == null)
			return false;
		
		empregadoRepository.delete(Empregado);
		
		Empregado EmpregadoContinuaExistindo = 
				findById(Empregado.getEmpregadoId()).get();
		
		if(EmpregadoContinuaExistindo == null)
			return true;

		return false;
	}	
}
